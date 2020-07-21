package com.mobileAuto;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class Common {
	static AndroidDriver<WebElement> driver;
	@Test()
		public void TC_01_Verify_LaunchApp() throws IOException {
		
		// TODO Auto-generated method stub
		
		DesiredCapabilities caps= new DesiredCapabilities();
		//caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "A68FA8E0");
		
		caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "subathrainfotech.healthcare");
		caps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "subathrainfotech.healthcare.MainActivity");
		
		 
		// To invoke chrome browser
		//caps.setCapability(AndroidMobileCapabilityType.BROWSER_NAME,"Chrome");
		//caps.setCapability(AndroidMobileCapabilityType.CHROMEDRIVER_EXECUTABLE, "c:\\Selenium\\chromedriver.exe");
		
		/*
		//caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
		//caps.setCapability(AndroidMobileCapabilityType.CHROMEDRIVER_EXECUTABLE, "D:\\Webdrivers\\chromedriver.exe");
		*/
		
		driver = new AndroidDriver<WebElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//driver.get("http://www.google.com");
	/*	TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source, new File("./Screenshots/LaunchApp.png"));
		*/
		//driver.toggleWifi();
	}

}
