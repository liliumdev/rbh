import BaseValidator from 'ember-cp-validations/validators/base';

const UniqueEmail = BaseValidator.extend({
    accountService: Ember.inject.service(),

    validate(value) {
        if(value == "")
          return true;
        
        return this.get('accountService').emailExists(value).then(function() {
            return true;
        }, function() {
            return "This email already exists in the database.";
        });
    }
});

UniqueEmail.reopenClass({
    /**
     * Define attribute specific dependent keys for your validator
     *
     * [
     *  `model.array.@each.${attribute}` --> Dependent is created on the model's context
     *  `${attribute}.isValid` --> Dependent is created on the `model.validations.attrs` context
     * ]
     *
     * @param {String}  attribute   The attribute being evaluated
     * @param {Unknown} options     Options passed into your validator
     * @return {Array}
     */
    getDependentsFor( /* attribute, options */ ) {
        return [];
    }
});

export default UniqueEmail;
