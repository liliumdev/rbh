import Ember from 'ember';
import BaseModel from 'restaurants-app/models/base-model';

var _modelProperties = ['suggestedTime', 'persons', 'tableId', 'freeTables'];

export default BaseModel.extend({
	modelProperties: _modelProperties
});
