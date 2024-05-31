package com.qa.abc.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.abc.Base.BaseTest;

public class AccountsPageTest extends BaseTest {
	@BeforeClass
	public void accPageSetup() {
		accountsPage = loginPage.doLogin("abc123@abc.com", "test123");
	}
	@Test
	public void accPageTitleTest() {
		String actTitle = accountsPage.getAccountPageTitle();
		Assert.assertEquals(actTitle, "My Account");
	}

}
