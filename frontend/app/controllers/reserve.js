import Ember from 'ember';

export default Ember.Controller.extend({
    login: Ember.inject.controller(),
    reservationService: Ember.inject.service(),

    loginView: true,
    hasError: false,
    errorMsg: "",

    actions: {
        completeReservation: function() {
            var restaurantId = this.get('restaurant.id');
            var time = this.get('finalTime');
            var table_id = this.get('finalTable');
            var persons = this.get('reservation.persons');
            var request = this.get('reservation.request');

            this.get('reservationService').reserve(restaurantId, time, table_id, persons, request).then(function() {
                this.set('hasError', false);
                this.set('errorMsg', "");
                this.transitionToRoute('my-reservations');
            }.bind(this), function(data) {
                this.set('hasError', true);
                this.set('errorMsg', data.responseText);
            }.bind(this));
        },

        loginWithoutRedirect: function() {
            this.get('login').send('loginWithoutRedirect', this.getProperties('identification', 'password')); 
        },

        toggleLoginRegister: function() {
            this.toggleProperty('loginView');
        }
    }
});
