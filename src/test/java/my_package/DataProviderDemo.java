package my_package;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class DataProviderDemo {

    WebDriver driver;
    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test(dataProvider = "loginData")
    public void login(String uname, String pwd) throws InterruptedException {
        driver.get("https://tutorialsninja.com/demo/index.php?route=account/login");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(uname);
        driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys(pwd);
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        Thread.sleep(3000);

    }

    @DataProvider(name="loginData", indices = {0,3,4})
    public Object[][] data(){

        return new Object[][]{ {"qq0@gmail.com", "11"},
                            {"ww1@gmail.com", "22"},
                            {"ee2@gmail.com", "33"},
                            {"rr3@gmail.com", "44"},
                            {"tt4@gmail.com", "55"}
        };
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
