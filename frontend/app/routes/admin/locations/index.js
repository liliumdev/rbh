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

	setupController: function(controller, models) {
		this._super(controller, models);

		controller.setProperties({
			lat: 43.854460,
		    lng: 18.380985,
		    zoom: 14,

		    locationPoints: Ember.A([])
		});
	}
});
