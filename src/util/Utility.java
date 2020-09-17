package util;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	
	public static void waitForVisibilityOfElementLocated(String key) throws Exception{
		WebDriverWait w = new WebDriverWait(NDTV.driver, 10);
	    w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperty(NDTV.xpathfile,key))));
	}
}
