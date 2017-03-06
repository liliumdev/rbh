import Ember from 'ember';
import Restaurant from 'restaurants-app/models/restaurant';

export default Ember.Controller.extend({
    restaurantService: Ember.inject.service(),
    flashMessages: Ember.inject.service(),

	actions: {
		addRestaurant: function() {
			this.transitionToRoute("/admin/restaurants/new");
		},

		delete: function(restaurant) {

			const flashMessages = Ember.get(this, 'flashMessages');
			this.get('restaurantService').delete(restaurant.id).then(function() {
				this.get('model.restaurants').removeObject(restaurant); 
                flashMessages.success("Deleted the restaurant.");
			}.bind(this), function(data) {
                flashMessages.danger(data.responseText);
			}.bind(this));
			
		},
	}
});

