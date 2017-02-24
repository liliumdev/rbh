import Ember from 'ember';

export default Ember.Route.extend({
    cityService: Ember.inject.service(),    
    countryService: Ember.inject.service(),

	model: function() {
		return Ember.RSVP.hash({
			locations: this.get('cityService').all(),
			countries: this.get('countryService').all()
		});
	},
});
