package com.handresc1127.applitools.test1LoginPage;


import static org.testng.Assert.assertEquals;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TraditionalTests {
	
	private WebDriver driver;

	@BeforeMethod
	public void beforeEach() {
		// Use Chrome browser
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		driver = new ChromeDriver(options);
	}
	
	@Test
	public void basicTest() {
		String url= "https://demo.applitools.com/hackathon.html";
		driver.get(url);
		SoftAssert sa = new SoftAssert();
		
		sa.assertTrue(driver.findElement(By.className("auth-header")) instanceof WebElement);
		//sa.assertThat(driver.findElement(By.className("auth-header")).getText(), containsString("Login Form"));
		
		sa.assertTrue(driver.findElement(By.id("username")) instanceof WebElement);
		sa.assertEquals(driver.findElement(By.id("username")).getAttribute("placeholder"),"Enter your username");
		sa.assertTrue(driver.findElement(By.xpath("//*[@id='username']/../label")) instanceof WebElement);
		sa.assertEquals(driver.findElement(By.xpath("//*[@id='username']/../label")).getText(),"Username");
		//sa.assertTrue(driver.findElement(By.xpath("//*[@id='username']/../*[contains(@class,'pre-icon')]")) instanceof WebElement);
		//sa.assertThat(driver.findElement(By.xpath("//*[@id='username']/../*[contains(@class,'pre-icon')]")).getAttribute("class"), containsString("os-icon-user-male-circle"));
		
		sa.assertTrue(driver.findElement(By.id("password")) instanceof WebElement);
		sa.assertEquals(driver.findElement(By.id("password")).getAttribute("placeholder"),"Enter your password");
		sa.assertTrue(driver.findElement(By.xpath("//*[@id='password']/../label")) instanceof WebElement);
		sa.assertEquals(driver.findElement(By.xpath("//*[@id='password']/../label")).getText(),"Password");
		//sa.assertTrue(driver.findElement(By.xpath("//*[@id='password']/../*[contains(@class,'pre-icon')]")) instanceof WebElement);
		//sa.assertThat(driver.findElement(By.xpath("//*[@id='password']/../*[contains(@class,'pre-icon')]")).getAttribute("class"), containsString("os-icon-fingerprint"));
		
		sa.assertTrue(driver.findElement(By.id("log-in")) instanceof WebElement);
		sa.assertTrue(driver.findElement(By.id("alertEmpty")) instanceof WebElement);
		sa.assertTrue(driver.findElement(By.className("alert-primary")) instanceof WebElement);
		sa.assertTrue(driver.findElements(By.xpath("//form/div[3]/div[2]/*")) instanceof ArrayList);
		sa.assertEquals(driver.findElements(By.xpath("//form/div[3]/div[2]/*")).size(),3);
		for (WebElement element: driver.findElements(By.xpath("//form/div[3]/div[2]/*"))) {
			sa.assertEquals(element.getAttribute("href"), url+"#");
		}
		// the asserts used till now will not throw any exception if they fail. The @Test will not fail either.
        // If you need the test method to fail at the end, showing all exceptions, you need to use assertAll()
        sa.assertAll();
		
	}
	
	@AfterClass
	public void afterEach() {
		// Close the browser.
		driver.quit();
	}
	
}
