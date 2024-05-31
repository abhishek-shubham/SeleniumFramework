package com.qa.abc.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.qa.abc.Base.BaseTest;
import com.qa.abc.Utils.AppConstants;
import com.qa.abc.factory.DriverFactory;
import com.qa.abc.pages.LoginPage;

public class LoginTest extends BaseTest {
	
	@Test
	public void loginPageTitleTest() {
		String actTitle=loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE_VALUE);
	}
	
	@Test
	public void loginPageUrlTest() {
		String actUrl = loginPage.getLoginPageUrl();
		Assert.assertTrue(actUrl.contains(AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE));
	}
	
	@Test
	public void forgotPasswordPresentTest() {
		Assert.assertTrue(loginPage.isForgotPwdExist());
	}
	
	@Test
	public void zdoLoginTest() {
		accountsPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(accountsPage.isLogoutLinkExist());
	}
}
