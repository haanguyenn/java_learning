package test.testng;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DemoTest {

    @BeforeTest
    public void beforeTest() {
        System.out.println("DemoTest: before test");
    }
    @AfterTest
    public void afterTest(){
        System.out.println("DemoTest: after test");
    }

    @Test
    public void loginDemoTest(){
        System.out.println("loginDemoTest");
    }
}
