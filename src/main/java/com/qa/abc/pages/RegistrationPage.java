package com.qa.abc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.abc.Utils.ElementUtils;

public class RegistrationPage {
	
	private WebDriver driver;
	private ElementUtils eleUtils;
	
	public RegistrationPage(WebDriver driver) {
		this.driver=driver;
		eleUtils = new ElementUtils(this.driver);
	}
	
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By subscribeYes = By.xpath("//label[@class=\"radio-inline\"][1]/input");
	private By subscribeNo = By.xpath("//label[@class=\"radio-inline\"][2]/input");
	private By agreeCheckbox = By.xpath("//input[@type=\"checkbox\"][@name=\"agree\"]");
	private By continueButton = By.xpath("//input[@value=\"Continue\"]");
	private By confirmPassword = By.xpath("//input[@placeholder=\"Password Confirm\"]");
	private By regSuccessMssg = By.xpath("//div[@id=\"content\"]/h1");
	private By logout = By.linkText("Logout");
	private By register = By.linkText("Register");
	
	public String registerUser(String fName,String lName, String email, String telephone, String password, String subscribe) {
		eleUtils.waitForEleVisible(firstName, 5);
		eleUtils.doSendKeys(firstName, fName);
		eleUtils.doSendKeys(lastName, lName);
		eleUtils.doSendKeys(this.email, email);
		eleUtils.doSendKeys(this.telephone, telephone);
		eleUtils.doSendKeys(this.password, password);
		eleUtils.doSendKeys(confirmPassword, password);
		doSubscribe(subscribe);
		eleUtils.doClick(agreeCheckbox);
		eleUtils.doClick(continueButton);
		String successMssg = eleUtils.waitForEleVisible(regSuccessMssg, 5).getText();
		eleUtils.doClick(logout);
		eleUtils.doClick(register);
		return successMssg;
	}

	private void doSubscribe(String subscribe) {
		if(subscribe.equalsIgnoreCase("yes")) {
			eleUtils.doClick(subscribeYes);
		}
		else {
			eleUtils.doClick(subscribeNo);
		}
	}

}
