import { moduleForComponent, test } from 'ember-qunit';
import hbs from 'htmlbars-inline-precompile';

moduleForComponent('admin-restaurant-nav-bar', 'Integration | Component | admin restaurant nav bar', {
  integration: true
});

test('it renders', function(assert) {

  // Set any properties with this.set('myProperty', 'value');
  // Handle any actions with this.on('myAction', function(val) { ... });

  this.render(hbs`{{admin-restaurant-nav-bar}}`);

  assert.equal(this.$().text().trim(), '');

  // Template block usage:
  this.render(hbs`
    {{#admin-restaurant-nav-bar}}
      template block text
    {{/admin-restaurant-nav-bar}}
  `);

  assert.equal(this.$().text().trim(), 'template block text');
});
