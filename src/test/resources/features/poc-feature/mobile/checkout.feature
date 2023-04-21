@ui
Feature: Check Out Product

  @test-poc @smoke-test
  Scenario Outline: User <user> checkout a product successfully
    Given the user is on login page
    When the user '<user>' logged in successfully
    And the user add product to cart
      | Product Name |
      | <product 1>  |
      | <product 2>  |
    And the user navigate to the cart detail
    And the user checkout the cart with First Name: 'Test' and Last Name: 'User' and Zipcode: '70000'
    Then the user finish check out successfully

    Examples:
      | user          | product 1           | product 2             |
      | standard_user | Sauce Labs Backpack | Sauce Labs Bike Light |
      | problem_user  | Sauce Labs Backpack | Sauce Labs Bike Light |

  @test-poc @smoke-test
  Scenario Outline: Number products in the cart displayed successfully
    Given the user is on login page
    When the user '<user>' logged in successfully
    And the user add product to cart
      | Product Name |
      | <product 1>  |
      | <product 2>  |
    Then the user should see the cart displayed with 2 product

    Examples:
      | user          | product 1           | product 2             |
      | standard_user | Sauce Labs Backpack | Sauce Labs Bike Light |
      | problem_user  | Sauce Labs Backpack | Sauce Labs Bike Light |
#
#  @test-poc @smoke-test
#  Scenario Outline: Total Price is reflecting correctly if user changes quantity on 'Shopping Cart Summary' Page
#    Given the user is on login page
#    When the user '<user>' logged in successfully
#    And the user add product to cart
#      | Product Name |
#      | <product 1>  |
#      | <product 2>  |
#    And the user navigate to the cart detail
#    And the user checkout the cart with First Name: 'Test' and Last Name: 'User' and Zipcode: '70000'
#    Then the user should see the price of product: "$29.99"
#    And the user should see total price in checkout overview
#
#    Examples:
#      | user          | product 1           | product 2             |
#      | standard_user | Sauce Labs Backpack | Sauce Labs Bike Light |
#      | problem_user  | Sauce Labs Backpack | Sauce Labs Bike Light |
#
#  @test-poc
#  Scenario Outline: User <user> cancel checkout successfully
#    Given the user is on login page
#    When the user '<user>' logged in successfully
#    And the user add product to cart
#      | Product Name |
#      | <product 1>  |
#      | <product 2>  |
#    And the user navigate to the cart detail
#    And the user checkout the cart with First Name: 'Test' and Last Name: 'User' and Zipcode: '70000'
#    And the user click cancel checkout button
#    Then the user cancel checkout successfully
#    And the user is on home page
#    And the user should see the cart displayed with 2 product
#
#    Examples:
#      | user          | product 1           | product 2             |
#      | standard_user | Sauce Labs Backpack | Sauce Labs Bike Light |
#      | problem_user  | Sauce Labs Backpack | Sauce Labs Bike Light |
#
#    Scenario: User checkout but not login