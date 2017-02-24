import Ember from 'ember';
import BaseService from 'restaurants-app/services/base-service';

export default BaseService.extend({
    all: function() {
        var cities = [];
        this.ajax({ url: "cities", type: "GET" }).then(function(data) {
            data.forEach(function(city) {
                cities.addObject(city);
            });
        });
        return cities;
    },

    create: function(countryId, name) {
        return this.ajax({ url: `countries/${countryId}/cities`, type: "POST", data: JSON.stringify({ name: name }) });
    },

    delete: function(cityId) {
        return this.ajax({ url: `cities/${cityId}`, type: "DELETE" });
    }


});
