package util;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import ndtvUIAutomation.NDTV;

public class Utility extends NDTV {

	public static void main(String[] args) throws Exception {
		
		String file = System.getProperty("user.dir") + "//src//config//config.properties";
//		System.out.println(getProperty(file,"url"));
	}
	
	public static String getProperty(String filename,String key) throws Exception
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(filename);
		prop.load(fis);
		
		return prop.getProperty(key);
		
	} 
	
	
	public static void waitForVisibilityOfElementLocated(String key) throws Exception{
		WebDriverWait w = new WebDriverWait(driver, 10);
	    w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperty(xpathfile,key))));
	}
	
	
	//wrapper method
	
	public static void clickUsingXpath(WebDriver dr,String xpathFile,String xpathKey) throws Exception
	{
		dr.findElement(By.xpath(getProperty(xpathFile, xpathKey))).click();
	}
	
	public static void typeUsingXpath(WebDriver dr,String xpathFile,String xpathKey,String dataFile,String dataToEnter) throws Exception
	{
		dr.findElement(By.xpath(getProperty(xpathFile, xpathKey))).sendKeys(getProperty(dataFile, dataToEnter));
	}
	
	public static String getTextUsingXpath(WebDriver dr,String xpathFile,String xpathKey) throws Exception
	{
		return dr.findElement(By.xpath(getProperty(xpathFile, xpathKey))).getText();
	}
	
	public static void assertCityPresenceWithTemperature(String cityName,String temp_redTxt,String temp_whiteTxt) throws Exception{
		WebElement city = driver.findElement(By.xpath("//div[@class='outerContainer' and @title='"+cityName+"']"));
		WebElement cityTemperatureRedText = driver.findElement(By.xpath("//div[@class='outerContainer' and @title='"+cityName+"']//div[@class='temperatureContainer']/span[1]"));
		WebElement cityTemperatureWhiteText = driver.findElement(By.xpath("//div[@class='outerContainer' and @title='"+cityName+"']//div[@class='temperatureContainer']/span[2]"));
		temp_redTxt  = cityTemperatureRedText.getText();
		temp_whiteTxt = cityTemperatureWhiteText.getText();
		
	   	Assert.assertTrue(city.isDisplayed(), ""+cityName+" is not on Map");
	   	Assert.assertTrue(cityTemperatureRedText.isDisplayed(), "Red temperature is not on Map");
	   	Assert.assertTrue(cityTemperatureWhiteText.isDisplayed(), "White temperature is not on Map");
	   	
	}
	
}
