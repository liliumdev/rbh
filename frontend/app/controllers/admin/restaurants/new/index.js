import Ember from 'ember';

export default Ember.Controller.extend({
	new: Ember.inject.controller('admin.restaurants.new'),

    logoUploaded: Ember.computed(function(file, response) {
	    return function(file, response) {
	    	console.log("logo uploaded");
	    };
  	}),

  	coverUploaded: Ember.computed(function(file, response) {
	    return function(file, response) {
	    	console.log("logo uploaded");
	    };
  	}),

  	maxfilesexceeded: Ember.computed(function(file) {
	    return function(file) {
	    	// Overwrite last file with this one
			this.removeAllFiles();
            this.addFile(file);
	    };
  	}),

	actions: {
		updateLocation: function(e) {
			let location = e.target.getLatLng();
			this.get('new').set('coordinates', [location.lat, location.lng]);
	    }
	}
});
