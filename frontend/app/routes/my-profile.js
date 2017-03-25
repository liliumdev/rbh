import Ember from 'ember';
import AuthenticatedRoute from 'restaurants-app/routes/authenticated-route';

export default AuthenticatedRoute.extend({
	accountService: Ember.inject.service(),

	model: function(params, transition) {
		return Ember.RSVP.hash({
			account: this.get('accountService').me()
		});
	}
});
