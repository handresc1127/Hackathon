package com.handresc1127.applitools;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import static com.google.common.base.Strings.isNullOrEmpty;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Eyes;

public class VisualAITests {

	private EyesRunner runner;
	private Eyes eyes;
	private static BatchInfo batch;
	private WebDriver driver;
	String url= "https://demo.applitools.com/hackathon.html";

	@BeforeClass
	public static void setBatch() {
		batch = new BatchInfo("Hackathon");
	}

	@BeforeMethod
	public void beforeEach() {
		runner = new ClassicRunner();
		eyes = new Eyes(runner);

		if (isNullOrEmpty(System.getenv("APPLITOOLS_API_KEY"))) {
			throw new RuntimeException("No API Key found; Please set environment variable 'APPLITOOLS_API_KEY'.");
		}
		eyes.setApiKey(System.getenv("APPLITOOLS_API_KEY"));
		eyes.setBatch(batch);
		eyes.setForceFullPageScreenshot(true);

		// Use Chrome browser
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		driver = new ChromeDriver(options);

	}

	@Test(priority = 1, testName = "Login Page UI Elements Test")
	public void uiLoginTest() {
		eyes.open(driver, "1. Login Page UI Elements Test", "uiLoginTest");
		driver.get(url);
		eyes.checkWindow("Login Window");
		eyes.closeAsync();
	}
	
	@DataProvider(name = "DataDrivenLogin")
	public Object[][] getDataFromDataprovider() {
		return new Object[][] { 
			{ "", "", "Both Username and Password must be present" }, 
			{ "Us3rn4m3", "", "Password must be present" }, 
			{ "", "Password", "Username must be present" },
			{"Us3rn4m3","Password", "Username and Password present"}};
	}
	
	@Test(priority = 2, testName = "Data-Driven Test", dataProvider="DataDrivenLogin")
    public void dataDrivenTest(String strUser, String strPass, String testName) {
    	eyes.open(driver, "2. Data Driven Test", testName);
    	driver.get(url);
    	
    	driver.findElement(By.id("username")).sendKeys(strUser);
    	driver.findElement(By.id("password")).sendKeys(strPass);
		driver.findElement(By.id("log-in")).click();
		eyes.checkWindow("Login function "+testName);

		eyes.closeAsync();
    }
	
	@Test(priority = 3, testName = "Table Sort Test")
	public void shortTableTest() {
		eyes.open(driver, "3. Table Sort Test", "shortTableTest");
		driver.get(url);
		driver.findElement(By.id("username")).sendKeys("Us3rn4m3");
    	driver.findElement(By.id("password")).sendKeys("Password");
		driver.findElement(By.id("log-in")).click();
		eyes.checkWindow("Short Table Before");
		driver.findElement(By.id("amount")).click();
		eyes.checkWindow("Short Table After");
		
		eyes.closeAsync();
	}
	
	@Test(priority = 4, testName = "Canvas Chart Test")
	public void canvasChartTest() {
		eyes.open(driver, "4. Canvas Chart Test", "canvasChartTest");
		driver.get(url);
		driver.findElement(By.id("username")).sendKeys("Us3rn4m3");
    	driver.findElement(By.id("password")).sendKeys("Password");
		driver.findElement(By.id("log-in")).click();
		driver.findElement(By.id("showExpensesChart")).click();
		eyes.checkWindow("Canvas Chart");
		
		driver.findElement(By.id("addDataset")).click();
		eyes.checkWindow("Canvas Chart add data set");
		
		eyes.closeAsync();
	}
	
	@Test(priority = 5, testName = "Dynamic Content Test")
	public void dynamicContentTest() {
		eyes.open(driver, "5. Dynamic Content Test", "dynamicContentTest");
		driver.get(url+"?showAd=true");
		driver.findElement(By.id("username")).sendKeys("Us3rn4m3");
    	driver.findElement(By.id("password")).sendKeys("Password");
		driver.findElement(By.id("log-in")).click();
		eyes.checkWindow("Dynamic Content");
		eyes.closeAsync();
	}

	
	@AfterMethod
	public void afterEach() {
		driver.quit();
		eyes.abortIfNotClosed();
	}
}
