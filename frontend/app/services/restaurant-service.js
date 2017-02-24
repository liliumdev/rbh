import Ember from 'ember';
import BaseService from 'restaurants-app/services/base-service';
import Restaurant from 'restaurants-app/models/restaurant';
import Review from 'restaurants-app/models/review';

export default BaseService.extend({
    randomRestaurants: function(limit) {
    	var restaurants = [];
	  	this.ajax({ url: `restaurants?limit=${limit}`, type: "GET"}).then(function(data) {
	  		data.forEach(function(restaurant) {
	            restaurants.addObject(Restaurant.create(restaurant));
	        });
	  	});  	
	  	return restaurants;
    },

    getById: function(id) {
    	var restaurant = Restaurant.create({});
    	this.ajax({ url: `restaurants/${id}`, type: "GET"}).then(function(data) {
    		restaurant.setProperties(data);
    	});        
        this.ajax({ url: `restaurants/${id}/reservations-today`, type: "GET"}).then(function(data) {
            restaurant.set('reservationsToday', data);
        });
    	return restaurant;
    },

    rate: function(restaurant, review) {
        return this.ajax({ url: `restaurants/${restaurant.id}/rate`, type: "POST", 
                           data: JSON.stringify({rating: review.rating, description: review.description})
                        });
    },

    didRate: function(id) {
        var review = Review.create({rating: 1, description: "", didRate: false});
        if(!this.get('session.isAuthenticated')) {
            return review;
        }

        this.ajax({ url: `restaurants/${id}/did-rate`, type: "GET"}).then(function(data) {
            review.set('didRate', data);
        });
        return review;
    }
});
