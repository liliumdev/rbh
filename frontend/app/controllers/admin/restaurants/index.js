import Ember from 'ember';

export default Ember.Controller.extend({
	actions: {
		addRestaurant: function() {
			this.transitionToRoute("/admin/restaurants/new");
		}
	}
});
