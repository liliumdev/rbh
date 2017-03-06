import Ember from 'ember';
import AdminRoute from 'restaurants-app/routes/admin-route';

export default AdminRoute.extend({
    cityService: Ember.inject.service(),    
    countryService: Ember.inject.service(),

	model: function() {
		return Ember.RSVP.hash({
			locations: this.get('cityService').all(),
			countries: this.get('countryService').all()
		});
	},
});
