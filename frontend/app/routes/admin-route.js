import Ember from 'ember';
import BaseRoute from 'restaurants-app/routes/base-route';

// Limits access to this route only to ADMIN users
export default BaseRoute.extend({
	beforeModel: function(transition) {
		if(this.get('currentUser.role') !== 'ADMIN') {
			return this.transitionTo("index");
		}
	}
});
