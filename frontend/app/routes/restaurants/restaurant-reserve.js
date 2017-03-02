import Ember from 'ember';

export default Ember.Route.extend({
    restaurantService: Ember.inject.service(),

	controllerName: 'restaurants.restaurant',
	templateName: 'restaurants.restaurant', 

    model: function(params, model) {
    	 console.log("parammms");
    	console.log(params);
    	console.log(model);

        return Ember.RSVP.hash({
            restaurant: this.get('restaurantService').getById(params.id),
            review: this.get('restaurantService').didRate(params.id)
        });
    },

    afterModel: function(model) {
    	console.log("AFTER MODEL");
    	console.log(model);
    },

    beforeModel: function(model) {
    	console.log("BEFORE MODEL");
    	console.log(model);
    },

    setupController(controller, model) {
    	console.log("SETUP");
    	console.log(model);
    },

    deserialize: function(model) {
    	console.log("DESERIALIZE");
    	console.log(model);
    }
});
