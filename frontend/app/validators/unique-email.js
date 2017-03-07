import Ember from 'ember';
import BaseValidator from 'ember-cp-validations/validators/base';

const UniqueEmail = BaseValidator.extend({
    accountService: Ember.inject.service(),

    validate(value) {
        if(value === "") {
          return true;
        }
        
        return this.get('accountService').emailExists(value).then(function() {
            return true;
        }, function() {
            return "This email already exists in the database.";
        });
    }
});

UniqueEmail.reopenClass({
    getDependentsFor( /* attribute, options */ ) {
        return [];
    }
});

export default UniqueEmail;
