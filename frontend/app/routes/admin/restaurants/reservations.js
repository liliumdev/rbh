import Ember from 'ember';

export default Ember.Route.extend({
	restaurantService: Ember.inject.service(),

	model: function(params, transition) {
		return Ember.RSVP.hash({
			restaurant: this.get('restaurantService').getById(params.id),
			reservations: this.get('restaurantService').allReservations(params.id, 'all')
		});
	},

	setupController: function(controller, models) {
		this._super(controller, models);
		controller.set('future', true);
	}
});
