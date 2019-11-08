package com.handresc1127.applitools.test1LoginPage;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TraditionalTests {
	
	private WebDriver driver;

	@Before
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
		
		assertTrue(driver.findElement(By.className("auth-header")) instanceof WebElement);
		assertThat(driver.findElement(By.className("auth-header")).getText(), containsString("Login Form"));
		
		assertTrue(driver.findElement(By.id("username")) instanceof WebElement);
		assertEquals("Enter your username",driver.findElement(By.id("username")).getAttribute("placeholder"));
		assertTrue(driver.findElement(By.xpath("//*[@id='username']/../label")) instanceof WebElement);
		assertEquals("Username",driver.findElement(By.xpath("//*[@id='username']/../label")).getText());
		assertTrue(driver.findElement(By.xpath("//*[@id='username']/../*[contains(@class,'pre-icon')]")) instanceof WebElement);
		assertThat(driver.findElement(By.xpath("//*[@id='username']/../*[contains(@class,'pre-icon')]")).getAttribute("class"), containsString("os-icon-user-male-circle"));
		
		assertTrue(driver.findElement(By.id("password")) instanceof WebElement);
		assertEquals("Enter your password",driver.findElement(By.id("password")).getAttribute("placeholder"));
		assertTrue(driver.findElement(By.xpath("//*[@id='password']/../label")) instanceof WebElement);
		assertEquals("Password",driver.findElement(By.xpath("//*[@id='password']/../label")).getText());
		assertTrue(driver.findElement(By.xpath("//*[@id='password']/../*[contains(@class,'pre-icon')]")) instanceof WebElement);
		assertThat(driver.findElement(By.xpath("//*[@id='password']/../*[contains(@class,'pre-icon')]")).getAttribute("class"), containsString("os-icon-fingerprint"));
		
		assertTrue(driver.findElement(By.id("log-in")) instanceof WebElement);
		assertTrue(driver.findElement(By.id("alertEmpty")) instanceof WebElement);
		assertTrue(driver.findElement(By.className("alert-primary")) instanceof WebElement);
		assertTrue(driver.findElements(By.xpath("//form/div[3]/div[2]/*")) instanceof ArrayList);
		assertEquals(3, driver.findElements(By.xpath("//form/div[3]/div[2]/*")).size());
		for (WebElement element: driver.findElements(By.xpath("//form/div[3]/div[2]/*"))) {
			assertEquals(url+"#", element.getAttribute("href"));
		}
		
	}
	
	@After
	public void afterEach() {
		// Close the browser.
		driver.quit();
	}
	
}
