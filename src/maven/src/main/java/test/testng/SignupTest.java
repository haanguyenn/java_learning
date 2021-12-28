package test.testng;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SignupTest {
    @BeforeMethod
    public void beforeMethod() {
        System.out.println("SignupTest: before method ");
    }
    @AfterMethod
    public void afterMethod() {
        System.out.println("SignupTest: after method ");
    }


    @Test
    public void signupWithCorrectEmail(){
        System.out.println("SignupWithCorrectEmail");

    }
    @Test
    public void signupWithIncorrectEmail(){
        System.out.println("SignupWithIncorrectEmail");

    }
}
