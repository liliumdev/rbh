/* jshint node: true */

module.exports = function(environment) {
  var ENV = {
    modulePrefix: 'restaurants-app',
    environment: environment,
    rootURL: '/',
    locationType: 'auto',
    EmberENV: {
      FEATURES: {
        // Here you can enable experimental features on an ember canary build
        // e.g. 'with-controller': true
      },
      EXTEND_PROTOTYPES: {
        // Prevent Ember Data from overriding Date.parse.
        Date: false
      },
      apiHost: null
    },

    APP: {
      // Here you can pass flags/options to your application instance
      // when it is created
    }
  };

  ENV['g-map'] = {
    key: 'AIzaSyC5xfl4ytPUyDUy_yulTluJpZoBZKECEyE'
  };

  ENV['ember-simple-auth'] = {
    authorizer: 'authorizer:token',
    baseURL: ''
  };

  ENV['ember-simple-auth-token'] = {
    refreshAccessTokens: false,
    authorizer: 'authorizer:token',
    identificationField: 'email',
    serverTokenEndpoint: ''
  };

  if (environment === 'development') {
    // ENV.APP.LOG_RESOLVER = true;
    ENV.APP.LOG_ACTIVE_GENERATION = true;
    ENV.APP.LOG_TRANSITIONS = true;
    ENV.APP.LOG_TRANSITIONS_INTERNAL = true;
    ENV.APP.LOG_VIEW_LOOKUPS = true;

    ENV.apiHost = "http://localhost:9000";
    ENV.apiVersion = "1";
  }

  if (environment === 'test') {
    // Testem prefers this...
    ENV.locationType = 'none';

    // keep test console output quieter
    ENV.APP.LOG_ACTIVE_GENERATION = false;
    ENV.APP.LOG_VIEW_LOOKUPS = false;

    ENV.APP.rootElement = '#ember-testing';
  }

  if (environment === 'production') {   
    ENV.apiHost = "https://polar-crag-51709.herokuapp.com";
    ENV.apiVersion = "1";
  }


  ENV['ember-simple-auth'].baseURL = ENV.apiHost;
  ENV['ember-simple-auth-token'].serverTokenEndpoint = `${ENV.apiHost}/api/v1/accounts/login`;

  return ENV;
};
