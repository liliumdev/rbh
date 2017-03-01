import Ember from 'ember';
import Filter from 'restaurants-app/models/filter';

export default Ember.Route.extend({
	restaurantService: Ember.inject.service(),
	categoryService: Ember.inject.service(),

	model: function() {
		return Ember.RSVP.hash({
			restaurants: this.get('restaurantService').all(),
			categories: this.get('categoryService').all(),
			filter: Filter.create({rating: 0, pricing: 0, categories: [], query: ''})
		});
	},
});
