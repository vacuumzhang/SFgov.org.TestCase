package com.sfgov.common;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class Common {

	private Logger logger = Logger.getLogger(Common.class);
	private WebDriver driver;
	public String sURL = "http://www.sfgov.org";

	public Common(WebDriver driver) {
		this.driver = driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	
	public WebDriver openBrower() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		logger.info("open Brower");
		return driver;

	}

	public void closeBrower() {

		driver.close();
		logger.info("close Brower");
	}

	public void openUrl(String url) {

		driver.get(url);
		logger.info("openUrl: " + url);

	}
	
	public void browerBack() {
		driver.navigate().back();
		logger.info("back to " + driver.getTitle() );
	}
	
	public void clickElement(WebElement target){
		String element = target.getText();
		logger.info("trying to click " + element);
		target.click();
		logger.info(element + " clicked");
		
	}
	
	public void sendKeyToElement(WebElement target, String input){
		String element = target.getText();
		target.sendKeys(input);
		logger.info(input + "send to " + element );
	}
	
	public void mouseMove(WebElement target){
		String element = target.getText();
		Actions action = new Actions(driver);
		action.moveToElement(target).build().perform();;
		logger.info("mouse move to " + element);
	}
	

	public void checkBrokenLinks() throws MalformedURLException, IOException {

		List<WebElement> links = driver.findElements(By.tagName("a"));
		logger.info("checking broken links for: " + driver.getCurrentUrl());
		for (int i = 0; i < links.size(); i++) {
			try{
				if ((links.get(i).getAttribute("href") != null) && !(links.get(i).getAttribute("href").equals(""))) {
					if (links.get(i).getAttribute("href").contains("http")) {
						int linkCode = getResponseCode(links.get(i).getAttribute("href").trim());
						if(linkCode == 404){
							logger.error(i+": 404 found -----" + links.get(i).getAttribute("href").trim());
						}else{
							logger.info(i+": workes fine -----" + links.get(i).getAttribute("href").trim());
						}
					}
				}
			} catch(StaleElementReferenceException elementHasDisappeared){
				logger.error("StaleElementReferenceException index: " + i);
			}
		}
	}
	
	public int getResponseCode(String sURL) throws MalformedURLException, IOException {
		URL url = new URL(sURL);
		HttpURLConnection huc = (HttpURLConnection) url.openConnection();
		huc.setRequestMethod("GET");
		huc.connect();
		return huc.getResponseCode();
		
	}

}
