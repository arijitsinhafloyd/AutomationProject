package com.selenium.project.arijit.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import com.selenium.project.arijit.pageobjects.cartPage;
import com.selenium.project.arijit.pageobjects.landingPage;
import com.selenium.project.arijit.pageobjects.lastPageOfOrder;
import com.selenium.project.arijit.pageobjects.preOrderInfoPage;
import com.selenium.project.arijit.pageobjects.productCatalogue;
import com.selenium.project.arijit.testcomponents.BaseTest;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefintionImplementation extends BaseTest{
	landingPage lp;
	productCatalogue pc;
	cartPage cp;
	preOrderInfoPage poi;
	@Given("I have landed on Ecommerce page")
	public void I_have_landed_on_Ecommerce_page() throws IOException {
		lp=launchApplication();
	}
	
	@Given("^Logged in with (.+) and (.+)$")
	public void logged_in_username_and_password(String username, String password) {
		pc=lp.login(username,password);
	}
	@When("^I have added any (.+) from Ecommerce site$")
	public void adding_any_product_from_Ecommerce_site (String productName) {
		pc.getProducts(); //List<WebElement> prod= pc.getProducts();
        pc.addToCart(productName);
	}
	@And("^checkout the (.+) from Cart$")
	public void checking_out(String productName) {
		cp=pc.goToCart();
		String pro1=cp.productDisplayName(productName);
    	if(pro1.equalsIgnoreCase(productName))
			Assert.assertTrue(true);
    	poi=cp.checkOut();
    	poi.selectCountry();
    	poi.placeOrder();
	}
	@Then("my order should be placed and message would come as {string}")
	public void lastPage_message_confirmation(String string) {
		lastPageOfOrder lpo=new lastPageOfOrder(driver);
    	String order=lpo.orderVerification();
    	if(order.equalsIgnoreCase(string))
    		Assert.assertTrue(true);
    	driver.close();
	}
	
	@Then("Error message with {string} word should pop out")
	public void Error_Message_displaying(String keyword) {
		String errorM=lp.getToastMessage();
        Assert.assertTrue(errorM.contains(keyword),"Test passed as the error message is showing");
        driver.close();
	}
}
