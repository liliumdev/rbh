import { moduleForComponent, test } from 'ember-qunit';
import hbs from 'htmlbars-inline-precompile';

moduleForComponent('restaurant-item', 'Integration | Component | restaurant item', {
  integration: true
});

test('it renders', function(assert) {

  // Set any properties with this.set('myProperty', 'value');
  // Handle any actions with this.on('myAction', function(val) { ... });

  this.render(hbs`{{restaurant-item}}`);

  assert.equal(this.$().text().trim(), '');

  // Template block usage:
  this.render(hbs`
    {{#restaurant-item}}
      template block text
    {{/restaurant-item}}
  `);

  assert.equal(this.$().text().trim(), 'template block text');
});
