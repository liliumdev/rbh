import { moduleForComponent, test } from 'ember-qunit';
import hbs from 'htmlbars-inline-precompile';

moduleForComponent('admin-restaurant-index', 'Integration | Component | admin restaurant index', {
  integration: true
});

test('it renders', function(assert) {

  // Set any properties with this.set('myProperty', 'value');
  // Handle any actions with this.on('myAction', function(val) { ... });

  this.render(hbs`{{admin-restaurant-index}}`);

  assert.equal(this.$().text().trim(), '');

  // Template block usage:
  this.render(hbs`
    {{#admin-restaurant-index}}
      template block text
    {{/admin-restaurant-index}}
  `);

  assert.equal(this.$().text().trim(), 'template block text');
});
