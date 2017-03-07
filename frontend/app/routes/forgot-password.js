import Ember from 'ember';
import BaseRoute from 'restaurants-app/routes/base-route';

export default BaseRoute.extend({
	setupController: function(controller, models) {		
        controller.setProperties({complete: false, email: ""});
	}
});
