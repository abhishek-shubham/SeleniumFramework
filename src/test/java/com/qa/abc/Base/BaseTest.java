package com.qa.abc.Base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.abc.Utils.ReadExcel;
import com.qa.abc.factory.DriverFactory;
import com.qa.abc.pages.AccountsPage;
import com.qa.abc.pages.LoginPage;
import com.qa.abc.pages.ProductInfo;
import com.qa.abc.pages.RegistrationPage;
import com.qa.abc.pages.ResultsPage;

public class BaseTest {

	WebDriver driver;
	protected LoginPage loginPage;
	protected AccountsPage accountsPage;
	protected ResultsPage resultPage;
	protected ProductInfo productInfo;
	protected RegistrationPage registerPage;
	protected SoftAssert softAssert;
	
	protected DriverFactory df;
	protected Properties prop;
	protected ReadExcel readExcel;
	
	@Parameters({"browser"})
	@BeforeTest
	public void setUp(String browserName) {
		df=new DriverFactory();
		prop=df.initProp();
		if(browserName!=null) {
			prop.setProperty("browser", browserName);
		}
		else {
			prop.setProperty("browser", "chrome");
		}	
		driver=df.initDriver(prop);
		loginPage=new LoginPage(driver);
		readExcel=new ReadExcel();
		softAssert=new SoftAssert();
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
