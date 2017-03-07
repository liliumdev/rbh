import Ember from 'ember';
import BaseModel from 'restaurants-app/models/base-model';

var _modelProperties = ['id', 'name', 'description', 'reviewRating', 'reviewCount', 'logoImageUrl', 
						'coverImageUrl', 'pricing', 'latLong', 'categories', 'menus', 'latLong', 
						'categories', 'photos'];

export default BaseModel.extend({
	modelProperties: _modelProperties,

	/* Helper function to access latitude and longitude from API */
	coordinates: Ember.computed('latLong', function() {
		return this.getWithDefault('latLong', {coordinates: [0, 0]});
	}),

	/* Latitude */
	lat: Ember.computed('latLong', function() {
		return this.get('coordinates').coordinates[0];
	}),

	/* Longitude */
	long: Ember.computed('latLong', function() {
		return this.get('coordinates').coordinates[1];
	}),


	/* Helper properties for displaying restaurant data */
	roundRating: Ember.computed('reviewRating', function() {
		return Math.round(this.getWithDefault('reviewRating', 0) * 10)/10;
	}),

	/* Returns the menu items from the active menu */
	menuItems: Ember.computed('menus', function() {
		return this.getWithDefault('menus', [{"menuItems": []}])[0]["menuItems"];
	}),

	/* Number of active stars in rating */
	activeStars: Ember.computed('reviewRating', function() {
		return new Array(Math.round(this.getWithDefault('reviewRating', 0)));
	}),

	/* Number of inactive stars in rating */
	inactiveStars: Ember.computed('reviewRating', function() {
		var inactive = 5 - Math.round(this.getWithDefault('reviewRating', 0));
		if(inactive != 0)
			return new Array(inactive);

		return 5;
	}),

	/* Number of active dollars in pricing */
	activeDollars: Ember.computed('pricing', function() {
		return new Array(this.getWithDefault('pricing', 0));
	}),

	/* Number of inactive dollars in pricing */
	inactiveDollars: Ember.computed('pricing', function() {
		return new Array(4 - this.getWithDefault('pricing', 0));
	})
});