@ui @test-poc
Feature: Login

  @smoke-test
  Scenario: User login successfully
    Given the user is on login page
    When the user enter username: 'standard_user' and password: 'secret_sauce'
    And the user click login button
    Then the user is on home page

  Scenario: User login with wrong password
    Given the user is on login page
    When the user enter username: 'standard_user' and password: 'secret_sauce123'
    And the user click login button
    Then the user should see an error message on login page: 'Username and password do not match any user in this service.'

  Scenario: User login with wrong username
    Given the user is on login page
    When the user enter username: 'standard_user1' and password: 'secret_sauce'
    And the user click login button
    Then the user should see an error message on login page: 'Username and password do not match any user in this service.'