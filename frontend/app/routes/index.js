import Ember from 'ember';
import BaseRoute from 'restaurants-app/routes/base-route';
import config from '../config/environment';

export default BaseRoute.extend({
    restaurantService: Ember.inject.service(),
    locationService: Ember.inject.service(),

	model: function() {
		return Ember.RSVP.hash({
			restaurants: this.get('restaurantService').randomRestaurants(6),
			locations: this.get('locationService').ciitesWithCount()
		});
	},

	setupController: function(controller, models) {
	    controller.set('restaurants', models.restaurants);
    	controller.set('locations', models.locations);
	},

	activate: function() {
	    Ember.$('body').toggleClass("grey-back");
    },

	deactivate: function() {
    	Ember.$('body').toggleClass("grey-back");
	}
});
