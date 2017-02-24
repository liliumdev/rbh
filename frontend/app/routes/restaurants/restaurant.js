import Ember from 'ember';

export default Ember.Route.extend({
    restaurantService: Ember.inject.service(),

    model: function(params, transition) {
        return Ember.RSVP.hash({
            restaurant: this.get('restaurantService').getById(params.id),
            review: this.get('restaurantService').didRate(params.id)
        });
    }
});
