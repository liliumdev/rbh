import Ember from 'ember';

export default Ember.Component.extend({
	maxfilesexceeded: Ember.computed(function(file) {
	    return function(file) {
	    	// Overwrite last file with this one
			this.removeAllFiles();
            this.addFile(file);
	    };
  	}),

  	addedLogo: Ember.computed(function(file) {
  		var self = this;
	    return function(file) {
	    	self.set('new.restaurant.logoImageUrl', file);
	    	self.set('new.chosenLogoName', file.name);
	    };
  	}),


  	addedCover: Ember.computed(function(file) {
  		var self = this;
	    return function(file) {
	    	self.set('new.restaurant.coverImageUrl', file);
	    	self.set('new.chosenCoverName', file.name);
	    };
  	}),

	actions: {
		updateLocation: function(e) {
			let location = e.target.getLatLng();
			this.get('new').set('coordinates', [location.lat, location.lng]);
	    },

        pricingDollarClicked: function(params) {
            this.set('new.restaurant.pricing', params.rating);
        },
	}
});
