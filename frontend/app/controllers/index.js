import Ember from 'ember';

export default Ember.Controller.extend({
    restaurantService: Ember.inject.service(),
    showSuggestions: false,
    suggestions: [],
    hiding: false,
    minTime: new Date(0, 0, 0, 8, 0, 0),
    maxTime: new Date(0, 0, 0, 23, 0, 0),

    findSuggestions: function() {
    	if(this.get('hiding') || this.get('reservation.query') === '') {
    		return;
    	}

    	var querySuggestions = this.get('restaurantService').filter({query: this.get('reservation.query'), pageSize: 5});
    	this.set('suggestions', querySuggestions);
    	this.set('showSuggestions', true);
    },

    queryChanged: function() {
    	this.set('reservation.restaurantId', null);
        Ember.run.debounce(this, this.findSuggestions, 500);
    }.observes("reservation.query"),

    actions: {
    	findTable: function() {
    		var reservation = this.get('reservation');
            this.set('searchFromIndex', true);

    		this.transitionToRoute(`/restaurants/${reservation.restaurantId}`);
        },

        setRestaurant: function(restaurant) {
        	var self = this;
        	this.set('reservation.query', restaurant.name);
        	this.set('reservation.restaurantId', restaurant.id);
        },

        hideSuggestions: function() {
        	var self = this;
        	self.set('hiding', true); // we need this to not show autosuggest when we already clicked on a restaurant
        	Ember.run.debounce(this, function() {         		
        		self.set('showSuggestions', false); 
        		self.set('hiding', false);
        	}, 750);
        },

        updateCenter: function(e) {
            let center = e.target.getCenter();
            this.set('mapLat', center.lat);
            this.set('mapLong', center.lng);
        }
    }
});
