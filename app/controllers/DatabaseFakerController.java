package controllers;

import com.google.inject.Inject;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import io.bloco.faker.Faker;
import models.*;
import play.Logger;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DatabaseFakerController extends Controller {
    @Inject
    protected RestaurantService restaurantService;

    @Inject
    protected CategoryService categoryService;

    @Inject
    protected MenuService menuService;

    @Inject
    protected MenuItemService menuItemService;

    @Inject
    protected CityService cityService;

    @Inject
    protected CountryService countryService;

    @Inject
    protected DiningTableService tableService;


    public Result index() {
        return ok(views.html.main.render());
    }

    @Transactional
    @SecureAuth.Authenticated(roles={"ADMIN"})
    public Result seedDatabase() {
        try {
            int[] gradoviId = new int[] {23, 19, 17, 22, 20};

            Faker faker = new Faker();
            final GeometryFactory gf = new GeometryFactory();

            double bosnaMinLat = 43.9608876;
            double bosnaMaxLat = 44.5518057;
            double bosnaMinLong = 17.7546209;
            double bosnaMaxLong = 18.0186077;


            Restaurant r = new Restaurant();
            r.setName(faker.company.name());
            r.setDescription(faker.lorem.paragraph(6) + "<br><br>" + faker.lorem.paragraph(4));
            r.setReviewRating(5.0);
            r.setReviewCount(0);
            r.setActiveMenu(0);
            r.setLogoImageUrl("0.jpg");
            r.setCoverImageUrl("0.jpg");
            r.setPricing(3);
            double lat = faker.number.between(bosnaMinLat, bosnaMaxLat);
            double lon = faker.number.between(bosnaMinLong, bosnaMaxLong);
            r.setLatLong(gf.createPoint(new Coordinate(lat, lon, 0.0)));
            Long grad = (long)gradoviId[faker.number.between(0, 4)];
            r.setCity(cityService.get(grad));

            r = restaurantService.create(r);

            List<Menu> menus = new ArrayList<Menu>();
            Menu m = new Menu();
            m.setRestaurant(r);
            m.setName("First menu");
            m = menuService.create(m);

            List<MenuItem> menuItems = new ArrayList<MenuItem>();
            int numOfMenuItems = faker.number.between(7, 13);
            for(int i = 0; i < numOfMenuItems; i++)
            {
                MenuItem mi = new MenuItem();
                mi.setMenu(m);
                mi.setName(faker.lorem.word() + " " + faker.lorem.word());
                mi.setDescription(faker.lorem.sentence());
                mi.setPrice(new BigDecimal(faker.number.between(7.0, 25.0)).setScale(2, RoundingMode.CEILING));
                mi.setSort((double)i);
                menuItems.add(menuItemService.create(mi));
            }
            m.setMenuItems(menuItems);
            menuService.update(m.getId(), m);

            menus.add(m);
            r.setMenus(menus);

            Set<Category> cats = new HashSet<Category>();
            cats.add(categoryService.get((long)59));
            cats.add(categoryService.get((long)60));
            cats.add(categoryService.get((long)61));
            r.setCategories(cats);
            r = restaurantService.update(r.getId(), r);

            List<DiningTable> tables = new ArrayList<DiningTable>();
            for(int i = 2; i <= 5; i++) {
                DiningTable t = new DiningTable();
                t.setAmount(2);
                t.setPersons(i);
                t.setRestaurant(r);
                tables.add(tableService.create(t));
            }

            r.setDiningTables(tables);
            restaurantService.update(r.getId(), r);
            return ok(Json.toJson(r));

        } catch (Exception e) {
            Logger.debug(e.toString());
        }

        return ok("Filled");
    }

}
