package com.qa.abc.tests;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.abc.Base.BaseTest;

public class ProductInfoTest extends BaseTest{
	
	@BeforeClass
	public void accPageSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void productInfoTest() {		
		resultPage = accountsPage.doSearch("Macbook");
		productInfo = resultPage.selectProduct("MacBook Pro");
		
		Map<String, String> prodInfoMap = productInfo.getProductInfo();
		softAssert.assertEquals(prodInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(prodInfoMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(prodInfoMap.get("Reward Points"), "800");
		softAssert.assertEquals(prodInfoMap.get("Availability"), "In Stock");
		softAssert.assertEquals(prodInfoMap.get("ProductPrice"), "$2,000.00");
		softAssert.assertEquals(prodInfoMap.get("extraPrice"), "$2,000.00");
		softAssert.assertAll();
	}

}
