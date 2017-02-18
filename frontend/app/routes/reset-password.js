import Ember from 'ember';

export default Ember.Route.extend({
    token: null,

    model: function(param) {
        var self = this;

        self.set('token', param.token);

        return Ember.RSVP.hash({
            token: self.get('token')
        });
    }
});
