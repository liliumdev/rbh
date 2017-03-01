import Ember from 'ember';

export default Ember.Route.extend({
    accountService: Ember.inject.service(),

	model: function(params, transition) {
		return Ember.RSVP.hash({
			account: this.get('accountService').getById(params.id)
		});
	}
});
