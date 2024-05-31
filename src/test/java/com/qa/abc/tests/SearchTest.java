package com.qa.abc.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.abc.Base.BaseTest;
import com.qa.abc.Pojo.Product;
import com.qa.opencart.dataprovider.ProductDataProvider;

public class SearchTest extends BaseTest{
	
	@BeforeClass
	public void accPageSetup() {
		accountsPage = loginPage.doLogin("abc123@abc.com", "test123");
	}
	
	@Test(dataProvider = "productDataViaPojo", dataProviderClass = ProductDataProvider.class)
	public void searchProductResultCountTest(Product product) {
		resultPage = accountsPage.doSearch(product.getSearchKey());
		System.out.println(resultPage.getProductResultsCount());
		Assert.assertTrue(resultPage.getProductResultsCount()>0);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/*@DataProvider
	public Object[][] getProductSearchKey() {
		return new Object[][] {
			{"MacBook"},
			{"iMac"},
			{"Samasung"}
		};
	}*/
	
	@Test(dataProvider = "productDataWithSearchKey", dataProviderClass = ProductDataProvider.class)
	public void searchPageTitleTest(String searchKey) {
		resultPage = accountsPage.doSearch(searchKey);
		String actTitle= resultPage.getResultsPageTitle(searchKey);
		Assert.assertEquals(actTitle, "Search - Macbook");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test(dataProvider = "productDataViaPojo", dataProviderClass = ProductDataProvider.class)
	public void selectProductTest(Product product) throws InterruptedException {
		resultPage = accountsPage.doSearch(product.getSearchKey());
		productInfo = resultPage.selectProduct(product.getProductName());
		String prodHeaderName = productInfo.getProductHeaderName();
		Assert.assertEquals(prodHeaderName, product.getProductName());
		Thread.sleep(2000);
	}
	
	/*@DataProvider
	public Object[][] getProductTestData() {
		return new Object[][] {
			{"MacBook","Macbook Pro"},
			{"iMac", "iMac"},
			{"Samasung","Samsung Galaxy Tab 10.1"}
		};
	}*/
	
	@Test(dataProvider = "productDataViaPojo", dataProviderClass = ProductDataProvider.class)
	public void imageCountTest(Product product) {
		resultPage = accountsPage.doSearch(product.getSearchKey());
		productInfo = resultPage.selectProduct(product.getProductName());
		int imageCount = productInfo.getProductImagesCount();
		Assert.assertEquals(imageCount, product.getProductImages());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/*@DataProvider
	public Object[][] getProductImageCount() {
		return new Object[][] {
			{"MacBook","Macbook Pro",4},
			{"iMac", "iMac",3},
			{"Samasung","Samsung Galaxy Tab 10.1",7},
			{"Samsung", "Samsung SyncMaster 941BW", 1}
		};
	}*/
	
	/*@DataProvider
	public Object[][] getProductTestDataUsingPojo() {
		return new Object[][] {
			{new Product("MacBook","Macbook Pro",4)},
			{new Product("iMac", "iMac",3)},
			{new Product("Samasung","Samsung Galaxy Tab 10.1",7)},
			{new Product("Samsung", "Samsung SyncMaster 941BW", 1)}
		};
	}*/
}
