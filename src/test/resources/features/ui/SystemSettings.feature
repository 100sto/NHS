@smokeTest   @regression
Feature: System settings page testing for NHS Patient portal

  @NHS_31
  Scenario: Verify Add new disease in the system
    Given The user lands on NHS portal login page
    When The user enters correct credentials
    And The user is on the NHS home page
    And The user clicks on 'System settings' side bar
    And The user adds new disease in the system and clicks 'Add disease' button
    Then The user can see the disease in 'Delete diseases from the system' table