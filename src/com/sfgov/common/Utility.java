package com.sfgov.common;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;



public class Utility {
	
	private static Logger logger = Logger.getLogger(Utility.class);
	
	public static void captureScreenshot(WebDriver driver,String screenshotName){
		try {
			TakesScreenshot ts=(TakesScreenshot)driver;
			
			File source=ts.getScreenshotAs(OutputType.FILE);
			
			FileUtils.copyFile(source, new File("./Screenshots/"+screenshotName+".png"));
			
			logger.info("Screenshot taken");
		} catch (Exception e){
			logger.info("Exception while taking screenshot " + e.getMessage());
		} 
	}
}