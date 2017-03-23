import Ember from 'ember';

export default Ember.Controller.extend({
	reservationService: Ember.inject.service(),

	actions: {
		delete: function(reservation) {
			const flashMessages = Ember.get(this, 'flashMessages');
			this.get('reservationService').deleteMyReservation(reservation.id).then(function() {
				this.get('model.reservations').removeObject(reservation);	
                flashMessages.success("Canceled the reservation.");		 
			}.bind(this), function(data) {
                flashMessages.danger(data.responseText);	
			}.bind(this));
		},
	}
});
