import Ember from 'ember';
import Reservation from 'restaurants-app/models/reservation';

export default Ember.Controller.extend({
    reservation: Reservation.create({restaurantId: null, persons: "2 people", date: new Date(), wishedTime: "9:00 AM", query: "" }),
    showSuggestions: false,

    actions: {
    	findTable: function() {
    		console.log("pls find!");
    		console.log(this.get('reservation'));
           /* var restaurantId = this.get('model.restaurant.id');
            this.get('reservationService').getReservationSuggestions(restaurantId, this.get('reservation')).then(function(data) {
                this.set('suggestions', data);
                this.set('gotSuggestions', true);
                this.set('hasError', false);

                var _tablesLeft = 0;
                $.each(data, function(i, suggestion) { _tablesLeft += suggestion.freeTables; });
                this.set('tablesLeft', _tablesLeft);

            }.bind(this), function(data) {
                this.set('suggestions', []);
                this.set('errorMsg', JSON.parse(data.responseText));
                this.set('gotSuggestions', false);
                this.set('hasError', true);
            }.bind(this)); */
        },
    }
});
