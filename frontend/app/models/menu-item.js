import Ember from 'ember';
import BaseModel from 'restaurants-app/models/base-model';

var _modelProperties = ['id', 'name', 'price', 'description'];

export default BaseModel.extend({
	modelProperties: _modelProperties,
});
