import { moduleForComponent, test } from 'ember-qunit';
import hbs from 'htmlbars-inline-precompile';

moduleForComponent('admin-restaurant-tables', 'Integration | Component | admin restaurant tables', {
  integration: true
});

test('it renders', function(assert) {

  // Set any properties with this.set('myProperty', 'value');
  // Handle any actions with this.on('myAction', function(val) { ... });

  this.render(hbs`{{admin-restaurant-tables}}`);

  assert.equal(this.$().text().trim(), '');

  // Template block usage:
  this.render(hbs`
    {{#admin-restaurant-tables}}
      template block text
    {{/admin-restaurant-tables}}
  `);

  assert.equal(this.$().text().trim(), 'template block text');
});
