import Ember from 'ember';

export default Ember.Component.extend({
	addedPhoto: Ember.computed(function(file) {
  		var self = this;
	    return function(file) {
	    	self.get('new.restaurant.photos').addObject(file);
	    };
  	}),

  	removedPhoto: Ember.computed(function(file) {
  		var self = this;
	    return function(file) {
	    	self.get('new.restaurant.photos').removeObject(file);
	    };
  	}),

  	actions: {
  		removeAddedPhoto: function(file) {
	  		this.get('new.restaurant.photos').removeObject(file);
	  	},

	  	removeAddedUploadedPhoto: function(file) {
	  		this.get('new.restaurant.photos').removeObject(file);
	  	}

  	}
  	
	
});
