import Ember from 'ember';

import {
    validator,
    buildValidations
}
from 'ember-cp-validations';

var Validations = buildValidations({
    email: [
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
	complete: false,

	init: function() {
        this._super();
        this.set('complete', false);
    },

	actions: {
        forgot: function() {
        	var self = this;
        	this.get('accountService').forgotPassword(this.get('email')).then(function() {
                self.set('complete', true);
            }, function() {
                self.set('hasError', true);
            });
        }
    }
});
