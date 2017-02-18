import Ember from 'ember';
import $ from 'jquery';
import config from '../config/environment';

export default Ember.Service.extend({
    session: Ember.inject.service('session'),

    ajax: function(params) {
        /* Add the JSON Web Token to the request if we're logged in */
        var token = this.get('session.data.authenticated.token');
        if(token !== undefined) {
        	params.beforeSend = function(request) {
        		request.setRequestHeader("Authorization", "Bearer " + token);
        	};
        }
        
        params.url = `${config.apiHost}/api/v${config.apiVersion}/${params.url}`;
        params.contentType = "application/json";
        return $.ajax(params);
    }
});
