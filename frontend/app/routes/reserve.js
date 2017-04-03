import Ember from 'ember';
import BaseRoute from 'restaurants-app/routes/base-route';

export default BaseRoute.extend({
    setupController: function(controller, model) {
        this._super(controller, model);

        var restaurantController = this.controllerFor('restaurants.restaurant');
        if(restaurantController.get('finalTime') === undefined)
        	this.transitionTo('index');
        
        controller.set('restaurant', restaurantController.get('model.restaurant'));
        controller.set('reservation', restaurantController.get('reservation'));
        controller.set('finalTime', restaurantController.get('finalTime'));
        controller.set('finalTable', restaurantController.get('finalTable'));
        controller.set('remember', false);
    }
});
