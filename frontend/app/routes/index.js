import Ember from 'ember';
import BaseRoute from 'restaurants-app/routes/base-route';
import config from '../config/environment';
import Reservation from 'restaurants-app/models/reservation';

export default BaseRoute.extend({
    restaurantService: Ember.inject.service(),
    locationService: Ember.inject.service(),
    geolocation: Ember.inject.service(),

	model: function() {
		return Ember.RSVP.hash({
			restaurants: this.get('restaurantService').nearestRestaurants(6, 0, 0),
			locations: this.get('locationService').citiesWithCount()
		});
	},

	setupController: function(controller, models) {
	    controller.set('restaurants', models.restaurants);
    	controller.set('locations', models.locations);
    	controller.set('suggestions', []);
    	controller.set('reservation', Reservation.create({restaurantId: null, persons: "2 people", date: new Date(), wishedTime: "9:00 AM", query: "" }));
    	controller.set('searchFromIndex', false);
    	controller.set('hiding', false);
    	controller.set('locations', models.locations);    	
	    controller.set('mapLat', 43.854460);
	    controller.set('mapLong', 18.380985);	
	    controller.set('mapZoom', 14);

	    var self = this;

		this.get('geolocation').getLocation().then(function(geoObject) {
			var currentLocation = self.get('geolocation').get('currentLocation');
			controller.set('userLocation', currentLocation);
			var lat = geoObject.coords.latitude, lng = geoObject.coords.longitude;
			controller.set('nearest', self.get('restaurantService').nearestRestaurants(0, lat, lng));
		}, function(error) {});
	},

	activate: function() {
	    Ember.$('body').toggleClass("grey-back");
    },

	deactivate: function() {
    	Ember.$('body').toggleClass("grey-back");
	}
});
