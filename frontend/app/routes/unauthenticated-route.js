import Ember from 'ember';
import BaseRoute from 'restaurants-app/routes/base-route';

// Limits access to this route only to unauthenticated users
export default BaseRoute.extend({
	beforeModel: function(transition) {
		if(this.get('session.isAuthenticated')) {
			return this.transitionTo("index");
		}
	}
});