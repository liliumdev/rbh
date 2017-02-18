import Ember from 'ember';
import {
    validator,
    buildValidations
}
from 'ember-cp-validations';

var Validations = buildValidations({
    identification: [
        validator('presence', {
            presence: true
        }),
        validator('format', {
            type: 'email'
        })
    ],
    password: [
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

export default Ember.Component.extend(Validations, {
	init: function() {
        this._super();
		this.empty();
    },

    empty: function() {
    	this.set('identification', '');
    	this.set('password', '');
    }
});
