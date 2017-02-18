import Ember from 'ember';

export default Ember.Service.extend({	
	role: Ember.computed.oneWay('session.data.authenticated.role')
});
