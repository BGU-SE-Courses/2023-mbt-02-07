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
    with(session) {
        session.writeText(xpaths.loginWindow.username, "student")
        session.writeText(xpaths.loginWindow.password, "sandbox")
        click(xpaths.loginWindow.loginButton)
    }
}

/**
 * Login as a teacher
 */

function loginAsTeacher(session) {
    with(session) {
        session.writeText(xpaths.loginWindow.username, "teacher")
        session.writeText(xpaths.loginWindow.password, "sandbox")
        click(xpaths.loginWindow.loginButton)
    }

}

/**
 * Enter a course from the main window
 */
function enterCourse(session) {
    with(session) {
        click(xpaths.mainWindow.courseButton)
    }
}

/**
 * Start answering a survey
 */
function enterSurvey(session) {
    with(session) {
        click(xpaths.courseWindow.startButton)
        while(true){

        }
    }
}


/**
 * Enter edit mode in the course window
 */
function enterEditMode(session) {
    with(session) {
        click(xpaths.courseWindow.setEditModeButton)
    }
}

/**
 * Change course restrictions to a later date
 */
function changeCourseRestrictions(session) {
    with(session) {
        click(xpaths.courseWindow.hamburgerMenu)
        click(xpaths.courseWindow.editSettings)
        click(xpaths.editMenu.restricitionMenu)
        click(xpaths.editMenu.addRestriction)
        click(xpaths.editMenu.date)
        selectByValue(xpaths.editMenu.month,"12")
        click(xpaths.editMenu.saveButton)
        while(true){

        }
    }
}
/**
 * Move From Welcome Window to the User Login Window
 */
function welcomeWindowToLoginWindow(session) {
    with (session) {
        click(xpaths.welcomeWindow.moveToLoginWindow);
    }
}

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