import Ember from 'ember';

export default Ember.Component.extend({
	actions: {
		addTable: function(table) {
			this.get('new.diningTables').addObject({persons: "", amount: ""});
		},

		deleteTable: function(table) {
			this.get('new.diningTables').removeObject(table);
		},

		apply: function() {	
            const flashMessages = Ember.get(this, 'flashMessages');

			var tables = this.get('new.diningTables');

            // Let's check are there any invalid items (empty name or invalid price)
            for(var i = 0; i < tables.length; i++) {
            	var persons = tables[i].persons;
            	var amount = tables[i].amount;
            	if(Ember.isEmpty(persons)) {            		
                	flashMessages.danger("Couldn't apply changes, there seem to be some tables without the number of persons.");
                	return;
            	} else if(Ember.isEmpty(amount)) {
                	flashMessages.danger("Couldn't apply changes, there seem to be some tables without an amount.");
                	return;
            	} else if(persons % 1 !== 0) {
                	flashMessages.danger("Number of persons must be an integer value!");
                	return;
            	} else if(amount % 1 !== 0) {
            		flashMessages.danger("Amount must be an integer value!");
                	return;
            	} else if(persons <= 0 || amount <= 0) {
                	flashMessages.danger("Number of persons and amount must be above zero!");
                	return;
            	}
			}

        	flashMessages.success("Applied changes!");
			this.set('new.restaurant.diningTables', this.get('new.diningTables'));
		}
	}
});
