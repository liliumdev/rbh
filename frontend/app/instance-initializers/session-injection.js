import Ember from 'ember';

export default {
  name: "session-injection",
  after: 'ember-simple-auth',

  initialize(appInstance) {
    appInstance.inject('route', 'session', 'service:session');
    appInstance.inject('route', 'currentUser', 'service:current-user');
    appInstance.inject('controller', 'session', 'service:session');
    appInstance.inject('component', 'session', 'service:session');
    appInstance.inject('component', 'currentUser', 'service:current-user');
    appInstance.inject('service:current-user', 'session', 'service:session');
  }
} 