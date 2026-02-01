package com.selenium.project.arijit.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.selenium.project.arijit.AbstractComponents.AbstractComponents;

public class orderPage extends AbstractComponents {
	
	WebDriver driver;
	public orderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}
	
	@FindBy(xpath="//tbody/tr/td[2]")
	private List<WebElement> productsinOrder;
	
	public List<WebElement> waitforProducts(){
		waitElementToBeAppear(productsinOrder);
		return productsinOrder;
	}
	
	public boolean getProductinOrderPage(String pn) {
		waitforProducts();
		boolean found=false;
		for(int i=0;i<productsinOrder.size();i++) {
			String product=productsinOrder.get(i).getText().trim();
			if(product.equalsIgnoreCase(pn)) {
				found=true;
				break;
			}
		}
		return found;
	}
}
