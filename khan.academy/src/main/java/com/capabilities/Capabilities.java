package com.capabilities;




import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.utils.BaseClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;

public class Capabilities extends BaseClass
{
	
	
	
	public static AndroidDriver<AndroidElement>  setCapabilities_Android() throws Exception
	{
	
		DesiredCapabilities cap = new DesiredCapabilities();
		
		// if using emulator  ----> provide device name as emulator
		//real device then you can provide your name
		
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, readProperties().get("Pixel_3a_API_27"));
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, readProperties().get("platFormName"));
		cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, readProperties().get("appActivity"));
		cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, readProperties().get("appPackage"));
		cap.setCapability(AndroidMobileCapabilityType.CHROMEDRIVER_EXECUTABLE,readProperties().get("chromeExecutable"));
		//capability.setCapability(MobileCapabilityType.);
		
		AndroidDriver<AndroidElement> driver = 
				new AndroidDriver<AndroidElement>(new URL("http://0.0.0.0:4723/wd/hub"), cap);
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		return driver;
	}
	
	
	public static IOSDriver<IOSElement>   getCapabilities() throws MalformedURLException
	{
		File fp = new File("src//UIKitCatalog.app");
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 8 Plus");
		capability.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13.5");
		capability.setCapability(MobileCapabilityType.PLATFORM_NAME, "IOS");
		capability.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
		capability.setCapability(MobileCapabilityType.APP,fp.getAbsolutePath());
		//capability.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.androidsample.generalstore.SplashActivity");
		//capability.setCapability(AndroidMobileCapabilityType.CHROMEDRIVER_EXECUTABLE,"/Users/mohammad.suhail/Desktop/restassured_jars/Selenium/chromeDriver/chrome86/chromedriver");
		//capability.setCapability(MobileCapabilityType.);
		
		IOSDriver<IOSElement> driver = 
				new IOSDriver<IOSElement>(new URL("http://0.0.0.0:8888/wd/hub"), capability);
	
		return driver;
	}
	
	/**
	 * Starts an emulator for the provided AVD name
	 * 
	 * @param nameOfAVD
	 * @throws Exception 
	 */
	public static void launchEmulator() throws Exception
	{
	 System.out.println("Starting emulator for '" + getDeviceName() + "' ...");
	 String[] aCommand = new String[] { readProperties().get("emulatorPath").toString(), "-avd", "Pixel_3a_API_27" };
	 try {
	  Process process = new ProcessBuilder(aCommand).start();
	  process.waitFor(10, TimeUnit.SECONDS);
	  System.out.println("Emulator launched successfully!");
	 } catch (Exception e) {
	  e.printStackTrace();
	 }
	}
	
	
	public static String getDeviceName() throws Exception
	{

		return readProperties().get("deviceName").toString();
		
	}
	
	

}
