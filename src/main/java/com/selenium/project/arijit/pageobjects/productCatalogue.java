package com.selenium.project.arijit.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.selenium.project.arijit.AbstractComponents.AbstractComponents;

public class productCatalogue extends AbstractComponents {
WebDriver driver;
	
	public productCatalogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		//PageFactory
		PageFactory.initElements(driver, this);
	}
	
	By products=By.cssSelector(".mb-3");
	By prodfilterElement=By.cssSelector("h5 b");
	By addToCartButton=By.xpath(".//button[contains(text(),'Cart')]");
	By toastM=By.cssSelector("#toast-container div div");
	By animate=By.cssSelector(".ng-animating");
	
	public List<WebElement> getProducts() {
		waitElementToBeAppear(products);
		return driver.findElements(products);
	}
	
	public void addToCart(String ProdName) {
		List<WebElement> products=getProducts();
		for(int i=0;i<products.size();i++) {
        	String product=products.get(i).findElement(prodfilterElement).getText().trim();
        	if(product.equals(ProdName)) {
        		WebElement addToCart = products.get(i).findElement(addToCartButton);
        		clicking(addToCart);
        		break;
        	}
        }
	}
	
	
	public String addToCartToastMessage() {
		waitElementToBeAppear(toastM);
		String toast1=driver.findElement(toastM).getText().trim();
		waitElementToBeDisappear(animate);
		waitElementToBeDisappear(toastM);
		return toast1;
	}
	
}
