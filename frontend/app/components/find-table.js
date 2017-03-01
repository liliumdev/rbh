import Ember from 'ember';

export default Ember.Component.extend({
    selectPeopleOptions: ['2 people', '3 people', '4 people', '5 people', '6 people', '7 people', '8 people', '9 people', '10 people'],
    selectTimeOptions: ['08:00 AM', '08:30 AM', '9:00 AM', '9:30 AM', '10:00 AM', '10:30 AM', '11:00 AM', '11:30 AM', '12:00 PM', '12:30 PM', '01:00 PM', '01:30 PM', '02:00 PM', '02:30 PM', '03:00 PM', '03:30 PM', '04:00 PM', '04:30 PM', '05:00 PM', '05:30 PM', '06:00 PM', '06:30 PM', '07:00 PM', '07:30 PM', '08:00 PM', '08:30 PM', '09:00 PM', '09:30 PM'],
    startDate: new Date(),
    endDate: new Date(),

    init: function() {
    	this._super();
    	this.set('startDate', new Date());
    	var today = new Date();
    	today.setMonth(today.getMonth() + 2); // You can reserve up to two months in front
    	this.set('endDate', today);
    }
});
