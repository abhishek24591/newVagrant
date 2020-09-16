package util;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Utility {

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
}
