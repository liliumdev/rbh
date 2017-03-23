package controllers.admin;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
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
import services.RestaurantService;
import services.exceptions.ServiceException;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static play.libs.Json.toJson;

public class RestaurantController extends BaseAdminController<Restaurant, RestaurantService> {

    @Transactional
    @SecureAuth.Authenticated(roles = {"ADMIN"})
    @BodyParser.Of(BodyParser.Json.class)
    public Result add() {
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
            // All mandatory validators have passed

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

            r.setReviewCount(0);
            r.setReviewRating(0.0);

            final GeometryFactory gf = new GeometryFactory();
            List<Double> latLong = r.getLatLongPoint().getCoordinates();
            r.setLatLong(gf.createPoint(new Coordinate(latLong.get(0), latLong.get(1), 0.0)));

            return created(toJson(service.create(r)));
        } catch(ServiceException e) {
            return badRequest("Restaurant creation error");
        }
    }

    @SecureAuth.Authenticated(roles = {"ADMIN"})
    public Result uploadImages() {
        try {
            Http.MultipartFormData body = request().body().asMultipartFormData();

            List<Http.MultipartFormData.FilePart> allFiles = body.getFiles();

            // No logo or image file uploaded
            if(allFiles.size() < 2) {
                return badRequest("File upload error");
            }

            Http.MultipartFormData.FilePart<File> logoImage = allFiles.get(0);
            Http.MultipartFormData.FilePart<File> coverImage = allFiles.get(1);

            if(logoImage != null && coverImage != null) {
                if((!logoImage.getContentType().equals("image/jpeg") && !logoImage.getContentType().equals("image/png")) ||
                        (!coverImage.getContentType().equals("image/jpeg") && !coverImage.getContentType().equals("image/png"))) {
                    return badRequest("Wrong file format!");
                }

                String logoImageUrl = this.uploadFile(logoImage.getFile(), "gallery/logos", true, 438, 350);
                String coverImageUrl = this.uploadFile(coverImage.getFile(), "gallery/covers", false, 0, 0);

                // Start building the response
                List<Photo.ImageUploaded> photos = new ArrayList<>();
                for(int i = 2; i < allFiles.size(); i++) {
                    Http.MultipartFormData.FilePart<File> photo = allFiles.get(i);
                    String uploadedPhoto = this.uploadFile(photo.getFile(), "gallery", true, 500, 500);
                    photos.add(new Photo.ImageUploaded(uploadedPhoto, 0.0));
                }

                Photo.ImagesUploadResult result = new Photo.ImagesUploadResult(logoImageUrl, coverImageUrl, photos);

                return ok(Json.toJson(result));
            } else {
                return badRequest("File upload error");
            }
        } catch(Exception e) {
            return badRequest("File upload error");
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

}
