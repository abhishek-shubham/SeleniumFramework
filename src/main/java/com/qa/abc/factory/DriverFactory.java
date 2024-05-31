package com.qa.abc.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.log4testng.Logger;

import com.qa.abc.frameworkException.FrameException;


public class DriverFactory {
	
	WebDriver driver;
	OptionsManager optManager;
	public static String highlightElement;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	public static final Logger log = Logger.getLogger(DriverFactory.class);
	
	public WebDriver initDriver(Properties prop) {
		String browserName = prop.getProperty("browser").trim();
		System.out.println("browser is : "+browserName);
		log.info("browser name is : "+browserName);
		optManager = new OptionsManager(prop);
		highlightElement = prop.getProperty("highlight");
		switch (browserName.toLowerCase()) {
		case "chrome":
			//driver=new ChromeDriver(optManager.getChromeOption());
			tlDriver.set(new ChromeDriver(optManager.getChromeOption()));
			break;
		case "firefox":
			//driver=new FirefoxDriver(optManager.getFirefoxOption());
			tlDriver.set(new FirefoxDriver(optManager.getFirefoxOption()));
			break;
		case "edge":
			//driver=new EdgeDriver(optManager.getEdgeOption());
			tlDriver.set(new EdgeDriver(optManager.getEdgeOption()));
			break;
		default:
			System.out.println("please provide correct browser");
			throw new FrameException("NoBrowserFoundException");
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}
	
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	
	public Properties initProp() {
		
		Properties prop = new Properties();
		FileInputStream ip = null;
		
		String env = System.getProperty("env");
		System.out.println("env is ----------------  "+env);
		log.info("env name is : "+env);
		try {
		if(env==null) {		
			System.out.println("no env is provided so running it on QA env");
			log.info("no env provided so running it on QA");
			ip = new FileInputStream(".\\src\\main\\resources\\config\\qa.config.properties");
		}
		else {
			switch (env.toLowerCase().trim()) {
			case "qa":
				ip = new FileInputStream(".\\src\\main\\resources\\config\\qa.config.properties");
				break;
			case "stage":
				ip = new FileInputStream(".\\src\\main\\resources\\config\\stage.config.properties");
				break;
			case "dev":
				ip = new FileInputStream(".\\src\\main\\resources\\config\\dev.config.properties");
				break;
			case "uat":
				ip = new FileInputStream(".\\src\\main\\resources\\config\\uat.config.properties");
				break;

			default:
				System.out.println("not valid environment");
				throw new FrameException("NotValidEnvGiven");
			}
		}
		}
		
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop;
		
		/*Properties prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream(".\\src\\main\\resources\\config\\qa.config.properties");
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;*/		
	}

}
