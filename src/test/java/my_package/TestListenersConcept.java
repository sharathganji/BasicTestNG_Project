package my_package;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.Duration;
//@Listeners(my_package.MyListener.class)
public class TestListenersConcept {
    WebDriver driver;
    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://tutorialsninja.com/demo/index.php?route=account/login");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test(priority = 0)
    public void login() throws InterruptedException {
        driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("sharath.ganji@gmail.com");
        driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("8187868603");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        Thread.sleep(3000);
    }

    @Test(priority = 1)
    public void verifyCurrentURL(){
        System.out.println(driver.getTitle()+" :page title"+"::"+driver.getCurrentUrl());
        Assert.assertEquals(driver.getCurrentUrl(), "hello world");
    }

    @Test(priority = 2, dependsOnMethods = {"verifyCurrentURL"})
    public void verifyTitle(){
        Assert.assertEquals(driver.getTitle(), "page title");
    }
    @Test
    public void viewOne(){
        System.out.println("view one");
    }
    @Test
    public void viewTwo(){
        System.out.println("view two");
    }
    @Test
    public void viewThree(){
        System.out.println("view three");
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
