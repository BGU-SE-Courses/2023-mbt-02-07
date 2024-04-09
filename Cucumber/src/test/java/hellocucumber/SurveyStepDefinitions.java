package hellocucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SurveyStepDefinitions {
    private final static String CHROME_DRIVER_PATH = "../Selenium/chromedriver.exe";
    private ChromeDriver driver;
    private WebDriverWait wait;


    @Then("the survey opens")
    public void theSurveyOpens() {
        assert driver.findElement(By.id("page-mod-survey-view")) != null;
    }


    @Given("a survey in a course {string} that is open for a student {string}")
    public void aSurveyInACourseThatIsOpenForAStudent(String course_name, String user_fullname) {
        //opening moodle
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://sandbox.moodledemo.net");
        //We first register as an admin
        driver.manage().window().setSize(new Dimension(1294, 1407));
        wait.until(webDriver -> driver.findElement(By.linkText("Log in")).isDisplayed());//TODO remove this
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Log in")));

        driver.findElement(By.linkText("Log in")).click();

        wait.until(webDriver -> driver.findElement(By.id("username")).isDisplayed());

        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("sandbox");
        driver.findElement(By.id("loginbtn")).click();

        //After registered as an admin, we create a new course, called "TestingSurvey"

        driver.findElement(By.linkText("Site administration")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Courses")));
        driver.findElement(By.linkText("Courses")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Add a new course")));
        driver.findElement(By.linkText("Add a new course")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='id_fullname']")));

        driver.findElement(By.id("id_fullname")).click();
        driver.findElement(By.id("id_fullname")).sendKeys(course_name);
        driver.findElement(By.id("id_shortname")).click();

        // create random 4 digit number
        Random random = new Random();
        int randomNumber = random.nextInt(9000) + 1000;
        String shortName = "TSY_s" + randomNumber;

        driver.findElement(By.id("id_shortname")).sendKeys(shortName);


        driver.findElement(By.id("id_saveanddisplay")).click();


        /**Adding a student to the course*/
        driver.findElement(By.linkText("Participants")).click();
        driver.findElement(By.cssSelector("#enrolusersbutton-1 .btn")).click();


        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("form-control"),6));
        String dropDownId= driver.findElements(By.className("form-control")).get(5).getAttribute("id");
        driver.findElements(By.className("form-control")).get(5).sendKeys(user_fullname);
        System.out.println("#"+ dropDownId+"-0 span:nth-child(2)");

        WebElement dropdownElement = driver.findElement(By.id(dropDownId));

        WebElement finalDropdownElement = dropdownElement;
        wait.until((driver)->{
            System.out.println((long) finalDropdownElement.findElements(By.tagName("option")).size());

            return driver.findElements(By.tagName("option")).size() == 693;


        });
        driver.findElement(By.id(dropDownId)).sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("btn-primary"),15));
        driver.findElements(By.className("btn-primary")).get(14).click();



        /** added student to course*/



        /**Adding a teacher to the course*/
        driver.findElement(By.cssSelector("#enrolusersbutton-1 .btn")).click();


        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("form-control"),6));
        dropDownId= driver.findElements(By.className("form-control")).get(5).getAttribute("id");
        driver.findElements(By.className("form-control")).get(5).sendKeys("Terri Teacher");
        System.out.println("#"+ dropDownId+"-0 span:nth-child(2)");

        dropdownElement = driver.findElement(By.id(dropDownId));

        WebElement finalDropdownElement1 = dropdownElement;
        wait.until((driver)->{
            System.out.println((long) finalDropdownElement1.findElements(By.tagName("option")).size());
            return driver.findElements(By.tagName("option")).size() == 693;


        });
        driver.findElement(By.id(dropDownId)).sendKeys(Keys.ENTER);
        //sleep for 10 miliseconds
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.id(dropDownId)).sendKeys(Keys.ESCAPE);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_roletoassign")));


        driver.findElement(By.id("id_roletoassign")).click();
        {
            WebElement dropdown = driver.findElement(By.id("id_roletoassign"));
            Select dropdownSelect = new Select(dropdown);
            dropdownSelect.selectByValue("3");
            dropdown.click();
        }



        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("btn-primary"),15));
        driver.findElements(By.className("btn-primary")).get(14).click();

        /** added teacher to course*/


        /** Adding the quiz*/
        driver.findElement(By.linkText("Course")).click();
        driver.findElement( By.xpath("//*[@type=\"checkbox\"]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#coursecontentcollapse0 .activity-add-text")));
        driver.findElement(By.cssSelector("#coursecontentcollapse0 .activity-add-text")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-label='Survey']")));
        driver.findElement(By.xpath("//div[@aria-label='Survey']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_name")));
        driver.findElement(By.id("id_name")).click();
        driver.findElement(By.id("id_name")).sendKeys("testSurvey");
        driver.findElement(By.id("id_template")).click();
        {
            WebElement dropdown = driver.findElement(By.id("id_template"));
            dropdown.findElement(By.xpath("//option[. = 'ATTLS (20 item version)']")).click();
        }
        driver.findElement(By.id("id_submitbutton")).click();
        driver.findElement(By.id("user-menu-toggle")).click();
        driver.findElement(By.linkText("Log out")).click();


    }

    @When("student logs in with username {string} and password {string}")
    public void studentLogsInWithUsernameAndPassword(String username, String password) {
        wait.until(webDriver -> driver.findElement(By.linkText("Log in")).isDisplayed());//TODO remove this
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Log in")));

        driver.findElement(By.linkText("Log in")).click();

        wait.until(webDriver -> driver.findElement(By.id("username")).isDisplayed());

        driver.findElement(By.id("username")).click();
        WebElement usernameTextbox = driver.findElement(By.id("username"));
        usernameTextbox.clear();
        usernameTextbox.sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("loginbtn")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Home")));
        driver.findElement(By.linkText("Home")).click();

    }

    @And("student attempts to open the survey on the course {string}")
    public void studentAttemptsToOpenTheSurveyOnTheCourse(String course_name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(course_name)));
        driver.findElement(By.linkText(course_name)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".modtype_survey .aalink")));
        driver.findElement(By.cssSelector(".modtype_survey .aalink")).click();

    }

    @When("teacher logs in with username {string} and password {string}")
    public void teacherLogsInWithUsernameAndPassword(String username, String password){
        wait.until(webDriver -> driver.findElement(By.linkText("Log in")).isDisplayed());//TODO remove this
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Log in")));

        driver.findElement(By.linkText("Log in")).click();

        wait.until(webDriver -> driver.findElement(By.id("username")).isDisplayed());

        driver.findElement(By.id("username")).click();
        WebElement usernameTextbox = driver.findElement(By.id("username"));
        usernameTextbox.clear();
        usernameTextbox.sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("loginbtn")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Home")));
        driver.findElement(By.linkText("Home")).click();
    }

    @And("teacher changes access to the survey on the course {string}")
    public void teacherChangesAccessToTheSurveyOnTheCourse(String courseName){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(courseName)));
        driver.findElement(By.linkText("TestingSurvey")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".modtype_survey .aalink")));
        driver.findElement(By.cssSelector(".modtype_survey .aalink")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Settings")));
        driver.findElement(By.linkText("Settings")).click();

        //Opening restriction menu
        driver.findElement(By.id("collapseElement-2")).click();
        //adding a restriction
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class=\"btn btn-secondary mt-1\"]")));
        driver.findElement(By.xpath("//*[@class=\"btn btn-secondary mt-1\"]")).click();

        //Selecting the date restriction
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class=\"btn btn-secondary w-100\"]")));
        driver.findElement(By.xpath("//*[@class=\"btn btn-secondary w-100\"]")).click();
        //Selecting year
        WebElement dropdown = driver.findElement(By.xpath("//*[@name=\"x[year]\"]"));
        Select dropdownSelect = new Select(dropdown);
        //Get currently selected year
        System.out.println(dropdownSelect.getFirstSelectedOption().getAttribute("value"));



        int currentYear = Integer.parseInt(dropdownSelect.getFirstSelectedOption().getAttribute("value"));
        //Select the option with value that is one higher than the current year
        dropdownSelect.selectByValue(String.valueOf(currentYear+1));
        System.out.println(dropdownSelect.getFirstSelectedOption().getAttribute("value"));

        //saving the restriction
        driver.findElement(By.xpath("//*[@id=\"id_submitbutton2\"]")).click();



        //Lastly we log out
        driver.findElement(By.id("user-menu-toggle")).click();
        driver.findElement(By.linkText("Log out")).click();
    }





    @Then("the student is not able to open the survey on the course {string}")
    public void theStudentIsNotAbleToOpenTheSurveyOnTheCourse(String courseName) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("TestingSurvey")));
        driver.findElement(By.linkText("TestingSurvey")).click();

        try {

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".modtype_survey .aalink")));
            driver.findElement(By.cssSelector(".modtype_survey .aalink")).click();
        }
        catch (Exception e){
            System.out.println("Survey is not clickable");
            //test passed
            assertTrue(true);
            return;
        }
        //test failed
        assertTrue(false);


    }

    @Given("a survey in a course {string} that is open for a student {string} and a teacher {string}")
    public void aSurveyInACourseThatIsOpenForAStudentAndATeacher(String course_name, String user_fullname, String teacherName) {

        //opening moodle
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://sandbox.moodledemo.net");
        //We first register as an admin
        driver.manage().window().setSize(new Dimension(1294, 1407));
        wait.until(webDriver -> driver.findElement(By.linkText("Log in")).isDisplayed());//TODO remove this
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Log in")));

        driver.findElement(By.linkText("Log in")).click();

        wait.until(webDriver -> driver.findElement(By.id("username")).isDisplayed());

        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("sandbox");
        driver.findElement(By.id("loginbtn")).click();

        //After registered as an admin, we create a new course, called "TestingSurvey"

        driver.findElement(By.linkText("Site administration")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Courses")));
        driver.findElement(By.linkText("Courses")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Add a new course")));
        driver.findElement(By.linkText("Add a new course")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='id_fullname']")));

        driver.findElement(By.id("id_fullname")).click();
        driver.findElement(By.id("id_fullname")).sendKeys(course_name);
        driver.findElement(By.id("id_shortname")).click();

        // create random 4 digit number
        Random random = new Random();
        int randomNumber = random.nextInt(9000) + 1000;
        String shortName = "TSY_s" + randomNumber;

        driver.findElement(By.id("id_shortname")).sendKeys(shortName);


        driver.findElement(By.id("id_saveanddisplay")).click();


        /**Adding a student to the course*/
        driver.findElement(By.linkText("Participants")).click();
        driver.findElement(By.cssSelector("#enrolusersbutton-1 .btn")).click();


        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("form-control"),6));
        String dropDownId= driver.findElements(By.className("form-control")).get(5).getAttribute("id");
        driver.findElements(By.className("form-control")).get(5).sendKeys(user_fullname);
        System.out.println("#"+ dropDownId+"-0 span:nth-child(2)");

        WebElement dropdownElement = driver.findElement(By.id(dropDownId));

        WebElement finalDropdownElement = dropdownElement;
        wait.until((driver)->{
            System.out.println((long) finalDropdownElement.findElements(By.tagName("option")).size());

            return driver.findElements(By.tagName("option")).size() == 693;


        });
        driver.findElement(By.id(dropDownId)).sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("btn-primary"),15));
        driver.findElements(By.className("btn-primary")).get(14).click();


        /** added student to course*/



        /**Adding a teacher to the course*/
        driver.findElement(By.cssSelector("#enrolusersbutton-1 .btn")).click();


        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("form-control"),6));
        dropDownId= driver.findElements(By.className("form-control")).get(5).getAttribute("id");
        driver.findElements(By.className("form-control")).get(5).sendKeys(teacherName);
        System.out.println("#"+ dropDownId+"-0 span:nth-child(2)");

        dropdownElement = driver.findElement(By.id(dropDownId));

        WebElement finalDropdownElement1 = dropdownElement;
        wait.until((driver)->{
            System.out.println((long) finalDropdownElement1.findElements(By.tagName("option")).size());
            return driver.findElements(By.tagName("option")).size() == 693;


        });
        driver.findElement(By.id(dropDownId)).sendKeys(Keys.ENTER);
        //sleep for 10 miliseconds
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.id(dropDownId)).sendKeys(Keys.ESCAPE);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_roletoassign")));


        driver.findElement(By.id("id_roletoassign")).click();
        {
            WebElement dropdown = driver.findElement(By.id("id_roletoassign"));
            Select dropdownSelect = new Select(dropdown);
            //TODO maybe add a wait until the xpath of theacher is found
            dropdownSelect.selectByValue("3");
            dropdown.click();
        }



        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("btn-primary"),15));
        driver.findElements(By.className("btn-primary")).get(14).click();

        /** added teacher to course*/


        /** Adding the quiz*/
        driver.findElement(By.linkText("Course")).click();
        driver.findElement( By.xpath("//*[@type=\"checkbox\"]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#coursecontentcollapse0 .activity-add-text")));
        driver.findElement(By.cssSelector("#coursecontentcollapse0 .activity-add-text")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-label='Survey']")));
        driver.findElement(By.xpath("//div[@aria-label='Survey']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_name")));
        driver.findElement(By.id("id_name")).click();
        driver.findElement(By.id("id_name")).sendKeys("testSurvey");
        driver.findElement(By.id("id_template")).click();
        {
            WebElement dropdown = driver.findElement(By.id("id_template"));
            dropdown.findElement(By.xpath("//option[. = 'ATTLS (20 item version)']")).click();
        }
        driver.findElement(By.id("id_submitbutton")).click();
        driver.findElement(By.id("user-menu-toggle")).click();
        driver.findElement(By.linkText("Log out")).click();

    }
}
