import Ember from 'ember';
import Account from 'restaurants-app/models/account';
import {
    validator,
    buildValidations
}
from 'ember-cp-validations';

var Validations = buildValidations({
    "model.account.firstName": validator('presence', {
        presence: true
    }),
    "model.account.lastName": validator('presence', {
        presence: true
    }),
    "model.account.email": [
        validator('presence', {
            presence: true
        }),
        validator('format', {
            type: 'email'
        })
    ]
}, {
    debounce: 500
});

export default Ember.Component.extend(Validations, {
    accountService: Ember.inject.service(),
    flashMessages: Ember.inject.service(),

    // Stuff needed for account modification form
    countryService: Ember.inject.service(),    
    country: 0,
    countries: null,
    city: 17,
    cities: [],
    role: 0,


    setForm: function() {
        var countryId = this.get('model.account.city.country');
        if(countryId !== undefined) {
            this.set('country', countryId);
            this.set('countries', this.get('countryService').all());
            this.set('cities', this.get('countryService').cities(countryId));        
            this.set('city', this.get('model.account.city.id'));     
            this.set('role', this.get('model.account.role'));
        }
    }.observes('model.account.id'),

	actions: {
        selectCountry: function(countryId) {
            var self = this;
            this.set('changedCountry', true);
            this.get('countryService').getById(countryId).then(function(country) {
                var cities = country.cities;
                self.set('cities', cities);
                if(cities.length > 0) {
                    self.set('city', cities[0].id);
                    self.set('model.account.city.id', cities[0].id);
                } else {
                    alert("DAYUMN!");
                }
            });
        },

        selectCity: function(cityId) {
            this.set('model.account.city.id', cityId);
            console.log("set city");
        },

        selectRole: function(role) {
            this.set('model.account.role', role);
        },
	}
});
