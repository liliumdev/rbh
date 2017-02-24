import Ember from 'ember';

export default Ember.Route.extend({
    categoryService: Ember.inject.service(),

	model: function() {
		return Ember.RSVP.hash({
			categories: this.get('categoryService').all()
		});
	},
});