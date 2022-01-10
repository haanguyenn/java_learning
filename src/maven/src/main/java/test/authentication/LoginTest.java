package test.authentication;

import driver.Driver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import models.components.global.BottomNavComponent;
import models.pages.LoginPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import test_data.LoginCreds;
import test_data.authentication.DataObjectBuilder;

public class LoginTest {
    @Test(dataProvider = "loginCredsData")
    public void loginWithCorrectCreds(LoginCreds loginCreds) {
        Driver.startAppiumServer();
        try {
            AndroidDriver<MobileElement> androidDriver = Driver.getAndroidDriver();
            LoginPage loginPage = new LoginPage(androidDriver);
            //Bottom Navigation
            BottomNavComponent bottomNavComponent = loginPage.bottomNavComponent();
            bottomNavComponent.clickLoginLabel();

            //Fill login form
            loginPage
                    .inputUsername(loginCreds.getUsername())
                    .inputPassword(loginCreds.getPassword())
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

            //Hard assertion: stop when error happens => in this case, sout never run
            //Assert.assertTrue(isTitleCorrect,errorMsg);
            //System.out.println("hello world");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Driver.stopAppiumServer();
        }
    }

    //2-dimension arrays,
    @DataProvider
    public LoginCreds[] loginCredsData() {
        String jsonPath = "/src/main/resources/test-data/loginCredsArray.json";
        LoginCreds[] loginCreds = DataObjectBuilder.buildLoginCreds(jsonPath);
        return loginCreds;
    }
}
