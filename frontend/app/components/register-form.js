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

    init: function() {
        this._super();
        this.set('countries', this.get('countryService').all());
        this.set('cities', this.get('countryService').cities(0));
    },

    actions: {
        register: function() {
            var self = this;
            var registerData = this.getProperties('firstName', 'lastName', 'email', 'password', 'password2', 'cityId');
            this.get('accountService').register(registerData).then(function() {
                self.set('complete', true);
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
