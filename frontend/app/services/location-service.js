import Ember from 'ember';
import BaseService from 'restaurants-app/services/base-service';

export default BaseService.extend({
  ciitesWithCount: function() {
  	return this.ajax({url: `cities/with-count`, type: "GET"});
  },
});
