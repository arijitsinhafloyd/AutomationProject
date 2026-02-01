package com.selenium.project.arijit.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.selenium.project.arijit.AbstractComponents.AbstractComponents;

public class landingPage extends AbstractComponents {
	WebDriver driver;
	
	public landingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		//PageFactory
		PageFactory.initElements(driver, this);
	}
	@FindBy(id="userEmail")
	WebElement email;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement login;
	
	@FindBy(css="#toast-container div div")
	By toast1=By.cssSelector("#toast-container div div");
	
	public productCatalogue login(String userEmail, String userPassword) {
		email.sendKeys(userEmail);
		password.sendKeys(userPassword);
		waitElementToBeAppear(login);
		clicking(login);
		productCatalogue pc=new productCatalogue(driver);
		return pc;
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getToastMessage() {
		waitElementToBeAppear(toast1);
		return driver.findElement(toast1).getText().trim();
	}
	
	public void disAppeartoast() {
		waitElementToBeDisappear(toast1);
	}
	
}
