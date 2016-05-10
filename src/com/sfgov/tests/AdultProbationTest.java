package com.sfgov.tests;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.sfgov.common.Common;
import com.sfgov.pagefactory.adultprobation.AdultProbationPageFactory;
import com.sfgov.pagefactory.sfgov.HomePageFactory;

public class AdultProbationTest {
	
	public Common cm;
	private WebDriver driver;
	
	@BeforeSuite
	public void setUp() throws IOException{
		cm = new Common(driver);
		driver = cm.openBrowser();
		cm.openUrl();
	}
	
	@AfterSuite
	public void tearDown(){
		
		cm.closeBrowser();
	}

	@Test
	public void  brokenLinkTest1() throws MalformedURLException, IOException{
		AdultProbationPageFactory hp = new AdultProbationPageFactory(driver);
		int size = hp.menu().size();
		for (int i = 0; i < size; i++){
			WebElement we = hp.menu().get(i);
			cm.clickElement(we);
			cm.checkBrokenLinks();
			cm.browerBack();
		}
	}
		
}
