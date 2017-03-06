import Ember from 'ember';
import BaseService from 'restaurants-app/services/base-service';

export default BaseService.extend({
    stats: function(id) {
    	var stats = Ember.Object.create();
    	this.ajax({ url: `admin`, type: "GET"}).then(function(data) {
    		stats.setProperties(data);
    	});     
    	return stats;
    },
});
