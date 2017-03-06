import Ember from 'ember';

export default Ember.Route.extend({
    adminService: Ember.inject.service(),

	model: function() {
		return Ember.RSVP.hash({
			stats: this.get('adminService').stats()
		});
	}
});
