// @provengo summon ctrl

//domain specific
// const GOALS = [
//     Ctrl.markEvent("changeCourseRestrictions In s2 At 2"),
//     Ctrl.markEvent("changeCourseRestrictions In s2 At 3"),
//     Ctrl.markEvent("changeCourseRestrictions In s2 At 4"),
//     Ctrl.markEvent("changeCourseRestrictions In s2 At 5")
// ];
/**
 * List of events "of interest" that we want test suites to cover.
 */
//two ways
function interleave_lists(list1, list2) {
    if (list1.length === 0) {
        return [list2];
    }
    if (list2.length === 0) {
        return [list1];
    }

    let results = [];

  // Interleave first element of list1 with all possible interleavings of list2
  interleave_lists(list1.slice(1), list2).forEach(interleaved => {
        results.push([list1[0]].concat(interleaved));
    });

    // Interleave first element of list2 with all possible interleavings of list1
    interleave_lists(list1, list2.slice(1)).forEach(interleaved => {
        results.push([list2[0]].concat(interleaved));
    });
    return results;
}

const interleaved = interleave_lists(actions1, actions2);
const res1 = interleaved.filter(x => x.indexOf("enterSurvey") < x.indexOf("changeCourseRestrictions"));
const res2 = interleaved.filter(y => y.indexOf("enterSurvey") > y.indexOf("changeCourseRestrictions")).map(y => y.slice(0, -1));
const res = res1.concat(res2);

let g = []
for (let x of res) {
    g.push(Ctrl.markEvent(x.join(" ")));
    bp.log.info(x.join(" "));
}
// let g = ["loginAsStudent enterCourse enterSurvey loginAsTeacher enterCourseTeacher changeCourseRestrictions",
//     "loginAsStudent enterCourse loginAsTeacher enterSurvey enterCourseTeacher changeCourseRestrictions",
//     "loginAsStudent enterCourse loginAsTeacher enterCourseTeacher enterSurvey changeCourseRestrictions",
//     "loginAsStudent loginAsTeacher enterCourse enterSurvey enterCourseTeacher changeCourseRestrictions",
//     "loginAsStudent loginAsTeacher enterCourse enterCourseTeacher enterSurvey changeCourseRestrictions",
//     "loginAsStudent loginAsTeacher enterCourseTeacher enterCourse enterSurvey changeCourseRestrictions",
//     "loginAsTeacher loginAsStudent enterCourse enterSurvey enterCourseTeacher changeCourseRestrictions",
//     "loginAsTeacher loginAsStudent enterCourse enterCourseTeacher enterSurvey changeCourseRestrictions",
//     "loginAsTeacher loginAsStudent enterCourseTeacher enterCourse enterSurvey changeCourseRestrictions",
//     "loginAsTeacher enterCourseTeacher loginAsStudent enterCourse enterSurvey changeCourseRestrictions",
//     "loginAsStudent enterCourse loginAsTeacher enterCourseTeacher changeCourseRestrictions",
//     "loginAsStudent loginAsTeacher enterCourse enterCourseTeacher changeCourseRestrictions",
//     "loginAsStudent loginAsTeacher enterCourseTeacher enterCourse changeCourseRestrictions",
//     "loginAsStudent loginAsTeacher enterCourseTeacher changeCourseRestrictions enterCourse",
//     "loginAsTeacher loginAsStudent enterCourse enterCourseTeacher changeCourseRestrictions",
//     "loginAsTeacher loginAsStudent enterCourseTeacher enterCourse changeCourseRestrictions",
//     "loginAsTeacher loginAsStudent enterCourseTeacher changeCourseRestrictions enterCourse",
//     "loginAsTeacher enterCourseTeacher loginAsStudent enterCourse changeCourseRestrictions",
//     "loginAsTeacher enterCourseTeacher loginAsStudent changeCourseRestrictions enterCourse",
//     "loginAsTeacher enterCourseTeacher changeCourseRestrictions loginAsStudent enterCourse"]
const GOALS = g;

const makeGoals = function(){
    return [ [ any(/Howdy/), any(/Venus/) ],
             [ any(/Mars/) ],
             [ Ctrl.markEvent("Classic!") ] ];
}

/**
 * Ranks test suites by how many events from the GOALS array were met.
 * The more goals are met, the higher the score.
 * 
 * It make no difference if a goal was met more then once.
 *
 * @param {Event[][]} ensemble The test suite to be ranked.
 * @returns Number of events from GOALS that have been met.
 */
function rankByMetGoals( ensemble ) {
    const unreachedGoals = [];
    for ( let idx=0; idx<GOALS.length; idx++ ) {
        unreachedGoals.push(GOALS[idx]);
    }

    for (let testIdx = 0; testIdx < ensemble.length; testIdx++) {
        let test = ensemble[testIdx];
        for (let eventIdx = 0; eventIdx < test.length; eventIdx++) {
            let event = test[eventIdx];
            for (let ugIdx=unreachedGoals.length-1; ugIdx >=0; ugIdx--) {
                let unreachedGoal = unreachedGoals[ugIdx];
                if ( unreachedGoal.contains(event) ) {
                    unreachedGoals.splice(ugIdx,1);
                }
            }
        }
    }

    return GOALS.length-unreachedGoals.length;
}

/**
 * Ranks potential test suites based on the percentage of goals they cover.
 * Goal events are defined in the GOALS array above. An ensemble with rank
 * 100 covers all the goal events.
 *
 * Multiple ranking functions are supported - to change ranking function,
 * use the `ensemble.ranking-function` configuration key, or the 
 * --ranking-function <functionName> command-line parameter.
 *
 * @param {Event[][]} ensemble the test suite/ensemble to be ranked
 * @returns the percentage of goals covered by `ensemble`.
 */
 function rankingFunction(ensemble) {
    
    // How many goals did `ensemble` hit?
    const metGoalsCount = rankByMetGoals(ensemble);
    // What percentage of the goals did `ensemble` cover?
    const metGoalsPercent = metGoalsCount/GOALS.length;

    return metGoalsPercent * 100; // convert to human-readable percentage
}

