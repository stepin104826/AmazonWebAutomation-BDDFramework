Feature: Amazon Shopping Cart Edit Feature

  As a user I want to be able to add or delete to update the
  orders/products from the shopping cart according to
  my wish

  @Regression @editcarttest
  Scenario: Editing the shopping cart successfully
    Given I am on the Shopping Cart page with some product(s) in the cart
    When I click "Delete" option, the added product(s) should be deleted
    And I click "Add to cart" from the recently viewed products, the product(s) should be added

