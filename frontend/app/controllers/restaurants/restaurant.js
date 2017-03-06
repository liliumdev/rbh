import Ember from 'ember';
import Reservation from 'restaurants-app/models/reservation';
import Review from 'restaurants-app/models/review';
import $ from 'jquery';

export default Ember.Controller.extend({
    restaurantService: Ember.inject.service(),
    reservationService: Ember.inject.service(),
    indexController: Ember.inject.controller('index'),
    reservation: Reservation.create({ persons: "2 people", date: new Date(), wishedTime: "9:00 AM" }),
    gotSuggestions: false,
    hasError: false,
    suggestions: [],
    errorMsg: "",
    tablesLeft: 0,
    inProcessOfReservation: false,
    showReviews: false,

    photos: Ember.computed('model.restaurant.photos', function() {
        if(Ember.isEmpty(this.get('model.restaurant.photos')))
            return Ember.A([]);
        var photosForGallery = Ember.A([]);
        var origPhotos = this.get('model.restaurant.photos');
        for(var i = 0; i < origPhotos.length; i++) {
            var photo = origPhotos[i];
            photosForGallery.addObject(
                    {
                        src: 'http://s3.eu-central-1.amazonaws.com/rbh-2017/gallery/' + photo.imageUrl, 
                        w: 600, 
                        h: 400, 
                        title: ''
                    }
                );
        }
        return photosForGallery;
    }),

    clear: function() {
        this.setProperties({
            suggestions: [],
            gotSuggestions: false,
            hasError: false,
            errorMsg: "",
            tablesLeft: 0,
            inProcessOfReservation: false,
            showReviews: false
        });

        var indexController = this.get('indexController');
        if(indexController.get('searchFromIndex')) {
            this.set('reservation', indexController.get('reservation'));
            indexController.set('searchFromIndex', false);
        } else {
            this.set('reservation', Reservation.create({ persons: "2 people", date: new Date(), wishedTime: "9:00 AM" }));
        }
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
                var _tablesLeft = 0;
                $.each(data, function(i, suggestion) { _tablesLeft += suggestion.freeTables; });
                this.set('tablesLeft', _tablesLeft);

                if(_tablesLeft > 0) {
                    this.set('suggestions', data);
                    this.set('gotSuggestions', true);
                    this.set('hasError', false);
                } else {
                    this.set('hasError', true);
                    this.set('errorMsg', "There are no free tables around your wished time. Sorry, please try another time.");
                    this.set('inProcessOfReservation', false);   
                }

            }.bind(this), function(data) {
                this.set('suggestions', []);
                this.set('errorMsg', JSON.parse(data.responseText));
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
        },

        toggleReviews: function() {
            this.toggleProperty('showReviews');
        }
    }
});
