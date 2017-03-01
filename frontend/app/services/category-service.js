import Ember from 'ember';
import BaseService from 'restaurants-app/services/base-service';
import Category from 'restaurants-app/models/category';

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

    getById: function(categoryId) {
        var category = Category.create({});
        this.ajax({ url: `categories/${id}`, type: "GET"}).then(function(data) {
            category.setProperties(data);
        });        
        return category;
    },

    create: function(category) {
        return this.ajax({ url: "categories", type: "POST", data: JSON.stringify(category) });
    },

    delete: function(categoryId) {
    	return this.ajax({ url: `categories/${categoryId}`, type: "DELETE"});
    },

    edit: function(categoryId, data) {
        return this.ajax({ url: `categories/${categoryId}`, type: "PUT", data: JSON.stringify(data) });
    },


});
