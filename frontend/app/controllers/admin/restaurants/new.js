import Ember from 'ember';

export default Ember.Controller.extend({
	ready: false,
	zoom: 14,

	coordinates: Ember.computed.alias('restaurant.latLong.coordinates'),

	lat: Ember.computed('restaurant.latLong.coordinates', function() {
		return this.get('restaurant.latLong.coordinates')[0];
	}),

	long: Ember.computed('restaurant.latLong.coordinates', function() {
		return this.get('restaurant.latLong.coordinates')[1];
	}),

	actions: {
		addRestaurant: function() {
			console.log("RESTORAN JE: ");
			console.log(this.get('restaurant'));
		}
	}
});
