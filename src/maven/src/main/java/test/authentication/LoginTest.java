package test.authentication;

import driver.Driver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import models.components.global.BottomNavComponent;
import models.pages.LoginPage;

public class LoginTest {
    public static void main(String[] args) {
        Driver.startAppiumServer();

        try {
            AndroidDriver<MobileElement> androidDriver = Driver.getAndroidDriver();
            LoginPage loginPage = new LoginPage(androidDriver);
            //Bottom Navigation
            BottomNavComponent bottomNavComponent = loginPage.bottomNavComponent();
            bottomNavComponent.clickLoginLabel();

            //Fill login form
            loginPage.inputUsername("hello@gmail.com");
            loginPage.inputPassword("12345678");
            loginPage.clickOnLoginBtn();

            //Get login text msg
            String loginMsg = loginPage.loginDialogueComponent().getMsgTitleSel();
            System.out.println(loginMsg);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Driver.stopAppiumServer();
        }
    }
}

