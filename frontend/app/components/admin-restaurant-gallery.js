import Ember from 'ember';

export default Ember.Component.extend({
	restaurantService: Ember.inject.service(),

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
	  		var self = this;
	  		this.get('restaurantService').deleteImage(file.imageUrl, 'gallery', true, self.get('new.restaurant.id')).then(function() {
	  			self.get('new.restaurant.photos').removeObject(file);
	  		}, function() {
	  			alert("Unspecified error while deleting this photo from AWS S3.");
	  		});
	  	}

  	}
  	
	
});
