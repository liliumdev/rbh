import Ember from 'ember';
import Location from 'restaurants-app/models/location';

export default Ember.Controller.extend({
    cityService: Ember.inject.service(),
    flashMessages: Ember.inject.service(),

    showAdd: false,
    location: { countryId: 0 },

    lat: 43.854460,
    lng: 18.380985,
    zoom: 14,

    locationPoints: Ember.A([]),

    locationZone: Ember.computed('locationPoints.@each.lat', 'locationPoints.@each.lng', function() {
        return this.get('locationPoints').map(r => ({ lat: r.lat, lng: r.lng }));
    }),

    actions: {
        add: function() {
            const flashMessages = Ember.get(this, 'flashMessages');

            var countryId = this.get('location.countryId');
            var name = this.get('location.name');
            var boundary = this.get('locationPoints').map(r => ([r.lat, r.lng]));

            if(boundary.length > 0 && boundary.length < 3) {
                flashMessages.danger("If you're creating a location boundary, there must be a minimum of 3 markers! Or don't create any markers at all.");
                return;
            }

            this.get('cityService').create(countryId, name, boundary).then(function(newLocation) {
                this.get('model.locations').pushObject(Location.create(newLocation));
                this.set('location.name', '');
            }.bind(this), function(data) {
                flashMessages.danger(data.responseText);
            }.bind(this));
        },

        delete: function(location) {
            const flashMessages = Ember.get(this, 'flashMessages');
            this.get('cityService').delete(location.id).then(function() {
                this.get('model.locations').removeObject(location);
            }.bind(this), function(data) {
                flashMessages.danger(data.responseText);
            }.bind(this));
        },
        
        // Edit related functionalities
        edit: function(city) {
            const flashMessages = Ember.get(this, 'flashMessages');
            this.get('cityService').edit(city.id, {name: city.name}).then(function() {
                // Basically the changes should just be visible
            }.bind(this), function(data) {
                flashMessages.danger(data.responseText);
            }.bind());
        },

        toggleEditBoundary: function(location) {
            this.set('showAdd', false);
            this.get('model.locations').setEach('editing', false);
            location.toggleProperty('editing');

            // Map Polygon to locationPoints and remove the last point which is not needed
            if(location.boundary !== null) {
                var locationPoints = location.boundary.coordinates[0].map(r => ({lat: r[0], lng: r[1] }));
                locationPoints.pop();
                this.set('locationPoints', locationPoints);
            } else {
                this.set('locationPoints', []);
            }
        },

        clearMarkers: function() {
            this.set('locationPoints', []);
        },

        finishEditBoundary: function(location) {
            const flashMessages = Ember.get(this, 'flashMessages');
            var boundary = this.get('locationPoints').map(r => ([r.lat, r.lng]));
            
            if(boundary.length > 0 && boundary.length < 3) {
                flashMessages.danger("If you're creating a location boundary, there must be a minimum of 3 markers! Or don't create any markers at all.");
                return;
            }

            this.get('cityService').editBoundary(location.id, boundary).then(function(updatedLocation) {
                location.set('editing', false);
                location.setProperties(updatedLocation);

                flashMessages.success("Updated location boundaries.");
            }.bind(this), function(data) {
                flashMessages.danger(data.responseText);
            }.bind(this));
        },

        cancelEdit: function(location) {            
            location.set('editing', false);
        },

        // Add location related stuff
        switchAdd: function() {
            this.toggleProperty('showAdd');
        },

        selectCountry: function(countryId) {
            this.set('location.countryId', countryId);
        },

        addMarker: function() {
            this.get('locationPoints').pushObject({lat: this.get('lat'), lng: this.get('lng')});
        },


        updateLocation: function(r, e) {
            let location = e.target.getLatLng();
            Ember.setProperties(r, {
                lat: location.lat,
                lng: location.lng
            });
        },

        updateCenter: function(e) {
            let center = e.target.getCenter();
            this.set('lat', center.lat);
            this.set('lng', center.lng);
        }
    }
});
