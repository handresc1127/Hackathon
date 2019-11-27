package com.handresc1127.applitools.test1LoginPage;

import static com.google.common.base.Strings.isNullOrEmpty;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Eyes;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

@RunWith(DataProviderRunner.class)
public class VisualAITests {

	private EyesRunner runner;
	private Eyes eyes;
	private static BatchInfo batch;
	private WebDriver driver;
	String url= "https://demo.applitools.com/hackathonV2.html";

	@BeforeClass
	public static void setBatch() {
		batch = new BatchInfo("Hackathon");
	}

	@Before
	public void beforeEach() {
		runner = new ClassicRunner();
		eyes = new Eyes(runner);

		if (isNullOrEmpty(System.getenv("APPLITOOLS_API_KEY"))) {
			throw new RuntimeException("No API Key found; Please set environment variable 'APPLITOOLS_API_KEY'.");
		}
		eyes.setApiKey(System.getenv("APPLITOOLS_API_KEY"));
		eyes.setBatch(batch);

		// Use Chrome browser
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		driver = new ChromeDriver(options);

	}

	@Test
	public void uiLoginTest() {
		eyes.open(driver, "1. Login Page UI Elements Test", "uiLoginTest");
		driver.get(url);
		eyes.checkWindow("Login Window");
		eyes.closeAsync();
	}
	
	@DataProvider
	public static Object[][] dataProvider() {
		return new Object[][] { 
			{ "", "", "Both Username and Password must be present" }, 
			{ "Us3rn4m3", "", "Password must be present" }, 
			{ "", "Password", "Username must be present" } };
	}
	
	@Test
    @UseDataProvider("dataProvider")
    public void functionalLoginTest(String strUser, String strPass, String testName) {
    	eyes.open(driver, "1. Login Page UI Elements Test", testName);
    	driver.get(url);
    	
    	driver.findElement(By.id("username")).sendKeys(strUser);
    	driver.findElement(By.id("password")).sendKeys(strPass);
		driver.findElement(By.id("log-in")).click();
		eyes.checkWindow("Login function "+testName);

		eyes.closeAsync();
    }

	
	
	
	@After
	public void afterEach() {
		driver.quit();
		eyes.abortIfNotClosed();
	}
}
