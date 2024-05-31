package com.qa.opencart.dataprovider;

import org.testng.annotations.DataProvider;

import com.qa.abc.Pojo.Product;

public class ProductDataProvider {

	@DataProvider(name="productDataViaPojo")
	public Object[][] getProductTestDataUsingPojo() {
		return new Object[][] {
			{new Product("MacBook","Macbook Pro",4)},
			{new Product("iMac", "iMac",3)},
			{new Product("Samasung","Samsung Galaxy Tab 10.1",7)},
			{new Product("Samsung", "Samsung SyncMaster 941BW", 1)}
		};
	}
	
	@DataProvider(name = "productDataWithSearchKey")
	public Object[][] getProductSearchKey() {
		return new Object[][] {
			{"MacBook"},
			{"iMac"},
			{"Samasung"}
		};
	}
	
	@DataProvider(name = "productDataWithName")
	public Object[][] getProductTestData() {
		return new Object[][] {
			{"MacBook","Macbook Pro"},
			{"iMac", "iMac"},
			{"Samasung","Samsung Galaxy Tab 10.1"}
		};
	}
	
	@DataProvider(name = "productDataWithImage")
	public Object[][] getProductImageCount() {
		return new Object[][] {
			{"MacBook","Macbook Pro",4},
			{"iMac", "iMac",3},
			{"Samasung","Samsung Galaxy Tab 10.1",7},
			{"Samsung", "Samsung SyncMaster 941BW", 1}
		};
	}
	
	
}
