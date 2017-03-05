package controllers;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import controllers.forms.RateForm;
import controllers.forms.RestaurantFilterForm;
import models.*;
import models.filters.RestaurantFilterBuilder;
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
import services.ReviewService;
import services.exceptions.ServiceException;

import javax.inject.Inject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static play.libs.Json.toJson;

public class RestaurantController extends BaseController<Restaurant, RestaurantService> {
    protected ReviewService reviewService;

    @Inject
    public void setReviewService(ReviewService reviewService)
    {
        this.reviewService = reviewService;
    }

    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public Result filter() {
        try {
            // Validate review form fields
            Form<RestaurantFilterForm> form = formFactory.form(RestaurantFilterForm.class).bindFromRequest();
            if(form.hasErrors())
                return badRequest(form.errorsAsJson());

            RestaurantFilterForm data = form.get();

            RestaurantFilterBuilder rfb = new RestaurantFilterBuilder()
                    .setName(data.getName())
                    .setQuery(data.getQuery())
                    .setPricing(data.getPricing())
                    .setRating(data.getRating())
                    .setCategories(data.getCategories())
                    .setSortKey("name")
                    .setSortAsc(true)
                    .setPageNumber(data.getPageNumber())
                    .setPageSize(data.getPageSize());

            return ok(Json.toJson(service.filter(rfb)));

        } catch (ServiceException e) {
            return internalServerError(Json.toJson("Internal server error in RestaurantController@filter"));
        }

    }

    @Transactional
    public Result all(Integer limit) {
        try {
            if(limit == null || limit == 0)
                return super.all();

            return ok(toJson(service.random(limit)));
        } catch (ServiceException e) {
            return internalServerError(Json.toJson("Internal server error in RestaurantController@all"));
        }
    }

    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    @SecureAuth.Authenticated(roles={"NORMAL", "ADMIN"})
    public Result rate(Long id) {
        try {
            // Validate review form fields
            Form<RateForm> form = formFactory.form(RateForm.class).bindFromRequest();
            if(form.hasErrors())
                return badRequest(form.errorsAsJson());

            RateForm data = form.get();
            Double newRating = service.rate(id, data.getRating(), data.getDescription(), ctx().request().username());
            if(newRating == null)
                return badRequest(Json.toJson("There's been an error while rating this restaurant."));

            return ok(Json.newObject().put("rating", newRating));
        } catch (ServiceException e) {
            return internalServerError(Json.toJson("Internal server error in RestaurantController@rate"));
        }
    }

    @Transactional
    public Result getNumberOfReservationsToday(Long id) {
        try {
            return ok(toJson(service.getNumberOfReservationsToday(id)));
        } catch (ServiceException e) {
            return internalServerError(Json.toJson("Internal server error in RestaurantController@reservationsToday"));
        }
    }


    @Transactional
    @SecureAuth.Authenticated(roles={"NORMAL", "ADMIN"})
    public Result didRate(Long id) {
        return ok(Json.toJson(reviewService.didReview(id, ctx().request().username())));
    }

    /* Admin functions */
    @Transactional
    @SecureAuth.Authenticated(roles={"ADMIN"})
    @BodyParser.Of(BodyParser.Json.class)
    public Result test(){
        try {
            Form<Restaurant> restaurantForm = formFactory.form(Restaurant.class).bindFromRequest();
            Restaurant r = restaurantForm.get();
            r.setActiveMenu(0);
            for(Category c : r.getCategoriesList()) {
                r.getCategories().add(c);
            }

            for(DiningTable t : r.getDiningTables()) {
                t.setRestaurant(r);
            }

            for(Photo p : r.getPhotos()) {
                p.setRestaurant(r);
            }

            for(Menu m : r.getMenus()) {
                m.setRestaurant(r);
                for(MenuItem mi : m.getMenuItems()) {
                    mi.setMenu(m);
                }
            }

            final GeometryFactory gf = new GeometryFactory();
            List<Double> latLong = r.getLatLongPoint().getCoordinates();
            r.setLatLong(gf.createPoint(new Coordinate(latLong.get(0), latLong.get(1), 0.0)));

            return created(toJson(service.create(r)));
        } catch (ServiceException e) {
            return badRequest("Restaurant creation error");
        }
    }

    @SecureAuth.Authenticated(roles={"ADMIN"})
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

            if (logoImage != null && coverImage != null) {
                if ((!logoImage.getContentType().equals("image/jpeg")  && !logoImage.getContentType().equals("image/png")) ||
                    (!coverImage.getContentType().equals("image/jpeg") && !coverImage.getContentType().equals("image/png")))
                {
                    return badRequest("Wrong file format!");
                }

                //String logoImageUrl = this.uploadFile(logoImage.getFile(), "gallery/logos", true, 400, 400);
                //String coverImageUrl = this.uploadFile(logoImage.getFile(), "gallery/covers", false, 0, 0);
                String logoImageUrl = "TEST1";
                String coverImageUrl = "TEST2222343";

                // Start building the response
                List<Photo.ImageUploaded> photos = new ArrayList<>();
                for(int i = 2; i < allFiles.size(); i++) {
                    photos.add(new Photo.ImageUploaded("someImageUrl" + i, 0.0));
                }

                Photo.ImagesUploadResult result = new Photo.ImagesUploadResult(logoImageUrl, coverImageUrl, photos);

                return ok(Json.toJson(result));
            } else {
                return badRequest("File upload error");
            }
        } catch (Exception e) {
            return badRequest("File upload error");
        }
    }

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
        } catch (IOException e) {
            throw new IOException("Error while uploading a file to Amazon S3.");
        }
    }

}
