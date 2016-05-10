package com.sfgov.tests;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sfgov.common.Common;
import com.sfgov.pagefactory.sfgov.HomePageFactory;

public class SFgovTest {
	
	public Common CM;
	private WebDriver driver;
	
	@BeforeSuite
	public void setUp() throws IOException{
		CM = new Common(driver);
		driver = CM.openBrowser();
		CM.openUrl();
	}
	
	@AfterSuite
	public void tearDown(){
		
		CM.closeBrowser();
	}

	@Test
	public void HomePageTest() {
		HomePageFactory hp = new HomePageFactory(driver);
		int size = hp.menu().size();
		for (int i = 1; i < size; i++){
			WebElement navElement = hp.menu().get(i);
			CM.clickElement(navElement);

			int num = hp.mainpageAccordion().size();
			
			for(int j = 0; j < num; j++){
				WebElement mainElement = hp.mainpageAccordion().get(j);
				CM.clickElement(mainElement);
				List<WebElement> subAccordion = hp.test(mainElement);
				int n = subAccordion.size();
				System.out.println(n);
				for(int k = 0; k < n; k++){
					WebElement subElement = subAccordion.get(k);
					CM.clickElement(subElement);
				}
			}
			CM.browerBack();
		}
	}
	
	@Test 
	public void  brokenLinkTest1() throws MalformedURLException, IOException{
		HomePageFactory hp = new HomePageFactory(driver);
		int size = hp.menu().size();
		for (int i = 0; i < size; i++){
			WebElement we = hp.menu().get(i);
			CM.clickElement(we);
			CM.checkBrokenLinks();
			CM.browerBack();
		}
	}
		
}
