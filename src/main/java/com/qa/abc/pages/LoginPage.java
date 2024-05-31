package com.qa.abc.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.abc.Utils.ElementUtils;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtils eleUtil;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtils(this.driver);
	}
	
	private By userIdEle = By.id("input-email");
	private By passwordEle = By.id("input-password");
	private By loginBttnEle = By.xpath("//input[@value='Login']");
	private By forgotPW = By.xpath("//a[text()='Forgotten Password']");
	private By footerLinks = By.xpath("//footer//a");
	private By register = By.linkText("Register");
	
	public String getLoginPageTitle() {
		return eleUtil.waitForTitleAndCapture("Account Login", 5);
	}
	
	public String getLoginPageUrl() {
		return eleUtil.waitForUrlContainsAndCapture("login", 5);
	}
	
	public boolean isForgotPwdExist() {
		return eleUtil.checkElementDisplayed(forgotPW);
	}
	
	public AccountsPage doLogin(String usernameApp, String passwordApp) {
		//eleUtil.waitForEleVisible(userIdEle, 5).sendKeys(usernameApp);
		eleUtil.doSendKeys(userIdEle, usernameApp);
		eleUtil.doSendKeys(passwordEle, passwordApp);
		//driver.findElement(userIdEle).sendKeys(usernameApp);
		//driver.findElement(passwordEle).sendKeys(passwordApp);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		eleUtil.doClick(loginBttnEle);
		return new AccountsPage(driver);
	}

	public List<String> getFoorterLinks() {
		List<WebElement> footerLink = driver.findElements(footerLinks);
		List<String> footerLinkText = new ArrayList<String>();
		for(WebElement e : footerLink) {
			String s = e.getText();
			footerLinkText.add(s);
		}
		return footerLinkText;
	}
	
	public RegistrationPage navigateToRegisterPage() {
		eleUtil.doClick(register);
		return new RegistrationPage(driver);
	}
}
