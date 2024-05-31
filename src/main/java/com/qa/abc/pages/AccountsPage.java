package com.qa.abc.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.abc.Utils.ElementUtils;

public class AccountsPage {
	private WebDriver driver;
	private ElementUtils eleUtil;

	public AccountsPage(WebDriver driver) {
		this.driver=driver;
		eleUtil = new ElementUtils(this.driver);
	}
	
	private By logout = By.linkText("Logout");
	private By searchField = By.xpath("//input[@placeholder=\"Search\"]");
	private By searchIcon = By.cssSelector("div#search button");
	private By accHeaders = By.cssSelector("div#content h2");
	
	public String getAccountPageTitle() {
		return eleUtil.waitForTitleAndCapture("My Account", 5);
	}
	
	public boolean isLogoutLinkExist() {
		return eleUtil.checkElementDisplayed(logout);
	}
	
	public List<String> getAccountPageHeaderList() {
		List<WebElement> headerList = driver.findElements(accHeaders);
		List<String> headersValue = new ArrayList<String>();
		for(WebElement w : headerList) {
			String s = w.getText();
			headersValue.add(s);
		}
		return headersValue;
	}
	
	public ResultsPage doSearch(String searchTerm) {
		eleUtil.waitForEleVisible(searchField, 5);
		eleUtil.doSendKeys(searchField, searchTerm);
		eleUtil.doClick(searchIcon);
		return new ResultsPage(driver);
	}
}
