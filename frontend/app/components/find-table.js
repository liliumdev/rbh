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

    // Without AM and PM
    //selectTimeOptions: ['08:00', '08:30', '09:00', '09:30', '10:00', '10:30', '11:00', '11:30', '12:00', '12:30', '13:00', '13:30', '14:00', '14:30', '15:00', '15:30', '16:00', '16:30', '17:00', '17:30', '18:00', '18:30', '19:00', '19:30', '20:00', '20:30', '21:00', '21:30'],

    // Generates without AM and PM
   /* selectTimeOptions: Ember.computed(function() {
        // Restaurants are assumed to work from 08:00 to 21:30
        availableHours = [];
        var dt = new Date(1970, 0, 1, 8, 0, 0, 0);

        while (dt.getUTCHours() <= 20) {
            availableHours.push(dt.toLocaleTimeString('en-GB').slice(0, -3)); // HH:MM:SS format, trim seconds
            dt.setMinutes(dt.getMinutes() + 30); // Offer time slots in half-hour increments
        }

        return availableHours;
    })*/
});
