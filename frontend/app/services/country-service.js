import BaseService from 'restaurants-app/services/base-service';

export default BaseService.extend({
  all: function() {
  	var countries = [];
  	this.ajax({url: "countries", type: "GET"}).then(function(data) {
  		data.forEach(function(country) {
            countries.addObject(country);
        });
  	});  	
  	return countries;
  },

  cities: function(country_id) {
  	var cities = [];
  	this.ajax({url: `countries/${country_id}`, type: "GET"}).then(function(data) {
  		data.cities.forEach(function(city) {
            cities.addObject(city);
        });
  	});  	
  	return cities;
  }
});
