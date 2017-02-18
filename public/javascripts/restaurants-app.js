"use strict";

/* jshint ignore:start */



/* jshint ignore:end */

define('restaurants-app/adapters/application', ['exports', 'ember-data', 'ember-simple-auth/mixins/data-adapter-mixin', 'restaurants-app/config/environment'], function (exports, _emberData, _emberSimpleAuthMixinsDataAdapterMixin, _restaurantsAppConfigEnvironment) {
	exports['default'] = _emberData['default'].RESTAdapter.extend(_emberSimpleAuthMixinsDataAdapterMixin['default'], {
		host: _restaurantsAppConfigEnvironment['default'].apiHost,
		namespace: 'api/v1',
		authorizer: 'authorizer:token'
	});
});
define('restaurants-app/app', ['exports', 'ember', 'restaurants-app/resolver', 'ember-load-initializers', 'restaurants-app/config/environment'], function (exports, _ember, _restaurantsAppResolver, _emberLoadInitializers, _restaurantsAppConfigEnvironment) {

  var App = undefined;

  _ember['default'].MODEL_FACTORY_INJECTIONS = true;

  App = _ember['default'].Application.extend({
    modulePrefix: _restaurantsAppConfigEnvironment['default'].modulePrefix,
    podModulePrefix: _restaurantsAppConfigEnvironment['default'].podModulePrefix,
    Resolver: _restaurantsAppResolver['default']
  });

  (0, _emberLoadInitializers['default'])(App, _restaurantsAppConfigEnvironment['default'].modulePrefix);

  exports['default'] = App;
});
define('restaurants-app/components/fa-icon', ['exports', 'ember-font-awesome/components/fa-icon'], function (exports, _emberFontAwesomeComponentsFaIcon) {
  Object.defineProperty(exports, 'default', {
    enumerable: true,
    get: function get() {
      return _emberFontAwesomeComponentsFaIcon['default'];
    }
  });
});
define('restaurants-app/components/fa-list', ['exports', 'ember-font-awesome/components/fa-list'], function (exports, _emberFontAwesomeComponentsFaList) {
  Object.defineProperty(exports, 'default', {
    enumerable: true,
    get: function get() {
      return _emberFontAwesomeComponentsFaList['default'];
    }
  });
});
define('restaurants-app/components/fa-stack', ['exports', 'ember-font-awesome/components/fa-stack'], function (exports, _emberFontAwesomeComponentsFaStack) {
  Object.defineProperty(exports, 'default', {
    enumerable: true,
    get: function get() {
      return _emberFontAwesomeComponentsFaStack['default'];
    }
  });
});
define('restaurants-app/components/flash-message', ['exports', 'ember-cli-flash/components/flash-message'], function (exports, _emberCliFlashComponentsFlashMessage) {
  Object.defineProperty(exports, 'default', {
    enumerable: true,
    get: function get() {
      return _emberCliFlashComponentsFlashMessage['default'];
    }
  });
});
define('restaurants-app/components/g-map-address-marker', ['exports', 'ember-g-map/components/g-map-address-marker'], function (exports, _emberGMapComponentsGMapAddressMarker) {
  Object.defineProperty(exports, 'default', {
    enumerable: true,
    get: function get() {
      return _emberGMapComponentsGMapAddressMarker['default'];
    }
  });
});
define('restaurants-app/components/g-map-address-route', ['exports', 'ember-g-map/components/g-map-address-route'], function (exports, _emberGMapComponentsGMapAddressRoute) {
  Object.defineProperty(exports, 'default', {
    enumerable: true,
    get: function get() {
      return _emberGMapComponentsGMapAddressRoute['default'];
    }
  });
});
define('restaurants-app/components/g-map-infowindow', ['exports', 'ember-g-map/components/g-map-infowindow'], function (exports, _emberGMapComponentsGMapInfowindow) {
  Object.defineProperty(exports, 'default', {
    enumerable: true,
    get: function get() {
      return _emberGMapComponentsGMapInfowindow['default'];
    }
  });
});
define('restaurants-app/components/g-map-marker', ['exports', 'ember-g-map/components/g-map-marker'], function (exports, _emberGMapComponentsGMapMarker) {
  Object.defineProperty(exports, 'default', {
    enumerable: true,
    get: function get() {
      return _emberGMapComponentsGMapMarker['default'];
    }
  });
});
define('restaurants-app/components/g-map-polyline-coordinate', ['exports', 'ember-g-map/components/g-map-polyline-coordinate'], function (exports, _emberGMapComponentsGMapPolylineCoordinate) {
  Object.defineProperty(exports, 'default', {
    enumerable: true,
    get: function get() {
      return _emberGMapComponentsGMapPolylineCoordinate['default'];
    }
  });
});
define('restaurants-app/components/g-map-polyline', ['exports', 'ember-g-map/components/g-map-polyline'], function (exports, _emberGMapComponentsGMapPolyline) {
  Object.defineProperty(exports, 'default', {
    enumerable: true,
    get: function get() {
      return _emberGMapComponentsGMapPolyline['default'];
    }
  });
});
define('restaurants-app/components/g-map-route-address-waypoint', ['exports', 'ember-g-map/components/g-map-route-address-waypoint'], function (exports, _emberGMapComponentsGMapRouteAddressWaypoint) {
  Object.defineProperty(exports, 'default', {
    enumerable: true,
    get: function get() {
      return _emberGMapComponentsGMapRouteAddressWaypoint['default'];
    }
  });
});
define('restaurants-app/components/g-map-route-waypoint', ['exports', 'ember-g-map/components/g-map-route-waypoint'], function (exports, _emberGMapComponentsGMapRouteWaypoint) {
  Object.defineProperty(exports, 'default', {
    enumerable: true,
    get: function get() {
      return _emberGMapComponentsGMapRouteWaypoint['default'];
    }
  });
});
define('restaurants-app/components/g-map-route', ['exports', 'ember-g-map/components/g-map-route'], function (exports, _emberGMapComponentsGMapRoute) {
  Object.defineProperty(exports, 'default', {
    enumerable: true,
    get: function get() {
      return _emberGMapComponentsGMapRoute['default'];
    }
  });
});
define('restaurants-app/components/g-map', ['exports', 'ember-g-map/components/g-map'], function (exports, _emberGMapComponentsGMap) {
  Object.defineProperty(exports, 'default', {
    enumerable: true,
    get: function get() {
      return _emberGMapComponentsGMap['default'];
    }
  });
});
define('restaurants-app/components/nav-link-to', ['exports', 'ember-bootstrap-nav-link/components/nav-link-to'], function (exports, _emberBootstrapNavLinkComponentsNavLinkTo) {
  Object.defineProperty(exports, 'default', {
    enumerable: true,
    get: function get() {
      return _emberBootstrapNavLinkComponentsNavLinkTo['default'];
    }
  });
});
define('restaurants-app/components/restaurant-listing', ['exports', 'ember'], function (exports, _ember) {
  exports['default'] = _ember['default'].Component.extend({});
});
define('restaurants-app/components/scroll-to', ['exports', 'ember-scroll-to/components/scroll-to'], function (exports, _emberScrollToComponentsScrollTo) {
  exports['default'] = _emberScrollToComponentsScrollTo['default'];
});
define('restaurants-app/components/welcome-page', ['exports', 'ember-welcome-page/components/welcome-page'], function (exports, _emberWelcomePageComponentsWelcomePage) {
  Object.defineProperty(exports, 'default', {
    enumerable: true,
    get: function get() {
      return _emberWelcomePageComponentsWelcomePage['default'];
    }
  });
});
define('restaurants-app/controllers/application', ['exports', 'ember'], function (exports, _ember) {
	exports['default'] = _ember['default'].Controller.extend({
		session: _ember['default'].inject.service(),
		currentUser: _ember['default'].inject.service(),

		logout: function logout() {
			console.log("sad bih trebel da se logoutujem, ne");
		},

		navbarClass: (function () {
			var routesTransparentNavbar = ['index', 'restaurants.restaurant'];
			return $.inArray(this.get('currentPath'), routesTransparentNavbar) != -1 ? "navbar-transparent" : "navbar-white";
		}).property('currentPath')
	});
});
define('restaurants-app/controllers/login', ['exports', 'ember'], function (exports, _ember) {
  exports['default'] = _ember['default'].Controller.extend({
    session: _ember['default'].inject.service(),
    flashMessages: _ember['default'].inject.service(),

    actions: {
      authenticate: function authenticate() {
        var flashMessages = _ember['default'].get(this, 'flashMessages');

        var credentials = this.getProperties('identification', 'password'),
            authenticator = 'authenticator:jwt';

        this.get('session').authenticate(authenticator, credentials).then((function (value) {
          this.transitionToRoute('index');
          flashMessages.success("Successfully logged in!");
        }).bind(this), function (reason) {
          flashMessages.danger("Wrong credentials.");
        });
      }
    }
  });
});
define('restaurants-app/controllers/register', ['exports', 'ember'], function (exports, _ember) {
  exports['default'] = _ember['default'].Controller.extend({});
});
define('restaurants-app/flash/object', ['exports', 'ember-cli-flash/flash/object'], function (exports, _emberCliFlashFlashObject) {
  Object.defineProperty(exports, 'default', {
    enumerable: true,
    get: function get() {
      return _emberCliFlashFlashObject['default'];
    }
  });
});
define('restaurants-app/helpers/and', ['exports', 'ember', 'ember-truth-helpers/helpers/and'], function (exports, _ember, _emberTruthHelpersHelpersAnd) {

  var forExport = null;

  if (_ember['default'].Helper) {
    forExport = _ember['default'].Helper.helper(_emberTruthHelpersHelpersAnd.andHelper);
  } else if (_ember['default'].HTMLBars.makeBoundHelper) {
    forExport = _ember['default'].HTMLBars.makeBoundHelper(_emberTruthHelpersHelpersAnd.andHelper);
  }

  exports['default'] = forExport;
});
define('restaurants-app/helpers/app-version', ['exports', 'ember', 'restaurants-app/config/environment'], function (exports, _ember, _restaurantsAppConfigEnvironment) {
  exports.appVersion = appVersion;
  var version = _restaurantsAppConfigEnvironment['default'].APP.version;

  function appVersion() {
    return version;
  }

  exports['default'] = _ember['default'].Helper.helper(appVersion);
});
define('restaurants-app/helpers/eq', ['exports', 'ember', 'ember-truth-helpers/helpers/equal'], function (exports, _ember, _emberTruthHelpersHelpersEqual) {

  var forExport = null;

  if (_ember['default'].Helper) {
    forExport = _ember['default'].Helper.helper(_emberTruthHelpersHelpersEqual.equalHelper);
  } else if (_ember['default'].HTMLBars.makeBoundHelper) {
    forExport = _ember['default'].HTMLBars.makeBoundHelper(_emberTruthHelpersHelpersEqual.equalHelper);
  }

  exports['default'] = forExport;
});
define('restaurants-app/helpers/gt', ['exports', 'ember', 'ember-truth-helpers/helpers/gt'], function (exports, _ember, _emberTruthHelpersHelpersGt) {

  var forExport = null;

  if (_ember['default'].Helper) {
    forExport = _ember['default'].Helper.helper(_emberTruthHelpersHelpersGt.gtHelper);
  } else if (_ember['default'].HTMLBars.makeBoundHelper) {
    forExport = _ember['default'].HTMLBars.makeBoundHelper(_emberTruthHelpersHelpersGt.gtHelper);
  }

  exports['default'] = forExport;
});
define('restaurants-app/helpers/gte', ['exports', 'ember', 'ember-truth-helpers/helpers/gte'], function (exports, _ember, _emberTruthHelpersHelpersGte) {

  var forExport = null;

  if (_ember['default'].Helper) {
    forExport = _ember['default'].Helper.helper(_emberTruthHelpersHelpersGte.gteHelper);
  } else if (_ember['default'].HTMLBars.makeBoundHelper) {
    forExport = _ember['default'].HTMLBars.makeBoundHelper(_emberTruthHelpersHelpersGte.gteHelper);
  }

  exports['default'] = forExport;
});
define('restaurants-app/helpers/is-array', ['exports', 'ember', 'ember-truth-helpers/helpers/is-array'], function (exports, _ember, _emberTruthHelpersHelpersIsArray) {

  var forExport = null;

  if (_ember['default'].Helper) {
    forExport = _ember['default'].Helper.helper(_emberTruthHelpersHelpersIsArray.isArrayHelper);
  } else if (_ember['default'].HTMLBars.makeBoundHelper) {
    forExport = _ember['default'].HTMLBars.makeBoundHelper(_emberTruthHelpersHelpersIsArray.isArrayHelper);
  }

  exports['default'] = forExport;
});
define('restaurants-app/helpers/is-equal', ['exports', 'ember-truth-helpers/helpers/is-equal'], function (exports, _emberTruthHelpersHelpersIsEqual) {
  Object.defineProperty(exports, 'default', {
    enumerable: true,
    get: function get() {
      return _emberTruthHelpersHelpersIsEqual['default'];
    }
  });
  Object.defineProperty(exports, 'isEqual', {
    enumerable: true,
    get: function get() {
      return _emberTruthHelpersHelpersIsEqual.isEqual;
    }
  });
});
define('restaurants-app/helpers/lt', ['exports', 'ember', 'ember-truth-helpers/helpers/lt'], function (exports, _ember, _emberTruthHelpersHelpersLt) {

  var forExport = null;

  if (_ember['default'].Helper) {
    forExport = _ember['default'].Helper.helper(_emberTruthHelpersHelpersLt.ltHelper);
  } else if (_ember['default'].HTMLBars.makeBoundHelper) {
    forExport = _ember['default'].HTMLBars.makeBoundHelper(_emberTruthHelpersHelpersLt.ltHelper);
  }

  exports['default'] = forExport;
});
define('restaurants-app/helpers/lte', ['exports', 'ember', 'ember-truth-helpers/helpers/lte'], function (exports, _ember, _emberTruthHelpersHelpersLte) {

  var forExport = null;

  if (_ember['default'].Helper) {
    forExport = _ember['default'].Helper.helper(_emberTruthHelpersHelpersLte.lteHelper);
  } else if (_ember['default'].HTMLBars.makeBoundHelper) {
    forExport = _ember['default'].HTMLBars.makeBoundHelper(_emberTruthHelpersHelpersLte.lteHelper);
  }

  exports['default'] = forExport;
});
define('restaurants-app/helpers/not-eq', ['exports', 'ember', 'ember-truth-helpers/helpers/not-equal'], function (exports, _ember, _emberTruthHelpersHelpersNotEqual) {

  var forExport = null;

  if (_ember['default'].Helper) {
    forExport = _ember['default'].Helper.helper(_emberTruthHelpersHelpersNotEqual.notEqualHelper);
  } else if (_ember['default'].HTMLBars.makeBoundHelper) {
    forExport = _ember['default'].HTMLBars.makeBoundHelper(_emberTruthHelpersHelpersNotEqual.notEqualHelper);
  }

  exports['default'] = forExport;
});
define('restaurants-app/helpers/not', ['exports', 'ember', 'ember-truth-helpers/helpers/not'], function (exports, _ember, _emberTruthHelpersHelpersNot) {

  var forExport = null;

  if (_ember['default'].Helper) {
    forExport = _ember['default'].Helper.helper(_emberTruthHelpersHelpersNot.notHelper);
  } else if (_ember['default'].HTMLBars.makeBoundHelper) {
    forExport = _ember['default'].HTMLBars.makeBoundHelper(_emberTruthHelpersHelpersNot.notHelper);
  }

  exports['default'] = forExport;
});
define('restaurants-app/helpers/or', ['exports', 'ember', 'ember-truth-helpers/helpers/or'], function (exports, _ember, _emberTruthHelpersHelpersOr) {

  var forExport = null;

  if (_ember['default'].Helper) {
    forExport = _ember['default'].Helper.helper(_emberTruthHelpersHelpersOr.orHelper);
  } else if (_ember['default'].HTMLBars.makeBoundHelper) {
    forExport = _ember['default'].HTMLBars.makeBoundHelper(_emberTruthHelpersHelpersOr.orHelper);
  }

  exports['default'] = forExport;
});
define('restaurants-app/helpers/pluralize', ['exports', 'ember-inflector/lib/helpers/pluralize'], function (exports, _emberInflectorLibHelpersPluralize) {
  exports['default'] = _emberInflectorLibHelpersPluralize['default'];
});
define('restaurants-app/helpers/singularize', ['exports', 'ember-inflector/lib/helpers/singularize'], function (exports, _emberInflectorLibHelpersSingularize) {
  exports['default'] = _emberInflectorLibHelpersSingularize['default'];
});
define('restaurants-app/helpers/xor', ['exports', 'ember', 'ember-truth-helpers/helpers/xor'], function (exports, _ember, _emberTruthHelpersHelpersXor) {

  var forExport = null;

  if (_ember['default'].Helper) {
    forExport = _ember['default'].Helper.helper(_emberTruthHelpersHelpersXor.xorHelper);
  } else if (_ember['default'].HTMLBars.makeBoundHelper) {
    forExport = _ember['default'].HTMLBars.makeBoundHelper(_emberTruthHelpersHelpersXor.xorHelper);
  }

  exports['default'] = forExport;
});
define('restaurants-app/initializers/app-version', ['exports', 'ember-cli-app-version/initializer-factory', 'restaurants-app/config/environment'], function (exports, _emberCliAppVersionInitializerFactory, _restaurantsAppConfigEnvironment) {
  var _config$APP = _restaurantsAppConfigEnvironment['default'].APP;
  var name = _config$APP.name;
  var version = _config$APP.version;
  exports['default'] = {
    name: 'App Version',
    initialize: (0, _emberCliAppVersionInitializerFactory['default'])(name, version)
  };
});
define('restaurants-app/initializers/container-debug-adapter', ['exports', 'ember-resolver/container-debug-adapter'], function (exports, _emberResolverContainerDebugAdapter) {
  exports['default'] = {
    name: 'container-debug-adapter',

    initialize: function initialize() {
      var app = arguments[1] || arguments[0];

      app.register('container-debug-adapter:main', _emberResolverContainerDebugAdapter['default']);
      app.inject('container-debug-adapter:main', 'namespace', 'application:main');
    }
  };
});
define('restaurants-app/initializers/data-adapter', ['exports', 'ember'], function (exports, _ember) {

  /*
    This initializer is here to keep backwards compatibility with code depending
    on the `data-adapter` initializer (before Ember Data was an addon).
  
    Should be removed for Ember Data 3.x
  */

  exports['default'] = {
    name: 'data-adapter',
    before: 'store',
    initialize: function initialize() {}
  };
});
define('restaurants-app/initializers/ember-data', ['exports', 'ember-data/setup-container', 'ember-data/-private/core'], function (exports, _emberDataSetupContainer, _emberDataPrivateCore) {

  /*
  
    This code initializes Ember-Data onto an Ember application.
  
    If an Ember.js developer defines a subclass of DS.Store on their application,
    as `App.StoreService` (or via a module system that resolves to `service:store`)
    this code will automatically instantiate it and make it available on the
    router.
  
    Additionally, after an application's controllers have been injected, they will
    each have the store made available to them.
  
    For example, imagine an Ember.js application with the following classes:
  
    App.StoreService = DS.Store.extend({
      adapter: 'custom'
    });
  
    App.PostsController = Ember.Controller.extend({
      // ...
    });
  
    When the application is initialized, `App.ApplicationStore` will automatically be
    instantiated, and the instance of `App.PostsController` will have its `store`
    property set to that instance.
  
    Note that this code will only be run if the `ember-application` package is
    loaded. If Ember Data is being used in an environment other than a
    typical application (e.g., node.js where only `ember-runtime` is available),
    this code will be ignored.
  */

  exports['default'] = {
    name: 'ember-data',
    initialize: _emberDataSetupContainer['default']
  };
});
define('restaurants-app/initializers/ember-simple-auth', ['exports', 'ember', 'restaurants-app/config/environment', 'ember-simple-auth/configuration', 'ember-simple-auth/initializers/setup-session', 'ember-simple-auth/initializers/setup-session-service'], function (exports, _ember, _restaurantsAppConfigEnvironment, _emberSimpleAuthConfiguration, _emberSimpleAuthInitializersSetupSession, _emberSimpleAuthInitializersSetupSessionService) {
  exports['default'] = {
    name: 'ember-simple-auth',
    initialize: function initialize(registry) {
      var config = _restaurantsAppConfigEnvironment['default']['ember-simple-auth'] || {};
      config.baseURL = _restaurantsAppConfigEnvironment['default'].rootURL || _restaurantsAppConfigEnvironment['default'].baseURL;
      _emberSimpleAuthConfiguration['default'].load(config);

      (0, _emberSimpleAuthInitializersSetupSession['default'])(registry);
      (0, _emberSimpleAuthInitializersSetupSessionService['default'])(registry);
    }
  };
});
define('restaurants-app/initializers/export-application-global', ['exports', 'ember', 'restaurants-app/config/environment'], function (exports, _ember, _restaurantsAppConfigEnvironment) {
  exports.initialize = initialize;

  function initialize() {
    var application = arguments[1] || arguments[0];
    if (_restaurantsAppConfigEnvironment['default'].exportApplicationGlobal !== false) {
      var theGlobal;
      if (typeof window !== 'undefined') {
        theGlobal = window;
      } else if (typeof global !== 'undefined') {
        theGlobal = global;
      } else if (typeof self !== 'undefined') {
        theGlobal = self;
      } else {
        // no reasonable global, just bail
        return;
      }

      var value = _restaurantsAppConfigEnvironment['default'].exportApplicationGlobal;
      var globalName;

      if (typeof value === 'string') {
        globalName = value;
      } else {
        globalName = _ember['default'].String.classify(_restaurantsAppConfigEnvironment['default'].modulePrefix);
      }

      if (!theGlobal[globalName]) {
        theGlobal[globalName] = application;

        application.reopen({
          willDestroy: function willDestroy() {
            this._super.apply(this, arguments);
            delete theGlobal[globalName];
          }
        });
      }
    }
  }

  exports['default'] = {
    name: 'export-application-global',

    initialize: initialize
  };
});
define('restaurants-app/initializers/flash-messages', ['exports', 'ember', 'restaurants-app/config/environment'], function (exports, _ember, _restaurantsAppConfigEnvironment) {
  exports.initialize = initialize;
  var deprecate = _ember['default'].deprecate;

  var merge = _ember['default'].assign || _ember['default'].merge;
  var INJECTION_FACTORIES_DEPRECATION_MESSAGE = '[ember-cli-flash] Future versions of ember-cli-flash will no longer inject the service automatically. Instead, you should explicitly inject it into your Route, Controller or Component with `Ember.inject.service`.';
  var addonDefaults = {
    timeout: 3000,
    extendedTimeout: 0,
    priority: 100,
    sticky: false,
    showProgress: false,
    type: 'info',
    types: ['success', 'info', 'warning', 'danger', 'alert', 'secondary'],
    injectionFactories: ['route', 'controller', 'view', 'component'],
    preventDuplicates: false
  };

  function initialize() {
    var application = arguments[1] || arguments[0];

    var _ref = _restaurantsAppConfigEnvironment['default'] || {};

    var flashMessageDefaults = _ref.flashMessageDefaults;

    var _ref2 = flashMessageDefaults || [];

    var injectionFactories = _ref2.injectionFactories;

    var options = merge(addonDefaults, flashMessageDefaults);
    var shouldShowDeprecation = !(injectionFactories && injectionFactories.length);

    application.register('config:flash-messages', options, { instantiate: false });
    application.inject('service:flash-messages', 'flashMessageDefaults', 'config:flash-messages');

    deprecate(INJECTION_FACTORIES_DEPRECATION_MESSAGE, shouldShowDeprecation, {
      id: 'ember-cli-flash.deprecate-injection-factories',
      until: '2.0.0'
    });

    options.injectionFactories.forEach(function (factory) {
      application.inject(factory, 'flashMessages', 'service:flash-messages');
    });
  }

  exports['default'] = {
    name: 'flash-messages',
    initialize: initialize
  };
});
define('restaurants-app/initializers/injectStore', ['exports', 'ember'], function (exports, _ember) {

  /*
    This initializer is here to keep backwards compatibility with code depending
    on the `injectStore` initializer (before Ember Data was an addon).
  
    Should be removed for Ember Data 3.x
  */

  exports['default'] = {
    name: 'injectStore',
    before: 'store',
    initialize: function initialize() {}
  };
});
define('restaurants-app/initializers/simple-auth-token', ['exports', 'ember-simple-auth-token/authenticators/token', 'ember-simple-auth-token/authenticators/jwt', 'ember-simple-auth-token/authorizers/token', 'ember-simple-auth-token/configuration', 'restaurants-app/config/environment'], function (exports, _emberSimpleAuthTokenAuthenticatorsToken, _emberSimpleAuthTokenAuthenticatorsJwt, _emberSimpleAuthTokenAuthorizersToken, _emberSimpleAuthTokenConfiguration, _restaurantsAppConfigEnvironment) {

  /**
    Ember Simple Auth Token's Initializer.
    By default load both the Token and JWT (with refresh) Authenticators.
  */
  exports['default'] = {
    name: 'ember-simple-auth-token',
    before: 'ember-simple-auth',
    initialize: function initialize(container) {
      _emberSimpleAuthTokenConfiguration['default'].load(container, _restaurantsAppConfigEnvironment['default']['ember-simple-auth-token'] || {});
      container.register('authorizer:token', _emberSimpleAuthTokenAuthorizersToken['default']);
      container.register('authenticator:token', _emberSimpleAuthTokenAuthenticatorsToken['default']);
      container.register('authenticator:jwt', _emberSimpleAuthTokenAuthenticatorsJwt['default']);
    }
  };
});
define('restaurants-app/initializers/store', ['exports', 'ember'], function (exports, _ember) {

  /*
    This initializer is here to keep backwards compatibility with code depending
    on the `store` initializer (before Ember Data was an addon).
  
    Should be removed for Ember Data 3.x
  */

  exports['default'] = {
    name: 'store',
    after: 'ember-data',
    initialize: function initialize() {}
  };
});
define('restaurants-app/initializers/transforms', ['exports', 'ember'], function (exports, _ember) {

  /*
    This initializer is here to keep backwards compatibility with code depending
    on the `transforms` initializer (before Ember Data was an addon).
  
    Should be removed for Ember Data 3.x
  */

  exports['default'] = {
    name: 'transforms',
    before: 'store',
    initialize: function initialize() {}
  };
});
define('restaurants-app/initializers/truth-helpers', ['exports', 'ember', 'ember-truth-helpers/utils/register-helper', 'ember-truth-helpers/helpers/and', 'ember-truth-helpers/helpers/or', 'ember-truth-helpers/helpers/equal', 'ember-truth-helpers/helpers/not', 'ember-truth-helpers/helpers/is-array', 'ember-truth-helpers/helpers/not-equal', 'ember-truth-helpers/helpers/gt', 'ember-truth-helpers/helpers/gte', 'ember-truth-helpers/helpers/lt', 'ember-truth-helpers/helpers/lte'], function (exports, _ember, _emberTruthHelpersUtilsRegisterHelper, _emberTruthHelpersHelpersAnd, _emberTruthHelpersHelpersOr, _emberTruthHelpersHelpersEqual, _emberTruthHelpersHelpersNot, _emberTruthHelpersHelpersIsArray, _emberTruthHelpersHelpersNotEqual, _emberTruthHelpersHelpersGt, _emberTruthHelpersHelpersGte, _emberTruthHelpersHelpersLt, _emberTruthHelpersHelpersLte) {
  exports.initialize = initialize;

  function initialize() /* container, application */{

    // Do not register helpers from Ember 1.13 onwards, starting from 1.13 they
    // will be auto-discovered.
    if (_ember['default'].Helper) {
      return;
    }

    (0, _emberTruthHelpersUtilsRegisterHelper.registerHelper)('and', _emberTruthHelpersHelpersAnd.andHelper);
    (0, _emberTruthHelpersUtilsRegisterHelper.registerHelper)('or', _emberTruthHelpersHelpersOr.orHelper);
    (0, _emberTruthHelpersUtilsRegisterHelper.registerHelper)('eq', _emberTruthHelpersHelpersEqual.equalHelper);
    (0, _emberTruthHelpersUtilsRegisterHelper.registerHelper)('not', _emberTruthHelpersHelpersNot.notHelper);
    (0, _emberTruthHelpersUtilsRegisterHelper.registerHelper)('is-array', _emberTruthHelpersHelpersIsArray.isArrayHelper);
    (0, _emberTruthHelpersUtilsRegisterHelper.registerHelper)('not-eq', _emberTruthHelpersHelpersNotEqual.notEqualHelper);
    (0, _emberTruthHelpersUtilsRegisterHelper.registerHelper)('gt', _emberTruthHelpersHelpersGt.gtHelper);
    (0, _emberTruthHelpersUtilsRegisterHelper.registerHelper)('gte', _emberTruthHelpersHelpersGte.gteHelper);
    (0, _emberTruthHelpersUtilsRegisterHelper.registerHelper)('lt', _emberTruthHelpersHelpersLt.ltHelper);
    (0, _emberTruthHelpersUtilsRegisterHelper.registerHelper)('lte', _emberTruthHelpersHelpersLte.lteHelper);
  }

  exports['default'] = {
    name: 'truth-helpers',
    initialize: initialize
  };
});
define("restaurants-app/instance-initializers/ember-data", ["exports", "ember-data/-private/instance-initializers/initialize-store-service"], function (exports, _emberDataPrivateInstanceInitializersInitializeStoreService) {
  exports["default"] = {
    name: "ember-data",
    initialize: _emberDataPrivateInstanceInitializersInitializeStoreService["default"]
  };
});
define('restaurants-app/instance-initializers/ember-simple-auth', ['exports', 'ember-simple-auth/instance-initializers/setup-session-restoration'], function (exports, _emberSimpleAuthInstanceInitializersSetupSessionRestoration) {
  exports['default'] = {
    name: 'ember-simple-auth',
    initialize: function initialize(instance) {
      (0, _emberSimpleAuthInstanceInitializersSetupSessionRestoration['default'])(instance);
    }
  };
});
define('restaurants-app/instance-initializers/session-injection', ['exports', 'ember'], function (exports, _ember) {
  exports['default'] = {
    name: "session-injection",
    after: 'ember-simple-auth',

    initialize: function initialize(appInstance) {
      appInstance.inject('route', 'session', 'service:session');
      appInstance.inject('controller', 'session', 'service:session');
      appInstance.inject('component', 'session', 'service:session');
    }
  };
});
define('restaurants-app/models/restaurant', ['exports', 'ember-data'], function (exports, _emberData) {
	exports['default'] = _emberData['default'].Model.extend({
		name: _emberData['default'].attr(),
		description: _emberData['default'].attr(),
		reviewRating: _emberData['default'].attr(),
		reviewCount: _emberData['default'].attr(),
		logoImageUrl: _emberData['default'].attr(),
		coverImageUrl: _emberData['default'].attr(),
		pricing: _emberData['default'].attr(),
		latLong: _emberData['default'].attr(),
		categories: _emberData['default'].attr(),
		menus: _emberData['default'].attr(),
		latLong: _emberData['default'].attr(),

		menuItems: Ember.computed('menus', function () {
			return this.get('menus')[0]["menuItems"];
		}),

		activeStars: Ember.computed('reviewRating', function () {
			return new Array(Math.round(this.get('reviewRating')));
		}),

		inactiveStars: Ember.computed('reviewRating', function () {
			if (5 - Math.round(this.get('reviewRating')) != 0) return new Array(5 - Math.round(this.get('reviewRating')));

			return [];
		}),

		activeDollars: Ember.computed('pricing', function () {
			return new Array(this.get('pricing'));
		}),

		inactiveDollars: Ember.computed('pricing', function () {
			return new Array(4 - this.get('pricing'));
		}),

		lat: Ember.computed('latLong', function () {
			return this.get('latLong').coordinates[0];
		}),

		long: Ember.computed('latLong', function () {
			return this.get('latLong').coordinates[1];
		})
	});
});
define('restaurants-app/resolver', ['exports', 'ember-resolver'], function (exports, _emberResolver) {
  exports['default'] = _emberResolver['default'];
});
define('restaurants-app/router', ['exports', 'ember', 'restaurants-app/config/environment'], function (exports, _ember, _restaurantsAppConfigEnvironment) {

  var Router = _ember['default'].Router.extend({
    location: _restaurantsAppConfigEnvironment['default'].locationType,
    rootURL: _restaurantsAppConfigEnvironment['default'].rootURL
  });

  Router.map(function () {
    this.route('restaurants', function () {
      this.route('restaurant', { path: ':id' });
    });
    this.route('about');
    this.route('privacy-policy');
    this.route('terms-of-use');
    this.route('login');
    this.route('logout');
    this.route('admin');
    this.route('register');
  });

  exports['default'] = Router;
});
define('restaurants-app/routes/about', ['exports', 'ember'], function (exports, _ember) {
  exports['default'] = _ember['default'].Route.extend({});
});
define('restaurants-app/routes/admin', ['exports', 'ember'], function (exports, _ember) {
  exports['default'] = _ember['default'].Route.extend({});
});
define('restaurants-app/routes/application', ['exports', 'ember'], function (exports, _ember) {

  // Ensure the application route exists for ember-simple-auth's `setup-session-restoration` initializer
  exports['default'] = _ember['default'].Route.extend();
});
define('restaurants-app/routes/index', ['exports', 'ember', 'restaurants-app/config/environment'], function (exports, _ember, _restaurantsAppConfigEnvironment) {
	exports['default'] = _ember['default'].Route.extend({
		model: function model() {
			var store = this.store;
			return _ember['default'].RSVP.hash({
				restaurants: store.query('restaurant', { limit: 6 }),
				locations: _ember['default'].$.getJSON(_restaurantsAppConfigEnvironment['default'].apiHost + '/api/v1/cities/with-count')
			});
		},

		setupController: function setupController(controller, models) {
			controller.set('restaurants', models.restaurants);
			controller.set('locations', models.locations);
		},

		activate: function activate() {
			_ember['default'].$('body').toggleClass("grey-back");
		},

		deactivate: function deactivate() {
			_ember['default'].$('body').toggleClass("grey-back");
		}
	});
});
define('restaurants-app/routes/login', ['exports', 'ember'], function (exports, _ember) {
  exports['default'] = _ember['default'].Route.extend({});
});
define('restaurants-app/routes/logout', ['exports', 'ember'], function (exports, _ember) {
	exports['default'] = _ember['default'].Route.extend({
		redirect: function redirect() {
			console.log("invalidiraj");
			this.get('session').invalidate();
			this.transitionTo('index');
		}
	});
});
define('restaurants-app/routes/privacy-policy', ['exports', 'ember'], function (exports, _ember) {
  exports['default'] = _ember['default'].Route.extend({});
});
define('restaurants-app/routes/register', ['exports', 'ember'], function (exports, _ember) {
  exports['default'] = _ember['default'].Route.extend({});
});
define('restaurants-app/routes/restaurants', ['exports', 'ember'], function (exports, _ember) {
  exports['default'] = _ember['default'].Route.extend({});
});
define('restaurants-app/routes/restaurants/restaurant', ['exports', 'ember'], function (exports, _ember) {
	exports['default'] = _ember['default'].Route.extend({
		model: function model(params, transition) {
			return this.store.find('restaurant', params.id);
		}
	});
});
define('restaurants-app/routes/terms-of-use', ['exports', 'ember'], function (exports, _ember) {
  exports['default'] = _ember['default'].Route.extend({});
});
define('restaurants-app/serializers/application', ['exports', 'ember-data'], function (exports, _emberData) {
	exports['default'] = _emberData['default'].JSONSerializer.extend({});
});
define('restaurants-app/services/ajax', ['exports', 'ember-ajax/services/ajax'], function (exports, _emberAjaxServicesAjax) {
  Object.defineProperty(exports, 'default', {
    enumerable: true,
    get: function get() {
      return _emberAjaxServicesAjax['default'];
    }
  });
});
define('restaurants-app/services/cookies', ['exports', 'ember-cookies/services/cookies'], function (exports, _emberCookiesServicesCookies) {
  exports['default'] = _emberCookiesServicesCookies['default'];
});
define('restaurants-app/services/current-user', ['exports', 'ember'], function (exports, _ember) {
	exports['default'] = _ember['default'].Service.extend({
		session: _ember['default'].inject.service(),

		role: _ember['default'].computed(function () {
			return this.get('session.data.authenticated.role');
		})
	});
});
define('restaurants-app/services/flash-messages', ['exports', 'ember-cli-flash/services/flash-messages'], function (exports, _emberCliFlashServicesFlashMessages) {
  Object.defineProperty(exports, 'default', {
    enumerable: true,
    get: function get() {
      return _emberCliFlashServicesFlashMessages['default'];
    }
  });
});
define('restaurants-app/services/scroller', ['exports', 'ember-scroll-to/services/scroller'], function (exports, _emberScrollToServicesScroller) {
  Object.defineProperty(exports, 'default', {
    enumerable: true,
    get: function get() {
      return _emberScrollToServicesScroller['default'];
    }
  });
});
define('restaurants-app/services/session', ['exports', 'ember-simple-auth/services/session'], function (exports, _emberSimpleAuthServicesSession) {
  exports['default'] = _emberSimpleAuthServicesSession['default'];
});
define('restaurants-app/session-stores/application', ['exports', 'ember-simple-auth/session-stores/adaptive'], function (exports, _emberSimpleAuthSessionStoresAdaptive) {
  exports['default'] = _emberSimpleAuthSessionStoresAdaptive['default'].extend();
});
define("restaurants-app/templates/about", ["exports"], function (exports) {
  exports["default"] = Ember.HTMLBars.template({ "id": "vSkmXRCh", "block": "{\"statements\":[[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"container\"],[\"static-attr\",\"id\",\"normal-container\"],[\"flush-element\"],[\"text\",\"\\n   \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"row\"],[\"flush-element\"],[\"text\",\"\\n      \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"col-md-8 col-sm-8 col-xs-12 col-centered\"],[\"flush-element\"],[\"text\",\"\\n          \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"row\"],[\"flush-element\"],[\"text\",\"\\n            \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"col-md-12 col-sm-12 col-xs-12 page-title v-align-center\"],[\"flush-element\"],[\"text\",\"\\n                About Restaurants\\n            \"],[\"close-element\"],[\"text\",\"\\n          \"],[\"close-element\"],[\"text\",\"\\n          \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"row\"],[\"flush-element\"],[\"text\",\"\\n              \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"col-md-12\"],[\"flush-element\"],[\"text\",\"\\n                  \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"card padded\"],[\"flush-element\"],[\"text\",\"\\n                      \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"content\"],[\"flush-element\"],[\"text\",\"\\n                              \"],[\"open-element\",\"p\",[]],[\"static-attr\",\"class\",\"regular-text\"],[\"flush-element\"],[\"text\",\"\\n                                  Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\\n                                  \"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\n                                  Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur.\\n                              \"],[\"close-element\"],[\"text\",\"\\n                      \"],[\"close-element\"],[\"text\",\"\\n                  \"],[\"close-element\"],[\"text\",\"\\n              \"],[\"close-element\"],[\"text\",\"\\n          \"],[\"close-element\"],[\"text\",\"\\n       \"],[\"close-element\"],[\"text\",\"\\n       \\n   \"],[\"close-element\"],[\"text\",\"\\n   \\n\"],[\"close-element\"]],\"locals\":[],\"named\":[],\"yields\":[],\"blocks\":[],\"hasPartials\":false}", "meta": { "moduleName": "restaurants-app/templates/about.hbs" } });
});
define("restaurants-app/templates/admin", ["exports"], function (exports) {
  exports["default"] = Ember.HTMLBars.template({ "id": "v0F/yd/K", "block": "{\"statements\":[[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"container\"],[\"static-attr\",\"id\",\"normal-container\"],[\"flush-element\"],[\"text\",\"\\n   \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"row\"],[\"flush-element\"],[\"text\",\"\\n      \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"col-md-12\"],[\"flush-element\"],[\"text\",\"\\n          \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"row\"],[\"flush-element\"],[\"text\",\"\\n            \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"col-md-12 page-title v-align-center\"],[\"flush-element\"],[\"text\",\"\\n                Admin\\n            \"],[\"close-element\"],[\"text\",\"\\n          \"],[\"close-element\"],[\"text\",\"\\n          \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"row\"],[\"flush-element\"],[\"text\",\"\\n\\t\\t\\t\"],[\"append\",[\"unknown\",[\"outlet\"]],false],[\"text\",\"\\n\\t\\t  \"],[\"close-element\"],[\"text\",\"\\n\\t   \"],[\"close-element\"],[\"text\",\"\\n\\t\"],[\"close-element\"],[\"text\",\"\\n\"],[\"close-element\"],[\"text\",\"\\n\\n\"]],\"locals\":[],\"named\":[],\"yields\":[],\"blocks\":[],\"hasPartials\":false}", "meta": { "moduleName": "restaurants-app/templates/admin.hbs" } });
});
define("restaurants-app/templates/application", ["exports"], function (exports) {
  exports["default"] = Ember.HTMLBars.template({ "id": "DfCr61az", "block": "{\"statements\":[[\"partial\",\"navbar\"],[\"text\",\"\\n\\n\"],[\"append\",[\"unknown\",[\"outlet\"]],false],[\"text\",\"\\n\\n\"],[\"partial\",\"footer\"]],\"locals\":[],\"named\":[],\"yields\":[],\"blocks\":[],\"hasPartials\":true}", "meta": { "moduleName": "restaurants-app/templates/application.hbs" } });
});
define("restaurants-app/templates/components/restaurant-listing", ["exports"], function (exports) {
  exports["default"] = Ember.HTMLBars.template({ "id": "PYAU+d4t", "block": "{\"statements\":[[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"restaurant-listing\"],[\"flush-element\"],[\"text\",\"\\n\"],[\"block\",[\"link-to\"],[\"restaurants.restaurant\",[\"get\",[\"item\",\"id\"]]],[[\"class\"],[\"img-responsive\"]],6],[\"text\",\"\\n\\t\"],[\"open-element\",\"h3\",[]],[\"flush-element\"],[\"block\",[\"link-to\"],[\"restaurants.restaurant\",[\"get\",[\"item\",\"id\"]]],null,5],[\"text\",\" \"],[\"close-element\"],[\"text\",\"\\n\\n\\t\"],[\"open-element\",\"p\",[]],[\"static-attr\",\"class\",\"rating\"],[\"flush-element\"],[\"text\",\"\\n\"],[\"block\",[\"each\"],[[\"get\",[\"item\",\"activeStars\"]]],null,4],[\"block\",[\"each\"],[[\"get\",[\"item\",\"inactiveStars\"]]],null,3],[\"text\",\"\\n\\t    \"],[\"open-element\",\"span\",[]],[\"static-attr\",\"class\",\"count\"],[\"flush-element\"],[\"text\",\"(\"],[\"append\",[\"unknown\",[\"item\",\"reviewCount\"]],false],[\"text\",\")\"],[\"close-element\"],[\"text\",\"\\n\\t\\t\\n\\t    \"],[\"open-element\",\"span\",[]],[\"static-attr\",\"class\",\"dollars\"],[\"flush-element\"],[\"text\",\"\\n\"],[\"block\",[\"each\"],[[\"get\",[\"item\",\"activeDollars\"]]],null,2],[\"block\",[\"each\"],[[\"get\",[\"item\",\"inactiveDollars\"]]],null,1],[\"text\",\"\\n\\t    \"],[\"close-element\"],[\"text\",\"\\n\\t\"],[\"close-element\"],[\"text\",\"\\n\\n\\t\"],[\"open-element\",\"p\",[]],[\"static-attr\",\"class\",\"tags\"],[\"flush-element\"],[\"text\",\"\\n\\t    \"],[\"open-element\",\"a\",[]],[\"static-attr\",\"href\",\"#\"],[\"flush-element\"],[\"text\",\"Italian\"],[\"close-element\"],[\"text\",\" |\\n\\t    \"],[\"open-element\",\"a\",[]],[\"static-attr\",\"href\",\"#\"],[\"flush-element\"],[\"text\",\"International\"],[\"close-element\"],[\"text\",\" |\\n\\t    \"],[\"open-element\",\"a\",[]],[\"static-attr\",\"href\",\"#\"],[\"flush-element\"],[\"text\",\"Mediterranean\"],[\"close-element\"],[\"text\",\"\\n\\t\"],[\"close-element\"],[\"text\",\"\\n\\n\\t\"],[\"block\",[\"link-to\"],[\"restaurants.restaurant\",[\"get\",[\"item\",\"id\"]]],[[\"class\"],[\"btn btn-default\"]],0],[\"text\",\"\\n\"],[\"close-element\"]],\"locals\":[],\"named\":[],\"yields\":[],\"blocks\":[{\"statements\":[[\"text\",\"Reserve Now\"]],\"locals\":[]},{\"statements\":[[\"text\",\"\\t\\t    $\\n\"]],\"locals\":[\"star\"]},{\"statements\":[[\"text\",\"\\t  \\t\\t\"],[\"open-element\",\"b\",[]],[\"flush-element\"],[\"text\",\"$\"],[\"close-element\"],[\"text\",\"\\n\"]],\"locals\":[\"star\"]},{\"statements\":[[\"text\",\"\\t    \\t\"],[\"open-element\",\"i\",[]],[\"static-attr\",\"class\",\"fa fa-star inactive\"],[\"static-attr\",\"aria-hidden\",\"true\"],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\n\"]],\"locals\":[\"star\"]},{\"statements\":[[\"text\",\"\\t  \\t\\t\"],[\"open-element\",\"i\",[]],[\"static-attr\",\"class\",\"fa fa-star\"],[\"static-attr\",\"aria-hidden\",\"true\"],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\n\"]],\"locals\":[\"star\"]},{\"statements\":[[\"text\",\" \"],[\"append\",[\"unknown\",[\"item\",\"name\"]],false],[\"text\",\" \"]],\"locals\":[]},{\"statements\":[[\"text\",\"\\t\\t\"],[\"open-element\",\"img\",[]],[\"static-attr\",\"class\",\"img-responsive\"],[\"dynamic-attr\",\"src\",[\"concat\",[\"/assets/images/restaurants/logos/\",[\"unknown\",[\"item\",\"logoImageUrl\"]]]]],[\"dynamic-attr\",\"alt\",[\"concat\",[[\"unknown\",[\"item\",\"name\"]]]]],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\n\"]],\"locals\":[]}],\"hasPartials\":false}", "meta": { "moduleName": "restaurants-app/templates/components/restaurant-listing.hbs" } });
});
define("restaurants-app/templates/components/scroll-to", ["exports"], function (exports) {
  exports["default"] = Ember.HTMLBars.template({ "id": "2Faroy4X", "block": "{\"statements\":[[\"block\",[\"if\"],[[\"has-block\",\"default\"]],null,1,0]],\"locals\":[],\"named\":[],\"yields\":[\"default\"],\"blocks\":[{\"statements\":[[\"text\",\"  \"],[\"append\",[\"unknown\",[\"label\"]],false],[\"text\",\"\\n\"]],\"locals\":[]},{\"statements\":[[\"text\",\"  \"],[\"yield\",\"default\"],[\"text\",\"\\n\"]],\"locals\":[]}],\"hasPartials\":false}", "meta": { "moduleName": "restaurants-app/templates/components/scroll-to.hbs" } });
});
define("restaurants-app/templates/footer", ["exports"], function (exports) {
  exports["default"] = Ember.HTMLBars.template({ "id": "LfVdcO1l", "block": "{\"statements\":[[\"open-element\",\"div\",[]],[\"static-attr\",\"id\",\"footer\"],[\"flush-element\"],[\"text\",\"\\n\\t\"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"container\"],[\"flush-element\"],[\"text\",\"\\n\\t    \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"row\"],[\"flush-element\"],[\"text\",\"\\n\\t        \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"col-md-6 col-sm-6 col-xs-12\"],[\"flush-element\"],[\"text\",\"\\n\\t            \"],[\"open-element\",\"ul\",[]],[\"flush-element\"],[\"text\",\"       \\n\\t            \\t\"],[\"block\",[\"nav-link-to\"],[\"privacy-policy\"],null,2],[\"text\",\"\\n\\t            \\t\"],[\"block\",[\"nav-link-to\"],[\"terms-of-use\"],null,1],[\"text\",\"\\t    \\n\\t                \"],[\"open-element\",\"li\",[]],[\"flush-element\"],[\"open-element\",\"a\",[]],[\"static-attr\",\"href\",\"#\"],[\"flush-element\"],[\"text\",\"Sitemap\"],[\"close-element\"],[\"close-element\"],[\"text\",\"\\n\\t                \"],[\"open-element\",\"li\",[]],[\"flush-element\"],[\"open-element\",\"a\",[]],[\"static-attr\",\"href\",\"#\"],[\"flush-element\"],[\"text\",\"Mobile Site\"],[\"close-element\"],[\"close-element\"],[\"text\",\"\\n\\t            \"],[\"close-element\"],[\"text\",\"\\n\\t        \"],[\"close-element\"],[\"text\",\"\\n\\t        \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"col md-6 col-sm-6 col-xs-12\"],[\"flush-element\"],[\"text\",\"\\n\\t            \"],[\"open-element\",\"ul\",[]],[\"static-attr\",\"class\",\"white\"],[\"flush-element\"],[\"text\",\"\\n                \\t\"],[\"block\",[\"nav-link-to\"],[\"about\"],null,0],[\"text\",\"\\n\\t                \"],[\"open-element\",\"li\",[]],[\"flush-element\"],[\"open-element\",\"a\",[]],[\"static-attr\",\"href\",\"#\"],[\"flush-element\"],[\"text\",\"Blog\"],[\"close-element\"],[\"close-element\"],[\"text\",\"\\n\\t                \"],[\"open-element\",\"li\",[]],[\"flush-element\"],[\"open-element\",\"a\",[]],[\"static-attr\",\"href\",\"#\"],[\"flush-element\"],[\"text\",\"Careers\"],[\"close-element\"],[\"close-element\"],[\"text\",\"\\n\\t                \"],[\"open-element\",\"li\",[]],[\"flush-element\"],[\"open-element\",\"a\",[]],[\"static-attr\",\"href\",\"#\"],[\"flush-element\"],[\"text\",\"Press\"],[\"close-element\"],[\"close-element\"],[\"text\",\"\\n\\t                \"],[\"open-element\",\"li\",[]],[\"flush-element\"],[\"open-element\",\"a\",[]],[\"static-attr\",\"href\",\"#\"],[\"flush-element\"],[\"text\",\"Advertise\"],[\"close-element\"],[\"close-element\"],[\"text\",\"\\n\\t            \"],[\"close-element\"],[\"text\",\"\\n\\t        \"],[\"close-element\"],[\"text\",\"\\n\\t    \"],[\"close-element\"],[\"text\",\"\\n\\t    \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"row\"],[\"flush-element\"],[\"text\",\"\\n\\t        \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"col-md-12\"],[\"flush-element\"],[\"text\",\"\\n\\t            \"],[\"open-element\",\"p\",[]],[\"flush-element\"],[\"text\",\"Copyright © 2016 All rights reserved.\"],[\"close-element\"],[\"text\",\"\\n\\t        \"],[\"close-element\"],[\"text\",\"\\n\\t    \"],[\"close-element\"],[\"text\",\"\\n\\t\"],[\"close-element\"],[\"text\",\"\\n\"],[\"close-element\"]],\"locals\":[],\"named\":[],\"yields\":[],\"blocks\":[{\"statements\":[[\"text\",\"About\"]],\"locals\":[]},{\"statements\":[[\"text\",\"Terms of use\"]],\"locals\":[]},{\"statements\":[[\"text\",\"Privacy Policy\"]],\"locals\":[]}],\"hasPartials\":false}", "meta": { "moduleName": "restaurants-app/templates/footer.hbs" } });
});
define("restaurants-app/templates/index", ["exports"], function (exports) {
  exports["default"] = Ember.HTMLBars.template({ "id": "aVaqbTuw", "block": "{\"statements\":[[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"jumbotron vertical-align index\"],[\"flush-element\"],[\"text\",\"\\n    \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"container-fluid\"],[\"flush-element\"],[\"text\",\"\\n        \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"row-fluid\"],[\"flush-element\"],[\"text\",\"\\n            \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"centering text-center\"],[\"flush-element\"],[\"text\",\"\\n                \"],[\"open-element\",\"h1\",[]],[\"flush-element\"],[\"text\",\"Make a free reservation\"],[\"close-element\"],[\"text\",\"\\n                \"],[\"open-element\",\"p\",[]],[\"flush-element\"],[\"text\",\"Choose your table from 757 restaurants near you\"],[\"close-element\"],[\"text\",\"\\n                \"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\n                \"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\n\\n                \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"container\"],[\"flush-element\"],[\"text\",\"\\n                    \"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\n                    \"],[\"open-element\",\"form\",[]],[\"static-attr\",\"role\",\"search\"],[\"static-attr\",\"class\",\"filter-box landing\"],[\"flush-element\"],[\"text\",\"\\n                        \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"input-group input-group-lg\"],[\"flush-element\"],[\"text\",\"\\n                            \"],[\"open-element\",\"span\",[]],[\"static-attr\",\"class\",\"input-group-addon\"],[\"flush-element\"],[\"text\",\"\\n                                \"],[\"open-element\",\"i\",[]],[\"static-attr\",\"class\",\"fa fa-search\"],[\"static-attr\",\"aria-hidden\",\"true\"],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\n                            \"],[\"close-element\"],[\"text\",\"\\n                            \\n                            \"],[\"open-element\",\"input\",[]],[\"static-attr\",\"type\",\"text\"],[\"static-attr\",\"name\",\"query\"],[\"static-attr\",\"class\",\"form-control\"],[\"static-attr\",\"placeholder\",\"Location, Restaurant or Cousine\"],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\n                            \\n                            \"],[\"open-element\",\"span\",[]],[\"static-attr\",\"class\",\"input-group-btn\"],[\"flush-element\"],[\"text\",\"                                \\n                                \"],[\"open-element\",\"button\",[]],[\"static-attr\",\"type\",\"button\"],[\"static-attr\",\"class\",\"btn btn-primary dropdown-toggle\"],[\"static-attr\",\"data-toggle\",\"dropdown\"],[\"flush-element\"],[\"text\",\"\\n                                   2 people \"],[\"open-element\",\"i\",[]],[\"static-attr\",\"class\",\"fa fa-chevron-down\"],[\"static-attr\",\"aria-hidden\",\"true\"],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\n                                \"],[\"close-element\"],[\"text\",\"\\n                                \"],[\"open-element\",\"ul\",[]],[\"static-attr\",\"class\",\"dropdown-menu\"],[\"flush-element\"],[\"text\",\"\\n                                    \"],[\"open-element\",\"li\",[]],[\"flush-element\"],[\"open-element\",\"a\",[]],[\"static-attr\",\"href\",\"#\"],[\"flush-element\"],[\"text\",\"2 People\"],[\"close-element\"],[\"close-element\"],[\"text\",\"\\n                                    \"],[\"open-element\",\"li\",[]],[\"flush-element\"],[\"open-element\",\"a\",[]],[\"static-attr\",\"href\",\"#\"],[\"flush-element\"],[\"text\",\"3 People\"],[\"close-element\"],[\"close-element\"],[\"text\",\"\\n                                    \"],[\"open-element\",\"li\",[]],[\"flush-element\"],[\"open-element\",\"a\",[]],[\"static-attr\",\"href\",\"#\"],[\"flush-element\"],[\"text\",\"4 People\"],[\"close-element\"],[\"close-element\"],[\"text\",\"\\n                                    \"],[\"open-element\",\"li\",[]],[\"flush-element\"],[\"open-element\",\"a\",[]],[\"static-attr\",\"href\",\"#\"],[\"flush-element\"],[\"text\",\"5 People\"],[\"close-element\"],[\"close-element\"],[\"text\",\"\\n                                \"],[\"close-element\"],[\"text\",\"\\n                            \"],[\"close-element\"],[\"text\",\"\\n                            \\n                            \"],[\"open-element\",\"span\",[]],[\"static-attr\",\"class\",\"input-group-btn\"],[\"flush-element\"],[\"text\",\"                                \\n                                \"],[\"open-element\",\"button\",[]],[\"static-attr\",\"type\",\"button\"],[\"static-attr\",\"class\",\"btn btn-primary dropdown-toggle\"],[\"static-attr\",\"data-toggle\",\"dropdown\"],[\"flush-element\"],[\"text\",\"Apr 29, 2016  \"],[\"open-element\",\"i\",[]],[\"static-attr\",\"class\",\"fa fa-chevron-down\"],[\"static-attr\",\"aria-hidden\",\"true\"],[\"flush-element\"],[\"close-element\"],[\"text\",\" \"],[\"close-element\"],[\"text\",\"\\n                                \"],[\"open-element\",\"ul\",[]],[\"static-attr\",\"class\",\"dropdown-menu\"],[\"flush-element\"],[\"text\",\"\\n                                    \"],[\"open-element\",\"li\",[]],[\"flush-element\"],[\"open-element\",\"a\",[]],[\"static-attr\",\"href\",\"#\"],[\"flush-element\"],[\"text\",\"to-do\"],[\"close-element\"],[\"close-element\"],[\"text\",\"\\n                                \"],[\"close-element\"],[\"text\",\"\\n                            \"],[\"close-element\"],[\"text\",\"\\n                            \\n                            \"],[\"open-element\",\"span\",[]],[\"static-attr\",\"class\",\"input-group-btn\"],[\"flush-element\"],[\"text\",\"\\n                                \"],[\"open-element\",\"button\",[]],[\"static-attr\",\"type\",\"button\"],[\"static-attr\",\"class\",\"btn btn-primary dropdown-toggle\"],[\"static-attr\",\"data-toggle\",\"dropdown\"],[\"flush-element\"],[\"text\",\"7:00 PM  \"],[\"open-element\",\"i\",[]],[\"static-attr\",\"class\",\"fa fa-chevron-down\"],[\"static-attr\",\"aria-hidden\",\"true\"],[\"flush-element\"],[\"close-element\"],[\"close-element\"],[\"text\",\"\\n                                \"],[\"open-element\",\"ul\",[]],[\"static-attr\",\"class\",\"dropdown-menu\"],[\"flush-element\"],[\"text\",\"\\n                                    \"],[\"open-element\",\"li\",[]],[\"flush-element\"],[\"open-element\",\"a\",[]],[\"static-attr\",\"href\",\"#\"],[\"flush-element\"],[\"text\",\"to-do\"],[\"close-element\"],[\"close-element\"],[\"text\",\"\\n                                \"],[\"close-element\"],[\"text\",\"\\n                            \"],[\"close-element\"],[\"text\",\"\\n                            \\n                            \"],[\"open-element\",\"span\",[]],[\"static-attr\",\"class\",\"input-group-btn red\"],[\"flush-element\"],[\"text\",\"\\n                                \"],[\"open-element\",\"button\",[]],[\"static-attr\",\"class\",\"btn btn-primary\"],[\"static-attr\",\"type\",\"submit\"],[\"flush-element\"],[\"text\",\"Find a table\"],[\"close-element\"],[\"text\",\"\\n                            \"],[\"close-element\"],[\"text\",\"\\n                        \"],[\"close-element\"],[\"text\",\"\\n                    \"],[\"close-element\"],[\"text\",\"\\n\\n                    \"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\n                    \"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\n\\n                \"],[\"close-element\"],[\"text\",\"\\n                \"],[\"comment\",\" /.col-lg-6 \"],[\"text\",\"\\n            \"],[\"close-element\"],[\"text\",\"\\n        \"],[\"close-element\"],[\"text\",\"\\n    \"],[\"close-element\"],[\"text\",\"\\n\"],[\"close-element\"],[\"text\",\"\\n\\n\"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"container\"],[\"flush-element\"],[\"text\",\"\\n    \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"row\"],[\"flush-element\"],[\"text\",\"\\n        \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"col-md-12\"],[\"flush-element\"],[\"text\",\"\\n            \"],[\"open-element\",\"h2\",[]],[\"flush-element\"],[\"text\",\"Popular for lunch today\"],[\"close-element\"],[\"text\",\"\\n        \"],[\"close-element\"],[\"text\",\"\\n\\n\"],[\"block\",[\"each\"],[[\"get\",[\"restaurants\"]]],null,2],[\"text\",\"\\n    \"],[\"close-element\"],[\"text\",\"\\n\"],[\"close-element\"],[\"text\",\"\\n\\n\"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"container\"],[\"flush-element\"],[\"text\",\"\\n    \"],[\"open-element\",\"h2\",[]],[\"flush-element\"],[\"text\",\"Popular locations\"],[\"close-element\"],[\"text\",\"\\n    \"],[\"open-element\",\"ul\",[]],[\"static-attr\",\"class\",\"row block-grid-4\"],[\"flush-element\"],[\"text\",\"\\n\"],[\"block\",[\"each\"],[[\"get\",[\"locations\"]]],null,0],[\"text\",\"    \"],[\"close-element\"],[\"text\",\"\\n\"],[\"close-element\"]],\"locals\":[],\"named\":[],\"yields\":[],\"blocks\":[{\"statements\":[[\"text\",\"            \"],[\"open-element\",\"li\",[]],[\"static-attr\",\"class\",\"popular-location\"],[\"flush-element\"],[\"text\",\"\\n                \"],[\"open-element\",\"p\",[]],[\"static-attr\",\"class\",\"title\"],[\"flush-element\"],[\"append\",[\"unknown\",[\"location\",\"0\"]],false],[\"close-element\"],[\"text\",\"\\n                \"],[\"open-element\",\"p\",[]],[\"static-attr\",\"class\",\"subtitle\"],[\"flush-element\"],[\"append\",[\"unknown\",[\"location\",\"1\"]],false],[\"text\",\" restaurants\"],[\"close-element\"],[\"text\",\"\\n            \"],[\"close-element\"],[\"text\",\"\\n\"]],\"locals\":[\"location\"]},{\"statements\":[],\"locals\":[]},{\"statements\":[[\"text\",\"            \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"col-md-4 col-sm-6 col-xs-12\"],[\"flush-element\"],[\"text\",\"\\n\"],[\"block\",[\"restaurant-listing\"],null,[[\"item\"],[[\"get\",[\"restaurant\"]]]],1],[\"text\",\"            \"],[\"close-element\"],[\"text\",\"\\n\"]],\"locals\":[\"restaurant\"]}],\"hasPartials\":false}", "meta": { "moduleName": "restaurants-app/templates/index.hbs" } });
});
define("restaurants-app/templates/login", ["exports"], function (exports) {
  exports["default"] = Ember.HTMLBars.template({ "id": "QkU+TPLL", "block": "{\"statements\":[[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"container\"],[\"static-attr\",\"id\",\"normal-container\"],[\"flush-element\"],[\"text\",\"\\n   \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"row\"],[\"flush-element\"],[\"text\",\"\\n\"],[\"block\",[\"each\"],[[\"get\",[\"flashMessages\",\"queue\"]]],null,1],[\"text\",\"\\n      \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"col-md-6 col-sm-8 col-xs-12 col-centered\"],[\"flush-element\"],[\"text\",\"\\n          \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"row\"],[\"flush-element\"],[\"text\",\"\\n            \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"col-md-8 col-sm-8 col-xs-6 page-title v-align-center\"],[\"flush-element\"],[\"text\",\"\\n                Login\\n            \"],[\"close-element\"],[\"comment\",\"\\n         \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"col-md-4 col-sm-4 col-xs-6 page-subtitle v-align-center\"],[\"flush-element\"],[\"text\",\"\\n                    \"],[\"block\",[\"link-to\"],[\"register\"],null,0],[\"text\",\"\\n            \"],[\"close-element\"],[\"text\",\"\\n          \"],[\"close-element\"],[\"text\",\"\\n          \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"row\"],[\"flush-element\"],[\"text\",\"\\n              \"],[\"open-element\",\"form\",[]],[\"static-attr\",\"class\",\"col-md-12\"],[\"modifier\",[\"action\"],[[\"get\",[null]],\"authenticate\"],[[\"on\"],[\"submit\"]]],[\"flush-element\"],[\"text\",\"                                            \\n                  \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"form-group\"],[\"flush-element\"],[\"text\",\"\\n                  \\t\"],[\"append\",[\"helper\",[\"input\"],null,[[\"id\",\"placeholder\",\"value\",\"class\",\"type\"],[\"identification\",\"Email\",[\"get\",[\"identification\"]],\"form-control\",\"email\"]]],false],[\"text\",\"\\n                  \"],[\"close-element\"],[\"text\",\"\\n                                        \\n                  \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"form-group\"],[\"flush-element\"],[\"text\",\"\\n                  \\t\"],[\"append\",[\"helper\",[\"input\"],null,[[\"id\",\"placeholder\",\"value\",\"class\",\"type\"],[\"password\",\"Password\",[\"get\",[\"password\"]],\"form-control\",\"password\"]]],false],[\"text\",\"\\n                  \"],[\"close-element\"],[\"text\",\"\\n                                        \\n                  \\n                  \"],[\"open-element\",\"button\",[]],[\"static-attr\",\"type\",\"submit\"],[\"static-attr\",\"class\",\"btn btn-primary\"],[\"flush-element\"],[\"text\",\"Login\"],[\"close-element\"],[\"text\",\"\\n            \"],[\"close-element\"],[\"text\",\"\\n          \"],[\"close-element\"],[\"text\",\"\\n       \"],[\"close-element\"],[\"text\",\"\\n       \\n   \"],[\"close-element\"],[\"text\",\"\\n   \\n\"],[\"close-element\"]],\"locals\":[],\"named\":[],\"yields\":[],\"blocks\":[{\"statements\":[[\"text\",\"Create Account\"]],\"locals\":[]},{\"statements\":[[\"text\",\"\\t  \"],[\"append\",[\"helper\",[\"flash-message\"],null,[[\"flash\"],[[\"get\",[\"flash\"]]]]],false],[\"text\",\"\\n\"]],\"locals\":[\"flash\"]}],\"hasPartials\":false}", "meta": { "moduleName": "restaurants-app/templates/login.hbs" } });
});
define("restaurants-app/templates/logout", ["exports"], function (exports) {
  exports["default"] = Ember.HTMLBars.template({ "id": "agzrOOn0", "block": "{\"statements\":[[\"append\",[\"unknown\",[\"outlet\"]],false],[\"text\",\"\\n\"]],\"locals\":[],\"named\":[],\"yields\":[],\"blocks\":[],\"hasPartials\":false}", "meta": { "moduleName": "restaurants-app/templates/logout.hbs" } });
});
define("restaurants-app/templates/navbar", ["exports"], function (exports) {
  exports["default"] = Ember.HTMLBars.template({ "id": "D5Bh8t6n", "block": "{\"statements\":[[\"open-element\",\"nav\",[]],[\"dynamic-attr\",\"class\",[\"concat\",[\"navbar \",[\"unknown\",[\"navbarClass\"]]]]],[\"flush-element\"],[\"text\",\"\\n    \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"container\"],[\"flush-element\"],[\"text\",\"\\n        \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"navbar-header\"],[\"flush-element\"],[\"text\",\"\\n            \"],[\"open-element\",\"button\",[]],[\"static-attr\",\"type\",\"button\"],[\"static-attr\",\"class\",\"navbar-toggle collapsed\"],[\"static-attr\",\"data-toggle\",\"collapse\"],[\"static-attr\",\"data-target\",\"#navbar\"],[\"static-attr\",\"aria-expanded\",\"false\"],[\"static-attr\",\"aria-controls\",\"navbar\"],[\"flush-element\"],[\"text\",\"\\n                \"],[\"open-element\",\"span\",[]],[\"static-attr\",\"class\",\"sr-only\"],[\"flush-element\"],[\"text\",\"Toggle navigation\"],[\"close-element\"],[\"text\",\"\\n                \"],[\"open-element\",\"span\",[]],[\"static-attr\",\"class\",\"icon-bar\"],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\n                \"],[\"open-element\",\"span\",[]],[\"static-attr\",\"class\",\"icon-bar\"],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\n                \"],[\"open-element\",\"span\",[]],[\"static-attr\",\"class\",\"icon-bar\"],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\n            \"],[\"close-element\"],[\"text\",\"\\n            \"],[\"block\",[\"link-to\"],[\"index\"],[[\"class\"],[\"navbar-brand\"]],7],[\"text\",\"\\n        \"],[\"close-element\"],[\"text\",\"\\n        \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"id\",\"navbar\"],[\"static-attr\",\"class\",\"collapse navbar-collapse navbar-right\"],[\"flush-element\"],[\"text\",\"\\n            \"],[\"open-element\",\"ul\",[]],[\"static-attr\",\"class\",\"nav navbar-nav\"],[\"flush-element\"],[\"text\",\"\\n                \"],[\"block\",[\"nav-link-to\"],[\"index\"],null,6],[\"text\",\"\\n\\n\"],[\"block\",[\"if\"],[[\"get\",[\"session\",\"isAuthenticated\"]]],null,5,1],[\"text\",\"            \"],[\"close-element\"],[\"text\",\"\\n        \"],[\"close-element\"],[\"text\",\"\\n        \"],[\"comment\",\"/.nav-collapse \"],[\"text\",\"\\n    \"],[\"close-element\"],[\"text\",\"\\n\"],[\"close-element\"]],\"locals\":[],\"named\":[],\"yields\":[],\"blocks\":[{\"statements\":[[\"text\",\"Login\"]],\"locals\":[]},{\"statements\":[[\"text\",\"                    \"],[\"block\",[\"nav-link-to\"],[\"login\"],null,0],[\"text\",\"\\n\"]],\"locals\":[]},{\"statements\":[[\"text\",\" Admin \"]],\"locals\":[]},{\"statements\":[[\"text\",\"                        \"],[\"block\",[\"nav-link-to\"],[\"admin\"],null,2],[\"text\",\"\\n\"]],\"locals\":[]},{\"statements\":[[\"text\",\" Logout \"]],\"locals\":[]},{\"statements\":[[\"text\",\"                    \"],[\"block\",[\"nav-link-to\"],[\"logout\"],null,4],[\"text\",\"\\n\"],[\"block\",[\"if\"],[[\"helper\",[\"eq\"],[[\"get\",[\"currentUser\",\"role\"]],\"ADMIN\"],null]],null,3]],\"locals\":[]},{\"statements\":[[\"text\",\"Index\"]],\"locals\":[]},{\"statements\":[[\"text\",\"Restaurants\"]],\"locals\":[]}],\"hasPartials\":false}", "meta": { "moduleName": "restaurants-app/templates/navbar.hbs" } });
});
define("restaurants-app/templates/privacy-policy", ["exports"], function (exports) {
  exports["default"] = Ember.HTMLBars.template({ "id": "A1f6uErx", "block": "{\"statements\":[[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"container\"],[\"static-attr\",\"id\",\"normal-container\"],[\"flush-element\"],[\"text\",\"\\n   \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"row\"],[\"flush-element\"],[\"text\",\"\\n      \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"col-md-8 col-sm-8 col-xs-12 col-centered\"],[\"flush-element\"],[\"text\",\"\\n          \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"row\"],[\"flush-element\"],[\"text\",\"\\n            \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"col-md-12 col-sm-12 col-xs-12 page-title v-align-center\"],[\"flush-element\"],[\"text\",\"\\n                Privacy Policy\\n            \"],[\"close-element\"],[\"text\",\"\\n          \"],[\"close-element\"],[\"text\",\"\\n          \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"row\"],[\"flush-element\"],[\"text\",\"\\n              \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"col-md-12\"],[\"flush-element\"],[\"text\",\"\\n                  \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"card padded\"],[\"flush-element\"],[\"text\",\"\\n                      \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"content\"],[\"flush-element\"],[\"text\",\"\\n                              \"],[\"open-element\",\"p\",[]],[\"static-attr\",\"class\",\"regular-text\"],[\"flush-element\"],[\"text\",\"\\n                                  \"],[\"open-element\",\"b\",[]],[\"flush-element\"],[\"text\",\"What is this Privacy Policy for?\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\nThis privacy policy is for this website restaurants.ba and served by Restaurants LLC and governs the privacy of its users who choose to use it.\\n\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\nThe policy sets out the different areas where user privacy is concerned and outlines the obligations & requirements of the users, the website and website owners. Furthermore the way this website processes, stores and protects user data and information will also be detailed within this policy.\\n\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\n                                  \"],[\"open-element\",\"b\",[]],[\"flush-element\"],[\"text\",\"The Website\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\nThis website and its owners take a proactive approach to user privacy and ensure the necessary steps are taken to protect the privacy of its users throughout their visiting experience. This website complies to all UK national laws and requirements for user privacy.\\n\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\n\"],[\"open-element\",\"b\",[]],[\"flush-element\"],[\"text\",\"Use of Cookies\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\nThis website uses cookies to better the users experience while visiting the website. Where applicable this website uses a cookie control system allowing the user on their first visit to the website to allow or disallow the use of cookies on their computer / device. This complies with recent legislation requirements for websites to obtain explicit consent from users before leaving behind or reading files such as cookies on a user's computer / device.\\n\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\nCookies are small files saved to the user's computers hard drive that track, save and store information about the user's interactions and usage of the website. This allows the website, through its server to provide the users with a tailored experience within this website.\\nUsers are advised that if they wish to deny the use and saving of cookies from this website on to their computers hard drive they should take necessary steps within their web browsers security settings to block all cookies from this website and its external serving vendors.\\n\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\nThis website uses tracking software to monitor its visitors to better understand how they use it. This software is provided by Google Analytics which uses cookies to track visitor usage. The software will save a cookie to your computers hard drive in order to track and monitor your engagement and usage of the website, but will not store, save or collect personal information. You can read Google's privacy policy here for further information [ http://www.google.com/privacy.html ].\\nOther cookies may be stored to your computers hard drive by external vendors when this website uses referral programs, sponsored links or adverts. Such cookies are used for conversion and referral tracking and typically expire after 30 days, though some may take longer. No personal information is stored, saved or collected.\\n\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\n\"],[\"open-element\",\"b\",[]],[\"flush-element\"],[\"text\",\"Contact & Communication\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\nUsers contacting this website and/or its owners do so at their own discretion and provide any such personal details requested at their own risk. Your personal information is kept private and stored securely until a time it is no longer required or has no use, as detailed in the Data Protection Act 1998. Every effort has been made to ensure a safe and secure form to email submission process but advise users using such form to email processes that they do so at their own risk.\\nThis website and its owners use any information submitted to provide you with further information about the products / services they offer or to assist you in answering any questions or queries you may have submitted. This includes using your details to subscribe you to any email newsletter program the website operates but only if this was made clear to you and your express permission was granted when submitting any form to email process. Or whereby you the consumer have previously purchased from or enquired about purchasing from the company a product or service that the email newsletter relates to. This is by no means an entire list of your user rights in regard to receiving email marketing material. Your details are not passed on to any third parties.\\n\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\n\"],[\"open-element\",\"b\",[]],[\"flush-element\"],[\"text\",\"Email Newsletter\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\nThis website operates an email newsletter program, used to inform subscribers about products and services supplied by this website. Users can subscribe through an online automated process should they wish to do so but do so at their own discretion. Some subscriptions may be manually processed through prior written agreement with the user.\\n\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\nSubscriptions are taken in compliance with UK Spam Laws detailed in the Privacy and Electronic Communications Regulations 2003. All personal details relating to subscriptions are held securely and in accordance with the Data Protection Act 1998. No personal details are passed on to third parties nor shared with companies / people outside of the company that operates this website. Under the Data Protection Act 1998 you may request a copy of personal information held about you by this website's email newsletter program. A small fee will be payable. If you would like a copy of the information held on you please write to the business address at the bottom of this policy.\\n\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\nEmail marketing campaigns published by this website or its owners may contain tracking facilities within the actual email. Subscriber activity is tracked and stored in a database for future analysis and evaluation. Such tracked activity may include; the opening of emails, forwarding of emails, the clicking of links within the email content, times, dates and frequency of activity [this is by no far a comprehensive list].\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\nThis information is used to refine future email campaigns and supply the user with more relevant content based around their activity.\\n\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\nIn compliance with UK Spam Laws and the Privacy and Electronic Communications Regulations 2003 subscribers are given the opportunity to un-subscribe at any time through an automated system. This process is detailed at the footer of each email campaign. If an automated un-subscription system is unavailable clear instructions on how to un-subscribe will by detailed instead.\\n\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\n\"],[\"open-element\",\"b\",[]],[\"flush-element\"],[\"text\",\"External Links\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\nAlthough this website only looks to include quality, safe and relevant external links, users are advised adopt a policy of caution before clicking any external web links mentioned throughout this website. (External links are clickable text / banner / image links to other websites, similar to; Folded Book Art or Cottages in Pembrokeshire.)\\n\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\nThe owners of this website cannot guarantee or verify the contents of any externally linked website despite their best efforts. Users should therefore note they click on external links at their own risk and this website and its owners cannot be held liable for any damages or implications caused by visiting any external links mentioned.\\n\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\n\"],[\"open-element\",\"b\",[]],[\"flush-element\"],[\"text\",\"Adverts and Sponsored Links\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\nThis website may contain sponsored links and adverts. These will typically be served through our advertising partners, to whom may have detailed privacy policies relating directly to the adverts they serve.\\n\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\nClicking on any such adverts will send you to the advertisers website through a referral program which may use cookies and will track the number of referrals sent from this website. This may include the use of cookies which may in turn be saved on your computers hard drive. Users should therefore note they click on sponsored external links at their own risk and this website and its owners cannot be held liable for any damages or implications caused by visiting any external links mentioned.\\n\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\n\"],[\"open-element\",\"b\",[]],[\"flush-element\"],[\"text\",\"Social Media Platforms\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\nCommunication, engagement and actions taken through external social media platforms that this website and its owners participate on are custom to the terms and conditions as well as the privacy policies held with each social media platform respectively.\\n\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\nUsers are advised to use social media platforms wisely and communicate / engage upon them with due care and caution in regard to their own privacy and personal details. This website nor its owners will ever ask for personal or sensitive information through social media platforms and encourage users wishing to discuss sensitive details to contact them through primary communication channels such as by telephone or email.\\n\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\nThis website may use social sharing buttons which help share web content directly from web pages to the social media platform in question. Users are advised before using such social sharing buttons that they do so at their own discretion and note that the social media platform may track and save your request to share a web page respectively through your social media platform account.\\n\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\n\"],[\"open-element\",\"b\",[]],[\"flush-element\"],[\"text\",\"Shortened Links in Social Media\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\nThis website and its owners through their social media platform accounts may share web links to relevant web pages. By default some social media platforms shorten lengthy urls [web addresses] (this is an example: http://bit.ly/zyVUBo). \\nUsers are advised to take caution and good judgement before clicking any shortened urls published on social media platforms by this website and its owners. Despite the best efforts to ensure only genuine urls are published many social media platforms are prone to spam and hacking and therefore this website and its owners cannot be held liable for any damages or implications caused by visiting any shortened links.\\n                              \"],[\"close-element\"],[\"text\",\"\\n                      \"],[\"close-element\"],[\"text\",\"\\n                  \"],[\"close-element\"],[\"text\",\"\\n              \"],[\"close-element\"],[\"text\",\"\\n          \"],[\"close-element\"],[\"text\",\"\\n       \"],[\"close-element\"],[\"text\",\"\\n       \\n   \"],[\"close-element\"],[\"text\",\"\\n   \\n\"],[\"close-element\"]],\"locals\":[],\"named\":[],\"yields\":[],\"blocks\":[],\"hasPartials\":false}", "meta": { "moduleName": "restaurants-app/templates/privacy-policy.hbs" } });
});
define("restaurants-app/templates/register", ["exports"], function (exports) {
  exports["default"] = Ember.HTMLBars.template({ "id": "csZcYSFo", "block": "{\"statements\":[[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"container\"],[\"static-attr\",\"id\",\"normal-container\"],[\"flush-element\"],[\"text\",\"\\n   \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"row\"],[\"flush-element\"],[\"text\",\"\\n\\n\"],[\"block\",[\"each\"],[[\"get\",[\"flashMessages\",\"queue\"]]],null,0],[\"text\",\"\\n      \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"col-md-6 col-sm-8 col-xs-12 col-centered\"],[\"flush-element\"],[\"text\",\"\\n          \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"row\"],[\"flush-element\"],[\"text\",\"\\n            \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"col-md-8 col-sm-8 col-xs-6 page-title v-align-center\"],[\"flush-element\"],[\"text\",\"\\n                Create Account\\n            \"],[\"close-element\"],[\"comment\",\"\\n         \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"col-md-4 col-sm-4 col-xs-6 page-subtitle v-align-center\"],[\"flush-element\"],[\"text\",\"\\n                    \"],[\"open-element\",\"a\",[]],[\"static-attr\",\"href\",\"#\"],[\"flush-element\"],[\"text\",\"Login\"],[\"close-element\"],[\"text\",\"\\n            \"],[\"close-element\"],[\"text\",\"\\n          \"],[\"close-element\"],[\"text\",\"\\n          \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"row\"],[\"flush-element\"],[\"text\",\"\\n              \"],[\"open-element\",\"form\",[]],[\"static-attr\",\"class\",\"col-md-12\"],[\"modifier\",[\"action\"],[[\"get\",[null]],\"register\"],[[\"on\"],[\"submit\"]]],[\"flush-element\"],[\"text\",\"\\n                  \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"form-group\"],[\"flush-element\"],[\"text\",\"\\n                    \"],[\"append\",[\"helper\",[\"input\"],null,[[\"id\",\"placeholder\",\"value\",\"class\",\"type\"],[\"firstName\",\"First Name\",[\"get\",[\"identification\"]],\"form-control\",\"text\"]]],false],[\"text\",\"\\n                  \"],[\"close-element\"],[\"text\",\"\\n                  \\n                  \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"form-group\"],[\"flush-element\"],[\"text\",\"\\n                    \"],[\"open-element\",\"input\",[]],[\"static-attr\",\"type\",\"text\"],[\"static-attr\",\"class\",\"form-control\"],[\"static-attr\",\"placeholder\",\"Last Name\"],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\n                  \"],[\"close-element\"],[\"text\",\"\\n                                        \\n                  \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"form-group\"],[\"flush-element\"],[\"text\",\"\\n                    \"],[\"open-element\",\"input\",[]],[\"static-attr\",\"type\",\"email\"],[\"static-attr\",\"class\",\"form-control\"],[\"static-attr\",\"placeholder\",\"Email\"],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\n                  \"],[\"close-element\"],[\"text\",\"\\n                  \\n                  \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"form-group\"],[\"flush-element\"],[\"text\",\"\\n                    \"],[\"open-element\",\"input\",[]],[\"static-attr\",\"type\",\"text\"],[\"static-attr\",\"class\",\"form-control\"],[\"static-attr\",\"placeholder\",\"Phone Number\"],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\n                  \"],[\"close-element\"],[\"text\",\"\\n                  \\n                  \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"form-group form-group-inline\"],[\"flush-element\"],[\"text\",\"\\n                      \"],[\"open-element\",\"select\",[]],[\"static-attr\",\"class\",\"form-control\"],[\"flush-element\"],[\"text\",\"\\n                          \"],[\"open-element\",\"option\",[]],[\"static-attr\",\"value\",\"Bosnia and Herzegovina\"],[\"flush-element\"],[\"text\",\"Bosnia and Herzegovina\"],[\"close-element\"],[\"text\",\"\\n                      \"],[\"close-element\"],[\"text\",\"\\n                      \"],[\"open-element\",\"select\",[]],[\"static-attr\",\"class\",\"form-control\"],[\"flush-element\"],[\"text\",\"\\n                          \"],[\"open-element\",\"option\",[]],[\"static-attr\",\"value\",\"Sarajevo\"],[\"flush-element\"],[\"text\",\"Sarajevo\"],[\"close-element\"],[\"text\",\"\\n                          \"],[\"open-element\",\"option\",[]],[\"static-attr\",\"value\",\"Tuzla\"],[\"flush-element\"],[\"text\",\"Tuzla\"],[\"close-element\"],[\"text\",\"\\n                          \"],[\"open-element\",\"option\",[]],[\"static-attr\",\"value\",\"Goražde\"],[\"flush-element\"],[\"text\",\"Goražde\"],[\"close-element\"],[\"text\",\"\\n                      \"],[\"close-element\"],[\"text\",\"\\n                  \"],[\"close-element\"],[\"text\",\"\\n                                        \\n                  \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"form-group\"],[\"flush-element\"],[\"text\",\"\\n                    \"],[\"open-element\",\"input\",[]],[\"static-attr\",\"type\",\"password\"],[\"static-attr\",\"class\",\"form-control\"],[\"static-attr\",\"placeholder\",\"Password\"],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\n                  \"],[\"close-element\"],[\"text\",\"\\n                  \\n                  \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"form-group\"],[\"flush-element\"],[\"text\",\"\\n                    \"],[\"open-element\",\"input\",[]],[\"static-attr\",\"type\",\"password\"],[\"static-attr\",\"class\",\"form-control\"],[\"static-attr\",\"placeholder\",\"Confirm Password\"],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\n                  \"],[\"close-element\"],[\"text\",\"\\n                  \\n                  \\n                  \"],[\"open-element\",\"button\",[]],[\"static-attr\",\"type\",\"submit\"],[\"static-attr\",\"class\",\"btn btn-primary\"],[\"flush-element\"],[\"text\",\"Create Account\"],[\"close-element\"],[\"text\",\"\\n            \"],[\"close-element\"],[\"text\",\"\\n          \"],[\"close-element\"],[\"text\",\"\\n       \"],[\"close-element\"],[\"text\",\"\\n       \\n   \"],[\"close-element\"],[\"text\",\"\\n   \\n\"],[\"close-element\"]],\"locals\":[],\"named\":[],\"yields\":[],\"blocks\":[{\"statements\":[[\"text\",\"\\t  \"],[\"append\",[\"helper\",[\"flash-message\"],null,[[\"flash\"],[[\"get\",[\"flash\"]]]]],false],[\"text\",\"\\n\"]],\"locals\":[\"flash\"]}],\"hasPartials\":false}", "meta": { "moduleName": "restaurants-app/templates/register.hbs" } });
});
define("restaurants-app/templates/restaurants", ["exports"], function (exports) {
  exports["default"] = Ember.HTMLBars.template({ "id": "7zCGkNE8", "block": "{\"statements\":[[\"append\",[\"unknown\",[\"outlet\"]],false],[\"text\",\"\\n\"]],\"locals\":[],\"named\":[],\"yields\":[],\"blocks\":[],\"hasPartials\":false}", "meta": { "moduleName": "restaurants-app/templates/restaurants.hbs" } });
});
define("restaurants-app/templates/restaurants/restaurant", ["exports"], function (exports) {
  exports["default"] = Ember.HTMLBars.template({ "id": "G2DeOZ/+", "block": "{\"statements\":[[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"jumbotron index restaurant\"],[\"dynamic-attr\",\"style\",[\"concat\",[\"background-image: url('/assets/images/restaurants/covers/\",[\"unknown\",[\"model\",\"coverImageUrl\"]],\"');\"]]],[\"flush-element\"],[\"text\",\"\\n\"],[\"close-element\"],[\"text\",\"\\n\\n\"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"container\"],[\"static-attr\",\"id\",\"restaurant-container\"],[\"flush-element\"],[\"text\",\"\\n       \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"row\"],[\"flush-element\"],[\"text\",\"\\n          \\n            \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"col-md-9 col-sm-9 col-xs-12 pull-right-sm\"],[\"flush-element\"],[\"text\",\"\\n                \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"restaurant-listing horizontal\"],[\"flush-element\"],[\"text\",\"\\n                    \"],[\"open-element\",\"h3\",[]],[\"flush-element\"],[\"open-element\",\"a\",[]],[\"static-attr\",\"href\",\"#\"],[\"flush-element\"],[\"append\",[\"unknown\",[\"model\",\"name\"]],false],[\"close-element\"],[\"close-element\"],[\"text\",\"\\n\\n                    \"],[\"open-element\",\"p\",[]],[\"static-attr\",\"class\",\"rating\"],[\"flush-element\"],[\"text\",\"\\n\"],[\"block\",[\"each\"],[[\"get\",[\"model\",\"activeStars\"]]],null,5],[\"block\",[\"each\"],[[\"get\",[\"model\",\"inactiveStars\"]]],null,4],[\"text\",\"\\n\\t\\t\\t\\t\\t\\t\"],[\"open-element\",\"span\",[]],[\"static-attr\",\"class\",\"count\"],[\"flush-element\"],[\"text\",\"(\"],[\"append\",[\"unknown\",[\"model\",\"reviewCount\"]],false],[\"text\",\")\"],[\"close-element\"],[\"text\",\"\\n                    \"],[\"close-element\"],[\"text\",\"\\n\\n                    \"],[\"open-element\",\"span\",[]],[\"static-attr\",\"class\",\"dollars\"],[\"flush-element\"],[\"text\",\"\\n\"],[\"block\",[\"each\"],[[\"get\",[\"model\",\"activeDollars\"]]],null,3],[\"block\",[\"each\"],[[\"get\",[\"model\",\"inactiveDollars\"]]],null,2],[\"text\",\"\\t\\t\\t\\t    \"],[\"close-element\"],[\"text\",\"\\n\\n                    \"],[\"open-element\",\"p\",[]],[\"static-attr\",\"class\",\"tags\"],[\"flush-element\"],[\"text\",\"\\n                        \"],[\"open-element\",\"a\",[]],[\"static-attr\",\"href\",\"#\"],[\"flush-element\"],[\"text\",\"Italian\"],[\"close-element\"],[\"text\",\" |\\n                        \"],[\"open-element\",\"a\",[]],[\"static-attr\",\"href\",\"#\"],[\"flush-element\"],[\"text\",\"International\"],[\"close-element\"],[\"text\",\" |\\n                        \"],[\"open-element\",\"a\",[]],[\"static-attr\",\"href\",\"#\"],[\"flush-element\"],[\"text\",\"Mediterranean\"],[\"close-element\"],[\"text\",\"\\n                    \"],[\"close-element\"],[\"text\",\"\\n\\n                    \"],[\"open-element\",\"button\",[]],[\"static-attr\",\"type\",\"button\"],[\"static-attr\",\"class\",\"btn btn-default\"],[\"flush-element\"],[\"open-element\",\"i\",[]],[\"static-attr\",\"class\",\"fa fa-star\"],[\"static-attr\",\"aria-hidden\",\"true\"],[\"flush-element\"],[\"close-element\"],[\"text\",\" Rate this place\"],[\"close-element\"],[\"text\",\"\\n                \"],[\"close-element\"],[\"text\",\"\\n            \"],[\"close-element\"],[\"text\",\"\\n           \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"col-md-3 col-sm-3 col-xs-12\"],[\"flush-element\"],[\"text\",\"\\n                \"],[\"open-element\",\"a\",[]],[\"static-attr\",\"href\",\"#\"],[\"static-attr\",\"id\",\"profile-pic\"],[\"flush-element\"],[\"text\",\"\\n                    \"],[\"open-element\",\"img\",[]],[\"static-attr\",\"class\",\"img-responsive\"],[\"dynamic-attr\",\"src\",[\"concat\",[\"/assets/images/restaurants/logos/\",[\"unknown\",[\"model\",\"logoImageUrl\"]]]]],[\"dynamic-attr\",\"alt\",[\"concat\",[[\"unknown\",[\"model\",\"name\"]]]]],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\n                \"],[\"close-element\"],[\"text\",\"\\n            \"],[\"close-element\"],[\"text\",\"\\n       \"],[\"close-element\"],[\"text\",\"\\n       \\n        \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"row\"],[\"flush-element\"],[\"text\",\"\\n            \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"col-md-3 col-sm-3 col-xs-12\"],[\"flush-element\"],[\"text\",\"\\n                \"],[\"open-element\",\"ul\",[]],[\"static-attr\",\"class\",\"sidebar\"],[\"flush-element\"],[\"text\",\"\\n                    \"],[\"open-element\",\"li\",[]],[\"flush-element\"],[\"append\",[\"helper\",[\"scroll-to\"],null,[[\"href\",\"label\"],[\"#section-reservation\",\"Reservation\"]]],false],[\"close-element\"],[\"text\",\"\\n                    \"],[\"open-element\",\"li\",[]],[\"flush-element\"],[\"append\",[\"helper\",[\"scroll-to\"],null,[[\"href\",\"label\"],[\"#section-about\",\"About\"]]],false],[\"close-element\"],[\"text\",\"\\n                    \"],[\"open-element\",\"li\",[]],[\"flush-element\"],[\"append\",[\"helper\",[\"scroll-to\"],null,[[\"href\",\"label\"],[\"#section-menu\",\"Menu\"]]],false],[\"close-element\"],[\"text\",\"\\n                    \"],[\"open-element\",\"li\",[]],[\"flush-element\"],[\"append\",[\"helper\",[\"scroll-to\"],null,[[\"href\",\"label\"],[\"#section-photos\",\"Photos\"]]],false],[\"close-element\"],[\"text\",\"\\n                \"],[\"close-element\"],[\"text\",\"\\n            \"],[\"close-element\"],[\"text\",\"\\n\\n            \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"col-md-9 col-sm-9 col-xs-12\"],[\"static-attr\",\"id\",\"pull-up-column\"],[\"flush-element\"],[\"text\",\"\\n                \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"card\"],[\"static-attr\",\"id\",\"section-reservation\"],[\"flush-element\"],[\"text\",\"\\n                    \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"padded\"],[\"flush-element\"],[\"text\",\"\\n                        \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"title\"],[\"flush-element\"],[\"text\",\"\\n                            \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"row\"],[\"flush-element\"],[\"text\",\"\\n                                \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"col-md-9 col-sm-9 col-xs-6 main v-align-center\"],[\"flush-element\"],[\"text\",\"\\n                                    Make a free reservation\\n                                \"],[\"close-element\"],[\"comment\",\"\\n                             \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"col-md-3 col-sm-3 col-xs-6 sub v-align-center\"],[\"flush-element\"],[\"text\",\"\\n                                    \"],[\"open-element\",\"span\",[]],[\"static-attr\",\"style\",\"height: 10px;\"],[\"flush-element\"],[\"text\",\"Booked 72 times today\"],[\"close-element\"],[\"text\",\"\\n                                \"],[\"close-element\"],[\"text\",\"\\n                            \"],[\"close-element\"],[\"text\",\"\\n                        \"],[\"close-element\"],[\"text\",\"\\n                        \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"content\"],[\"flush-element\"],[\"text\",\"\\n                           \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"row\"],[\"flush-element\"],[\"text\",\"\\n                               \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"col-md-12\"],[\"flush-element\"],[\"text\",\"\\n                                    \"],[\"open-element\",\"form\",[]],[\"static-attr\",\"role\",\"search\"],[\"static-attr\",\"class\",\"filter-box not-landing\"],[\"flush-element\"],[\"text\",\"\\n                                        \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"input-group input-group-lg\"],[\"flush-element\"],[\"text\",\"\\n                                            \"],[\"open-element\",\"span\",[]],[\"static-attr\",\"class\",\"input-group-btn\"],[\"flush-element\"],[\"text\",\"\\n\\n                                        \"],[\"open-element\",\"button\",[]],[\"static-attr\",\"type\",\"button\"],[\"static-attr\",\"class\",\"btn btn-primary dropdown-toggle\"],[\"static-attr\",\"data-toggle\",\"dropdown\"],[\"flush-element\"],[\"text\",\"\\n                                           2 people \"],[\"open-element\",\"i\",[]],[\"static-attr\",\"class\",\"fa fa-chevron-down\"],[\"static-attr\",\"aria-hidden\",\"true\"],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\n                                        \"],[\"close-element\"],[\"text\",\"\\n                                        \"],[\"open-element\",\"ul\",[]],[\"static-attr\",\"class\",\"dropdown-menu\"],[\"flush-element\"],[\"text\",\"\\n                                            \"],[\"open-element\",\"li\",[]],[\"flush-element\"],[\"open-element\",\"a\",[]],[\"static-attr\",\"href\",\"#\"],[\"flush-element\"],[\"text\",\"2 People\"],[\"close-element\"],[\"close-element\"],[\"text\",\"\\n                                            \"],[\"open-element\",\"li\",[]],[\"flush-element\"],[\"open-element\",\"a\",[]],[\"static-attr\",\"href\",\"#\"],[\"flush-element\"],[\"text\",\"3 People\"],[\"close-element\"],[\"close-element\"],[\"text\",\"\\n                                            \"],[\"open-element\",\"li\",[]],[\"flush-element\"],[\"open-element\",\"a\",[]],[\"static-attr\",\"href\",\"#\"],[\"flush-element\"],[\"text\",\"4 People\"],[\"close-element\"],[\"close-element\"],[\"text\",\"\\n                                            \"],[\"open-element\",\"li\",[]],[\"flush-element\"],[\"open-element\",\"a\",[]],[\"static-attr\",\"href\",\"#\"],[\"flush-element\"],[\"text\",\"5 People\"],[\"close-element\"],[\"close-element\"],[\"text\",\"\\n                                        \"],[\"close-element\"],[\"text\",\"\\n                                        \"],[\"close-element\"],[\"text\",\"\\n                                            \"],[\"open-element\",\"span\",[]],[\"static-attr\",\"class\",\"input-group-btn\"],[\"flush-element\"],[\"text\",\"\\n                        \"],[\"open-element\",\"button\",[]],[\"static-attr\",\"type\",\"button\"],[\"static-attr\",\"class\",\"btn btn-primary dropdown-toggle\"],[\"static-attr\",\"data-toggle\",\"dropdown\"],[\"flush-element\"],[\"text\",\"Apr 29, 2016  \"],[\"open-element\",\"i\",[]],[\"static-attr\",\"class\",\"fa fa-chevron-down\"],[\"static-attr\",\"aria-hidden\",\"true\"],[\"flush-element\"],[\"close-element\"],[\"text\",\" \"],[\"close-element\"],[\"text\",\"\\n                                        \"],[\"open-element\",\"ul\",[]],[\"static-attr\",\"class\",\"dropdown-menu\"],[\"flush-element\"],[\"text\",\"\\n                                            \"],[\"open-element\",\"li\",[]],[\"flush-element\"],[\"open-element\",\"a\",[]],[\"static-attr\",\"href\",\"#\"],[\"flush-element\"],[\"text\",\"to-do\"],[\"close-element\"],[\"close-element\"],[\"text\",\"\\n                                        \"],[\"close-element\"],[\"text\",\"\\n                                        \"],[\"close-element\"],[\"text\",\"\\n                                            \"],[\"open-element\",\"span\",[]],[\"static-attr\",\"class\",\"input-group-btn\"],[\"flush-element\"],[\"text\",\"\\n                        \"],[\"open-element\",\"button\",[]],[\"static-attr\",\"type\",\"button\"],[\"static-attr\",\"class\",\"btn btn-primary dropdown-toggle\"],[\"static-attr\",\"data-toggle\",\"dropdown\"],[\"flush-element\"],[\"text\",\"7:00 PM  \"],[\"open-element\",\"i\",[]],[\"static-attr\",\"class\",\"fa fa-chevron-down\"],[\"static-attr\",\"aria-hidden\",\"true\"],[\"flush-element\"],[\"close-element\"],[\"text\",\" \"],[\"close-element\"],[\"text\",\"\\n                                        \"],[\"open-element\",\"ul\",[]],[\"static-attr\",\"class\",\"dropdown-menu\"],[\"flush-element\"],[\"text\",\"\\n                                            \"],[\"open-element\",\"li\",[]],[\"flush-element\"],[\"open-element\",\"a\",[]],[\"static-attr\",\"href\",\"#\"],[\"flush-element\"],[\"text\",\"to-do\"],[\"close-element\"],[\"close-element\"],[\"text\",\"\\n                                        \"],[\"close-element\"],[\"text\",\"\\n                                        \"],[\"close-element\"],[\"text\",\"\\n                                            \"],[\"open-element\",\"span\",[]],[\"static-attr\",\"class\",\"input-group-btn red\"],[\"flush-element\"],[\"text\",\"\\n                                        \"],[\"open-element\",\"button\",[]],[\"static-attr\",\"class\",\"btn btn-primary\"],[\"static-attr\",\"type\",\"submit\"],[\"flush-element\"],[\"text\",\"Find a table\"],[\"close-element\"],[\"text\",\"\\n                                        \"],[\"close-element\"],[\"text\",\"\\n                                        \"],[\"close-element\"],[\"text\",\"\\n                                    \"],[\"close-element\"],[\"text\",\"\\n                                                                        \\n                                    \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"regular-header\"],[\"flush-element\"],[\"text\",\"\\n                                        Availability on Jan 17, 2017 around 7:00 PM for 2 people:\\n                                    \"],[\"close-element\"],[\"text\",\"\\n                                    \\n                                    \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"tiny-text grey\"],[\"flush-element\"],[\"text\",\"\\n                                        \"],[\"open-element\",\"i\",[]],[\"static-attr\",\"class\",\"fa fa-info-circle\"],[\"static-attr\",\"aria-hidden\",\"true\"],[\"flush-element\"],[\"close-element\"],[\"text\",\" 12 tables left\\n                                               \\n                                        \"],[\"open-element\",\"i\",[]],[\"static-attr\",\"class\",\"fa fa-check-circle green\"],[\"static-attr\",\"aria-hidden\",\"true\"],[\"flush-element\"],[\"close-element\"],[\"text\",\" 72 reservations today\\n                                    \"],[\"close-element\"],[\"text\",\"\\n                                    \\n                                    \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"regular-text\"],[\"flush-element\"],[\"text\",\"\\n                                        Select the best time that fits you:\\n                                    \"],[\"close-element\"],[\"text\",\"\\n                                    \\n                                    \"],[\"open-element\",\"a\",[]],[\"static-attr\",\"class\",\"btn btn-primary\"],[\"static-attr\",\"href\",\"#\"],[\"flush-element\"],[\"text\",\"06:30 PM\"],[\"close-element\"],[\"text\",\"\\n                                    \"],[\"open-element\",\"a\",[]],[\"static-attr\",\"class\",\"btn btn-primary\"],[\"static-attr\",\"href\",\"#\"],[\"flush-element\"],[\"text\",\"06:30 PM\"],[\"close-element\"],[\"text\",\"\\n                                    \"],[\"open-element\",\"a\",[]],[\"static-attr\",\"class\",\"btn btn-primary\"],[\"static-attr\",\"href\",\"#\"],[\"flush-element\"],[\"text\",\"06:30 PM\"],[\"close-element\"],[\"text\",\"\\n                                    \"],[\"open-element\",\"a\",[]],[\"static-attr\",\"class\",\"btn btn-primary\"],[\"static-attr\",\"href\",\"#\"],[\"flush-element\"],[\"text\",\"06:30 PM\"],[\"close-element\"],[\"text\",\"\\n                                    \\n                               \"],[\"close-element\"],[\"text\",\"\\n                            \"],[\"close-element\"],[\"text\",\"\\n                        \"],[\"close-element\"],[\"text\",\"\\n                    \"],[\"close-element\"],[\"text\",\"\\n\\n                \"],[\"close-element\"],[\"text\",\"\\n                \\n                \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"card\"],[\"static-attr\",\"id\",\"section-about\"],[\"flush-element\"],[\"text\",\"\\n                    \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"padded\"],[\"flush-element\"],[\"text\",\"\\n                        \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"title\"],[\"flush-element\"],[\"text\",\"\\n                            \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"row\"],[\"flush-element\"],[\"text\",\"\\n                                \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"col-md-12 main\"],[\"flush-element\"],[\"text\",\"\\n                                    About \"],[\"append\",[\"unknown\",[\"model\",\"name\"]],false],[\"text\",\"\\n                                \"],[\"close-element\"],[\"text\",\"\\n                            \"],[\"close-element\"],[\"text\",\"\\n                        \"],[\"close-element\"],[\"text\",\"\\n                    \"],[\"close-element\"],[\"text\",\"\\n                    \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"id\",\"map\"],[\"flush-element\"],[\"text\",\"\\n\"],[\"block\",[\"g-map\"],null,[[\"lat\",\"lng\",\"zoom\"],[[\"get\",[\"model\",\"lat\"]],[\"get\",[\"model\",\"long\"]],15]],1],[\"text\",\"                    \"],[\"close-element\"],[\"text\",\"\\n                    \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"padded\"],[\"flush-element\"],[\"text\",\"\\n                        \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"content\"],[\"flush-element\"],[\"text\",\"\\n                           \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"row\"],[\"flush-element\"],[\"text\",\"\\n                               \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"col-md-12\"],[\"flush-element\"],[\"text\",\"\\n                                    \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"subtitle\"],[\"flush-element\"],[\"text\",\"\\n                                        Description\\n                                    \"],[\"close-element\"],[\"text\",\"\\n                                    \"],[\"open-element\",\"p\",[]],[\"static-attr\",\"class\",\"regular-text\"],[\"flush-element\"],[\"text\",\"\\n                                        \"],[\"append\",[\"unknown\",[\"model\",\"description\"]],true],[\"text\",\"\\n                                    \"],[\"close-element\"],[\"text\",\"\\n                               \"],[\"close-element\"],[\"text\",\"\\n                            \"],[\"close-element\"],[\"text\",\"\\n                        \"],[\"close-element\"],[\"text\",\"\\n                    \"],[\"close-element\"],[\"text\",\"\\n                \"],[\"close-element\"],[\"text\",\"\\n                \\n                \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"card\"],[\"static-attr\",\"id\",\"section-menu\"],[\"flush-element\"],[\"text\",\"\\n                    \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"padded\"],[\"flush-element\"],[\"text\",\"\\n                        \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"title\"],[\"flush-element\"],[\"text\",\"\\n                            \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"row\"],[\"flush-element\"],[\"text\",\"\\n                                \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"col-md-12 main\"],[\"flush-element\"],[\"text\",\"\\n                                    Menu\\n                                \"],[\"close-element\"],[\"text\",\"\\n                            \"],[\"close-element\"],[\"text\",\"\\n                        \"],[\"close-element\"],[\"text\",\"\\n                    \"],[\"close-element\"],[\"text\",\"\\n                    \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"padded top-border\"],[\"flush-element\"],[\"text\",\"\\n                        \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"content\"],[\"flush-element\"],[\"text\",\"\\n                           \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"row\"],[\"flush-element\"],[\"text\",\"\\n                               \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"col-md-12\"],[\"flush-element\"],[\"text\",\"\\n                                   \"],[\"open-element\",\"ul\",[]],[\"static-attr\",\"class\",\"restaurant-menu\"],[\"flush-element\"],[\"text\",\"\\n\"],[\"block\",[\"each\"],[[\"get\",[\"model\",\"menuItems\"]]],null,0],[\"text\",\"                                  \"],[\"close-element\"],[\"text\",\"\\n                                   \\n                               \"],[\"close-element\"],[\"text\",\"\\n                            \"],[\"close-element\"],[\"text\",\"\\n                        \"],[\"close-element\"],[\"text\",\"\\n                    \"],[\"close-element\"],[\"text\",\"\\n                \"],[\"close-element\"],[\"text\",\"\\n                \\n                \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"card\"],[\"static-attr\",\"id\",\"section-photos\"],[\"flush-element\"],[\"text\",\"\\n                    \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"padded\"],[\"flush-element\"],[\"text\",\"\\n                        \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"title\"],[\"flush-element\"],[\"text\",\"\\n                            \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"row\"],[\"flush-element\"],[\"text\",\"\\n                                \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"col-md-9 col-sm-9 col-xs-6 main v-align-center\"],[\"flush-element\"],[\"text\",\"\\n                                    Restaurants Photo\\n                                \"],[\"close-element\"],[\"comment\",\"\\n                             \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"col-md-3 col-sm-3 col-xs-6 sub v-align-center\"],[\"flush-element\"],[\"text\",\"\\n                                    \"],[\"open-element\",\"span\",[]],[\"static-attr\",\"style\",\"height: 10px;\"],[\"flush-element\"],[\"open-element\",\"a\",[]],[\"static-attr\",\"href\",\"#\"],[\"flush-element\"],[\"text\",\"See All Photos (12)\"],[\"close-element\"],[\"close-element\"],[\"text\",\"\\n                                \"],[\"close-element\"],[\"text\",\"\\n                            \"],[\"close-element\"],[\"text\",\"\\n                        \"],[\"close-element\"],[\"text\",\"\\n                        \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"content\"],[\"flush-element\"],[\"text\",\"\\n                           \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"row\"],[\"flush-element\"],[\"text\",\"\\n                               \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"col-md-6\"],[\"flush-element\"],[\"text\",\"\\n                                    \"],[\"open-element\",\"a\",[]],[\"static-attr\",\"class\",\"restaurant-img\"],[\"static-attr\",\"href\",\"#\"],[\"flush-element\"],[\"text\",\"\\n                                        \"],[\"open-element\",\"img\",[]],[\"static-attr\",\"class\",\"img-responsive\"],[\"static-attr\",\"src\",\"/assets/images/restaurant-featured.png\"],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\n                                    \"],[\"close-element\"],[\"text\",\"\\n                               \"],[\"close-element\"],[\"text\",\"\\n                               \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"col-md-3\"],[\"flush-element\"],[\"text\",\"\\n                                   \"],[\"open-element\",\"a\",[]],[\"static-attr\",\"class\",\"restaurant-img\"],[\"static-attr\",\"href\",\"#\"],[\"flush-element\"],[\"text\",\"\\n                                        \"],[\"open-element\",\"img\",[]],[\"static-attr\",\"class\",\"img-responsive\"],[\"static-attr\",\"src\",\"/assets/images/restaurant-square.png\"],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\n                                    \"],[\"close-element\"],[\"text\",\"\\n                                    \\n                                    \"],[\"open-element\",\"a\",[]],[\"static-attr\",\"class\",\"restaurant-img\"],[\"static-attr\",\"href\",\"#\"],[\"flush-element\"],[\"text\",\"\\n                                        \"],[\"open-element\",\"img\",[]],[\"static-attr\",\"class\",\"img-responsive\"],[\"static-attr\",\"src\",\"/assets/images/restaurant-square.png\"],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\n                                    \"],[\"close-element\"],[\"text\",\"\\n                                    \\n                                    \"],[\"open-element\",\"a\",[]],[\"static-attr\",\"class\",\"restaurant-img\"],[\"static-attr\",\"href\",\"#\"],[\"flush-element\"],[\"text\",\"\\n                                        \"],[\"open-element\",\"img\",[]],[\"static-attr\",\"class\",\"img-responsive\"],[\"static-attr\",\"src\",\"/assets/images/restaurant-square.png\"],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\n                                    \"],[\"close-element\"],[\"text\",\"\\n                               \"],[\"close-element\"],[\"text\",\"\\n                               \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"col-md-3\"],[\"flush-element\"],[\"text\",\"\\n                                   \"],[\"open-element\",\"a\",[]],[\"static-attr\",\"class\",\"restaurant-img\"],[\"static-attr\",\"href\",\"#\"],[\"flush-element\"],[\"text\",\"\\n                                        \"],[\"open-element\",\"img\",[]],[\"static-attr\",\"class\",\"img-responsive\"],[\"static-attr\",\"src\",\"/assets/images/restaurant-square.png\"],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\n                                    \"],[\"close-element\"],[\"text\",\"\\n                                    \\n                                    \"],[\"open-element\",\"a\",[]],[\"static-attr\",\"class\",\"restaurant-img\"],[\"static-attr\",\"href\",\"#\"],[\"flush-element\"],[\"text\",\"\\n                                        \"],[\"open-element\",\"img\",[]],[\"static-attr\",\"class\",\"img-responsive\"],[\"static-attr\",\"src\",\"/assets/images/restaurant-square.png\"],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\n                                    \"],[\"close-element\"],[\"text\",\"\\n                                    \\n                                    \"],[\"open-element\",\"a\",[]],[\"static-attr\",\"class\",\"restaurant-img\"],[\"static-attr\",\"href\",\"#\"],[\"flush-element\"],[\"text\",\"\\n                                        \"],[\"open-element\",\"img\",[]],[\"static-attr\",\"class\",\"img-responsive\"],[\"static-attr\",\"src\",\"/assets/images/restaurant-square.png\"],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\n                                    \"],[\"close-element\"],[\"text\",\"\\n                               \"],[\"close-element\"],[\"text\",\"\\n                            \"],[\"close-element\"],[\"text\",\"\\n                        \"],[\"close-element\"],[\"text\",\"\\n                    \"],[\"close-element\"],[\"text\",\"\\n                \"],[\"close-element\"],[\"text\",\"\\n            \"],[\"close-element\"],[\"text\",\"\\n\\n                \\n\\n\\n\\n        \"],[\"close-element\"],[\"text\",\"\\n    \"],[\"close-element\"]],\"locals\":[],\"named\":[],\"yields\":[],\"blocks\":[{\"statements\":[[\"text\",\"\\t\\t\\t\\t\\t\\t\\t\\t  \\t\\t\"],[\"open-element\",\"li\",[]],[\"static-attr\",\"class\",\"item\"],[\"flush-element\"],[\"text\",\"\\n\\t                                      \"],[\"open-element\",\"span\",[]],[\"flush-element\"],[\"append\",[\"unknown\",[\"menuItem\",\"name\"]],false],[\"close-element\"],[\"text\",\"\\n\\t                                      \"],[\"open-element\",\"span\",[]],[\"flush-element\"],[\"text\",\"$\"],[\"append\",[\"unknown\",[\"menuItem\",\"price\"]],false],[\"close-element\"],[\"text\",\"\\n\\t                                    \"],[\"close-element\"],[\"text\",\"\\n\\t                                    \"],[\"open-element\",\"li\",[]],[\"flush-element\"],[\"text\",\"\\n\\t                                        \"],[\"open-element\",\"span\",[]],[\"flush-element\"],[\"text\",\"\\n\\t                                            \"],[\"append\",[\"unknown\",[\"menuItem\",\"description\"]],false],[\"text\",\"\\n\\t                                        \"],[\"close-element\"],[\"text\",\"\\n\\t                                    \"],[\"close-element\"],[\"text\",\"\\n\"]],\"locals\":[\"menuItem\"]},{\"statements\":[[\"text\",\"                    \\t\\t\"],[\"append\",[\"helper\",[\"g-map-marker\"],[[\"get\",[\"context\"]]],[[\"lat\",\"lng\"],[[\"get\",[\"model\",\"lat\"]],[\"get\",[\"model\",\"long\"]]]]],false],[\"text\",\"\\n\"]],\"locals\":[\"context\"]},{\"statements\":[[\"text\",\"\\t\\t\\t\\t\\t    $\\n\"]],\"locals\":[\"star\"]},{\"statements\":[[\"text\",\"\\t\\t\\t\\t  \\t\\t\"],[\"open-element\",\"b\",[]],[\"flush-element\"],[\"text\",\"$\"],[\"close-element\"],[\"text\",\"\\n\"]],\"locals\":[\"star\"]},{\"statements\":[[\"text\",\"\\t\\t\\t\\t\\t    \\t\"],[\"open-element\",\"i\",[]],[\"static-attr\",\"class\",\"fa fa-star inactive\"],[\"static-attr\",\"aria-hidden\",\"true\"],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\n\"]],\"locals\":[\"star\"]},{\"statements\":[[\"text\",\"\\t\\t\\t\\t\\t  \\t\\t\"],[\"open-element\",\"i\",[]],[\"static-attr\",\"class\",\"fa fa-star\"],[\"static-attr\",\"aria-hidden\",\"true\"],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\n\"]],\"locals\":[\"star\"]}],\"hasPartials\":false}", "meta": { "moduleName": "restaurants-app/templates/restaurants/restaurant.hbs" } });
});
define("restaurants-app/templates/terms-of-use", ["exports"], function (exports) {
  exports["default"] = Ember.HTMLBars.template({ "id": "9v63xEQ5", "block": "{\"statements\":[[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"container\"],[\"static-attr\",\"id\",\"normal-container\"],[\"flush-element\"],[\"text\",\"\\n   \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"row\"],[\"flush-element\"],[\"text\",\"\\n      \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"col-md-8 col-sm-8 col-xs-12 col-centered\"],[\"flush-element\"],[\"text\",\"\\n          \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"row\"],[\"flush-element\"],[\"text\",\"\\n            \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"col-md-12 col-sm-12 col-xs-12 page-title v-align-center\"],[\"flush-element\"],[\"text\",\"\\n                Terms of Use\\n            \"],[\"close-element\"],[\"text\",\"\\n          \"],[\"close-element\"],[\"text\",\"\\n          \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"row\"],[\"flush-element\"],[\"text\",\"\\n              \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"col-md-12\"],[\"flush-element\"],[\"text\",\"\\n                  \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"card padded\"],[\"flush-element\"],[\"text\",\"\\n                      \"],[\"open-element\",\"div\",[]],[\"static-attr\",\"class\",\"content\"],[\"flush-element\"],[\"text\",\"\\n                              \"],[\"open-element\",\"p\",[]],[\"static-attr\",\"class\",\"regular-text\"],[\"flush-element\"],[\"text\",\"\\n                                  \"],[\"open-element\",\"b\",[]],[\"flush-element\"],[\"text\",\"Introduction\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\nThese Website Standard Terms and Conditions written on this webpage shall manage your use of this website. These Terms will be applied fully and affect to your use of this Website. By using this Website, you agreed to accept all terms and conditions written in here. You must not use this Website if you disagree with any of these Website Standard Terms and Conditions.\\n\\nMinors or people below 18 years old are not allowed to use this Website.\\n\\n                                  \"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"b\",[]],[\"flush-element\"],[\"text\",\"Intellectual Property Rights\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\nOther than the content you own, under these Terms, Restaurants and/or its licensors own all the intellectual property rights and materials contained in this Website.\\n\\nYou are granted limited license only for purposes of viewing the material contained on this Website.\\n\\n                                  \"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"b\",[]],[\"flush-element\"],[\"text\",\"Restrictions\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\nYou are specifically restricted from all of the following\\n                          \"],[\"close-element\"],[\"text\",\"\\n\"],[\"open-element\",\"ul\",[]],[\"static-attr\",\"class\",\"regular-text\"],[\"flush-element\"],[\"text\",\"\\n\"],[\"open-element\",\"li\",[]],[\"flush-element\"],[\"text\",\"publishing any Website material in any other media;\"],[\"close-element\"],[\"text\",\"\\n\"],[\"open-element\",\"li\",[]],[\"flush-element\"],[\"text\",\"selling, sublicensing and/or otherwise commercializing any Website material;\"],[\"close-element\"],[\"text\",\"\\n\"],[\"open-element\",\"li\",[]],[\"flush-element\"],[\"text\",\"publicly performing and/or showing any Website material;\"],[\"close-element\"],[\"text\",\"\\n\"],[\"open-element\",\"li\",[]],[\"flush-element\"],[\"text\",\"using this Website in any way that is or may be damaging to this Website;\"],[\"close-element\"],[\"text\",\"\\n\"],[\"open-element\",\"li\",[]],[\"flush-element\"],[\"text\",\"using this Website in any way that impacts user access to this Website;\"],[\"close-element\"],[\"text\",\"\\n\"],[\"open-element\",\"li\",[]],[\"flush-element\"],[\"text\",\"using this Website contrary to applicable laws and regulations, or in any way may cause harm to the Website, or to any person or business entity;\"],[\"close-element\"],[\"text\",\"\\n\"],[\"open-element\",\"li\",[]],[\"flush-element\"],[\"text\",\"engaging in any data mining, data harvesting, data extracting or any other similar activity in relation to this Website;\"],[\"close-element\"],[\"text\",\"\\n\"],[\"open-element\",\"li\",[]],[\"flush-element\"],[\"text\",\"using this Website to engage in any advertising or marketing.\"],[\"close-element\"],[\"text\",\"\\n\"],[\"close-element\"],[\"text\",\"\\n\"],[\"open-element\",\"p\",[]],[\"static-attr\",\"class\",\"regular-text\"],[\"flush-element\"],[\"text\",\"\\nCertain areas of this Website are restricted from being access by you and Restaurants may further restrict access by you to any areas of this Website, at any time, in absolute discretion. Any user ID and password you may have for this Website are confidential and you must maintain confidentiality as well.\\n\\n                                  \"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"b\",[]],[\"flush-element\"],[\"text\",\"Your Content\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\nIn these Website Standard Terms and Conditions, “Your Content” shall mean any audio, video text, images or other material you choose to display on this Website. By displaying Your Content, you grant Restaurants a non-exclusive, worldwide irrevocable, sub licensable license to use, reproduce, adapt, publish, translate and distribute it in any and all media.\\n\\nYour Content must be your own and must not be invading any third-party’s rights. Restaurants reserves the right to remove any of Your Content from this Website at any time without notice.\\n\\n                                  \"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"b\",[]],[\"flush-element\"],[\"text\",\"No warranties\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\nThis Website is provided “as is,” with all faults, and Restaurants express no representations or warranties, of any kind related to this Website or the materials contained on this Website. Also, nothing contained on this Website shall be interpreted as advising you.\\n\\n                                  \"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"b\",[]],[\"flush-element\"],[\"text\",\"Limitation of liability\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\nIn no event shall Restaurants, nor any of its officers, directors and employees, shall be held liable for anything arising out of or in any way connected with your use of this Website whether such liability is under contract.  Restaurants, including its officers, directors and employees shall not be held liable for any indirect, consequential or special liability arising out of or in any way related to your use of this Website.\\n\\n                                  \"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"b\",[]],[\"flush-element\"],[\"text\",\"Indemnification\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\nYou hereby indemnify to the fullest extent Restaurants from and against any and/or all liabilities, costs, demands, causes of action, damages and expenses arising in any way related to your breach of any of the provisions of these Terms.\\n\\n                                  \"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"b\",[]],[\"flush-element\"],[\"text\",\"Severability\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\nIf any provision of these Terms is found to be invalid under any applicable law, such provisions shall be deleted without affecting the remaining provisions herein.\\n\\n                                  \"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"b\",[]],[\"flush-element\"],[\"text\",\"Variation of Terms\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\nRestaurants is permitted to revise these Terms at any time as it sees fit, and by using this Website you are expected to review these Terms on a regular basis.\\n\\n                                  \"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"b\",[]],[\"flush-element\"],[\"text\",\"Assignment\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\nThe Restaurants is allowed to assign, transfer, and subcontract its rights and/or obligations under these Terms without any notification. However, you are not allowed to assign, transfer, or subcontract any of your rights and/or obligations under these Terms.\\n\\n                                  \"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"b\",[]],[\"flush-element\"],[\"text\",\"Entire Agreement\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\nThese Terms constitute the entire agreement between Restaurants and you in relation to your use of this Website, and supersede all prior agreements and understandings.\\n\\n                                  \"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"b\",[]],[\"flush-element\"],[\"text\",\"Governing Law ∧ Jurisdiction\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"open-element\",\"br\",[]],[\"flush-element\"],[\"close-element\"],[\"text\",\"\\nThese Terms will be governed by and interpreted in accordance with the laws of the State of Bosnia and Herzegovina, and you submit to the non-exclusive jurisdiction of the state and federal courts located in Bosnia and Herzegovina for the resolution of any disputes.\\n\\nThese terms and conditions have been generated at termsandcondiitionssample.com.\\n                             \\n                              \"],[\"close-element\"],[\"text\",\"\\n                      \"],[\"close-element\"],[\"text\",\"\\n                  \"],[\"close-element\"],[\"text\",\"\\n              \"],[\"close-element\"],[\"text\",\"\\n          \"],[\"close-element\"],[\"text\",\"\\n       \"],[\"close-element\"],[\"text\",\"\\n       \\n   \"],[\"close-element\"],[\"text\",\"\\n   \\n\"],[\"close-element\"]],\"locals\":[],\"named\":[],\"yields\":[],\"blocks\":[],\"hasPartials\":false}", "meta": { "moduleName": "restaurants-app/templates/terms-of-use.hbs" } });
});
/* jshint ignore:start */



/* jshint ignore:end */

/* jshint ignore:start */

define('restaurants-app/config/environment', ['ember'], function(Ember) {
  var prefix = 'restaurants-app';
/* jshint ignore:start */

try {
  var metaName = prefix + '/config/environment';
  var rawConfig = document.querySelector('meta[name="' + metaName + '"]').getAttribute('content');
  var config = JSON.parse(unescape(rawConfig));

  var exports = { 'default': config };

  Object.defineProperty(exports, '__esModule', { value: true });

  return exports;
}
catch(err) {
  throw new Error('Could not read config from meta tag with name "' + metaName + '".');
}

/* jshint ignore:end */

});

/* jshint ignore:end */

/* jshint ignore:start */

if (!runningTests) {
  require("restaurants-app/app")["default"].create({"LOG_ACTIVE_GENERATION":true,"LOG_TRANSITIONS":true,"LOG_TRANSITIONS_INTERNAL":true,"LOG_VIEW_LOOKUPS":true,"name":"restaurants-app","version":"0.0.0+e293eee1"});
}

/* jshint ignore:end */
//# sourceMappingURL=restaurants-app.map
