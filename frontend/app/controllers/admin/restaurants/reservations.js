import Ember from 'ember';
import moment from 'moment';

export default Ember.Controller.extend({
	restaurantService: Ember.inject.service(),
	future: true,

	events: Ember.computed('model.reservations.[]', function() {
		var reservations = this.get('model.reservations.[]');
		
		var properEvents = reservations.map(r => ({
			title: `${r.account.firstName} ${r.account.lastName} - table for ${r.persons}`, 
			start: moment(r.forTime, ["YYYY-MM-DD HH:mm"]).toDate(),
			end: moment(r.forTime, ["YYYY-MM-DD HH:mm"]).add(30, 'minutes').toDate(),
			name: `${r.account.firstName} ${r.account.lastName}`,
			persons: r.persons,
			request: r.request
			})
		);

		console.log(properEvents);
		return properEvents;
	}),

	headerOptions: {
	    left:   'title',
	    center: '',
	    right:  'listWeek agendaWeek month today prev,next'
	},

	actions: {
	/*	seeFuture: function() {
			this.set('model.reservations', this.get('restaurantService').allReservations(this.get('model.restaurant.id'), 'future'));
			this.set('future', true);
		},

		seePast: function() {
			this.set('model.reservations', this.get('restaurantService').allReservations(this.get('model.restaurant.id'), 'past'));
			this.set('future', false);
		},*/

		eventClicked: function(event) {
			this.set('event', event);
			Ember.$('#reservationModal').modal('show');
		}
	}
});
