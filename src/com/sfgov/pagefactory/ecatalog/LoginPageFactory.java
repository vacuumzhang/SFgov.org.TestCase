package com.sfgov.pagefactory.ecatalog;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sfgov.common.Common;
import com.sfgov.common.ReadProperties;

public class LoginPageFactory {
	private WebDriver driver;
	private Common CM;

	// By userename = By.name("email_address");
	// By pwd = By.name("password");
	// By loginButton = By.xpath("//*[@id='tdb5']/span[2]");

	public LoginPageFactory(WebDriver driver) {
		this.driver = driver;
		CM = new Common(driver);
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		PageFactory.initElements(this.driver, this);
	}

	@FindBy(name = "email_address")
	WebElement userename;

	@FindBy(name = "password")
	WebElement pwd;

	@FindBy(xpath = "//*[@id='tdb5']/span[2]")
	WebElement loginButton;
	
	@FindBy(xpath = ".//*[@id='bodyContent']/table/tbody/tr/td")
	WebElement messageStackError;

	public WebElement username() {
		return userename;
	}

	public WebElement pwd() {
		return pwd;
	}

	public WebElement loginButton() {
		return loginButton;
	}
	
	public WebElement messageStackError() {
		return messageStackError;
	}
	
	public void loginMethod() throws IOException{
		ReadProperties rd = new ReadProperties();
		String emailAddress = rd.propertiesReader("config.properties", "email_address");
		String password = rd.propertiesReader("config.properties", "password");
		LoginPageFactory LoginPageFactory = new LoginPageFactory(driver);
		LoginPageFactory.username().sendKeys(emailAddress);
		LoginPageFactory.pwd().sendKeys(password);
		LoginPageFactory.loginButton().click();
		
	}

}
