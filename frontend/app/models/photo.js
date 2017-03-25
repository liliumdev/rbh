import Ember from 'ember';
import BaseModel from 'restaurants-app/models/base-model';

var _modelProperties = ['id', 'image_url', 'sort'];

export default BaseModel.extend({
	modelProperties: _modelProperties,

});
