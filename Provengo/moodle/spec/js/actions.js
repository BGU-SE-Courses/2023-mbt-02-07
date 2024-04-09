

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
    sync({request: Event("end(loginAsStudent)" )})
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
    sync({request: Event("end(loginAsTeacher)" )})
}

/**
 * Enter a course from the main window
 */
function enterCourse(session) {
    sync({request : Event("start(enterCourse)")})  
    with (session) {
        click(xpaths.mainWindow.courseButton)
    }
    sync({request: Event("end(enterCourse)" )})
}/**
 * Enter a course from the main window
 */
function enterCourseTeacher(session) {
    sync({request : Event("start(enterCourseTeacher)")})  
    with (session) {
        click(xpaths.mainWindow.courseButton)
    }
    sync({request: Event("end(enterCourseTeacher)" )})
}

/**
 * Start answering a survey
 */
function enterSurvey(session) {
    sync({request : Event("start(enterSurvey)")})
    with(session) {
        click(xpaths.courseWindow.startButton)
    }
    sync({request: Event("end(enterSurvey)" )})
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
    sync({request: Event("end(changeCourseRestrictions)" )})
}
