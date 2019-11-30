package com.handresc1127.applitools.test1LoginPage;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

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
	
	@Test(priority = 1, testName = "Login Page UI Elements Test")
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
			{ "", "Password", "Username must be present" },
			{"Us3rn4m3","Password", "Username and Password present"}};
	}
	
    @Test(priority = 2, testName = "Data-Driven Test", dataProvider="DataDrivenLogin")
    public void dataDrivenTest(String strUser, String strPass, String strResponse) {
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
		if(strResponse.equals("Username and Password present")) {
			sa.assertEquals(driver.findElement(By.id("logged-user-name")).isDisplayed(), true);
		}else {
			sa.assertEquals(alertPrimary.getText(), strResponse);
			sa.assertEquals(alertPrimary.getAttribute("class"), "alert alert-warning");
			sa.assertEquals(alertPrimary.getAttribute("style"), "display: block;");
		}
		sa.assertAll();
    }
    
    @Test(priority = 3, testName = "Table Sort Test")
    public void shortTableTest() {
		driver.get(url);
		SoftAssert sa = new SoftAssert();
        WebElement user, pass, login, transactionsTable, transactionsTable2, amount;
		
		try {user=driver.findElement(By.id("username"));}catch(Exception e){user=null;}
		try {pass=driver.findElement(By.id("password"));}catch(Exception e){pass=null;}
		try {login=driver.findElement(By.id("log-in"));}catch(Exception e){login=null;}
		user.sendKeys("Us3rn4m3");
		pass.sendKeys("Password");
		login.click();
		
		try {transactionsTable=driver.findElement(By.id("transactionsTable"));}catch(Exception e){transactionsTable=null;}
		String [][]table= getTable(driver,transactionsTable);
		
		try {amount=driver.findElement(By.id("amount"));}catch(Exception e){amount=null;}
		amount.click();
		
		try {transactionsTable2=driver.findElement(By.id("transactionsTable"));}catch(Exception e){transactionsTable2=null;}
		String [][]table2= getTable(driver,transactionsTable2);
		System.out.println(table2);
		String [][] table3=orderByDesc(table, 4);

		for(int i=0;i<table2.length;i++) {
			for(int j=0;j<table2[i].length;j++) {
				sa.assertEquals(table2[i][j], table3[i][j]);
			}
		}
		
		sa.assertAll();
    }
    
	public String[][] getTable(WebDriver driver, WebElement tableElement) {
		List<WebElement> trCollection = tableElement.findElements(By.tagName("tr"));
		String[][] tabla = new String[999][999];
		int rowNum = 0;
		int colNum;
		int colMax = 0;
		for (WebElement trElement : trCollection) {
			List<WebElement> tdCollection = trElement.findElements(By.tagName("td"));
			if (tdCollection.isEmpty()) {
				tdCollection = trElement.findElements(By.tagName("th"));
			}
			if (tdCollection.size() > colMax)
				colMax = tdCollection.size();
			colNum = 0;
			for (WebElement tdElement : tdCollection) {
				tabla[rowNum][colNum] = tdElement.getText();
				colNum++;
			}
			rowNum++;
		}
		assertTrue(rowNum > 0);
		assertTrue(colMax > 0);
		String[][] tablaReturn = new String[rowNum][colMax];
		for (int i = 0; i < rowNum; i++) {
			System.arraycopy(tabla[i], 0, tablaReturn[i], 0, colMax);
		}
		return tablaReturn;
	}
	
	public String [][] orderByDesc(String[][] table,int indexColumn){
		for (int i=1;i<table.length;i++) {
			double min= Double.parseDouble(table[i][indexColumn].replace(" USD", "").replace(",", "").replace("+", "").replace(" ", "")); 
			int indexMin=i;
			for(int j=i;j<table.length;j++) {
				double value= Double.parseDouble(table[j][indexColumn].replace(" USD", "").replace(",", "").replace("+", "").replace(" ", ""));
				if(value<min) {
					min=value;
					indexMin=j;
				}
			}
			String [] aux=table[i];
			table[i]=table[indexMin];
			table[indexMin]=aux;
		}
		return table;
	}
	
	@Test(priority = 4, testName = "Canvas Chart Test")
	public void canvasChartTest() {
		driver.get(url);
		SoftAssert sa = new SoftAssert();
        WebElement user, pass, login, showExpensesChart;
		
		try {user=driver.findElement(By.id("username"));}catch(Exception e){user=null;}
		try {pass=driver.findElement(By.id("password"));}catch(Exception e){pass=null;}
		try {login=driver.findElement(By.id("log-in"));}catch(Exception e){login=null;}
		user.sendKeys("Us3rn4m3");
		pass.sendKeys("Password");
		login.click();
		
		try {showExpensesChart=driver.findElement(By.id("showExpensesChart"));}catch(Exception e){showExpensesChart=null;}
		showExpensesChart.click();
		
		
		sa.assertAll();
	}
	
	@AfterClass
	public void afterEach() {
		// Close the browser.
		driver.quit();
	}
	
}
