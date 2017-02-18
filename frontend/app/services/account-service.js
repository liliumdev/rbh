import Ember from 'ember';
import BaseService from 'restaurants-app/services/base-service';

export default BaseService.extend({
    emailExists: function(email) {
    	return this.ajax({ url: `accounts/exists/${email}`, type: "GET" });
    },
    register: function(data) {
    	return this.ajax({ url: 'accounts', type: "POST", data: JSON.stringify(data)})
    },
    forgotPassword: function(email) {
    	return this.ajax({ url: 'accounts/forgot', type: "POST", data: JSON.stringify({email: email})})
    },
    resetPassword: function(token, password) {
    	return this.ajax({ url: `accounts/reset/${token}`, type: "POST", data: JSON.stringify({password: password}) });
    }
});
