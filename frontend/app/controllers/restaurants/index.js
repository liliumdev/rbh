import Ember from 'ember';

export default Ember.Controller.extend({
	restaurantService: Ember.inject.service(),
	dropdownOpen: false,


	actions: {
		search: function() {
			var chosenCategories = this.get('model.categories').filterBy('active', true).mapBy("name");
			this.set('model.filter.categories', chosenCategories);
			var filteredRestaurants = this.get('restaurantService').filter(this.get('model.filter'));
			this.set('model.restaurants', filteredRestaurants);
			this.set('dropdownOpen', false);
		},

        switchCategory: function(index) {
        	var category = this.get('model.categories').objectAt(index);
        	var oldValue = Ember.getWithDefault(category, 'active', false);
        	Ember.set(category, 'active', !oldValue);
        }, 


        ratingStarClicked: function(params) {
            this.set('model.filter.rating', params.rating);
        },

        pricingDollarClicked: function(params) {
            this.set('model.filter.pricing', params.rating);
        },

        clearRating: function() {
        	this.set('model.filter.rating', 0);
        },

        clearPricing: function() {
        	this.set('model.filter.pricing', 0);
        },

	    toggleDropdown() {
	        this.toggleProperty('dropdownOpen');
	    },
	}
});
