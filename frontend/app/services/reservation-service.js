import Ember from 'ember';
import BaseService from 'restaurants-app/services/base-service';
import ReservationSuggestion from 'restaurants-app/models/reservation-suggestion';
import Reservation from 'restaurants-app/models/reservation';
import moment from 'moment';

export default BaseService.extend({
    getReservationSuggestions: function(restaurant_id, reservation) {
        var data = {
            wishedTime: moment(reservation.date).format("YYYY-MM-DD") + " " +
                moment(reservation.wishedTime, ["h:mm A"]).format("HH:mm"),
            restaurantId: restaurant_id,
            persons: reservation.persons.replace(" people", "")
        };

        return this.ajax({ url: 'reservations/suggestions', type: "POST", data: JSON.stringify(data) });
    },

    isReservationAvailable: function(restaurant_id, time, table_id) {
        var data = {
            restaurantId: restaurant_id,
            tableId: table_id,
            persons: 1, // not used in this api call
            time: moment(new Date(time)).format("YYYY-MM-DD HH:mm")
        };

        return this.ajax({ url: 'reservations/available', type: "POST", data: JSON.stringify(data) });
    },

    reserve: function(restaurant_id, time, table_id, persons) {
        var data = {
            restaurantId: restaurant_id,
            tableId: table_id,
            persons: persons.replace(" people", ""),
            time: moment(new Date(time)).format("YYYY-MM-DD HH:mm")
        };

        return this.ajax({ url: 'reservations', type: "POST", data: JSON.stringify(data) });
    },

    myReservations: function() {
        var reservations = [];
        this.ajax({ url: 'reservations/my', type: "GET" }).then(function(data) {
            data.forEach(function(reservation) {
                reservations.addObject(Reservation.create(reservation));
            });
        });
        return reservations;
    },

    deleteMyReservation: function(reservationId) {
        return this.ajax({ url: `reservations/my/${reservationId}`, type: "DELETE"});
    }
});
