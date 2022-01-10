package driver;

import caps.MobileCapabilityEx;
import flags.AndroidSeverFlagEx;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class DriverEx {
    private static AppiumDriverLocalService appiumServer;
    private static AndroidDriver<MobileElement> androidDriver;
    private final static String NODE_LOCATION = "/Users/macbook/.nvm/versions/node/v16.13.0/bin/node";
    private final static String APPIUM_LOCATION = "/Users/macbook/.nvm/versions/node/v16.13.0/bin/appium";

    public AppiumDriver<MobileElement>getAndroidDriver(){
        if(androidDriver == null){
            initAppiumServer();
        }
        return androidDriver;
    }

    private AppiumDriver<MobileElement> initAppiumServer() {
        AppiumServiceBuilder appiumServiceBuilder = new AppiumServiceBuilder();
        appiumServiceBuilder.withIPAddress("127.0.0.1").usingAnyFreePort();
        //Auto download chromdriver
        appiumServiceBuilder.withArgument(AndroidSeverFlagEx.ALLOW_INSECURE, "chromedriver_autodownload");
        appiumServiceBuilder.usingDriverExecutable(new File(NODE_LOCATION));
        appiumServiceBuilder.withAppiumJS(new File(APPIUM_LOCATION));
        appiumServer = AppiumDriverLocalService.buildService(appiumServiceBuilder);
        appiumServer.start();

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityEx.PLATFORM_NAME, "Android"); // extend từ MobileCapability
        desiredCapabilities.setCapability(MobileCapabilityEx.AUTOMATION_NAME, "uiautomator2");
        desiredCapabilities.setCapability(MobileCapabilityEx.UDID, "emulator-5554");
        desiredCapabilities.setCapability(MobileCapabilityEx.APP_PACKAGE, "com.wdiodemoapp");
        desiredCapabilities.setCapability(MobileCapabilityEx.APP_ACTIVITY, "com.wdiodemoapp.MainActivity");
//        desiredCapabilities.setCapability("noReset", "false");
        desiredCapabilities.setCapability(MobileCapabilityEx.NEW_COMMAND_TIMEOUT, 60);

        androidDriver = new AndroidDriver<>(appiumServer.getUrl(), desiredCapabilities);//connect with all desired capability
        androidDriver.manage().timeouts().implicitlyWait(60L, TimeUnit.SECONDS);
        return androidDriver;

    }

    public void quitAppiumSession(){
        if (androidDriver!=null){
            androidDriver.quit();
            androidDriver= null;
        }
    }
    public static void stopAppiumServer() {
        //kill using port tránh treo port
        String killNodeWindowCmd = "taskkill /F /IM node.exe";
        String killNodeLinuxCmd = "killall node";
        String killNodeCmd = System.getProperty("os.name").toLowerCase().startsWith("windows")
                ? killNodeWindowCmd : killNodeLinuxCmd;
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec(killNodeCmd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
