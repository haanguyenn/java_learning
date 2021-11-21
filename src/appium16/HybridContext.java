package appium16;

import appium14.Driver;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

//TODO 1 : Get app context and switch context
//TODO 2 : Set up chromedriver_autodownload
//TODO 3 : Handle webview context
//Ps to my Mentor: I couldn't load webview in emulator (white screen) => write code without proper execution
// I skipped Menu list items
//TODO 4 : Run app in background


public class HybridContext {
    public static void main(String[] args) {
        Driver.startAppiumServer();
        try{
            AppiumDriver<MobileElement> appiumDriver = Driver.getAndroidDriver();
            MobileElement webViewLabel = appiumDriver.findElementByAccessibilityId("Webview");
            webViewLabel.click();

//            get context -> return set
            for (String context : appiumDriver.getContextHandles()) {
                System.out.println(context);
            }

            //switch context native -> webview && interact with webElement
            appiumDriver.context("WEBVIEW_com.wdiodemoapp");
            //Cannot find element so I try/catch here (TODO 3)
            try{
                WebDriverWait webDriverWait = new WebDriverWait(appiumDriver,5);
                webDriverWait.until(ExpectedConditions.visibilityOf(appiumDriver.findElementByCssSelector(".navbar__toggle")));
                WebElement navbarToggleBtnElem = appiumDriver.findElementByCssSelector(".navbar__toggle");
                navbarToggleBtnElem.click();

                WebElement searchBtnElem = appiumDriver.findElementByCssSelector(".DocSearch");
                searchBtnElem.click();
                webDriverWait.until(ExpectedConditions.visibilityOf(appiumDriver.findElementByCssSelector(".DocSearch-Input")));
                WebElement docSearchInputField = appiumDriver.findElementByCssSelector(".DocSearch-Input");
                docSearchInputField.click();
                docSearchInputField.clear();
                docSearchInputField.sendKeys("Appium");
            }catch(Exception ignore){
            }

            //switch context webview -> native
            appiumDriver.context("NATIVE_APP");
            MobileElement loginLabel = appiumDriver.findElementByAccessibilityId("Login");
            loginLabel.click();
            appiumDriver.runAppInBackground(Duration.ofSeconds(10));

            MobileElement emailInputEle = appiumDriver.findElementByXPath("//android.widget.EditText[@content-desc='input-email']");
            MobileElement passwordInputEle = appiumDriver.findElementByXPath("//android.widget.EditText[@content-desc='input-password']");
            MobileElement loginBtnEle = appiumDriver.findElementByAccessibilityId("button-LOGIN");
            emailInputEle.sendKeys("hello@gmail.com");
            passwordInputEle.sendKeys("123345555");
        }catch(Exception exception){
            exception.printStackTrace();
        }finally {
            Driver.stopAppiumServer();
        }
    }
}
