package com.selenium.project.arijit.testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.selenium.project.arijit.pageobjects.landingPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	public landingPage lp;
	
	public WebDriver invokeBrowser() throws IOException {
		
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")
				+"\\src\\main\\java\\com\\selenium\\project\\arijit\\resources\\GlobalData.properties");
		prop.load(fis);
		String browser=System.getProperty("browser")!=null ? System.getProperty("browser"):prop.getProperty("browser");
		
		if(browser.contains("chrome")) {
			ChromeOptions options=new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browser.contains("headless")) 
			{
				//options.addArguments("headless");
				options.addArguments("--headless=new");
				options.addArguments("--window-size=1920,1080");
				options.addArguments("--disable-gpu");
				//options.addArguments("--no-sandbox");
			    //options.addArguments("--disable-dev-shm-usage");
			}
			driver = new ChromeDriver(options);
			//driver.manage().window().setSize(new Dimension(1400,900));//full screen - to remove code flakiness
		}
		
		else if(browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		
		else if(browser.equalsIgnoreCase("Edge")) {
			driver = new FirefoxDriver();
		}
		if(!browser.contains("headless"))
			driver.manage().window().maximize();
		return driver;
	}
	
	@BeforeMethod(alwaysRun=true)
	public landingPage launchApplication() throws IOException {
		driver=invokeBrowser();
		lp=new landingPage(driver);
		lp.goTo();
		return lp;
	}
	
	@AfterMethod(alwaysRun=true)
	public void af(){
		if(driver!=null)
		driver.quit();
	}
	
	public List<HashMap<String,String>> getDataFromJSON(String filepath) throws IOException{
		String jSonContent=FileUtils.readFileToString(new File(filepath),StandardCharsets.UTF_8);
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jSonContent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File myPath=new File(System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png");
		FileUtils.copyFile(src, myPath);
		return System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
	}
}
