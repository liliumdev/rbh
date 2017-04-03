import Ember from 'ember';

export default Ember.Controller.extend({
    session: Ember.inject.service(),
    flashMessages: Ember.inject.service(),

    authenticate: function(credentials) {
        var authenticator = 'authenticator:jwt';

        return this.get('session').authenticate(authenticator, credentials);
    },

    actions: {
        login: function(credentials, doRedirect, remember) {
            var expirationTime = remember === true ? (30 * 24 * 60 * 60) : 'Session'; // 30 days
            this.set('session.store.cookieExpirationTime', expirationTime);
            console.log("exp time");
            console.log(expirationTime);

            const flashMessages = Ember.get(this, 'flashMessages');
            var self = this;
            this.authenticate(credentials).then(function(value) {
                if(doRedirect) {
                    self.transitionToRoute('index');
                }
                flashMessages.success("Successfully logged in!");
            }.bind(doRedirect), function(reason) {
                flashMessages.danger("Wrong credentials.");
            });
        },

        loginNormal: function() {
            var credentials = this.getProperties('identification', 'password');
            var remember = this.get('remember');
            this.send('login', credentials, true, remember);
        },

        loginWithoutRedirect: function(credentials, remember) {
            this.send('login', credentials, false, remember);
        }
    }
});
