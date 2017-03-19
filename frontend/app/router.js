import Ember from 'ember';
import config from './config/environment';

const Router = Ember.Router.extend({
  location: config.locationType,
  rootURL: config.rootURL
});

Router.map(function() {
  this.route('restaurants', function() {
    this.route('restaurant', {path: ':id'}, function() {});
    //this.route('restaurant-reserve');
  });
  this.route('about');
  this.route('privacy-policy');
  this.route('terms-of-use');
  this.route('login');
  this.route('logout');
  this.route('admin', function() {
    this.route('users', function() {
      this.route('edit', {path: ':id'});
    });
    this.route('categories', function() {});
    this.route('locations', function() {});
    this.route('restaurants', function() {
      this.route('new', function() {
        this.route('menu');
        this.route('gallery');
        this.route('tables');
      });
      this.route('edit', {path: ':id'}, function() {});
    });
  });
  this.route('register');
  this.route('forgot-password');
  this.route('reset-password', {path: '/reset-password/:token'});
  this.route('categories', {path: '/categories/:name'});
  this.route('reserve');
  this.route('my-reservations');

  this.route('users', function() {});
});

export default Router;
