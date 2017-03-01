import Ember from 'ember';
import BaseModel from 'restaurants-app/models/base-model';

var _modelProperties = ['query', 'rating', 'pricing', 'categories'];

export default BaseModel.extend({
	modelProperties: _modelProperties,
});
