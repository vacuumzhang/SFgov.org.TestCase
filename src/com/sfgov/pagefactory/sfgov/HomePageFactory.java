package com.sfgov.pagefactory.sfgov;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.sfgov.common.Common;

public class HomePageFactory {

	private Logger logger = Logger.getLogger(HomePageFactory.class);
	
	private WebDriver driver;
	private Common cm;

	public HomePageFactory(WebDriver driver){
		this.driver = driver;
		cm = new Common(driver);
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		PageFactory.initElements(this.driver, this);
		Assert.assertEquals(driver.getTitle().trim(), "SFGOV");
		logger.info("SFGOV page found, title verified.");
	}
	
	@FindBy(how = How.XPATH, using =".//*[@id='menu']/li[*]/a")
	List<WebElement> menu;
	
	public List<WebElement> menu(){
		return menu;
	}
	
	@FindBy(how = How.XPATH, using = ".//*[@class='panel multi']")
	List<WebElement> mainpageAccordion;
	
	public List<WebElement> mainpageAccordion(){
		return mainpageAccordion;
	}
	
	
	public List<WebElement> test(WebElement target){
		return target.findElements(By.xpath(".//*[@class='panel']"));
	}
	
}
