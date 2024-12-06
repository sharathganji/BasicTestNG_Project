package my_package;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class ParamTest {
    WebDriver driver;
    @BeforeClass
    @Parameters({"browser"})
    public void setUp(String br){
        WebDriverManager.chromedriver().setup();

        switch (br){
            case "chrome"   : driver = new ChromeDriver(); break;
            case "firefox"  : driver = new FirefoxDriver(); break;
            default: System.out.println("invalid browser is entered:"); return;
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://tutorialsninja.com/demo/index.php?route=account/login");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void testPageTitle(){
        String pageTitle = driver.getTitle();
        System.out.println("page Title: "+pageTitle);
        Assert.assertEquals(pageTitle, "Account Login", "page title is not matching!");
    }

    @Test
    public void testCurrentURL(){
        String currentUrl = driver.getCurrentUrl();
        System.out.println("currentURL:"+ currentUrl);
        Assert.assertEquals(currentUrl, "https://tutorialsninja.com/demo/index.php?route=account/login", "current URL is not matching!!");
    }

    @Test
    public void testRegisterLink(){
        Assert.assertTrue(driver.findElement(By.xpath("(//a[@href='https://tutorialsninja.com/demo/index.php?route=account/register' and text() = 'Register'])[2]")).isDisplayed());
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }


}
