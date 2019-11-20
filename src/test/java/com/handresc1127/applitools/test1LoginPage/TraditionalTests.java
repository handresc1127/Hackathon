package com.handresc1127.applitools.test1LoginPage;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TraditionalTests {
	
	private WebDriver driver;
	String url= "https://demo.applitools.com/hackathonV2.html";

	@BeforeTest
	public void beforeEach() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		driver = new ChromeDriver(options);
	}
	
	@Test
	public void uiLoginTest() {
		driver.get(url);
		SoftAssert sa = new SoftAssert();
		
		WebElement user, pass, iconUser, iconPass,labelUser,labelPass,login,alertEmpty,alertPrimary;
		java.util.List<WebElement> socials;
		String iconUserClass,iconPassClass;
		
		try {user=driver.findElement(By.id("username"));}catch(Exception e){user=null;}
		try {pass=driver.findElement(By.id("password"));}catch(Exception e){pass=null;}
		try {iconUser=driver.findElement(By.xpath("//*[@id='username']/../*[contains(@class,'pre-icon')]"));}catch(Exception e){iconUser=null;}
		try {iconPass=driver.findElement(By.xpath("//*[@id='password']/../*[contains(@class,'pre-icon')]"));}catch(Exception e){iconPass=null;}
		try {iconUserClass=iconUser.getAttribute("class");}catch(Exception e){iconUserClass=null;}
		try {iconPassClass=iconPass.getAttribute("class");}catch(Exception e){iconPassClass=null;}
		try {labelUser=driver.findElement(By.xpath("//*[@id='username']/../label"));}catch(Exception e){labelUser=null;}
		try {labelPass=driver.findElement(By.xpath("//*[@id='password']/../label"));}catch(Exception e){labelPass=null;}
		try {login=driver.findElement(By.id("log-in"));}catch(Exception e){login=null;}
		try {alertEmpty=driver.findElement(By.id("alertEmpty"));}catch(Exception e){alertEmpty=null;}
		try {alertPrimary=driver.findElement(By.className("alert-primary"));}catch(Exception e){alertPrimary=null;}
		try {socials=driver.findElements(By.xpath("//form/div[3]/div[2]/*"));}catch(Exception e){socials=null;}
		
		
		sa.assertTrue(user instanceof WebElement,"WebElement username is present");
		sa.assertEquals(user.getAttribute("placeholder"),"Enter your username");
		sa.assertTrue(labelUser instanceof WebElement,"WebElement label user is present");
		sa.assertEquals(labelUser.getText(),"Username");
		sa.assertTrue(iconUser instanceof WebElement,"WebElement icon User is present");
		sa.assertEquals(iconUserClass, "pre-icon os-icon os-icon-user-male-circle");
		
		sa.assertTrue(pass instanceof WebElement,"WebElement password is present");
		sa.assertEquals(pass.getAttribute("placeholder"),"Enter your password");
		sa.assertTrue(labelPass instanceof WebElement,"WebElement label pass is present");
		sa.assertEquals(labelPass.getText(),"Password");
		sa.assertTrue(iconPass instanceof WebElement,"WebElement icon Pass is present");
		sa.assertEquals(iconPassClass, "pre-icon os-icon os-icon-fingerprint");
		
		sa.assertTrue(login instanceof WebElement,"WebElement login is present");
		sa.assertTrue(alertEmpty instanceof WebElement,"WebElement alert empty is present");
		sa.assertTrue(alertPrimary instanceof WebElement,"WebElement alert primary is present");
		sa.assertTrue(socials instanceof ArrayList,"WebElement socials are present");
		sa.assertEquals(socials.size(),3);
		for (WebElement element: socials) {
			sa.assertEquals(element.getAttribute("href"), url+"#");
		}
		// the asserts used till now will not throw any exception if they fail. The @Test will not fail either.
        // If you need the test method to fail at the end, showing all exceptions, you need to use assertAll()
        sa.assertAll();
		
	}
	
	
	@DataProvider(name = "DataDrivenLogin")
	public Object[][] getDataFromDataprovider() {
		return new Object[][] { 
			{ "", "", "Both Username and Password must be present" }, 
			{ "Us3rn4m3", "", "Password must be present" }, 
			{ "", "Password", "Username must be present" } };
	}
	
    @Test(dataProvider="DataDrivenLogin")
    public void paramTest(String strUser, String strPass, String strResponse) {
		driver.get(url);
		SoftAssert sa = new SoftAssert();
        WebElement user, pass, login, alertPrimary;
		
		try {user=driver.findElement(By.id("username"));}catch(Exception e){user=null;}
		try {pass=driver.findElement(By.id("password"));}catch(Exception e){pass=null;}
		try {login=driver.findElement(By.id("log-in"));}catch(Exception e){login=null;}
		try {alertPrimary= driver.findElement(By.className("alert-primary"));}catch(Exception e){alertPrimary=null;}
		
		user.sendKeys(strUser);
		pass.sendKeys(strPass);
		login.click();
		sa.assertEquals(alertPrimary.getText(), strResponse);
		sa.assertEquals(alertPrimary.getAttribute("class"), "alert alert-warning");
		sa.assertEquals(alertPrimary.getAttribute("style"), "display: block;");
		sa.assertAll();
    }
	
	@AfterClass
	public void afterEach() {
		// Close the browser.
		driver.quit();
	}
	
}
