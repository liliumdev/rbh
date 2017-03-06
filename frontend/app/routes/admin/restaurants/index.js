import Ember from 'ember';

export default Ember.Route.extend({
    restaurantService: Ember.inject.service(),

	model: function() {
		return Ember.RSVP.hash({
			restaurants: this.get('restaurantService').all()
		});
	}
});
