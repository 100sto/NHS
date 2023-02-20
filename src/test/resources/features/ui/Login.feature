@smokeLoginTest
Feature: Login functionality for NHS Patient portal

  Scenario: Verify the the user is logged in successfully
    Given The user lands on NHS portal login page
    When The user enters correct credentials
    Then The user is on the NHS home page

  Scenario Outline: Verify the error message for “username“ and “password“
    Given The user lands on NHS portal login page
    When The user enters wrong "<userName>" and "<password>"
    Then The user is still on the login page
    Examples:
      | userName | password |
      |          | wrong    |
      | wrong    |          |
      |          |          |
#      | correct  | wrong    |
#      | empty    | empty    |
#      | empty    | wrong    |