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

    locationPoints: Ember.A([{
        lat: 43.835332,
        lng: 18.359699
    }, {
        lat: 43.850746,
        lng: 18.455400
    }, {
        lat: 43.864238,
        lng: 18.378239
    }, ]),

    locationZone: Ember.computed('locationPoints.@each.lat', 'locationPoints.@each.lng', function() {
        return this.get('locationPoints').map(r => ({ lat: r.lat, lng: r.lng }));
    }),

    actions: {
        add: function() {
            const flashMessages = Ember.get(this, 'flashMessages');

            var countryId = this.get('location.countryId');
            var name = this.get('location.name');

            this.get('cityService').create(countryId, name).then(function(newLocation) {
                this.get('model.locations').pushObject(newLocation);
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
        
        edit: function(city) {
            const flashMessages = Ember.get(this, 'flashMessages');
            this.get('cityService').edit(city.id, {name: city.name}).then(function() {
                // Basically the changes should just be visible
            }.bind(this), function(data) {
                flashMessages.danger(data.responseText);
            }.bind());
        },

        switchAdd: function() {
            this.toggleProperty('showAdd');
        },

        selectCountry: function(countryId) {
            this.set('location.countryId', countryId);
        },

        addMarker: function() {
            this.get('locationPoints').pushObject({lat: this.get('lat'), lng: this.get('lng')});
        },


        updateLocation(r, e) {
            let location = e.target.getLatLng();
            Ember.setProperties(r, {
                lat: location.lat,
                lng: location.lng
            });
        }
    }
});
