import Ember from 'ember';
import moment from 'moment';

export default Ember.Controller.extend({
	restaurantService: Ember.inject.service(),
    flashMessages: Ember.inject.service(),

	notReady: Ember.computed('restaurant', 'restaurant.city', 'restaurant.diningTables', 
							 'restaurant.name', 'restaurant.menus', 'restaurant.logoImageUrl', 
							 'restaurant.coverImageUrl', 'uploading', 'restaurant.workingTimeFrom', 
							 'restaurant.workingTimeTo', 'isLocationProper', function() {

		var restaurant = this.get('restaurant');

		var from = this.get('restaurant.workingTimeFrom');
  		var to = this.get('restaurant.workingTimeTo');

		// Logo and cover must not be empty, name must not be empty, menu needs to have
		// at least one item, a location must be chosen, there must be at least one table
		// and table properties need to be valid (integers, and > 0), also the location
		// must be inside a boundary (if there is one)
		var mainMenu = this.get('restaurant.menus')[0];
		var validation = Ember.isEmpty(this.get('restaurant.logoImageUrl')) 	|| 
					     Ember.isEmpty(this.get('restaurant.coverImageUrl'))	||
					     Ember.isEmpty(this.get('restaurant.name'))				||
				   	     Ember.isEmpty(mainMenu.menuItems)						||
				   	     !restaurant.hasOwnProperty('city')						||
				   	     Ember.isEmpty(this.get('restaurant.diningTables'))	    ||
				   	     moment(to).isSameOrBefore(from) 						||
				   	     !this.get('isLocationProper');



		return validation;
	}),

	zoom: 14,

	coordinates: Ember.computed.alias('restaurant.latLongPoint.coordinates'),

	lat: Ember.computed('restaurant.latLongPoint.coordinates', function() {
		return this.get('restaurant.latLongPoint.coordinates')[0];
	}),

	long: Ember.computed('restaurant.latLongPoint.coordinates', function() {
		return this.get('restaurant.latLongPoint.coordinates')[1];
	}),

	actions: {
		addRestaurant: function() {
			const flashMessages = Ember.get(this, 'flashMessages');
			var self = this;

			this.set('uploading', true);

			// First upload images
			this.get('restaurantService').uploadImages(this.get('restaurant')).then(function(response) {
				var restaurant = self.get('restaurant');
				
			 	// Images are uploaded
				self.get('restaurant').setProperties(response);

				// Transform time variables
		        self.get('restaurant').setProperties({
		        	workingTimeFrom: moment(restaurant.workingTimeFrom).format("YYYY-MM-DD HH:mm"),
		        	workingTimeTo: moment(restaurant.workingTimeTo).format("YYYY-MM-DD HH:mm"),
		        	minimumCancelTime: moment(restaurant.minimumCancelTime).format("YYYY-MM-DD HH:mm")
		        });

				// Restaurant model is now ready
				self.get('restaurantService').add(self.get('restaurant')).then(function() {		
	                flashMessages.success("Added a restaurant.");		
	                self.transitionToRoute('admin.restaurants.index');
				}, function(response) {
	                flashMessages.danger("Couldn't add an account.");
				});
			}, function(data) {
	                flashMessages.danger("Couldn't add restaurant.");
	                flashMessages.danger(data);
			});
		},
	}
});
