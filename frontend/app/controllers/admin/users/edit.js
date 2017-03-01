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

export default Ember.Controller.extend(Validations, {
    accountService: Ember.inject.service(),
    flashMessages: Ember.inject.service(),

    // Stuff needed for account modification form
    countryService: Ember.inject.service(),    
    countryId: 0, // Bosnia default
    countries: null,
    cityId: 17,
    cities: [],
    role: 0,

	init: function() {
		this._super();
        this.set('countries', this.get('countryService').all());
        this.set('cities', this.get('countryService').cities(0));
	},

	setForm: function() {
		this.set('cityId', this.get('model.account.city.id'));
		this.set('role', this.get('model.account.role'));
	}.observes('model.account.city.id', 'model.account.role'),

	actions: {
		edit: function() {
			if(this.get('model.account.password') == '') {
				this.set('model.account.password', null);
			}

			const flashMessages = Ember.get(this, 'flashMessages');
			this.get('accountService').edit(this.get('model.account.id'), this.get('model.account')).then(function(newAccount) {
                this.transitionToRoute('admin.users.index');
                flashMessages.success("Modified an account.");
			}.bind(this), function(data) {
                flashMessages.danger("Couldn't modify an account:" + data.responseText);
			}.bind());
		},

        selectCountry: function(countryId) {
            this.set('countryId', countryId);
            this.set('cities', this.get('countryService').cities(countryId));
        },

        selectCity: function(cityId) {
            this.set('model.account.city.id', cityId);
        },

        selectRole: function(role) {
            this.set('model.account.role', role);
        },
	}
});
