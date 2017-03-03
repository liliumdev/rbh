import Ember from 'ember';


export default Ember.Controller.extend({
	currentUser: Ember.inject.service(),

	// Which routes have a transparent navbar
	navbarClass: function() {
        var routesTransparentNavbar = [
            'index',
            'restaurants.restaurant.index',
            'restaurants.restaurant-reserve'
        ];
        return ($.inArray(this.get('currentPath'), routesTransparentNavbar) != -1 ? "navbar-transparent" : "navbar-white");
	 }.property('currentPath')
});
