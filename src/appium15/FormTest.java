package appium15;


import appium14.Driver;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
//TODO 1 : Swipe vertically and click button Active
//TODO 2 : Send/Delete text in input field
//TODO 3 : Select Dropdown list & Get option list

public class FormTest {
    public static void main(String[] args) {
        Driver.startAppiumServer();
        try {
            AppiumDriver<MobileElement> androidDriver = Driver.getAndroidDriver();
            //Click on Form label
            MobileElement formLabel = androidDriver.findElementByAccessibilityId("Forms");
            formLabel.click();

            //Check form screen availability
            WebDriverWait webDriverWait = new WebDriverWait(androidDriver, 3);
            webDriverWait.until(ExpectedConditions.visibilityOf(androidDriver.findElementByAccessibilityId("text-input")));

            //Input text in input field
            MobileElement inputField = androidDriver.findElementByAccessibilityId("text-input");
            inputField.sendKeys("Hello Appium");
            inputField.clear();
            inputField.sendKeys("Hello Appium, again");

            //Toggle ON/OFF
            MobileElement toggleSwitch = androidDriver.findElementByAccessibilityId("switch");
            toggleSwitch.click();

            //Get window size -> return Dimension from selenium
            Dimension windowSize = androidDriver.manage().window().getSize();
            int screenHeight = windowSize.getHeight();
            int screenWidth = windowSize.getWidth();

            //calculate touch point: x in the middle of width, y is scrolling area
            int xStartPoint = screenWidth * 50 / 100;
            int xEndPoint = xStartPoint;
            int yStartPoint = screenHeight * 90 / 100;
            int yEndPoint = screenHeight * 10 / 100;

            //Convert to Point option => return (x,y)
            PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
            PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);

            //Touch action to perform swipe down
            TouchAction touchAction = new TouchAction(androidDriver);
            // Note: need waitAction(WaitOptions waitOptions) (1sec or 500ms)
            touchAction
                    .press(startPoint)
                    .waitAction(new WaitOptions().withDuration(Duration.ofMillis(1000)))
                    .moveTo(endPoint)
                    .release()
                    .perform();
            //Perform swipe up
            touchAction
                    .longPress(endPoint)
                    .moveTo(startPoint)
                    .release()
                    .perform();

            //Perform swipe down again to click button
            touchAction
                    .press(startPoint)
                    .waitAction(new WaitOptions().withDuration(Duration.ofMillis(1000)))
                    .moveTo(endPoint)
                    .release()
                    .perform();

            //Select & get item in dropdown list
            MobileElement dropdownListEle = androidDriver.findElementByAccessibilityId("Dropdown");
            dropdownListEle.click();
            webDriverWait.until(ExpectedConditions.visibilityOf(androidDriver.findElement(By.id("android:id/content"))));
            List<MobileElement> optionList = androidDriver.findElementsById("android:id/text1");
            for (MobileElement optionItem : optionList) {
                System.out.println(optionItem.getText());
            }

            MobileElement optionItem = androidDriver.findElementByXPath("//*[@text='Appium is awesome']");
            optionItem.click();

//            Click on btn Active & OK
            MobileElement activeBtn = androidDriver.findElementByAccessibilityId("button-Active");
            activeBtn.click();
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("android:id/content")));
            MobileElement activeDialogEle = androidDriver.findElementById("android:id/message");
            System.out.println(activeDialogEle.getText());
            MobileElement btnOKDialogEle = androidDriver.findElementById("android:id/button1");
            btnOKDialogEle.click();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Driver.stopAppiumServer();
        }
    }
}
