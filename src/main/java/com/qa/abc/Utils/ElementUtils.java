package com.qa.abc.Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.abc.factory.DriverFactory;

public class ElementUtils {
	
	private WebDriver driver;
	private JavaScriptUtil js;
	
	public ElementUtils(WebDriver driver) {
		this.driver=driver;
		js = new JavaScriptUtil(this.driver);
	}
	
	public String waitForTitleAndCapture(String titleFraction, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if(wait.until(ExpectedConditions.titleContains(titleFraction))) {
			String title = driver.getTitle();
			return title;
		}
		else {
			System.out.println("title is not present within the "+timeOut);
			return null;
		}
	}
	public void doSendKeys(By ele, String data) {
		WebElement element = getElement(ele);
		element.clear();
		element.sendKeys(data);
	}
	public void doClick(By ele) {
		driver.findElement(ele).click();
	}
	
	public String doGetElementText(By ele) {
		return getElement(ele).getText();
	}
	
	public WebElement getElement(By ele) {
		WebElement element = null;
		element = driver.findElement(ele);
		
		if(Boolean.parseBoolean(DriverFactory.highlightElement)) {
			js.flash(element);
		}
		return element;
	}
	
	public List<WebElement> getElements(By ele) {
		return driver.findElements(ele);
	}
	public WebElement waitForEleVisible(By ele, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
		if(Boolean.parseBoolean(DriverFactory.highlightElement)) {
			js.flash(element);
		}
		return element;
	}
	
	public List<WebElement> waitForElementsVisible(By ele, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ele));
	}

	public boolean checkElementDisplayed(By ele) {
		//return driver.findElement(ele).isDisplayed();
		WebElement element = driver.findElement(ele);
		if(Boolean.parseBoolean(DriverFactory.highlightElement)) {
			js.flash(element);
		}
		return element.isDisplayed();
	}
	public String waitForUrlContainsAndCapture(String url, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		if(wait.until(ExpectedConditions.urlContains(url))) {
			return driver.getCurrentUrl();			
		}
		else {
			System.out.println("url not displayed within "+timeout+" sec");
			return null;
		}
	}
}
