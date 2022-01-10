package test;

import driver.DriverEx;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BaseTest {
    //Thread safe by synchronizedList. Ensure conflicts among threads when iterating the list below
    private final static List<DriverEx> driverThreadPool = Collections.synchronizedList(new ArrayList<>());

    //LocalThread to isolate appiumThread
    private static ThreadLocal<DriverEx> driverThread;
    //alwaysRun overrides any other rules available
    //()->{} anonymous function
    @BeforeSuite(alwaysRun = true)
    public static void beforeSuite(){
        driverThread = ThreadLocal.withInitial(()->{
            DriverEx driverThread = new DriverEx();
            driverThreadPool.add(driverThread);
            return driverThread;
        });
    }
    @AfterSuite(alwaysRun = true)
    public static void afterSuite(){
        for (DriverEx driverThread : driverThreadPool) {
            driverThread.quitAppiumSession();
        }
    }
    public static AppiumDriver<MobileElement>getDriver(){
        return driverThread.get().getAndroidDriver();
    }

}
