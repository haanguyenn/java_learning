package appium15;

import appium14.Driver;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

//TODO : Swipe horizontally until see GREAT COMMUNITY then break

public class HorizontalSwipeTest {
    public static void main(String[] args) {
        Driver.startAppiumServer();
        try {
            AppiumDriver<MobileElement> androidDriver = Driver.getAndroidDriver();
            //Click on Swipe label
            MobileElement swipeLabel = androidDriver.findElementByAccessibilityId("Swipe");
            swipeLabel.click();

            //Check Swipe available on screen
            WebDriverWait webDriverWait = new WebDriverWait(androidDriver, 3);
            webDriverWait.until(ExpectedConditions.visibilityOf(androidDriver.findElementByXPath("//*[contains(@text, 'Or swipe vertical to')]")));


            //Get window size
            Dimension windowSize = androidDriver.manage().window().getSize();
            int screenHeight = windowSize.getHeight();
            int screenWidth = windowSize.getWidth();

            //calculate touch point:
            int xStartPoint = screenWidth * 90 / 100;
            int xEndPoint = screenWidth * 10 / 100;
            int yStartPoint = screenHeight * 50 / 100;
            int yEndPoint = yStartPoint;

            //Convert to Point option => return (x,y)
            PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
            PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);

            //Touch action
            int SWIPE_MAX_TIME = 5;
            int swipeTime = 0;
            TouchAction touchAction = new TouchAction(androidDriver);
            //Flow: swipe = 0, check element. If element on screen -> matchedCards not empty == true-> break
            while (swipeTime <= SWIPE_MAX_TIME) {
                List<MobileElement> matchedCards = androidDriver.findElementsByXPath("//*[@text ='GREAT COMMUNITY']");
                if (!matchedCards.isEmpty()) {
                    break;
                } else {
                    touchAction
                            .press(startPoint)
                            .waitAction(new WaitOptions().withDuration(Duration.ofMillis(500)))
                            .moveTo(endPoint)
                            .release()
                            .perform();
                    swipeTime++;
                }
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        Driver.stopAppiumServer();
    }
}
