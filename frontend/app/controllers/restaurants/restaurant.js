import Ember from 'ember';
import Reservation from 'restaurants-app/models/reservation';
import Review from 'restaurants-app/models/review';

export default Ember.Controller.extend({
    restaurantService: Ember.inject.service(),
    reservation: Reservation.create({ people: "2 people", date: new Date(), wishedTime: "9:00 AM" }),

    actions: {
        findTable: function() {
            // TODO Reserve a table
            console.log(this.get('reservation').serialize());
        },

        starClicked: function(params) {
            this.set('model.review.rating', params.rating);
        },

        rate: function() {
            var restaurant = this.get('model.restaurant');
            var review = this.get('model.review');

            this.get('restaurantService').rate(restaurant, review).then(function(data) {
                this.set('model.restaurant.reviewRating', data.rating);
                this.set('model.restaurant.reviewCount', this.get('model.restaurant.reviewCount') + 1);
                this.set('model.review.didRate', true);
            }.bind(this), function() {
                console.log("Error while rating.");
            });
        }
    }
});
