import Ember from 'ember';

export default Ember.Component.extend({
	flashMessages: Ember.inject.service(),

	actions: {
		reorderItems: function(itemModels, draggedModel) {
			this.set('new.menuItems', itemModels);
			var menuItems = this.get('new.menuItems');

			for(var i = 0; i < menuItems.length; i++) {
				Ember.set(menuItems[i], 'sort', i);
			}

			this.send('apply');
		},

		addMenuItem: function(item) {
			this.get('new.menuItems').addObject({name: "", description: "", price: 0});
		},

		deleteMenuItem: function(item) {
			this.get('new.menuItems').removeObject(item);
		},

		apply: function() {	
            const flashMessages = Ember.get(this, 'flashMessages');

			var menuItems = this.get('new.menuItems');

            // Let's check are there any invalid items (empty name or invalid price)
            for(var i = 0; i < menuItems.length; i++) {
            	var itemPrice = menuItems[i].price;
            	if(Ember.isEmpty(menuItems[i].name)) {            		
                	flashMessages.danger("Couldn't apply changes, there seem to be some menu items without a name.");
                	return;
            	} else if(Ember.isEmpty(itemPrice) || itemPrice === 0) {
                	flashMessages.danger("Couldn't apply changes, there seem to be some menu items without a price.");
                	return;
            	} else if(itemPrice < 0) {
                	flashMessages.danger("Item price can't be negative!");
                	return;
            	} else if(!(!isNaN(parseFloat(itemPrice)) && isFinite(itemPrice))) {
                	flashMessages.danger("Item price must be a number!");
                	return;
            	}
            	delete menuItems[i].id;
			}

        	flashMessages.success("Applied changes!");
			this.set('new.restaurant.menus', [{
	    		name: "Main menu", 
	    		menuItems: this.get('new.menuItems')
	    	}]);
		}
	}
});
