@smokeTest   @regression
Feature: Home page testing for NHS Patient portal

  @NHS_28
  Scenario: Verify the patient is added in the home page from 'Add patient' side bar
    Given The user lands on NHS portal login page
    When The user enters correct credentials
    And The user is on the NHS home page
    And The user clicks on 'Add patient' side bar
    And The user adds the patient details and clicks 'Add patient' button
    Then The user can see the patient in 'Patients waiting' table
