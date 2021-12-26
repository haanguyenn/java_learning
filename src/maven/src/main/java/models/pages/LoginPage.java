package models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import models.components.authentication.LoginDialogueComponent;
import models.components.global.BottomNavComponent;
import org.openqa.selenium.By;

public class LoginPage {
    private final AppiumDriver<MobileElement>appiumDriver;
    private static final By usernameSel = MobileBy.AccessibilityId("input-email");
    private static final By passwordSel = MobileBy.AccessibilityId("input-password");
    private static final By loginBtnSel = MobileBy.AccessibilityId("button-LOGIN");
    private BottomNavComponent bottomNavComponent;
    private LoginDialogueComponent loginDialogueComponent;

    public LoginPage(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public LoginPage inputUsername(String username){
        appiumDriver.findElement(usernameSel).sendKeys(username);
        return this;
    }
    public LoginPage inputPassword(String password){
        appiumDriver.findElement(passwordSel).sendKeys(password);
        return this;
    }
    public LoginPage clickOnLoginBtn(){
        appiumDriver.findElement(loginBtnSel).click();
        return this;
    }

    public BottomNavComponent bottomNavComponent(){
        return new BottomNavComponent(appiumDriver);
    }

    public LoginDialogueComponent loginDialogueComponent() {
        return new LoginDialogueComponent(appiumDriver);
    }

    //    public MobileElement usernameSel(){
//        return appiumDriver.findElement(usernameSel);
//    }
//    public MobileElement passwordSel(){
//        return appiumDriver.findElement(passwordSel);
//    }
//    public MobileElement loginBtnSel(){
//        return appiumDriver.findElement(loginBtnSel);

//    }




}
