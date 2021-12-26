package test.testng;

import org.testng.annotations.*;

public class LoginTest {
    //Order: before test > before class > before method
    @BeforeTest
    public void beforeTest() {
        System.out.println("before test");
    }
    @AfterTest
    public void afterTest() {
        System.out.println("after test");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("before class");
    }
    @AfterClass
    public void afterClass() {
        System.out.println("after class");
    }

    //Before method: run before every method here
    @BeforeMethod
    public void beforeMethod() {
        System.out.println("before method ");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("after method ");
    }

    @Test
    public void loginCorrectCreds() {
        System.out.println("Login loginCorrectCreds");

    }

    @Test
    public void loginIncorrectCreds() {
        System.out.println("Login loginIncorrectCreds");

    }
}
