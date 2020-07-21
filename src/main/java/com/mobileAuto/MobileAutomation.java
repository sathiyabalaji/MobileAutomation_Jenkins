package com.mobileAuto;
//package com.appium.practice.Appium;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.mobile.NetworkConnection.ConnectionType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.connection.ConnectionState;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class MobileAutomation extends Common {

	@Test()
	public void TC_03_Verify_CloseApp_is_successful() throws IOException {
		Common.driver.closeApp();
		
	}
		
	public static void verticalSwipeByPercentages(AndroidDriver driver,double startPercentage, double endPercentage) {
        Dimension size = driver.manage().window().getSize();
        int x = (int) (size.width)/2;
        int y_startPoint = (int) (size.height * startPercentage);
        int y_endPoint = (int) (size.height * endPercentage);
 
        		TouchAction touchAction=new TouchAction(driver);
                touchAction.press(PointOption.point(x, y_startPoint))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(x, y_endPoint))
                .release().perform();
    }
}