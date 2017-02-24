import Ember from 'ember';
import config from './config/environment';

const Router = Ember.Router.extend({
  location: config.locationType,
  rootURL: config.rootURL
});

Router.map(function() {
  this.route('restaurants', function() {
    this.route('restaurant', {path: ':id'});
  });
  this.route('about');
  this.route('privacy-policy');
  this.route('terms-of-use');
  this.route('login');
  this.route('logout');
  this.route('admin');
  this.route('register');
  this.route('forgot-password');
  this.route('reset-password', {path: '/reset-password/:token'});
  this.route('categories', {path: '/categories/:category'});
  this.route('reserve');
  this.route('reserve.register');
  this.route('reserve.login');
});

export default Router;
