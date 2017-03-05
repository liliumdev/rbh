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

    filter: function(options) {
        var restaurants = [];
        this.ajax({ url: `restaurants/filter`, type: "POST", data: JSON.stringify(options)}).then(function(data) {
            data.forEach(function(restaurant) {
                restaurants.addObject(Restaurant.create(restaurant));
            });
        });     
        return restaurants;
    },

    all: function(limit) {
        var restaurants = [];
        this.ajax({ url: `restaurants`, type: "GET"}).then(function(data) {
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
    },

    /* Admin stuff */
    uploadImages: function(restaurant) {
        // Let's test uploading of file
        var data = new FormData();
        data.append("files[]", restaurant.logoImageUrl); // logoImageUrl is actually a File
        data.append("files[]", restaurant.coverImageUrl); // coverImageUrl is actually a File

        for(var i = 0; i < restaurant.photos.length; i++) {
            data.append("files[]", restaurant.photos[i]);
        }

        return this.ajaxWithoutContentType({
            url: 'restaurants/upload-images',
            data: data,
            cache: false,
            contentType: false,
            processData: false,
            type: 'POST'
        });
    }
});
