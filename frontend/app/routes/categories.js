import Ember from 'ember';

export default Ember.Route.extend({	
	restaurantService: Ember.inject.service(),

	model: function(params, transition) {
        return Ember.RSVP.hash({
            restaurants: this.get('restaurantService').filter({'categories': [params.name]}),
            category: params.name
        });
    }
});
