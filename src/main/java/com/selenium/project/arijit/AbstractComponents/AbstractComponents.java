package com.selenium.project.arijit.AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.selenium.project.arijit.pageobjects.cartPage;
import com.selenium.project.arijit.pageobjects.orderPage;

public class AbstractComponents {
	
	WebDriver driver;
	public AbstractComponents(WebDriver driver){
		this.driver=driver;	
		PageFactory.initElements(driver, this);
	}
	
	public void waitElementToBeAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(findBy));
	}
	
	@FindBy(css="button[routerlink='/dashboard/cart']")
	WebElement cart;
	
	@FindBy(css="button[routerlink='/dashboard/myorders']")
	WebElement orderButtonHeader;
	
	public cartPage goToCart() {
		waitElementToBeAppear(cart);
		clicking(cart);
		cartPage cp= new cartPage(driver);
		return cp;
	}
	
	public void waitElementToBeDisappear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}
	
	public void waitElementToBeAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void waitElementToBeAppear(List<WebElement> findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
		wait.until(ExpectedConditions.visibilityOfAllElements(findBy));
	}
	
	public void clicking(WebElement wb) {
		JavascriptExecutor js=((JavascriptExecutor) driver);
		js.executeScript("arguments[0].click();",wb);
	}
	
	public orderPage goToOrderPage() {
		waitElementToBeAppear(orderButtonHeader);
		clicking(orderButtonHeader);
		orderPage op=new orderPage(driver);
		return op;
	}
	
	public void sendKeys(WebElement wb, String value) {
		JavascriptExecutor js=((JavascriptExecutor) driver);
		 js.executeScript("arguments[0].focus();" +
			        "arguments[0].value = arguments[1];" +

			        // input event (required)
			        "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +

			        // keyboard events (THIS triggers dropdown logic)
			        "arguments[0].dispatchEvent(new KeyboardEvent('keydown', { bubbles: true, key: 'i' }));" +
			        "arguments[0].dispatchEvent(new KeyboardEvent('keyup', { bubbles: true, key: 'i' }));" +

			        // open dropdown explicitly (many libs listen to ArrowDown)
			        "arguments[0].dispatchEvent(new KeyboardEvent('keydown', { bubbles: true, key: 'ArrowDown' }));",
		        wb, value);

		 
	}
	
}
