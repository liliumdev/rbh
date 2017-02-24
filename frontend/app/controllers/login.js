import Ember from 'ember';

export default Ember.Controller.extend({
    session: Ember.inject.service(),
    flashMessages: Ember.inject.service(),

    authenticate: function(credentials) {
        var authenticator = 'authenticator:jwt';

        return this.get('session').authenticate(authenticator, credentials);
    },

    actions: {
        loginNormal: function() {
            const flashMessages = Ember.get(this, 'flashMessages');
            var credentials = this.getProperties('identification', 'password');
            var self = this;
            this.authenticate(credentials).then(function(value) {
                self.transitionToRoute('index');
                flashMessages.success("Successfully logged in!");
            }, function(reason) {
                flashMessages.danger("Wrong credentials.");
            });
        },

        loginWithoutRedirect: function(credentials) {
            const flashMessages = Ember.get(this, 'flashMessages');
            var self = this;
            this.authenticate(credentials).then(function(value) {
                flashMessages.success("Successfully logged in!");
            }, function(reason) {
                flashMessages.danger("Wrong credentials.");
            });
        }
    }
});
