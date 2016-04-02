package com.sfgov.tests;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.sfgov.common.Common;

public class AdultProbationTest {

	private WebDriver driver;
	private Common cm;
	private String sURL = "http://sfgov.org/adultprobation/";
	
	@BeforeSuite
	public void setUp() {
		cm = new Common(driver);
		cm.openBrower();
		cm.openUrl(sURL);
	}

	@AfterSuite
	public void tearDown() {
		cm.closeBrower();
	}

	@Test
	public void brokenLinkTest() {
		

	}
}
