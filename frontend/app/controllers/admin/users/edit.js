import Ember from 'ember';
import Account from 'restaurants-app/models/account';

export default Ember.Controller.extend({
    accountService: Ember.inject.service(),
    flashMessages: Ember.inject.service(),

	actions: {
		edit: function() {
			if(this.get('model.account.password') == '') {
				this.set('model.account.password', null);			
            }

			const flashMessages = Ember.get(this, 'flashMessages');
			this.get('accountService').edit(this.get('model.account.id'), this.get('model.account')).then(function(newAccount) {
                this.transitionToRoute('admin.users.index');
                flashMessages.success("Modified an account.");
			}.bind(this), function(data) {
                flashMessages.danger("Couldn't modify an account:" + data.responseText);
			}.bind());
		}
	}
});
