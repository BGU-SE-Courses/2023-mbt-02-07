/* @provengo summon selenium */
/* @provengo summon ctrl */
let ctrls = {
    userLogin: Ctrl.markEvent("userLogin"),
    teacherLogin: Ctrl.markEvent("teacherLogin"),
    startedAnswering:  Ctrl.markEvent("startedAnswering"),
    changedPermissions: Ctrl.markEvent("changedPermissions"),
}

let sequences = [
    [ctrls.userLogin, ctrls.teacherLogin, ctrls.startedAnswering, ctrls.changedPermissions],
    [ctrls.userLogin, ctrls.startedAnswering, ctrls.teacherLogin, ctrls.changedPermissions],
    [ctrls.teacherLogin, ctrls.userLogin, ctrls.startedAnswering, ctrls.changedPermissions],
    // are there any other possible sequences?
    // use those for the goals
]



// bthread('Go To Login Window', function () {
//     let s = new SeleniumSession('s1').start(URL)
//     sync(request(Event("welcomeWindowToLoginWindow"), welcomeWindowToLoginWindow(s)))
// })

// bthread('User Login', function () {
//     let s = new SeleniumSession('s1').start(URL)
//     sync({
//         waitFor: Event("welcomeWindowToLoginWindow")
//     })
//     sync({request: ctrls.userLogin})
//     sync(request(Event("loginAsStudent"), loginAsStudent(s)))
// })

// bthread('Enter Course', function () {
//     let s = new SeleniumSession('s1').start(URL)
//     sync({
//         waitFor: Event("loginAsStudent")
//     })
//     sync(
//         request(Event("enterCourse"), enterCourse(s))
//     )
// })

// bthread('Start Survey', function () {
//     let s = new SeleniumSession('s1').start(URL)
//     sync({
//         waitFor: Event("enterCourse")
//     })
//     sync({request: enterSurvey(s)})
//     sync({request: ctrls.startedAnswering})
// })


// // from here on, this is the second use case
// // if you run them both, the first one
// // might not be able to enter the survey because of the restriction
// bthread('Go To Login Window', function () {
//     let s = new SeleniumSession('s2').start(URL)
//     sync(request(Event("welcomeWindowToLoginWindow"), welcomeWindowToLoginWindow(s)))
// })

// bthread('Login As Teacher', function () {
//     let s = new SeleniumSession('s2').start(URL)
//     sync({
//         waitFor: Event("welcomeWindowToLoginWindow")
//     })
//     sync({request: ctrls.teacherLogin})
//     sync(request(Event("loginAsTeacher"), loginAsTeacher(s)))
// })

// bthread('Enter Course', function () {
//     let s = new SeleniumSession('s2').start(URL)
//     sync({
//         waitFor: Event("loginAsTeacher")
//     })
//     sync(
//         request(Event("enterCourse"), enterCourse(s))
//     )
// })

// bthread('Change Access Permissions', function () {
//     let s = new SeleniumSession('s2').start(URL)
//     sync({
//         waitFor: Event("enterCourse")
//     })
//     enterEditMode(s)
//     changeCourseRestrictions(s)
//     sync({request: ctrls.changedPermissions})

// })


let endSet = [Event("end(loginAsStudent)"),Event("end(loginAsTeacher)"),Event("end(enterCourseTeacher)"),Event("end(enterCourse)"),Event("end(enterEditMode)"),Event("end(changeCourseRestrictions)"),Event("end(enterSurvey)")]

bthread('Student session', function () {
    let s = new SeleniumSession('s1').start(URL)
    loginAsStudent(s)
    enterCourse(s)
    enterSurvey(s)
})
bthread('Teacher session', function () {
    let s = new SeleniumSession('s2').start(URL)
    loginAsTeacher(s)
    enterCourse(s)
    enterEditMode(s)
    changeCourseRestrictions(s)
})
bthread('block in case', function () {
    sync({ waitFor: Event("end(changeCourseRestrictions)") })
    sync({ block: Event("start(enterSurvey)") })
}
)

let actions1 = ["loginAsStudent", "enterCourse", "enterSurvey"]

let actions2 = ["loginAsTeacher", "enterCourseTeacher", "enterEditMode", "changeCourseRestrictions"]

bthread(`mark for domain`, function () {
    const end = EventSet("", e => e.name.startsWith("end("));
    let e = sync({ waitFor: end })
    let session = e.data
    let action =  e.name.split("(")[1].split(")")[0]
    let i = 0;
    let b = false;
    for (; i < actions1.length + actions2.length && !b; i++) {
        if (action == "changeCourseRestrictions") {
            sync({ request: Ctrl.markEvent(`changeCourseRestrictions In s2 At ${i}`) })
            b = true
        }
        e = sync({ waitFor: end })
        session = e.data
        action =  e.name.split("(")[1].split(")")[0]  
    }
}
)

// let combos = []
// for (let i = 0; i < actions1.length; i++) {
//     for (let j = i+1; j < actions1.length+actions2.length; j++) {
//         combos.push([i, j])
//     }
// }

// function markCombos(action1, action2) {
//     combos.forEach(combo => {
//         bthread(`mark ${action1} and ${action2}`, function () {
//             sync({ waitFor: Ctrl.markEvent(`${action1} In s1 At ${combo[0]}`) })
//             sync({ waitFor: Ctrl.markEvent(`${action2} In s2 At ${combo[1]}`) })
//             if (combo[0] < combo[1]) { sync({ request: Ctrl.markEvent(`${action1} in s1 and ${action2} in s2 in order`) }) }
//             else {sync({ request: Ctrl.markEvent(`${action2} in s2 and ${action1} in s1 in order`) })}
//         }
//         )
//     }
//     )
// }

bthread(`mark for two ways`, function () {
    let e = sync({ waitFor: endSet })
    let session = e.data
    let action = e.name.split("(")[1].split(")")[0]
    let arr = []
    while (action != "changeCourseRestrictions") {
        arr.push(action)
        e = sync({ waitFor: endSet })
        session = e.data
        action = e.name.split("(")[1].split(")")[0]
    }
    let s = arr.join(" ")
    sync({ request: Ctrl.markEvent(`${s}`) })
})
        


// actions1.forEach(a1 => {
//     actions2.forEach(a2 => {
//         markCombos(a1, a2 )
//     })
// }
// )

// actions1.forEach(a1 => {
//     actions2.forEach(a2 => {
//         markCombos(a2, a1)
//     })
// }
// )