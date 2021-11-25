package appium17;

import appium14.Driver;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import java.awt.*;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SwipeToNotification {
    public static void main(String[] args) {
        Driver.startAppiumServer();
        try {
            AppiumDriver<MobileElement> androidDriver = Driver.getAndroidDriver();
            Dimension getWindowSize = androidDriver.manage().window().getSize();
            int screenHeight = getWindowSize.getHeight();
            int screenWidth = getWindowSize.getWidth();

            int xStartPoint = screenWidth / 2;
            int xEndPoint = xStartPoint;
            int yStartPoint = 0;
            int yEndPoint = screenHeight / 2;

            PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
            PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);


            TouchAction touchAction = new TouchAction(androidDriver);
            touchAction
                    .press(startPoint)
                    .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1)))
                    .moveTo(endPoint)
                    .release()
                    .perform();

            List<MobileElement> notificationElemList = androidDriver.findElements(By.id("android:id/notification_main_column"));
            if(notificationElemList.isEmpty()){
                throw new Exception("notificationElemList is empty");
            }
            Map<String, String>notificationList= new HashMap<>();

            notificationElemList.forEach(notification->{
                String title = notification.findElement(By.id("android:id/title")).getText();
                String text = notification.findElement(By.id("android:id/big_text")).getText();
                notificationList.put(title,text);
            });
            touchAction
                    .press(endPoint)
                    .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1)))
                    .moveTo(startPoint)
                    .release()
                    .perform();
            Thread.sleep(2000);
            notificationList.values().forEach(content->{
                System.out.println(content);
            });

        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            Driver.stopAppiumServer();
        }
    }
}
