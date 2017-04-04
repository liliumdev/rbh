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


    checkIsLocationProper: function() {
    	if(this.get('new.locationPoints').length == 0) {
    		this.set('new.isLocationProper', true);
    		return;
    	}

    	// Is the location proper, raycast algorithm
		var coordinates = this.get('new').get('coordinates')
	    var locationPoints = this.get('new.locationPoints');

	    var x = coordinates[0], y = coordinates[1];

	    var inside = false;
	    for (var i = 0, j = locationPoints.length - 1; i < locationPoints.length; j = i++) {
	        var xi = locationPoints[i].lat, yi = locationPoints[i].lng;
	        var xj = locationPoints[j].lat, yj = locationPoints[j].lng;

	        var intersect = ((yi > y) != (yj > y))
	            && (x < (xj - xi) * (y - yi) / (yj - yi) + xi);
	        if (intersect) inside = !inside;
	    }

	    this.set('new.isLocationProper', inside);
    },

	actions: {
		updateCenter: function(e) {
			let center = e.target.getCenter();
			this.set('new.mapLat', center.lat);
			this.set('new.mapLng', center.lng);
	    },

		updateLocation: function(e) {
			let location = e.target.getLatLng();
			this.get('new').set('coordinates', [location.lat, location.lng]);
			this.checkIsLocationProper();
	    },

	    selectedCity: function(city) {
	    	if(city.boundary !== null) {
                var locationPoints = city.boundary.coordinates[0].map(r => ({lat: r[0], lng: r[1] }));
                locationPoints.pop();
                this.set('new.locationPoints', locationPoints);
            } else {
                this.set('new.locationPoints', Ember.A([]));
            }

			this.checkIsLocationProper();	
	    },

	    removedCategory: function(category) {
	    	var categoriesList = this.get('new.restaurant.categoriesList');
	    	this.set('new.restaurant.categoriesList', categoriesList.filter(function(c) { return c.id !== category.id}));
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
