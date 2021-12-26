package models.components.global;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class BottomNavComponent {
    //1. Create attribute 2./
    private final AppiumDriver<MobileElement> appiumDriver;
    private static final By loginLabelSel = MobileBy.AccessibilityId("Login");
    private static final By formLabelSel = MobileBy.AccessibilityId("Forms");
    private static final By swipeLabelSel = MobileBy.AccessibilityId("Swipe");


    public BottomNavComponent(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    //return mobile element
    public MobileElement loginLabelElem(){
        return appiumDriver.findElement(loginLabelSel);
    }

    public MobileElement formLabelElem(){
        return appiumDriver.findElement(formLabelSel);
    }

    public MobileElement swipeLabelElem(){
        return appiumDriver.findElement(swipeLabelSel);
    }

    //return interaction method
    public void clickLoginLabel(){
        appiumDriver.findElement(loginLabelSel).click();
    }

}
