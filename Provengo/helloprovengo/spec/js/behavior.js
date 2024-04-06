/* @provengo summon selenium */

let sequences = [
    [ctrls.userLogin, ctrls.teacherLogin, ctrls.startedAnswering, ctrls.changedPermissions],
    [ctrls.userLogin, ctrls.startedAnswering, ctrls.teacherLogin, ctrls.changedPermissions],
    [ctrls.teacherLogin, ctrls.userLogin, ctrls.startedAnswering, ctrls.changedPermissions],
    // are there any other possible sequences?
    // use those for the goals
]

let ctrls = {
    userLogin: Ctrl.markEvent("userLogin"),
    teacherLogin: Ctrl.markEvent("teacherLogin"),
    startedAnswering:  Ctrl.markEvent("startedAnswering"),
    changedPermissions: Ctrl.markEvent("changedPermissions"),
}

bthread('Go To Login Window', function () {
    let s = new SeleniumSession('s1').start(URL)
    sync(request(Event("welcomeWindowToLoginWindow"), welcomeWindowToLoginWindow(s)))
})
bthread('User Login', function () {
    let s = new SeleniumSession('s1').start(URL)
    sync({
        waitFor: Event("welcomeWindowToLoginWindow")
    })
    sync({request: ctrls.userLogin})
    sync(request(Event("loginAsStudent"), loginAsStudent(s)))
})

bthread('Enter Course', function () {
    let s = new SeleniumSession('s1').start(URL)
    sync({
        waitFor: Event("loginAsStudent")
    })
    sync(
        request(Event("enterCourse"), enterCourse(s))
    )
})

bthread('Start Survey', function () {
    let s = new SeleniumSession('s1').start(URL)
    sync({
        waitFor: Event("enterCourse")
    })
    sync({request: enterSurvey(s)})
    sync({request: ctrls.startedAnswering})
})


// from here on, this is the second use case
// if you run them both, the first one
// might not be able to enter the survey because of the restriction
bthread('Go To Login Window', function () {
    let s = new SeleniumSession('s2').start(URL)
    sync(request(Event("welcomeWindowToLoginWindow"), welcomeWindowToLoginWindow(s)))
})

bthread('Login As Teacher', function () {
    let s = new SeleniumSession('s2').start(URL)
    sync({
        waitFor: Event("welcomeWindowToLoginWindow")
    })
    sync({request: ctrls.teacherLogin})
    sync(request(Event("loginAsTeacher"), loginAsTeacher(s)))
})

bthread('Enter Course', function () {
    let s = new SeleniumSession('s2').start(URL)
    sync({
        waitFor: Event("loginAsTeacher")
    })
    sync(
        request(Event("enterCourse"), enterCourse(s))
    )
})

bthread('Change Access Permissions', function () {
    let s = new SeleniumSession('s2').start(URL)
    sync({
        waitFor: Event("enterCourse")
    })
    enterEditMode(s)
    changeCourseRestrictions(s)
    sync({request: ctrls.changedPermissions})

})