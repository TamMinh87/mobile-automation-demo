@api
Feature: Check API Login

  @test-poc-api
  Scenario Outline: User Login with email '<email>'
    Given the user set API resource to 'login'
    And the user have a request payload login
    And the user set request header with key 'Content-Type' and value 'application/json'
    And the user update request payload with following items
      | Path    | Value   |
      | $.email | <email> |
    And the user call POST request
    Then the user expect to see response code 200
    And the user expect the response return in correct the schema login
    Then the user expect to get an response with correct information
      | Path         | Value   |
      | $.data.Name  | <name>  |
      | $.data.Email | <email> |
    And the user get field '$.data.Token' in response and save to test context with key '$.token'
    And the user set Token for the request with key '$.token'

    Examples:
      | email                  | name      |
      | Developer5@gmail.com   | simon     |
      | Developer3@yopmail.com | Developer |

