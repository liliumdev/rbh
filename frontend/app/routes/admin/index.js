import Ember from 'ember';
import AdminRoute from 'restaurants-app/routes/admin-route';

export default AdminRoute.extend({
    adminService: Ember.inject.service(),

	model: function() {
		return Ember.RSVP.hash({
			stats: this.get('adminService').stats()
		});
	}
});
