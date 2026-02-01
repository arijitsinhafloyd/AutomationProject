package com.selenium.project.arijit.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.selenium.project.arijit.AbstractComponents.AbstractComponents;

public class lastPageOfOrder extends AbstractComponents {
	WebDriver driver;
			
		public lastPageOfOrder(WebDriver driver) {
			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}
		
		@FindBy(css="td h1")
		WebElement orderTextElement;
		
		public String orderVerification() {
			waitElementToBeAppear(orderTextElement);
			String order=orderTextElement.getText().trim();
	    	return order;
		}
			
}
