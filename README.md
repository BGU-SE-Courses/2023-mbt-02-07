# Software Quality Engineering - System Testing

This is a repository for the system-testing assignment of the Software Quality Engineering course at the [Ben-Gurion University](https://in.bgu.ac.il/), Israel.

## Assignment Description

In this assignment, we tested an open-source software called [Moodle](https://sandbox.moodledemo.net/).

Moodle is an open-source learning management system (LMS) widely used by educational institutions and organizations to deliver online courses and training programs. It offers a comprehensive set of features for course creation, content management, communication, assessment, and reporting. With Moodle, instructors can create interactive courses, facilitate discussions, assess student progress, and generate reports. The platform is highly customizable, allowing users to tailor it to their specific needs through themes, plugins, and add-ons. Supported by a large community of users and developers, Moodle provides extensive support, resources, and documentation, making it a popular choice for online learning worldwide.

## Installation

1. Selenium web driver with matching version to your Chrome version.
2. For the Provengo part: up to date Google Chrome version that matches the chrome driver version.
3. For the Cucumber project: java with Maven, Selenium Web Server .jar.

## What we tested

We tested the survey module that allows for creating and taking surveys, as well as managing access restrictions for surveys. We chose to test the following user stories:

*User Story 1:* A student starts answering a survey.

*Preconditions:* There is an existing course such that the student enrolled to, and an existing survey in this course such that the student is permitted to access to.

*Expected outcome:* The student is moving to the survey window and is able to answer it.

*User Story 2:* A teacher changes the access restrictions of an existing survey.

*Preconditions:* There is an existing course such that the teacher is teaching, and an existing survey in this course.

*Expected outcome:* The access restrictions to the survey changes with respect to the teacher's new restrictions, and students can access the quiz if and only if they match the new restrictions.


## How we tested
We used two different testing methods:
1. [Cucumber](https://cucumber.io/), a behavior-driven testing framework.
2. [Provengo](https://provengo.tech/), a story-based testing framework.

Each of the testing methods is elaborated in its own directory.

## Results
Update all README.md files (except for d-e, see Section 1). Specifically, replace all $$*TODO*…$$ according to the instructions inside the $$.

## Detected Bugs
We detected the following bugs:

1. Bug 1:
   1. General description: Students/Teachers can't enter a course they are registered to, this happens mostly in the evening.
   2. Steps to reproduce: After 7 pm, as an admin create a new course, then add a new student and a teacher to it. then, log out from the admin and log in as a student. 
   3. Expected result: To be able to log in to the course as the added student
   4. Actual result: Student isn't able to enter.
   5. unfortunately, we weren't able to find a way to contact the developers
3. Bug 2: ...

$$*TODO* if you did not detect the bug, you should delete this section$$
$$
