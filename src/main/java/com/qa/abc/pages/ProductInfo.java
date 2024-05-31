package com.qa.abc.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.abc.Utils.ElementUtils;

public class ProductInfo {

	private WebDriver driver;
	private ElementUtils eleUtils;
	
	public ProductInfo(WebDriver driver) {
		this.driver=driver;
		eleUtils=new ElementUtils(this.driver);
	}
	
	private By prodHeader = By.cssSelector("div#content h1");
	private By prodImages = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.xpath("//div[@id='content']//ul[@class='list-unstyled'][1]/li");
	private By productPriceData = By.xpath("//div[@id='content']//ul[@class='list-unstyled'][2]/li");
	
	private Map<String, String> productInfoMap;
	
	
	public String getProductHeaderName() {
		return eleUtils.doGetElementText(prodHeader);
	}
	
	public int getProductImagesCount() {
		return eleUtils.waitForElementsVisible(prodImages, 5).size();
	}
	
	public Map<String, String> getProductInfo() {
		productInfoMap = new HashMap<String, String>();
		getProductMetaData();
		getProductPriceData();
		productInfoMap.put("Product Name", getProductHeaderName());
		return productInfoMap;
	}
	
	private void getProductMetaData() {		
		List<WebElement> metaList = eleUtils.getElements(productMetaData);
		for(WebElement w : metaList) {
			String s = w.getText();
			String metaInfo[] = s.split(":");
			String key = metaInfo[0].trim();
			String value = metaInfo[1].trim();
			productInfoMap.put(key, value);
		}		
		System.out.println("prod info ----------------- "+productInfoMap);
	}
	
	private void getProductPriceData() {
		List<WebElement> priceList = eleUtils.getElements(productPriceData);
		String priceValue = priceList.get(0).getText();
		String exTaxPrice = priceList.get(1).getText();
		String extraTaxPrice = exTaxPrice.split(":")[1].trim();
		productInfoMap.put("ProductPrice", priceValue);
		productInfoMap.put("extraPrice", extraTaxPrice);
		
	}

}
