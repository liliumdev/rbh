import Ember from 'ember';
import Location from 'restaurants-app/models/location';

export default Ember.Controller.extend({
    cityService: Ember.inject.service(),
    flashMessages: Ember.inject.service(),

    showAdd: false,
    location: { countryId: 0 },

    lat: 45.528178,
    lng: -122.670059,
    zoom: 14,

    restaurants: Ember.A([{
        name: 'Sinju Restaurant',
        rating: 4,
        lat: 45.528531,
        lng: -122.681682
    }, {
        name: 'Burgerville',
        rating: 3.8,
        lat: 45.530970,
        lng: -122.661968
    }, {
        name: 'Le Pigeon',
        rating: 4.5,
        lat: 45.522752,
        lng: -122.657979,
        isOpen: true
    }, ]),

    dangerZone: Ember.computed('restaurants.@each.lat', 'restaurants.@each.lng', function() {
        return this.get('restaurants').map(r => ({ lat: r.lat, lng: r.lng }));
    }),

    actions: {
        add: function() {
            const flashMessages = Ember.get(this, 'flashMessages');

            var countryId = this.get('location.countryId');
            var name = this.get('location.name');

            this.get('cityService').create(countryId, name).then(function(newLocation) {
                this.get('model.locations').pushObject(newLocation);
                this.set('location.name', '');
            }.bind(this), function() {
                flashMessages.danger("Couldn't create a location.");
            }.bind(this));
        },

        delete: function(location) {
            const flashMessages = Ember.get(this, 'flashMessages');
            this.get('cityService').delete(location.id).then(function() {
                this.get('model.locations').removeObject(location);
            }.bind(this), function() {
                flashMessages.danger("Couldn't delete the location.");
            }.bind(this));
        },

        switchAdd: function() {
            this.toggleProperty('showAdd');
        },

        selectCountry: function(countryId) {
            this.set('location.countryId', countryId);
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
