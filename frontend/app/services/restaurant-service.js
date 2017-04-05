import BaseService from 'restaurants-app/services/base-service';
import Restaurant from 'restaurants-app/models/restaurant';
import Review from 'restaurants-app/models/review';
import Reservation from 'restaurants-app/models/reservation';

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

    nearestRestaurants: function(limit, lat, lng) {
        // If lat or long is 0, returns best rated restaurants
        var restaurants = [];
        this.ajax({ url: `restaurants/nearest?limit=${limit}&lat=${lat}&lng=${lng}`, type: "GET"}).then(function(data) {
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

    all: function() {
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

    getById_wo_reservations: function(id) {
        return this.ajax({ url: `restaurants/${id}`, type: "GET"});        
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
    uploadImages: function(restaurant, logoProvided, coverProvided) {
        var isLogoProvided = logoProvided ? 1 : 0;
        var isCoverProvided = coverProvided ? 1 : 0;

        // Let's test uploading of file
        var data = new FormData();

        if(logoProvided) {
            data.append("files[]", restaurant.logoImageUrl); // logoImageUrl is actually a File
        }
        if(coverProvided) {
            data.append("files[]", restaurant.coverImageUrl); // coverImageUrl is actually a File
        }

        for(var i = 0; i < restaurant.photos.length; i++) {
            data.append("files[]", restaurant.photos[i]);
        }

        // Were there any images actually to upload?
        if(!logoProvided && !coverProvided && restaurant.photos.length === 0) {
            // Just return an empty promise
            return new Ember.RSVP.Promise(function(resolve, reject) { resolve("Empty"); });
        }

        return this.ajaxWithoutContentType({
            url: `restaurants/upload-images?logoProvided=${isLogoProvided}&coverProvided=${isCoverProvided}`,
            data: data,
            cache: false,
            contentType: false,
            processData: false,
            type: 'POST'
        });
    },

    add: function(restaurant) {
        // This was only a helper field (country id), also we should delete the boundary
        delete restaurant.city.country;
        delete restaurant.city.boundary; // causes problems on the backend

        return this.ajax({ url: 'restaurants', type: "POST", data: JSON.stringify(restaurant)});
    },

    update: function(restaurant) { // This was only a helper field (country id), also we should delete the boundary
        delete restaurant.city.country;
        delete restaurant.city.boundary; // causes problems on the backend


        return this.ajax({ url: `restaurants/${restaurant.id}`, type: "PUT", data: JSON.stringify(restaurant)});
    },

    delete: function(restaurantId) {
        return this.ajax({ url: `restaurants/${restaurantId}`, type: "DELETE"});
    },

    deleteImage: function(filename, directory, thumb, restaurantId) {
        var doThumb = thumb ? 1 : 0;
        return this.ajax({ url: `restaurants/delete-image/${restaurantId}/${filename}/${directory}?thumb=${doThumb}`, type: "DELETE"});
    },

    allReservations: function(restaurantId, time) {
        var reservations = Ember.A([]);
        this.ajax({ url: `restaurants/${restaurantId}/reservations/${time}`, type: "GET"}).then(function(data) {
            data.forEach(function(reservation) {
                reservations.addObject(Reservation.create(reservation));
            });
        });     
        return reservations;
    }
});
