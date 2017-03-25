import Ember from 'ember';
import AdminRoute from 'restaurants-app/routes/admin-route';

export default AdminRoute.extend({
    categoryService: Ember.inject.service(),

	model: function() {
		return Ember.RSVP.hash({
			categories: this.get('categoryService').all()
		});
	}
});
