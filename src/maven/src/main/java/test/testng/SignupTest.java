package test.testng;

import org.testng.annotations.Test;

public class SignupTest {
    @Test
    public void signupWithCorrectEmail(){
        System.out.println("SignupWithCorrectEmail");

    }
    @Test
    public void signupWithIncorrectEmail(){
        System.out.println("SignupWithIncorrectEmail");

    }
}
