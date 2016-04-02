package com.sfgov.pagefactory.sfgov;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.sfgov.common.Common;

public class ResidentsPageFactory {

	private Logger logger = Logger.getLogger(ResidentsPageFactory.class);
	
	private WebDriver driver;
	private Common cm;
	
	public ResidentsPageFactory(WebDriver driver){
		this.driver = driver;
		cm = new Common(driver);
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		PageFactory.initElements(this.driver, this);
	}
	
	@FindBy(how = How.XPATH, using = ".//*[@class='panel multi']")
	List<WebElement> emergency;
	
	public List<WebElement> emergency(){
		return emergency;
	}

	
}
