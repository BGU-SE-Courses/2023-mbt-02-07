
#Feature: A set of scenarios for testing the "example" module
#
#  Scenario: Testing how a case where a user adds a product to the cart
#    Given an example scenario
#    When all step definitions are implemented
#    Then the scenario passes


#Student starts answering a survey ; Teacher changes the access restrictions.

  #TODO parametrize it
Feature: Testing the "example" module

  Scenario Outline: A student starts answering a survey
    Given a survey in a course "<course>" that is open for a student "<user_fullname>"
    When student logs in with username "<student_username>" and password "<password>"
    And student attempts to open the survey on the course "<course>"
    Then the survey opens

    Examples:
      | course        | student_username | password | user_fullname |
      | TestingSurvey | student | sandbox  | Sam Student |


  Scenario Outline:  Teacher changes access restriction to survey
    Given a survey in a course "<course>" that is open for a student "<user_fullname>" and a teacher "<teacher_fullname>"
    When teacher logs in with username "<teacher_username>" and password "<password>"
    And teacher changes access to the survey on the course "<course>"
    And student logs in with username "<student_username>" and password "<password>"
    Then the student is not able to open the survey on the course "<course>"

    Examples:
      | course        | student_username | password | user_fullname | teacher_fullname | teacher_username |
      | TestingSurvey | student          | sandbox  | Sam Student   | Terri Teacher    | teacher          |