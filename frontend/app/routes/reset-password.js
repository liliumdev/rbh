import Ember from 'ember';
import UnauthenticatedRoute from 'restaurants-app/routes/unauthenticated-route';

export default UnauthenticatedRoute.extend({
    token: null,

    model: function(param) {
        var self = this;

        self.set('token', param.token);

        return Ember.RSVP.hash({
            token: self.get('token')
        });
    }
});
