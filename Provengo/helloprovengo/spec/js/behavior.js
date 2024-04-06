/* @provengo summon selenium */


bthread('Go To Login Window', function () {
    let s = new SeleniumSession('s1').start(URL)
    sync(request(Event("welcomeWindowToLoginWindow"), welcomeWindowToLoginWindow(s)))
})
bthread('User Login', function () {
    let s = new SeleniumSession('s1').start(URL)
    sync({
        waitFor: Event("welcomeWindowToLoginWindow")
    })
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

})