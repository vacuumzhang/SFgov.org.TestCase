package com.sfgov.pagefactory.adultprobation;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.sfgov.common.Common;


public class AdultProbationPageFactory {
	
	private WebDriver driver;
	public AdultProbationPageFactory(WebDriver driver){
		this.driver = driver;
		new Common(driver);
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		PageFactory.initElements(this.driver, this);
	}
	
	
	
	@FindBy(how = How.CSS, using =".expanded>a")
	List<WebElement> menu;
	
	public List<WebElement> menu(){
		return menu;
	}

}



