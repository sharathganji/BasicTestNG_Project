package my_package;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FirstTestCase {


    @Test(priority = 0)
    public void openApplication(){
        System.out.println("open application");
    }
    @Test(priority = 1)
    public void login(){
        System.out.println("Login");
    }
    @Test(priority = 2)
    public void logOut(){
        System.out.println("logout");
    }


}
