package controllers.admin;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import controllers.BaseAdminController;
import controllers.SecureAuth;
import models.*;
import modules.PlayS3;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Http;
import play.mvc.Result;
import services.CityService;
import services.PhotoService;
import services.RestaurantService;
import services.exceptions.ServiceException;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static play.libs.Json.toJson;

public class RestaurantController extends BaseAdminController<Restaurant, RestaurantService> {
    protected CityService cityService;
    private PhotoService photoService;

    @Inject
    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    @Inject
    public void setPhotoService(PhotoService photoService) {
        this.photoService = photoService;
    }

    @Transactional
    public BoundRestaurant bindRestaurantFromRequest(Boolean updating, Long existingId) throws ServiceException {
        BoundRestaurant result = new BoundRestaurant();
        try {
            Form<Restaurant> restaurantForm = formFactory.form(Restaurant.class).bindFromRequest();
            Restaurant r = restaurantForm.get();

            // Validate mandatory fields
            if(r.getName() == null) {
                restaurantForm.errors().put("name", ValidationHelper.singleError("name", "This field is mandatory."));
            } else if (r.getName().length() < 3){
                restaurantForm.errors().put("name", ValidationHelper.singleError("name", "This field must have more than 3 letters."));
            }

            if(r.getLatLongPoint() == null) {
                restaurantForm.errors().put("location", ValidationHelper.singleError("location", "This field is mandatory."));
            }

            if(r.getWorkingTimeFrom() == null) {
                restaurantForm.errors().put("workingTimeFrom", ValidationHelper.singleError("workingTimeFrom", "This field is mandatory."));
            }

            if(r.getWorkingTimeTo() == null) {
                restaurantForm.errors().put("workingTimeTo", ValidationHelper.singleError("workingTimeTo", "This field is mandatory."));
            }

            if(r.getMinimumCancelTime() == null) {
                restaurantForm.errors().put("minimumCancelTime", ValidationHelper.singleError("minimumCancelTime", "This field is mandatory."));
            }

            final GeometryFactory gf = new GeometryFactory();
            List<Double> latLong = r.getLatLongPoint().getCoordinates();
            Point point = gf.createPoint(new Coordinate(latLong.get(0), latLong.get(1), 0.0));

            // Let's check is this restaurant inside the city bounds
            City city = cityService.get(r.getCity().getId());
            if(city == null) {
                restaurantForm.errors().put("location", ValidationHelper.singleError("city", "Invalid city!"));
            } else {
                Polygon cityBoundary = city.getBoundary();
                if (cityBoundary != null) {
                    if (!point.within(cityBoundary)) {
                        restaurantForm.errors().put("location", ValidationHelper.singleError("location", "Restaurant location must be inside the city boundaries!"));
                    }
                }
            }

            result.setRestaurantForm(restaurantForm);

            if(restaurantForm.hasErrors()) {
                return result;
            }

            // All mandatory validators have passed
            r.setLatLong(point);

            // Parse times and convert to java.util.date
            // In following order: working time FROM, working time TO, minimum cancel time
            ArrayList<String> timeStrings = new ArrayList<>();
            ArrayList<Date> times = new ArrayList<>();

            timeStrings.add(restaurantForm.data().get("workingTimeFrom"));
            timeStrings.add(restaurantForm.data().get("workingTimeTo"));
            timeStrings.add(restaurantForm.data().get("minimumCancelTime"));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            for(int i = 0; i < 3; i++) {
                LocalDateTime ld = LocalDateTime.parse(timeStrings.get(i), formatter).minusHours(1); // very hacky because of timezones
                Instant in = ld.toInstant(ZoneOffset.UTC); // we're converting everything to UTC
                times.add(Date.from(in));
            }

            r.setWorkingTimeFrom(times.get(0));
            r.setWorkingTimeTo(times.get(1));
            r.setMinimumCancelTime(times.get(2));

            r.setActiveMenu(0);

            if(r.getCategoriesList() != null) {
                for(Category c : r.getCategoriesList()) {
                    r.getCategories().add(c);
                }
            }

            if(r.getDiningTables() != null) {
                for(DiningTable t : r.getDiningTables()) {
                    t.setRestaurant(r);
                }
            }

            if(r.getPhotos() != null) {
                for(Photo p : r.getPhotos()) {
                    p.setRestaurant(r);
                }
            }

            if(r.getMenus() != null) {
                for(Menu m : r.getMenus()) {
                    m.setRestaurant(r);
                    for(MenuItem mi : m.getMenuItems()) {
                        mi.setMenu(m);
                    }
                }
            }

            if(!updating) {
                r.setReviewCount(0);
                r.setReviewRating(0.0);
                result.setRestaurant(r);
            } else {
                Restaurant existing = service.get(existingId);
                existing.setName(r.getName());
                existing.setDescription(r.getDescription());
                existing.setLogoImageUrl(r.getLogoImageUrl());
                existing.setCity(r.getCity());
                existing.setPricing(r.getPricing());
                existing.setWorkingTimeFrom(r.getWorkingTimeFrom());
                existing.setWorkingTimeTo(r.getWorkingTimeTo());
                existing.setMinimumCancelTime(r.getMinimumCancelTime());
                existing.setLatLong(point);

                List<Photo> existingPhotos = existing.getPhotos();
                for(Photo p : r.getPhotos()) {
                    if(p.getId() == null) {
                        existingPhotos.add(p);
                    }
                }

                List<DiningTable> existingTables = existing.getDiningTables();
                List<DiningTable> newTables = r.getDiningTables();

                // Any old tables to remove?
                for(int i = 0; i < existingTables.size(); i++) {
                    DiningTable dt = existingTables.get(i);

                    // There was a problem using .stream().filter().findFirst() <- kept throwing nullpointerexception
                    DiningTable nt = null;
                    for(int j = 0; j < newTables.size(); j++) {
                        if(dt.getId().equals(newTables.get(j).getId())) {
                            nt = newTables.get(j); break;
                        }
                    }

                    if(nt == null) {
                        // This table has been removed
                        existingTables.remove(dt);
                        i--;
                    } else {
                        dt.setPersons(nt.getPersons());
                        dt.setAmount(nt.getAmount());
                    }
                }

                // Any new tables to add?
                for(DiningTable t : newTables) {
                    if(t.getId() == null) {
                        existingTables.add(t);
                    }
                }

                // We can just replace the menu because there are no special relationships except the restaurant
                existing.getMenus().clear();
                existing.getMenus().addAll(r.getMenus());

                // Same goes for categories
                existing.getCategories().clear();
                existing.getCategories().addAll(r.getCategoriesList());

                result.setRestaurant(existing);
            }

            return result;
        } catch(ServiceException e) {
            throw new ServiceException("Couldn't bind Restaurant from form data!");
        }
    }

    @Transactional
    @SecureAuth.Authenticated(roles = {"ADMIN"})
    @BodyParser.Of(BodyParser.Json.class)
    public Result add() {
        try {
            BoundRestaurant br = bindRestaurantFromRequest(false, null);
            Form<Restaurant> form = br.getRestaurantForm();
            if(form != null) {
                if(form.hasErrors()) {
                    return badRequest(form.errorsAsJson());
                }

                Restaurant r = br.getRestaurant();
                return created(toJson(service.create(r)));
            }

            return badRequest("Restaurant creation error");
        } catch (Exception e) {
            return badRequest("Restaurant creation error");
        }
    }

    @Transactional
    @SecureAuth.Authenticated(roles = {"ADMIN"})
    @BodyParser.Of(BodyParser.Json.class)
    public Result update(Long id) {
        try {
            BoundRestaurant br = bindRestaurantFromRequest(true, id);
            Form<Restaurant> form = br.getRestaurantForm();
            if(form != null) {
                if(form.hasErrors()) {
                    return badRequest(form.errorsAsJson());
                }

                Restaurant r = br.getRestaurant();
                return ok(toJson(service.update(id, r)));
            }


            return super.update(id);
            //return badRequest("Restaurant creation error");
        } catch (Exception e) {
            return badRequest("Restaurant edit error");
        }
    }

    @SecureAuth.Authenticated(roles = {"ADMIN"})
    public Result uploadImages(Integer logoProvided, Integer coverProvided) {
        try {
            Http.MultipartFormData body = request().body().asMultipartFormData();

            List<Http.MultipartFormData.FilePart> allFiles = body.getFiles();

            Integer minSize = (logoProvided > 0 ? 1 : 0) + (coverProvided > 0 ? 1 : 0);

            // No logo or image file uploaded
            if(allFiles.size() < minSize) {
                return badRequest("File upload error");
            }

            Http.MultipartFormData.FilePart<File> logoImage = null;
            if(logoProvided > 0) {
                logoImage = allFiles.get(0);
            }
            Http.MultipartFormData.FilePart<File> coverImage = null;
            if(coverProvided > 0) {
                coverImage = allFiles.get(logoProvided > 0 ? 1 : 0); // if there's a logo too, cover will be the second file, otherwise the first
            }

            String logoImageUrl = "";
            if(logoImage != null) {
                if((!logoImage.getContentType().equals("image/jpeg") && !logoImage.getContentType().equals("image/png"))) {
                    return badRequest("Wrong file format for logo!");
                }

                logoImageUrl = this.uploadFile(logoImage.getFile(), "gallery/logos", true, 438, 350);
            }

            String coverImageUrl = "";
            if(coverImage != null) {
                if((!coverImage.getContentType().equals("image/jpeg") && !coverImage.getContentType().equals("image/png"))) {
                    return badRequest("Wrong file format for cover!");
                }

                coverImageUrl = this.uploadFile(coverImage.getFile(), "gallery/covers", false, 0, 0);
            }

            // Start building the response
            List<Photo.ImageUploaded> photos = new ArrayList<>();
            for(int i = minSize; i < allFiles.size(); i++) {
                Http.MultipartFormData.FilePart<File> photo = allFiles.get(i);
                String uploadedPhoto = this.uploadFile(photo.getFile(), "gallery", true, 500, 500);
                photos.add(new Photo.ImageUploaded(uploadedPhoto, 0.0));
            }

            Photo.ImagesUploadResult result = new Photo.ImagesUploadResult(logoImageUrl, coverImageUrl, photos);

            return ok(Json.toJson(result));
        } catch(Exception e) {
            return badRequest("File upload error");
        }
    }

    @Transactional
    public Result deleteImage(Long restaurantId, String file, String directory, Integer thumb) {
        try {
            service.deletePhotoFromAws(restaurantId, directory, file, thumb > 0);
            return ok();
        } catch (ServiceException e) {
            return internalServerError("Could not delete an image.");
        }
    }

    @Transactional
    @SecureAuth.Authenticated(roles = {"ADMIN"})
    public Result delete(Long id) {
        return super.delete(id);
    }

    @SecureAuth.Authenticated(roles = {"ADMIN"})
    public String uploadFile(File file, String directory, Boolean thumbIt, Integer sizeX, Integer sizeY) throws IOException {
        try {
            // Generate filename
            String filename = java.util.UUID.randomUUID().toString() + ".jpg";
            File image = file;

            // Original file converted to JPG without any changes, except in format
            File jpgImage = File.createTempFile("img", ".jpg");
            Thumbnails.of(image).scale(1).outputFormat("jpg").toFile(jpgImage);

            // Uploaded original file (converted to JPG) to S3
            PutObjectRequest por = new PutObjectRequest(PlayS3.getBucketName(), directory + "/" + filename, jpgImage);
            por.withCannedAcl(CannedAccessControlList.PublicRead);
            PlayS3.getAmazonS3().putObject(por);

            // Should we also create a thumb of this image
            if(thumbIt) {
                File galleryThumb = File.createTempFile("img", ".jpg");
                Thumbnails.of(image)
                        .crop(Positions.CENTER)
                        .size(sizeX, sizeY)
                        .outputFormat("jpg")
                        .toFile(galleryThumb);

                // Upload the thumb
                por = new PutObjectRequest(PlayS3.getBucketName(), directory + "/thumbs/" + filename, galleryThumb);
                por.withCannedAcl(CannedAccessControlList.PublicRead);
                PlayS3.getAmazonS3().putObject(por);
                galleryThumb.deleteOnExit();
            }

            jpgImage.deleteOnExit();

            return filename;
        } catch(IOException e) {
            throw new IOException("Error while uploading a file to Amazon S3.");
        }
    }

    @Transactional
    @SecureAuth.Authenticated(roles = {"ADMIN"})
    public Result reservations(Long id, String time) {
        try {
            return ok(toJson(service.getAllReservations(id, time)));
        } catch (ServiceException e) {
            return badRequest("Unknown restaurant ID.");
        }
    }

    public class BoundRestaurant {
        private Restaurant restaurant;
        private Form<Restaurant> restaurantForm;

        public BoundRestaurant() {
            restaurant = null;
            restaurantForm = null;
        }

        public Restaurant getRestaurant() {
            return restaurant;
        }

        public void setRestaurant(Restaurant restaurant) {
            this.restaurant = restaurant;
        }

        public Form<Restaurant> getRestaurantForm() {
            return restaurantForm;
        }

        public void setRestaurantForm(Form<Restaurant> restaurantForm) {
            this.restaurantForm = restaurantForm;
        }
    }

}
