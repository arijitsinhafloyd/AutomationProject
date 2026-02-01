package com.selenium.project.arijit.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.selenium.project.arijit.AbstractComponents.AbstractComponents;

public class cartPage extends AbstractComponents{
	
	WebDriver driver;
	
	public cartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> productsCartPage;
	
	@FindBy(xpath="//button[text()='Checkout']")
	WebElement checkOutButton; 
	
	public String productDisplayName(String ProdName) {
		String pro1="";
		for(int i=0;i<productsCartPage.size();i++) {
    		pro1=productsCartPage.get(i).getText().trim();
    	}
		return pro1;
	}
	
	public preOrderInfoPage checkOut() {
		waitElementToBeAppear(checkOutButton);
		clicking(checkOutButton);
		return new preOrderInfoPage(driver);
	}
	

}
