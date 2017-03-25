import Ember from 'ember';
import {
    validator,
    buildValidations
}
from 'ember-cp-validations';

var Validations = buildValidations({
    firstName: validator('presence', {
        presence: true
    }),
    lastName: validator('presence', {
        presence: true
    }),
    email: [
        validator('presence', {
            presence: true
        }),
        validator('format', {
            type: 'email'
        }),
        validator('unique-email')
    ],
    password: [
        validator('presence', {
            presence: true,
        }),
        validator('length', {
            min: 8
        }),
    ],
    password2: [
        validator('presence', {
            presence: true
        }),
        validator('confirmation', {
            on: 'password'
        })
    ]
}, {
    debounce: 500
});

export default Ember.Component.extend(Validations, {
    accountService: Ember.inject.service(),
    countryService: Ember.inject.service(),
    hasError: false,
    complete: false,
    countryId: 0,
    countries: null,
    cityId: 17,
    cities: [],
    session: Ember.inject.service(),
    flashMessages: Ember.inject.service(),

    authenticate: function(credentials) {
        var authenticator = 'authenticator:jwt';
        return this.get('session').authenticate(authenticator, credentials);
    },

    init: function() {
        this._super();
        this.set('countries', this.get('countryService').all());
        this.set('cities', this.get('countryService').cities(0));
    },

    actions: {
        register: function() {
            var self = this;
            var registerData = this.getProperties('firstName', 'lastName', 'email', 'password', 'password2', 'cityId');
            var credentials = {identification: registerData.email, password: registerData.password};
            this.get('accountService').register(registerData).then(function() {
                self.authenticate(credentials).then(function(value) {
                    self.set('complete', true);
                }, function(reason) {
                    // This shouldn't happen
                    console.log("Error while logging in after registration ...");
                });
            }, function() {
                self.set('hasError', true);
            });
        },

        selectCountry: function(countryId) {
            this.set('countryId', countryId);
            this.set('cities', this.get('countryService').cities(countryId));
        },

        selectCity: function(cityId) {
            this.set('cityId', cityId);
        },
    }


});
