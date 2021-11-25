package appium17;

import appium14.Driver;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

import java.time.Duration;

public class HandleMultipleApps {
    public static void main(String[] args) {
        Driver.startAppiumServer();
        try {
            AppiumDriver<MobileElement> androidDriver = Driver.getAndroidDriver();
            MobileElement loginLabel = androidDriver.findElementByAccessibilityId("Login");
            loginLabel.click();
            //Return element
            MobileElement emailInputEle = androidDriver.findElementByXPath("//android.widget.EditText[@content-desc='input-email']");
            MobileElement passwordInputEle = androidDriver.findElementByXPath("//android.widget.EditText[@content-desc='input-password']");
            MobileElement loginBtnEle = androidDriver.findElementByAccessibilityId("button-LOGIN");

            //input username password
            emailInputEle.sendKeys("hello@gmail.com");
            passwordInputEle.sendKeys("123345555");
            loginBtnEle.click();
            //put app into bg until we call it again
            androidDriver.runAppInBackground(Duration.ofSeconds(-1));
            //open settings of application -> adb -> get app package & activity check packages of the apps
            androidDriver.activateApp("com.android.settings");
            Thread.sleep(4000);

            //Do something in settings -> Show/ battery percentage
            MobileElement batteryElem = androidDriver.findElementByXPath("//*[@text = 'Battery']");
            batteryElem.click();
            MobileElement batteryShowPercentageToggle = androidDriver.findElement(By.id("android:id/switch_widget"));
            boolean isBatteryShowPercentageToggleOn = batteryShowPercentageToggle.getText().equals("OFF");
            Thread.sleep(5000);
            if (isBatteryShowPercentageToggleOn) {
                batteryShowPercentageToggle.click();
                batteryShowPercentageToggle.click();
            } else {
                batteryShowPercentageToggle.click();
            }
            //Relaunch the app
            androidDriver.activateApp("com.wdiodemoapp");

        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            Driver.stopAppiumServer();
        }
    }
}
