import Ember from 'ember';
import BaseService from 'restaurants-app/services/base-service';

export default BaseService.extend({
    all: function() {
        var categories = [];
        this.ajax({ url: "categories", type: "GET" }).then(function(data) {
            data.forEach(function(category) {
                categories.addObject(category);
            });
        });
        return categories;
    },
    create: function(category) {
        return this.ajax({ url: "categories", type: "POST", data: JSON.stringify(category) });
    },
    delete: function(categoryId) {
    	return this.ajax({ url: `categories/${categoryId}`, type: "DELETE"});
    }
});
