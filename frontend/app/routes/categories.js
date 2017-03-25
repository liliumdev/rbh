import Ember from 'ember';
import BaseRoute from 'restaurants-app/routes/base-route';

export default BaseRoute.extend({
	restaurantService: Ember.inject.service(),

	model: function(params, transition) {
        return Ember.RSVP.hash({
            restaurants: this.get('restaurantService').filter({'categories': [params.name]}),
            category: params.name
        });
    }
});
