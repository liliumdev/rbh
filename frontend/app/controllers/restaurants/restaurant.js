import Ember from 'ember';
import Reservation from 'restaurants-app/models/reservation';
import Review from 'restaurants-app/models/review';
import $ from 'jquery';

export default Ember.Controller.extend({
    restaurantService: Ember.inject.service(),
    reservationService: Ember.inject.service(),
    reservation: Reservation.create({ persons: "2 people", date: new Date(), wishedTime: "9:00 AM" }),
    gotSuggestions: false,
    hasError: false,
    suggestions: [],
    errorMsg: "",
    tablesLeft: 0,
    inProcessOfReservation: false,

    clear: function() {
        this.setProperties({
            suggestions: [],
            gotSuggestions: false,
            hasError: false,
            errorMsg: "",
            tablesLeft: 0,
            inProcessOfReservation: false
        });
    }.observes('model.restaurant'),

    actions: {
        reserveTable: function(time, table) {
            if (!this.get('inProcessOfReservation')) {
                this.set('inProcessOfReservation', true);

                var restaurantId = this.get('model.restaurant.id');

                this.get('reservationService').isReservationAvailable(restaurantId, time, table).then(function(data) {
                    this.set('inProcessOfReservation', false);
                    this.set('finalTime', time);
                    this.set('finalTable', table);
                    this.transitionToRoute('reserve');
                }.bind(this), function(data) {
                    this.set('hasError', true);
                    this.set('errorMsg', data.responseText);
                    this.set('inProcessOfReservation', false);
                    Ember.run.later((function() {
                        this.send('findTable');
                    }.bind(this)), 1500);
                }.bind(this));
            }
        },

        findTable: function() {
            var restaurantId = this.get('model.restaurant.id');
            this.get('reservationService').getReservationSuggestions(restaurantId, this.get('reservation')).then(function(data) {
                this.set('suggestions', data);
                this.set('gotSuggestions', true);
                this.set('hasError', false);

                var _tablesLeft = 0;
                $.each(data, function(i, suggestion) { _tablesLeft += suggestion.freeTables; });
                this.set('tablesLeft', _tablesLeft);

            }.bind(this), function(data) {
                this.set('suggestions', []);
                this.set('errorMsg', data);
                this.set('gotSuggestions', false);
                this.set('hasError', true);
            }.bind(this));
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
