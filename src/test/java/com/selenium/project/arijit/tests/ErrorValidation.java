package com.selenium.project.arijit.tests;
import com.selenium.project.arijit.testcomponents.Retry;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.selenium.project.arijit.pageobjects.productCatalogue;
import com.selenium.project.arijit.testcomponents.BaseTest;

import junit.framework.Assert;

public class ErrorValidation extends BaseTest{
	@Test(retryAnalyzer=Retry.class)
	 public void loginErrorValidation() throws IOException {
        //Error Validation
        lp.login("contact@arijitsinha.com", "Asbnbf@1234");
        String errorM=lp.getToastMessage();
        Assert.assertTrue("Test passed as the error message is showing",errorM.contains("Incorrect"));
	}
	
	@Test(groups={"ErrorValidations"})
	public void ProductVerification() {
		String ProdName="ADDATIMES ORIGINAL";
		boolean found=false;
		productCatalogue pc=lp.login("contact@arijitsinha.com", "Asdf@1234");
    	List<WebElement> li=pc.getProducts();
    	for(int i=0;i<li.size();i++) {
    		String text=li.get(i).getText().trim();
    		if(text.equals(ProdName)) {
    			found=true;
    			break;
    		}
    		
    	}
    	Assert.assertFalse("Product not found",found);
    	
	}
	
}
