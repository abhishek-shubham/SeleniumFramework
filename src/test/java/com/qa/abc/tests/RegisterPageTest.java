package com.qa.abc.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.abc.Base.BaseTest;
import com.qa.abc.Utils.AppConstants;
import com.qa.abc.Utils.ReadExcel;

public class RegisterPageTest extends BaseTest{
	
	@BeforeClass
	public void navigateToRegisterPage() {
		registerPage = loginPage.navigateToRegisterPage();
	}
	
	/*@DataProvider(name="regData")
	public Object[][] registerData() {
		return new Object[][] {
			{"abhi","shubh","1234512345","password","yes"},
			{"abc","def","0987609876","password2nd","no"},
			{"uvw","xyz","5432154321","password3rd","yes"}
		};
	}*/
	
	@DataProvider(name="regDataFromExcel")
	public Object[][] registerData(){
		//Object[][] regData = ReadExcel.getTestData(AppConstants.REGISTER_SHEET_NAME);
		Object[][] regData = ReadExcel.getTestData(AppConstants.REGISTER_SHEET_NAME);
		return regData;
	}
	
	public String getRandomEmail() {
		String email = "test"+System.currentTimeMillis()+"@gmall.com";
		return email;
	}

	//@Test(dataProvider = "regData")
	@Test(dataProvider = "regDataFromExcel")
	public void userRegisterTest(String fname, String lName, String telephone, String password, String subscription ) {
		String actRegSuccMssg = registerPage.registerUser(fname	, lName, getRandomEmail() , telephone, password,subscription);
		Assert.assertEquals(actRegSuccMssg, AppConstants.USER_REGISTER_SUCCESS_MESSAGE);
	}
}
