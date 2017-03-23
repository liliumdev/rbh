import Ember from 'ember';
import AdminRoute from 'restaurants-app/routes/admin-route';

export default AdminRoute.extend({
    accountService: Ember.inject.service(),

	model: function() {
		return Ember.RSVP.hash({
			accounts: this.get('accountService').all()
		});
	}
});
