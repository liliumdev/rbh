import Ember from 'ember';
import AdminRoute from 'restaurants-app/routes/admin-route';
import Restaurant from 'restaurants-app/models/restaurant';
import MenuItem from 'restaurants-app/models/menu-item';
import DiningTable from 'restaurants-app/models/dining-table';
import Photo from 'restaurants-app/models/photo';

export default AdminRoute.extend({

	setupController: function(controller, models) {		
	    controller.set('restaurant', Restaurant.create({
	    	name: "",
	    	diningTables: [], 
	    	menus: [{
	    		name: "Main menu", 
	    		menuItems: []
	    	}], 
	    	photos: [], 
	    	categoriesList: Ember.A([]),
	    	latLongPoint: {type: "Point", coordinates: [43.854460, 18.380985]},
	    	logoImageUrl: "",
	    	coverImageUrl: "",
	    	pricing: 1,
	    	workingTimeFrom: new Date(0, 0, 0, 8, 0, 0), // 8 AM by default
			workingTimeTo: new Date(0, 0, 0, 23, 0, 0), // 11 PM by default
			minimumCancelTime: new Date(0, 0, 0, 0, 0, 0), // you can cancel anytime
	    }));

	    controller.set('menuItems', Ember.A([MenuItem.create({name: "", description: "", price: ""})]));
	    controller.set('diningTables', Ember.A([DiningTable.create({amount: "", persons: ""})]));
	    controller.set('mapLat', 43.854460);
	    controller.set('mapLong', 18.380985);	
	    controller.set('chosenLogoName', "");
	    controller.set('chosenCoverName', "");
	    controller.set('uploading', false)
	}
});
