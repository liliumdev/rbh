import Ember from 'ember';

export default Ember.Route.extend({
    setupController: function(controller, model) {
        this._super(controller, model);

        var restaurantController = this.controllerFor('restaurants.restaurant');
        if(restaurantController.get('finalTime') === undefined)
        	this.transitionTo('index');
        
        controller.set('restaurant', restaurantController.get('model.restaurant'));
        controller.set('reservation', restaurantController.get('reservation'));
        controller.set('finalTime', restaurantController.get('finalTime'));
        controller.set('finalTable', restaurantController.get('finalTable'));
    }
});
