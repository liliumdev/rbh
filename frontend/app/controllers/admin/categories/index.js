import Ember from 'ember';
import Category from 'restaurants-app/models/category';

export default Ember.Controller.extend({
    categoryService: Ember.inject.service(),
    flashMessages: Ember.inject.service(),

	showAdd: false,
	category: {},

	actions: {
		add: function() {
			const flashMessages = Ember.get(this, 'flashMessages');

			this.get('categoryService').create(this.get('category')).then(function(newCategory) {
				this.get('model.categories').pushObject(newCategory); 
				this.set('category.name', '');
			}.bind(this), function(data) {
                flashMessages.danger(data.responseText);
			}.bind(this));
			console.log(this.get('category'));
		},

		delete: function(category) {
			const flashMessages = Ember.get(this, 'flashMessages');
			this.get('categoryService').delete(category.id).then(function() {
				this.get('model.categories').removeObject(category); 
			}.bind(this), function(data) {
                flashMessages.danger(data.responseText);
			}.bind(this));
		},

		edit: function(category) {
			const flashMessages = Ember.get(this, 'flashMessages');
			this.get('categoryService').edit(category.id, {name: category.name}).then(function() {
				// Basically the changes should just be visible
			}.bind(this), function(data) {
                flashMessages.danger(data.responseText);
			}.bind());
		},

        switchAdd: function() {
            this.toggleProperty('showAdd');
        }
	}
});
