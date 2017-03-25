import Ember from 'ember';
import Account from 'restaurants-app/models/account';
import {
    validator,
    buildValidations
}
from 'ember-cp-validations';

var Validations = buildValidations({
    "account.firstName": validator('presence', {
        presence: true
    }),
    "account.lastName": validator('presence', {
        presence: true
    }),
    "account.email": [
        validator('presence', {
            presence: true
        }),
        validator('format', {
            type: 'email'
        }),
        validator('unique-email')
    ],
    "account.password": [
        validator('presence', {
            presence: true,
        }),
        validator('length', {
            min: 8
        }),
    ]
}, {
    debounce: 500
});

export default Ember.Controller.extend(Validations, {
    accountService: Ember.inject.service(),
    flashMessages: Ember.inject.service(),

    // Stuff needed for account creation form
    countryService: Ember.inject.service(),    
    countryId: 0, // Bosnia default
    countries: null,
    cityId: 17,
    cities: [],
    role: 0,

	showAdd: false,
	account: Account.create({role: 0, cityId: 17}),

	init: function() {
		this._super();
        this.set('countries', this.get('countryService').all());
        this.set('cities', this.get('countryService').cities(0));
	},

	actions: {
		add: function() {

			const flashMessages = Ember.get(this, 'flashMessages');
			this.get('accountService').create(this.get('account')).then(function(newAccount) {
				this.get('model.accounts').pushObject(Account.create(newAccount)); 
				this.set('account', Account.create({role: 0, cityId: 17}));
				this.send('switchAdd');
                flashMessages.success("Created an account.");
			}.bind(this), function() {
                flashMessages.danger("Couldn't create an account.");
			}.bind(this));
		},

		delete: function(account) {
			const flashMessages = Ember.get(this, 'flashMessages');
			this.get('accountService').delete(account.id).then(function() {
				this.get('model.accounts').removeObject(account); 
			}.bind(this), function(data) {
                flashMessages.danger(data.responseText);
			}.bind(this));
		},

		giveRole: function(index, role) {
			var account = this.get('model.accounts').objectAt(index);
			const flashMessages = Ember.get(this, 'flashMessages');
			this.get('accountService').giveRole(account.id, role).then(function() {
				account.set('role', role); 
			}.bind(this), function(data) {
                flashMessages.danger(data.responseText);
			}.bind(this));
		},

        selectCountry: function(countryId) {
            this.set('countryId', countryId);
            this.set('cities', this.get('countryService').cities(countryId));
        },

        selectCity: function(cityId) {
            this.set('account.cityId', cityId);
        },

        selectRole: function(role) {
            this.set('account.role', role);
        },

        switchAdd: function() {
            this.toggleProperty('showAdd');
        }
	}
});
