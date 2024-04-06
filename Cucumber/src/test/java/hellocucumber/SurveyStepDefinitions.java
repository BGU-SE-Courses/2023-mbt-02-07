package hellocucumber;

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
   
        // create random 4 digit number
        Random random = new Random();
        int randomNumber = random.nextInt(9000) + 1000;
        String shortName = "TSY_s" + randomNumber;

        driver.findElement(By.id("id_shortname")).sendKeys(shortName);
        
        // driver.findElement(By.id("id_shortname")).sendKeys("TSY_s1234");//TODO change it to be random

        driver.findElement(By.id("id_saveanddisplay")).click();
//        driver.findElement(By.linkText("TestingSurvey")).click();


        /**Adding a student to the course*/
        driver.findElement(By.linkText("Participants")).click();
        driver.findElement(By.cssSelector("#enrolusersbutton-1 .btn")).click();

//        System.out.println(driver.findElements(By.className("form-control")));
//        System.out.println((long) driver.findElements(By.className("form-control")).size());
        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("form-control"),6));
        String dropDownId= driver.findElements(By.className("form-control")).get(5).getAttribute("id");
        driver.findElements(By.className("form-control")).get(5).sendKeys("Sam Student");
        System.out.println("#"+ dropDownId+"-0 span:nth-child(2)");
//        driver.findElements(By.className("form-control")).get(5).sendKeys(Keys.ENTER);
//        driver.querySelector('[aria-label="Select users"]');
        //choose the first suggestion(giving the search above)
                // Locate the first option and click on it
//        WebElement firstOption = driver.findElement(By.xpath("//select[@aria-label='Select users']/option[1]"));
//        firstOption.click();
        // driver.findElement(By.id("form_autocomplete_input-1712332058318")).sendKeys("Sam Student");
        // driver.findElement(By.cssSelector("#form_autocomplete_suggestions-1712332058318-0 span:nth-child(2)")).click();
//        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#"+ dropDownId+"-0 span:nth-child(2)")));
//        driver.findElement(By.cssSelector("#"+ dropDownId+"-0 span:nth-child(2)")).click();
        WebElement dropdownElement = driver.findElement(By.id(dropDownId));
//        wait.until(() -> {dropdownElement.findElements(By.tagName("option")).size() == 1);
//        wait.until(ExpectedConditions.numberOfElementsToBe(By.tagName("option"), 1));
        WebElement finalDropdownElement = dropdownElement;
        wait.until((driver)->{
            System.out.println((long) finalDropdownElement.findElements(By.tagName("option")).size());
//            return dropdownElement.findElements(By.tagName("option")).size() == 693;
            return driver.findElements(By.tagName("option")).size() == 693;


        });
        driver.findElement(By.id(dropDownId)).sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("btn-primary"),15));
        driver.findElements(By.className("btn-primary")).get(14).click();


//document.querySelectorAll( # for id and . for class)


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
//            while(dropdown.findElements(By.xpath("//option[. = 'Teacher']")).size() == 0){
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
            //TODO maybe add a wait until the xpath of theacher is found
            dropdownSelect.selectByValue("3");
            dropdown.click();
            
//            dropdown.findElement(By.xpath("//option[. = 'Teacher']")).click();
        }



        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("btn-primary"),15));
        driver.findElements(By.className("btn-primary")).get(14).click();

        /** added teacher to course*/









    }

    @When("the teacher changes the access restrictions")
    public void theTeacherChangesTheAccessRestrictions() {
    }

    @Then("the student is unable to submit the survey")
    public void theStudentIsUnableToSubmitTheSurvey() {
    }
}
