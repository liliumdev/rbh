import Ember from 'ember';
import BaseRoute from 'restaurants-app/routes/base-route';
import Restaurant from 'restaurants-app/models/restaurant';

export default BaseRoute.extend({
	setupController: function(controller, models) {		
	    controller.set('restaurant', Restaurant.create({
	    	ready: false,
	    	diningTables: [], 
	    	menus: [{name: "Main menu", menuItems: []}], 
	    	photos: [], 
	    	categories: [],
	    	latLong: {type: "Point", coordinates: [43.854460, 18.380985]},
	    	logoImageUrl: "",
	    	coverImageUrl: ""
	    }));
	    controller.set('mapLat', 43.854460);
	    controller.set('mapLong', 18.380985);	
	}
});
