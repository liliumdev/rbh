import Ember from 'ember';
import AuthenticatedRoute from 'restaurants-app/routes/authenticated-route';

export default AuthenticatedRoute.extend({
	redirect: function() {
		this.get('session').invalidate();
		this.transitionTo('index');
	}
});
