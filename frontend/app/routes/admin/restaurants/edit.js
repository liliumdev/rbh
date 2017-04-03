import Ember from 'ember';
import AdminRoute from 'restaurants-app/routes/admin-route';
import Restaurant from 'restaurants-app/models/restaurant';
import MenuItem from 'restaurants-app/models/menu-item';
import DiningTable from 'restaurants-app/models/dining-table';
import Photo from 'restaurants-app/models/photo';

export default AdminRoute.extend({
	restaurantService: Ember.inject.service(),
	categoryService: Ember.inject.service(),
    cityService: Ember.inject.service(),

	model: function(params, transition) {
		return Ember.RSVP.hash({
			restaurant: this.get('restaurantService').getById_wo_reservations(params.id),
			categories: this.get('categoryService').all(),
			locations: this.get('cityService').all()
		});
	},

	setupController: function(controller, models) {		
	    var lat = models.restaurant.latLong.coordinates[0], lng = models.restaurant.latLong.coordinates[1];

	    // Convert restaurant API json to frontend required json
	    // This should be done in the model ... but not possible atm
	    var restaurant = Restaurant.create({});
	    restaurant.setProperties(models.restaurant);
	    restaurant.set('categoriesList', restaurant.categories);

	    controller.set('restaurant', restaurant);
	    controller.set('menuItems', restaurant.menus[0].menuItems);
	    controller.set('diningTables', restaurant.diningTables);
	    controller.set('lat', lat);
	    controller.set('long', lng);	
	    controller.set('mapLat', lat);
	    controller.set('mapLong', lng);	

	    controller.set('chosenLogoName', restaurant.logoImageUrl);
	    controller.set('chosenCoverName', restaurant.coverImageUrl);

	    controller.set('uploading', false)		

	    var locationPoints = [];
	    if(restaurant.city.boundary !== null) {
		    locationPoints = restaurant.city.boundary.coordinates[0].map(r => ({lat: r[0], lng: r[1] }));
	        locationPoints.pop();
	    }

    	controller.set('locationPoints', locationPoints);
    	controller.set('isLocationProper', true);
    	controller.set('zoom', 12);
	}
});
