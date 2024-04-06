
#Feature: A set of scenarios for testing the "example" module
#
#  Scenario: Testing how a case where a user adds a product to the cart
#    Given an example scenario
#    When all step definitions are implemented
#    Then the scenario passes


#Student starts answering a survey ; Teacher changes the access restrictions.

Feature: Testing the "example" module

  Scenario: A student starts answering a survey which is then restricted by the teacher
    Given a survey that is open to students and a student starts answering it
    When the teacher changes the access restrictions
    Then the student is unable to submit the survey