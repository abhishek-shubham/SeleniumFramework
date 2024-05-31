package com.qa.abc.Utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.qa.abc.factory.DriverFactory;

public class ScreenshotUtils {
	WebDriver driver;
	protected static DriverFactory df ;
	public static String takeScreenshot() {
		df = new DriverFactory();
		File src = ((TakesScreenshot)df.getDriver()).getScreenshotAs(OutputType.FILE);
		//File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String myPath = System.getProperty("user.dir")+"/screenshot/"+System.currentTimeMillis()+".png";
		File destination = new File(myPath);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return myPath;
	}
}
