import Ember from 'ember';
import UnauthenticatedRoute from 'restaurants-app/routes/unauthenticated-route';

export default UnauthenticatedRoute.extend({
	setupController: function(controller, model) {
        this._super(controller, model);
        
        controller.set('remember', false);
    }
});
