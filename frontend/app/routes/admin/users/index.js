import Ember from 'ember';

export default Ember.Route.extend({
    accountService: Ember.inject.service(),

	model: function() {
		return Ember.RSVP.hash({
			accounts: this.get('accountService').all()
		});
	}
});
