import Ember from 'ember';
import BaseModel from 'restaurants-app/models/base-model';

var _modelProperties = ['id', 'rating', 'description', 'restaurant', 'didRate'];

export default BaseModel.extend({
	modelProperties: _modelProperties,
});
