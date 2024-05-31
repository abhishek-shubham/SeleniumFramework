package com.qa.abc.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.abc.Base.BaseTest;
import com.qa.abc.Pojo.Product;
import com.qa.opencart.dataprovider.ProductDataProvider;

public class SearchNew extends BaseTest{
	@BeforeClass
	public void accPageSetup() {
		accountsPage = loginPage.doLogin("abc123@abc.com", "test123");
	}
	
	
}
