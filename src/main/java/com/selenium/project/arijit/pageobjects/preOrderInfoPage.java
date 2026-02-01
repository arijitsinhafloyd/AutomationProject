package com.selenium.project.arijit.pageobjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.selenium.project.arijit.AbstractComponents.AbstractComponents;

public class preOrderInfoPage extends AbstractComponents {
WebDriver driver;
	
	public preOrderInfoPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement countrySelect;
	
	@FindBy(css="div[class='form-group'] section button")
	List<WebElement> countryDropdownOptions;
	
	@FindBy(xpath="//a[contains(text(),'Order')]")
	WebElement orderButton;
	
	public void selectCountry() {
		waitElementToBeAppear(countrySelect);
		clicking(countrySelect);
		sendKeys(countrySelect,"ind");
		//countrySelect.sendKeys("ind");
		//Actions actions = new Actions(driver);
		
		waitElementToBeAppear(countryDropdownOptions);
		for(int i=0;i<countryDropdownOptions.size();i++) {
    		String country=countryDropdownOptions.get(i).getText().trim();
    		if(country.equalsIgnoreCase("India")) {
    			clicking(countryDropdownOptions.get(i));
    			break;
    		}
    	}
	}
	
	public void placeOrder() {
		clicking(orderButton);
	}
	
	
	
	
}
