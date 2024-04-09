/* @provengo summon selenium */
/* @provengo summon ctrl */




let endSet = [Event("end(loginAsStudent)"), Event("end(loginAsTeacher)"), Event("end(enterCourseTeacher)"),
Event("end(enterCourse)"), Event("end(changeCourseRestrictions)"), Event("end(enterSurvey)")]

//The students behavior is entering a survey. to do so, he first needs to login and enter a course
bthread('Student session', function () {
    let s = new SeleniumSession('s1').start(URL)
    loginAsStudent(s)
    enterCourse(s)
    enterSurvey(s)
})

//The teacher behavior is changing the survey restrictions. to do so, he first needs to login and enter a course
bthread('Teacher session', function () {
    let s = new SeleniumSession('s2').start(URL)
    loginAsTeacher(s)
    enterCourseTeacher(s)
    changeCourseRestrictions(s)
})


//if survey restrictions are changed, the student can't enter the survey anymore
bthread('block in case', function () {
    sync({ waitFor: Event("end(changeCourseRestrictions)") })
    sync({block: Event("end(enterSurvey)")})
})


//We wait till an event of changeCourseRestrictions happens, and then we mark it.
bthread(`mark for domain`, function () {
    let e = sync({waitFor: endSet})
    let i = 0;
    while (e.name != "end(changeCourseRestrictions)") {
        e = sync({ waitFor: endSet })
        i++
    }


    sync({ request: Ctrl.markEvent(`changeCourseRestrictions In s2 At ${i}`) })
    }
)

//we till event of changeCourseRestrictions happens, and then we mark the whole sequence of events to it.
    
bthread(`mark for two ways`, function () {
    let e = sync({ waitFor: endSet })
    let arr = []
    arr.push(e.name)
    while ((arr.length < 6 && arr.indexOf("end(changeCourseRestrictions)") === -1) || (arr.length < 5 ))  {
        e = sync({ waitFor: endSet })
        arr.push(e.name)
    }
    let s = (arr.filter(x => x.startsWith("end("))).map(x=>x.split("(")[1].split(")")[0]).join(" ")
    sync({ request: Ctrl.markEvent(s) })
})
