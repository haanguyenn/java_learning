package appium14;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class LaunchApp {
    public static void main(String[] args) {
        Driver.startAppiumServer();
        //try catch here to avoid No such element exception
        try {
            AndroidDriver<MobileElement> androidDriver = Driver.getAndroidDriver();
            MobileElement loginLabel = androidDriver.findElementByAccessibilityId("Login");
            loginLabel.click();
            final int EMAIL_INPUT_INDEX = 0;
            final int PASSWORD_INPUT_INDEX = 1;

            // Find field by xpath
            //Return list of element (in case xpath too long or complicated)
            //Nếu không tìm được, nó sẽ trả ra list empty. Sẽ không return NosuchElement
            List<MobileElement> credsFormEle = androidDriver.findElementsByXPath("//android.widget.EditText");
            credsFormEle.get(EMAIL_INPUT_INDEX).sendKeys("hello@gmail.com");//avoid get(0), get(1) => dirty code
            credsFormEle.get(PASSWORD_INPUT_INDEX).sendKeys("123345555");//avoid get(0), get(1) => dirty code

            //Return element
            MobileElement emailInputEle = androidDriver.findElementByXPath("//android.widget.EditText[@content-desc='input-email']");
            MobileElement passwordInputEle = androidDriver.findElementByXPath("//android.widget.EditText[@content-desc='input-password']");
            MobileElement loginBtnEle = androidDriver.findElementByAccessibilityId("button-LOGIN");

            //input username password
            emailInputEle.sendKeys("hello@gmail.com");
            passwordInputEle.sendKeys("123345555");
            //Search text by xpath
            MobileElement loginFeatureDescEle = androidDriver.findElementByXPath("//*[contains(@text,'When the device')]");
            System.out.println(loginFeatureDescEle.getText());

            //find by uiselector
            MobileElement loginDescEleByUiSele = androidDriver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"When the device\").className(\"android.widget.TextView\")");
            System.out.println(loginDescEleByUiSele.getText());

            loginBtnEle.click();

            //Explicit wait
            WebDriverWait wait = new WebDriverWait(androidDriver, 45L);
            //wait max 45s then find if element is located => find element by ID (avoid No such element)
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/alertTitle")));
            MobileElement loginDialogEle = androidDriver.findElementById("android:id/alertTitle");
            System.out.println(loginDialogEle.getText());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Driver.stopAppiumServer();
        }
    }
}
