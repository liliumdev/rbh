import Ember from 'ember';
import BaseService from 'restaurants-app/services/base-service';
import Account from 'restaurants-app/models/account';

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
    },

    // admin    
    all: function() {
        var accounts = [];
        this.ajax({ url: "accounts", type: "GET" }).then(function(data) {
            data.forEach(function(account) {
                accounts.addObject(Account.create(account));
            });
        });        
        return accounts;
    },

    getById: function(id) {
        var account = Account.create({});
        this.ajax({ url: `accounts/${id}`, type: "GET"}).then(function(data) {
            account.setProperties(data);
        });        

        return account;
    },

    me: function() {
        var account = Account.create({});
        this.ajax({ url: `accounts/me`, type: "GET"}).then(function(data) {
            account.setProperties(data);
        });        

        return account;
    },

    edit: function(accountId, data) {
        // This was only a helper field (country id)
        delete data.city.country;
        return this.ajax({ url: `accounts/${accountId}`, type: "PUT", data: JSON.stringify(data) });
    },

    create: function(data) {
        return this.ajax({ url: `accounts/${data.role}`, type: "POST", data: JSON.stringify(data)})
    },

    delete: function(accountId) {
        return this.ajax({ url: `accounts/${accountId}`, type: "DELETE"});
    },

    giveRole: function(accountId, role) {
        var motion = (role === 1 ? "promote" : "demote");
        return this.ajax({ url: `accounts/${accountId}/${motion}`, type: "GET"})
    },
});
