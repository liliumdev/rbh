import Ember from 'ember';
import BaseModel from 'restaurants-app/models/base-model';

var _modelProperties = ['id', 'persons', 'amount'];

export default BaseModel.extend({
	modelProperties: _modelProperties,
});
