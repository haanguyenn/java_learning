package test.authentication;

import driver.Driver;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.components.global.BottomNavComponent;
import models.pages.LoginPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import test.BaseTest;

public class LoginTestEx extends BaseTest {
    @Test
    public void loginWithCorrectCreds() {
        AppiumDriver<MobileElement> androidDriver = getDriver();
        LoginPage loginPage = new LoginPage(androidDriver);
        //Bottom Navigation
        BottomNavComponent bottomNavComponent = loginPage.bottomNavComponent();
        bottomNavComponent.clickLoginLabel();

        //Fill login form
        loginPage
                .inputUsername("hello@gmail.com")
                .inputPassword("12345678")
                .clickOnLoginBtn();

        //Get login text msg
        String actualLoginMsg = loginPage.loginDialogueComponent().getMsgTitleSel();
        String errorMsg = "[ERR] Login msg is incorrect";
        boolean isTitleCorrect = actualLoginMsg.equals("success");

        //Verification
//            Assert.assertEquals(actualLoginMsg,"success", "[ERR] Login msg is incorrect");

        //SoftAssert. NEED to call assertAll at the end. If not, scripts always Passed
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(isTitleCorrect, errorMsg);
        softAssert.assertEquals(actualLoginMsg, "success", "Hello");
        softAssert.assertAll();


    }

}
