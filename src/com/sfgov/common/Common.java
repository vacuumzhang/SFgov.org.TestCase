package com.sfgov.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;

public class Common {

	private Logger logger = Logger.getLogger(Common.class);
	private WebDriver driver;
	public String chromePath = "/Users/Simon/Documents/Selenium/Workspace/SFgov.org.TestCase/drivers/chromedriver";
	public String excelPath = "./TestData/test.xlsx";
	public XSSFWorkbook excelWorkbook;
	public XSSFSheet excelSheet;
	public XSSFRow row;
	public XSSFCell cell;

	public Common(WebDriver driver) {
		this.driver = driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}


	public WebDriver openBrowser() throws IOException {
		
		ReadProperties rd = new ReadProperties();
		String browerType = rd.propertiesReader("config.properties", "browser");
		System.out.println(browerType);
		
		if (browerType.equals("Firefox")) {
			driver = new FirefoxDriver();
		} else if (browerType.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver", chromePath);
			driver = new ChromeDriver();
		} else if (browerType.equals("Safari")) {
			System.setProperty("webdriver.safari.noinstall", "true");
			driver = new SafariDriver();
		} else if (browerType.equals("Remote")) {
			driver = new RemoteWebDriver(new URL("http://10.1.10.146:5566/wd/hub"), DesiredCapabilities.firefox());
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		logger.info("open Browser");
		return driver;
	}

	public void closeBrowser() {
		driver.close();
		logger.info("close Browser");
	}

	public void openUrl() {
		ReadProperties rd = new ReadProperties();
		try {
			String url = rd.propertiesReader("./config.properties", "url");
			driver.get(url);
			logger.info("openUrl: " + url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void browerBack() {
		driver.navigate().back();
		logger.info("back to " + driver.getTitle());
	}

	public void clickElement(WebElement target) {
		String element = target.getText();
		logger.info("trying to click " + element);
		target.click();
		logger.info(element + " clicked");
	}

	public void sendKeyToElement(WebElement target, String input) {
		String element = target.getText();
		target.sendKeys(input);
		logger.info(input + "send to " + element);
	}

	public void mouseMove(WebElement target) {
		String element = target.getText();
		Actions action = new Actions(driver);
		action.moveToElement(target).build().perform();
		;
		logger.info("mouse move to " + element);
	}

	public void verifyText(WebElement target, String expected) {
		String element = target.getText();
		try {
			Assert.assertEquals(element, expected);
			logger.info("expected text verified: " + element);
		} catch (Exception e) {
			logger.error("expected text not found: " + expected);
		}
	}

	public void checkBrokenLinks() throws MalformedURLException, IOException {
		List<WebElement> links = driver.findElements(By.tagName("a"));
		logger.info("checking broken links for: " + driver.getCurrentUrl());
		for (int i = 0; i < links.size(); i++) {
			try {
				if ((links.get(i).getAttribute("href") != null) && !(links.get(i).getAttribute("href").equals(""))) {
					if (links.get(i).getAttribute("href").contains("http")) {
						int linkCode = getResponseCode(links.get(i).getAttribute("href").trim());
						if (linkCode == 404) {
							logger.error(i + ": 404 found -----" + links.get(i).getAttribute("href").trim());
						} else {
							logger.info(i + ": workes fine -----" + links.get(i).getAttribute("href").trim());
						}
					}
				}
			} catch (StaleElementReferenceException elementHasDisappeared) {
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

	// get data from
	public Object[][] setCellData() {

		FileInputStream fis;
		try {
			fis = new FileInputStream(excelPath);
			excelWorkbook = new XSSFWorkbook(fis);
			excelSheet = excelWorkbook.getSheet("sheet1");
			int rowCount = excelSheet.getLastRowNum() - excelSheet.getFirstRowNum();
			int colCount = excelSheet.getRow(0).getLastCellNum();

			Object[][] data = new Object[rowCount + 1][colCount];
			for (int rowNum = 0; rowNum <= rowCount; rowNum++) {
				for (int colNum = 0; colNum < colCount; colNum++) {
					data[rowNum][colNum] = excelSheet.getRow(rowNum).getCell(colNum).getStringCellValue();
				}
			}

			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;

		}

	}
}
