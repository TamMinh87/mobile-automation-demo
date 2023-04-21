@api @test-poc-api
Feature: Check API get user

  Scenario: User get User
    Given the user set API resource to 'getUserById'
    And the user update API base path with value '212387'
    And the user set Token for the request with key '$.token'
    And the user call GET request
    Then the user expect to see response code 200
    And the user expect the response return in correct the schema getUserById
    Then the user expect to get an response with correct information
      | Path   | Value  |
      | $.name | simon  |
      | $.id   | 212387 |