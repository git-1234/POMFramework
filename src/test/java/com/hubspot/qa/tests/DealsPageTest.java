package com.hubspot.qa.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hubspot.qa.pages.BasePage;
import com.hubspot.qa.pages.HomePage;
import com.hubspot.qa.pages.LoginPage;
import com.hubspot.qa.pages.DealsPage;
import com.hubspot.qa.util.TestUtil;

public class DealsPageTest {
	
	public WebDriver driver;
	public BasePage basePage;
	public Properties prop;
	public LoginPage loginPage;
	public HomePage homePage;
	public DealsPage dealsPage;
	
	@BeforeMethod
	public void setUp(){
		basePage = new BasePage();
		prop = basePage.init_properties();
		driver = basePage.init();
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		dealsPage = homePage.goToDealsPage();
	}
	
	@DataProvider
	public Object[][] getDealsTestData(){
		return TestUtil.getTestData("deal");
	}
	
	@Test(dataProvider= "getDealsTestData")
	public void createDealTest(String dealName, String pipeLine, String dealStage, String totalAmt){
		dealsPage.createNewDeal(dealName, pipeLine, dealStage, totalAmt);
	}
	
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	
	
	}
}
