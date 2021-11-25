package appium17;

import appium14.Driver;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;

public class TakeScreenshot {
    public static void main(String[] args) {
        Driver.startAppiumServer();
        try{
            AppiumDriver<MobileElement> androidDriver = Driver.getAndroidDriver();
            MobileElement formLabel = androidDriver.findElementByAccessibilityId("Forms");
            formLabel.click();
            WebDriverWait webDriverWait = new WebDriverWait(androidDriver, 3);
            webDriverWait.until(ExpectedConditions.visibilityOf(androidDriver.findElementByAccessibilityId("text-input")));
            MobileElement switchToggleElem = androidDriver.findElementByAccessibilityId("switch");

            //screenshot the whole screen
            File base64LoginFormScreenshot = androidDriver.getScreenshotAs(OutputType.FILE);
            String fileLoginPath = System.getProperty("user.dir").concat("/screenshot/").concat("LoginForm.png");
            FileUtils.copyFile(base64LoginFormScreenshot, new File(fileLoginPath));

            //screenshot the element
            File base64SwitchToggleScreenshot = switchToggleElem.getScreenshotAs(OutputType.FILE);
            String fileSwitchTogglePath = System.getProperty("user.dir").concat("/screenshot/").concat("SwitchToggle.png");
            FileUtils.copyFile(base64SwitchToggleScreenshot, new File(fileSwitchTogglePath));

        }catch(Exception exception){
            exception.printStackTrace();
        }finally {
            Driver.stopAppiumServer();
        }
    }
}
