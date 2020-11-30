package com.capabilities;




import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.utils.BaseClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class Capabilities extends BaseClass
{
	
	
	
	public static AndroidDriver<AndroidElement>  setCapabilities_Android() throws Exception
	{
	
		DesiredCapabilities cap = new DesiredCapabilities();
		
		// if using emulator  ----> provide device name as emulator
		//real device then you can provide your name
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_3a_API_27");
		//cap.setCapability(MobileCapabilityType.DEVICE_NAME, readProperties().get("Pixel_3a_API_27"));
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, readProperties().get("platFormName"));
		cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, readProperties().get("appActivity"));
		cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, readProperties().get("appPackage"));
		cap.setCapability(AndroidMobileCapabilityType.CHROMEDRIVER_EXECUTABLE,readProperties().get("chromeExecutable"));
		//capability.setCapability(MobileCapabilityType.);
		
		AndroidDriver<AndroidElement> driver = 
				new AndroidDriver<AndroidElement>(new URL("http://0.0.0.0:8888/wd/hub"), cap);
		
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
