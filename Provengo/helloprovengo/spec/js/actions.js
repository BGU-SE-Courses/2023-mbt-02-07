//
// function composeQuery(session, data) {
//   session.writeText(xpaths.searchWindow.searchInput, data.text)
// }
//
// function startSearch(session) {
//   with(session) {
//     click(xpaths.searchWindow.searchButton)
//   }
// }
//
// function feelLucky(session) {
//   with(session) {
//     click(xpaths.searchWindow.feelingLuckyButton)
//   }
// }
//
// function startSurvey(session) {
//     with(session) {
//         click(xpaths.surveyWindow.startButton)
//     }
// }
//

/**
 * Login as a student
 * @param session
 */
function loginAsStudent(session) {
    sync({request : Event("start(loginAsStudent)")})
    with (session) {
        click(xpaths.welcomeWindow.moveToLoginWindow);
        session.writeText(xpaths.loginWindow.username, "student")
        session.writeText(xpaths.loginWindow.password, "sandbox")
        click(xpaths.loginWindow.loginButton)
    }
    sync({request: Event("end(loginAsStudent)",  session.name)})
}

/**
 * Login as a teacher
 */

function loginAsTeacher(session) {
    sync({request : Event("start(loginAsTeacher)")})
    with (session) {
        click(xpaths.welcomeWindow.moveToLoginWindow);
        session.writeText(xpaths.loginWindow.username, "teacher")
        session.writeText(xpaths.loginWindow.password, "sandbox")
        click(xpaths.loginWindow.loginButton)
    }
    sync({request: Event("end(loginAsTeacher)",  session.name)})
}

/**
 * Enter a course from the main window
 */
function enterCourse(session) {
    sync({request : Event("start(enterCourseTeacher)")})  
    with (session) {
        click(xpaths.mainWindow.courseButton)
    }
    sync({request: Event("end(enterCourseTeacher)",  session.name)})
}

/**
 * Start answering a survey
 */
function enterSurvey(session) {
    sync({request : Event("start(enterSurvey)")})
    with(session) {
        click(xpaths.courseWindow.startButton)
    }
    sync({request: Event("end(enterSurvey)",  session.name)})
}




/**
 * Change course restrictions to a later date
 */
function changeCourseRestrictions(session) {
    sync({request : Event("start(changeCourseRestrictions)")})
    with (session) {
        click(xpaths.courseWindow.setEditModeButton)
        click(xpaths.courseWindow.hamburgerMenu)
        click(xpaths.courseWindow.editSettings)
        click(xpaths.editMenu.restricitionMenu)
        click(xpaths.editMenu.addRestriction)
        click(xpaths.editMenu.date)
        selectByValue(xpaths.editMenu.month,"12")
        click(xpaths.editMenu.saveButton)
    }
    sync({request: Event("end(changeCourseRestrictions)",  session.name)})
}
/**
 * Move From Welcome Window to the User Login Window
 */

// /**
//  * After getting to the course window as a teacher, add a new survey with a specified name
//  */
// function addSurveyToCourse(session, data) {
//     with (session) {
//         click(xpaths.courseWindow.setEditModeButton);
//         click(xpaths.courseWindow.addActivityToTopic1Button);
//         click(xpaths.courseWindow.selectSurveyActivityButton);
//         session.writeText(xpaths.courseWindow.surveyNameInput, data.text);
//         click(xpaths.courseWindow.surveyTypeInput);
//         /**
//          select somehow option to the survey type (relevant xpath is xpaths.courseWindow.selectTypeOption)
//          */
//         click(xpaths.courseWindow.saveAndDisplayButton);
//     }
// }

// /**
//  * logout a connected user
//  */
// function logout(session) {
//     with (session) {
//         click(xpaths.logout.openLogoutOption);
//         click(xpaths.logout.selectLogoutOption);
//     }
// }
//
// /**
//  * After logging in as a student and getting into the course page, start answering a survey
//  */
// function startSurvey(session) {
//     with (session) {
//         click(xpaths.courseWindow.startAnsweringSurveyButton);
//     }
// }