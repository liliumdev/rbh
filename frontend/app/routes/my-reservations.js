import Ember from 'ember';
import AuthenticatedRoute from 'restaurants-app/routes/authenticated-route';

export default AuthenticatedRoute.extend({
	reservationService: Ember.inject.service(),

	model: function() {
		return Ember.RSVP.hash({
			reservations: this.get('reservationService').myReservations()
		});
	}
});
