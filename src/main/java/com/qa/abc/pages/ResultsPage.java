package com.qa.abc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.abc.Utils.ElementUtils;

public class ResultsPage {
	WebDriver driver;
	ElementUtils eleUtil;
	
	public ResultsPage(WebDriver driver) {
		this.driver=driver;
		eleUtil = new ElementUtils(this.driver);
	}
	
	private By resultsProduct = By.cssSelector("div.product-layout.product-grid");
	
	public String getResultsPageTitle(String searchKey) {
		return eleUtil.waitForTitleAndCapture(searchKey, 5);
	}
	
	public int getProductResultsCount() {
		int resultCount = eleUtil.waitForElementsVisible(resultsProduct, 5).size();
		return resultCount;
	}
	
	public ProductInfo selectProduct(String productName) {
		By productNameLocator = By.linkText(productName);
		eleUtil.doClick(productNameLocator);
		return new ProductInfo(driver);
	}

}
