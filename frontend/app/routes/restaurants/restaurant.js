import Ember from 'ember';
import BaseRoute from 'restaurants-app/routes/base-route';

export default BaseRoute.extend({
    restaurantService: Ember.inject.service(),

    model: function(params, transition) {
        return Ember.RSVP.hash({
            restaurant: this.get('restaurantService').getById(params.id),
            review: this.get('restaurantService').didRate(params.id)
        });
    }
});
