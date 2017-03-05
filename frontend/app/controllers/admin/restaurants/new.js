import Ember from 'ember';

export default Ember.Controller.extend({
	restaurantService: Ember.inject.service(),

	notReady: Ember.computed('restaurant', 'restaurant.city', 'restaurant.diningTables', 
							 'restaurant.name', 'restaurant.menus', 'restaurant.logoImageUrl', 
							 'restaurant.coverImageUrl', function() {

		var restaurant = this.get('restaurant');

		// Logo and cover must not be empty, name must not be empty, menu needs to have
		// at least one item, a location must be chosen, there must be at least one table
		// and table properties need to be valid (integers, and > 0)
		var mainMenu = this.get('restaurant.menus')[0];
		var validation = Ember.isEmpty(this.get('restaurant.logoImageUrl')) 	|| 
					     Ember.isEmpty(this.get('restaurant.coverImageUrl'))	||
					     Ember.isEmpty(this.get('restaurant.name'))				||
				   	     Ember.isEmpty(mainMenu.menuItems)						||
				   	     !restaurant.hasOwnProperty('city')						||
				   	     Ember.isEmpty(this.get('restaurant.diningTables'));

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
			console.log("RESTORAN JE: ");
			console.log(this.get('restaurant'));
			 this.get('restaurantService').uploadImages(this.get('restaurant')).then(function(response) {
				this.get('restaurant').setProperties(response);

				// The photos have been uploaded, now we can submit
				JSON.stringify((this.get('restaurant')));
			}.bind(this), function(response) {
				// Error
			}); 
		}
	}
});
