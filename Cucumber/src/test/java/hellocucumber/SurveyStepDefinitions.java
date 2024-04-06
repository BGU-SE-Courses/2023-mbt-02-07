package hellocucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;

public class SurveyStepDefinitions {
    private final static String CHROME_DRIVER_PATH = "../Selenium/chromedriver.exe";
    private ChromeDriver driver;
    private WebDriverWait wait;
    @Given("a survey that is open to students and a student starts answering it")
    public void aSurveyThatIsOpenToStudentsAndAStudentStartsAnsweringIt() {

        //opening moodle
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        driver.get("https://sandbox.moodledemo.net/login/index.php");
        driver.get("https://sandbox.moodledemo.net");


        //TODO change all to xpath and instead of "isDisplayed" use visibilityOfElement
        //TODO instead of wait and then click change it to one line.
        //We first register as an admin
        driver.manage().window().setSize(new Dimension(1294, 1407));
        wait.until(webDriver -> driver.findElement(By.linkText("Log in")).isDisplayed());//TODO remove this
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Log in")));

        driver.findElement(By.linkText("Log in")).click();

        wait.until(webDriver -> driver.findElement(By.id("username")).isDisplayed());   

        driver.findElement(By.id("username")).click();
        // driver.findElement(By.xpath("//* [@id='username']")).click();
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("sandbox");
        driver.findElement(By.id("loginbtn")).click();

        //After registered as an admin, we create a new course, called "TestingSurvey"

        driver.findElement(By.linkText("Site administration")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Courses")));
        driver.findElement(By.linkText("Courses")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Add a new course")));
        driver.findElement(By.linkText("Add a new course")).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("id_fullname")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='id_fullname']")));

        driver.findElement(By.id("id_fullname")).click();
        driver.findElement(By.id("id_fullname")).sendKeys("TestingSurvey");
        driver.findElement(By.id("id_shortname")).click();
        driver.findElement(By.id("id_shortname")).sendKeys("TSY_s17");
        driver.findElement(By.id("id_saveanddisplay")).click();
//        driver.findElement(By.linkText("TestingSurvey")).click();
        driver.findElement(By.linkText("Participants")).click();
        driver.findElement(By.cssSelector("#enrolusersbutton-1 .btn")).click();
//        {
//          WebElement element = driver.findElement(By.cssSelector("#enrolusersbutton-1 .btn"));
//          Actions builder = new Actions(driver);
//          builder.moveToElement(element).perform();
//        }
//        {
//          WebElement element = driver.findElement(By.tagName("body"));
//          Actions builder = new Actions(driver);
//          builder.moveToElement(element, 0, 0).perform();
//        }
        // driver.findElement(By.id("form_autocomplete_input-1712332058318")).click();
//        driver.findElement(By.xpath("//textarea[@aria-label='Select users']"))
//                .sendKeys("Sam Student");
        System.out.println(driver.findElements(By.className("form-control")));
        String dropDownId= driver.findElements(By.className("form-control")).get(1).getAttribute("id");
        driver.findElements(By.className("form-control")).get(1).sendKeys("Sam Student");

//        driver.querySelector('[aria-label="Select users"]');
        //choose the first suggestion(giving the search above)
                // Locate the first option and click on it
//        WebElement firstOption = driver.findElement(By.xpath("//select[@aria-label='Select users']/option[1]"));
//        firstOption.click();


        // driver.findElement(By.id("form_autocomplete_input-1712332058318")).sendKeys("Sam Student");
        // driver.findElement(By.cssSelector("#form_autocomplete_suggestions-1712332058318-0 span:nth-child(2)")).click();
         driver.findElement(By.cssSelector("#"+ dropDownId+"-0 span:nth-child(2)")).click();
        driver.findElement(By.id("yui_3_18_1_1_1712332058082_231")).click();
        driver.findElement(By.id("yui_3_18_1_1_1712332058082_288")).click();
        driver.findElement(By.id("yui_3_18_1_1_1712332058082_37")).click();
        driver.findElement(By.id("form_autocomplete_input-1712332058319")).click();
        driver.findElement(By.id("form_autocomplete_input-1712332058319")).click();
        driver.findElement(By.id("form_autocomplete_input-1712332058319")).sendKeys("Terri Teacher");
        driver.findElement(By.id("form_autocomplete_input-1712332058319")).sendKeys(Keys.ENTER);
        driver.findElement(By.id("id_roletoassign")).click();
        {
          WebElement dropdown = driver.findElement(By.id("id_roletoassign"));
          dropdown.findElement(By.xpath("//option[. = 'Teacher']")).click();
        }
        driver.findElement(By.id("yui_3_18_1_1_1712332058082_453")).click();
        {
          WebElement element = driver.findElement(By.cssSelector(".modal-footer > .btn-primary"));
          Actions builder = new Actions(driver);
          builder.moveToElement(element).perform();
        }





    }

    @When("the teacher changes the access restrictions")
    public void theTeacherChangesTheAccessRestrictions() {
    }

    @Then("the student is unable to submit the survey")
    public void theStudentIsUnableToSubmitTheSurvey() {
    }
}
