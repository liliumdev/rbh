import Ember from 'ember';

export default Ember.Controller.extend({
	restaurantService: Ember.inject.service(),
	future: true,

	actions: {
		seeFuture: function() {
			this.set('model.reservations', this.get('restaurantService').allReservations(this.get('model.restaurant.id'), 'future'));
			this.set('future', true);
		},

		seePast: function() {
			this.set('model.reservations', this.get('restaurantService').allReservations(this.get('model.restaurant.id'), 'past'));
			this.set('future', false);
		}
	}
});
