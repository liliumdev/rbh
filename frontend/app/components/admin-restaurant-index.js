import Ember from 'ember';
import moment from 'moment';

export default Ember.Component.extend({
	optionsFrom: {scrollbar: true, dynamic: false},
	optionsTo: {scrollbar: true, dynamic: false},
	optionsCancel: {
		scrollbar: true, 
		minHour: 0, 
		maxHour: 23, 
		interval: 30, // 30 minutes
		timeFormat: 'HH:mm',
		dynamic: false
	},

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

  	timeError: Ember.computed('new.restaurant.workingTimeFrom', 'new.restaurant.workingTimeTo', function() {
  		var from = this.get('new.restaurant.workingTimeFrom');
  		var to = this.get('new.restaurant.workingTimeTo');
  		if(moment(to).isSameOrBefore(from)) {
  			return true;
  		}

  		return false;
  	}),

	actions: {
		updateLocation: function(e) {
			let location = e.target.getLatLng();
			this.get('new').set('coordinates', [location.lat, location.lng]);
	    },

        pricingDollarClicked: function(params) {
            this.set('new.restaurant.pricing', params.rating);
        },

        changeWorkingTimeFrom: function(time) {
        	this.set('new.restaurant.workingTimeFrom', time);
        },

        changeWorkingTimeTo: function(time) {
        	this.set('new.restaurant.workingTimeTo', time);
        },

        changeMinCancelTime: function(time) {
        	this.set('new.restaurant.minimumCancelTime', time);
        }
	}
});
