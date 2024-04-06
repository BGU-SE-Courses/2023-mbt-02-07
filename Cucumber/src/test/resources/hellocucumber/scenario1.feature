
#Feature: A set of scenarios for testing the "example" module
#
#  Scenario: Testing how a case where a user adds a product to the cart
#    Given an example scenario
#    When all step definitions are implemented
#    Then the scenario passes


#Student starts answering a survey ; Teacher changes the access restrictions.

  #TODO parametrize it
Feature: Testing the "example" module

  Scenario: A student starts answering a survey which is then restricted by the teacher
    Given a survey in a course that is open for a student
    When student attempts to open the survey
    Then the survey opens