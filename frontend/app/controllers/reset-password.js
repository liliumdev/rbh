import Ember from 'ember';
import {
    validator,
    buildValidations
}
from 'ember-cp-validations';

var Validations = buildValidations({
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


export default Ember.Controller.extend(Validations, {
    accountService: Ember.inject.service(),
	hasError: false,
	complete: false,

	init: function() {
		this._super();
		this.set('hasError', false);
		this.set('complete', false);
	},

	actions: {
		reset: function() {
			var self = this;
        	this.get('accountService').resetPassword(this.get('model.token'), this.get('password')).then(function() {
                self.set('complete', true);
            }, function() {
                self.set('hasError', true);
            });
		}
	}
});
