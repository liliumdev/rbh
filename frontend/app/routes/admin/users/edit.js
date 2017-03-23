import Ember from 'ember';
import AdminRoute from 'restaurants-app/routes/admin-route';

export default AdminRoute.extend({
    accountService: Ember.inject.service(),

	model: function(params, transition) {
		return Ember.RSVP.hash({
			account: this.get('accountService').getById(params.id)
		});
	}
});
