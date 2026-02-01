package com.selenium.project.arijit.tests;


import com.selenium.project.arijit.testcomponents.Retry;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.selenium.project.arijit.pageobjects.cartPage;
import com.selenium.project.arijit.pageobjects.lastPageOfOrder;
import com.selenium.project.arijit.pageobjects.orderPage;
import com.selenium.project.arijit.pageobjects.preOrderInfoPage;
import com.selenium.project.arijit.pageobjects.productCatalogue;
import com.selenium.project.arijit.testcomponents.BaseTest;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class StandAloneTest extends BaseTest {
	
	@Test(dataProvider="getData", groups= {"Purchase"},retryAnalyzer=Retry.class)
	 public void getOrder(HashMap<String,String> hm) throws IOException {
	    	 
	        
	        //Login Flow
	        productCatalogue pc=lp.login(hm.get("email"), hm.get("password"));
	    	String toast=lp.getToastMessage();
	    	
	    	//Login Verification
	    	Assert.assertEquals(toast, "Login Successfully");
	    	lp.disAppeartoast();
		//Just to see how it works ci/cd

	    	//Adding products to Cart
	    	pc.getProducts(); //List<WebElement> prod= pc.getProducts();
	        pc.addToCart(hm.get("product"));
	        String toast1=pc.addToCartToastMessage();
	    	Assert.assertEquals(toast1, "Product Added To Cart");
	    	
	    	//Going to cart page
	    	cartPage cp=pc.goToCart();
	    	
	    	//Verification of products in Cart and Checkout
	    	String pro1=cp.productDisplayName(hm.get("product"));
	    	if(pro1.equalsIgnoreCase(hm.get("product")))
    			Assert.assertTrue(true);
	    	preOrderInfoPage poi=cp.checkOut();
	    	
	    	//Pre-Order Info selection
	    	poi.selectCountry();
	    	poi.placeOrder();
	    	
	    	//Verification of Placing Order
	    	lastPageOfOrder lpo=new lastPageOfOrder(driver);
	    	String order=lpo.orderVerification();
	    	if(order.equalsIgnoreCase("Thankyou for the order."))
	    		Assert.assertTrue(true);
	    	
	 }
	
	@Test(dataProvider="getData")//(dependsOnMethods={"getOrder"})
	public void PlacedOrderinCart(HashMap<String,String> input) {
		productCatalogue pc=lp.login(input.get("email"), input.get("password"));
		orderPage op = pc.goToOrderPage();
		boolean found=op.getProductinOrderPage(input.get("product"));
		Assert.assertTrue(found,"The product found");
	}
	
	/*@DataProvider
	public Object[][] getData() {
		return new Object[][] {{"contact@arijitsinha.com", "Asdf@1234","IPHONE 13 PRO"},{"sinha@gmail.com", "Asdf@1234","ZARA COAT 3"}};
	}*/
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String,String>> data=getDataFromJSON(System.getProperty("user.dir")+"\\src\\test\\java\\com\\selenium\\project\\arijit\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
		/*HashMap<String,String> map=new HashMap<String,String>();
		map.put("email", "contact@arijitsinha.com");
		map.put("password","Asdf@1234");
		map.put("product", "IPHONE 13 PRO");
		
		HashMap<String,String> map1=new HashMap<String,String>();
		map1.put("email", "sinha@gmail.com");
		map1.put("password","Asdf@1234");
		map1.put("product", "ZARA COAT 3");
		
		return new Object[][] {{map},{map1}};*/
		}
	
}
