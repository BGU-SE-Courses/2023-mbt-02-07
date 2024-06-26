/*
 *  This is a good place to put common test data, project-wide constants, etc.
 */

const URL = 'https://sandbox.moodledemo.net/';

const xpaths = {
    welcomeWindow: {
    moveToLoginWindow: '//*[@id="usernavigation"]/div[5]/div/span/a',
    },
    courseWindow: {
      startButton: "//*[@class=\"activitytitle media  modtype_survey position-relative align-self-start\"]",
      setEditModeButton: "//*[@type=\"checkbox\"]",
      hamburgerMenu: "//*[@id=\"action-menu-7\"]",
      editSettings: "//*[@id=\"action-menu-7-menu\"]/a[1]/span",
    },
    loginWindow: {
    username: '//*[@id="username"]',
    password: '//*[@id="password"]',
    loginButton: '//*[@id="loginbtn"]'
    },
    mainWindow: {
    courseButton: '//a[text()="My first course"]'
    },
    editMenu: {
        editSettings: '//*[@id="action-menu-7-menu"]/a[1]/span',
        restricitionMenu: "//*[@id=\"collapseElement-2\"]",
        addRestriction: "/html/body/div[5]/div[5]/div/div[3]/div/section/div[2]/form/fieldset[3]/div[2]/div/div[2]/div[1]/div/div[1]/div[5]/button",
        date: "//*[@class=\"btn btn-secondary w-100\"]",
        month: "//*[@name=\"x[month]\"]",
        saveButton: "//*[@id=\"id_submitbutton2\"]",
    }
}
