import BaseService from 'restaurants-app/services/base-service';

export default BaseService.extend({
  citiesWithCount: function() {
  	return this.ajax({url: `cities/with-count`, type: "GET"});
  },
});
