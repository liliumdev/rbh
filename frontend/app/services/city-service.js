import BaseService from 'restaurants-app/services/base-service';
import Location from 'restaurants-app/models/location';

export default BaseService.extend({
    all: function() {
        var cities = [];
        this.ajax({ url: "cities", type: "GET" }).then(function(data) {
            data.forEach(function(city) {
                cities.addObject(Location.create(city));
            });
        });
        return cities;
    },

    create: function(countryId, name, boundary) {
        // First point in boundary must be the same as the last point
        boundary.pushObject(boundary[0]);

        var data = {
            name: name,
            boundary: {"type": "MultiPolygon", "coordinates": [ boundary ]},
            country: {id: countryId}
        };

        return this.ajax({ url: `countries/${countryId}/cities`, type: "POST", data: JSON.stringify(data)});
    },

    delete: function(cityId) {
        return this.ajax({ url: `cities/${cityId}`, type: "DELETE" });
    },

    edit: function(cityId, data) {
        return this.ajax({ url: `cities/${cityId}`, type: "PUT", data: JSON.stringify(data) });
    },

    editBoundary: function(cityId, boundary) {
        // First point in boundary must be the same as the last point
        boundary.pushObject(boundary[0]);
        
        var data = {
            boundary: {"type": "MultiPolygon", "coordinates": [ boundary ]}
        };

        return this.ajax({ url: `cities/${cityId}/boundary`, type: "PUT", data: JSON.stringify(data) });
    },
});
