package com.sfgov.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sfgov.common.Common;
import com.sfgov.pagefactory.ecatalog.HomePageFactory;

import com.sfgov.pagefactory.ecatalog.LoginPageFactory;
import com.sfgov.pagefactory.ecatalog.WelcomePageFactory;

public class LoginTests {
	
	private WebDriver driver;
	public HomePageFactory HomePage;
	//public SignOnPage SignOnPage;
	public WelcomePageFactory WelcomePage;
	public LoginPageFactory LoginPageFactory;
	
	public Common CM;

	@BeforeMethod
	public void setUp() throws IOException {
		CM = new Common(driver);
		driver = CM.openBrowser();
		CM.openUrl();
	}

	@AfterMethod
	public void tearDown() {
		driver.manage().deleteAllCookies();
		CM.closeBrowser();
	}

	@Test(description = "right password and username")
	public void login01() throws IOException {
		HomePage = new HomePageFactory(driver);
		CM.clickElement(HomePage.logYourSelfLink());
		
		LoginPageFactory = new LoginPageFactory(driver);
		LoginPageFactory.loginMethod();
		
		WelcomePage = new WelcomePageFactory(driver);
		CM.clickElement(WelcomePage.LogOfButton());

	}

	@Test(dataProvider="getData")  //wrong password, FindBy
	public void login04(String username, String password) {
		
		CM = new Common(driver);
		HomePage = new HomePageFactory(driver);
		CM.clickElement(HomePage.logYourSelfLink());
		LoginPageFactory = new LoginPageFactory(driver);
		LoginPageFactory.username().sendKeys(username);
		LoginPageFactory.pwd().sendKeys(password);
		LoginPageFactory.loginButton().click();
		String ActualText = " Error: No match for E-Mail Address and/or Password.";
		CM.verifyText(LoginPageFactory.messageStackError(), ActualText);
	}
	
	@DataProvider
	public Object[][] getData() throws IOException{
		return CM.setCellData();
		
	}

}
