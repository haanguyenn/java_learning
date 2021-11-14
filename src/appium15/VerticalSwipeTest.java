package appium15;

import appium14.Driver;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

//TODO : Swipe vertically until see WebdriverIO logo

public class VerticalSwipeTest {
    public static void main(String[] args) {
        Driver.startAppiumServer();
        try {
            AppiumDriver<MobileElement> androidDriver = Driver.getAndroidDriver();
            MobileElement swipeLabel = androidDriver.findElementByAccessibilityId("Swipe");
            swipeLabel.click();

            WebDriverWait webDriverWait = new WebDriverWait(androidDriver, 3);
            webDriverWait.until(ExpectedConditions.visibilityOf(androidDriver.findElementByXPath("//*[contains(@text, 'Or swipe vertical to')]")));
            Dimension windowSize = androidDriver.manage().window().getSize();
            int screenHeight = windowSize.getHeight();
            int screenWidth = windowSize.getWidth();

            int xStartPoint = screenWidth * 50 / 100;
            int xEndPoint = xStartPoint;
            int yStartPoint = screenHeight * 90 / 100;
            int yEndPoint = screenHeight * 10 / 100;
            ;

            //Convert to Point option => return (x,y)
            PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
            PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);

           //Perform touchAction
            TouchAction touchAction = new TouchAction(androidDriver);
            boolean isContinue = true;
            //in case the logo on top of screen -> try to find it in the beginning
            while(isContinue){
                try{
                    MobileElement webLogo = androidDriver.findElementByAccessibilityId("WebdriverIO logo");
                    isContinue = false;
                }catch (Exception exception){
                    touchAction
                            .press(startPoint)
                            .waitAction(new WaitOptions().withDuration(Duration.ofMillis(500)))
                            .moveTo(endPoint)
                            .release()
                            .perform();
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        Driver.stopAppiumServer();
    }
}
