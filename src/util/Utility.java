package util;

import java.io.FileInputStream;
import java.util.List;
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
	
	public static void assertCityPresenceWithTemperature(WebDriver dr,String cityName,String temp_redTxt,String temp_whiteTxt) throws Exception{
		WebElement city = dr.findElement(By.xpath("//div[@class='outerContainer' and @title='"+cityName+"']"));
		WebElement cityTemperatureRedText = dr.findElement(By.xpath("//div[@class='outerContainer' and @title='"+cityName+"']//div[@class='temperatureContainer']/span[1]"));
		WebElement cityTemperatureWhiteText = dr.findElement(By.xpath("//div[@class='outerContainer' and @title='"+cityName+"']//div[@class='temperatureContainer']/span[2]"));
		temp_redTxt  = cityTemperatureRedText.getText();
		temp_whiteTxt = cityTemperatureWhiteText.getText();
		
	   	Assert.assertTrue(city.isDisplayed(), ""+cityName+" is not on Map");
	   	Assert.assertTrue(cityTemperatureRedText.isDisplayed(), "Red temperature is not on Map");
	   	Assert.assertTrue(cityTemperatureWhiteText.isDisplayed(), "White temperature is not on Map");
	   	
	}
	
	public static void displayWeatherInformationOfDesiredCity(WebDriver dr,String cityNm){
		WebElement WeatherPopUp = dr.findElement(By.xpath("//div[text()='"+cityNm+"']//following::div[@class='leaflet-popup-content']/div[1]"));
		List<WebElement> weatherInformation = WeatherPopUp.findElements(By.tagName("span"));
		String cityNameText = WeatherPopUp.findElement(By.tagName("div")).getText();
		System.out.println("Weather information of " + cityNm + " is as below: ");
		System.out.println(cityNameText);
		for(WebElement el:weatherInformation){
			System.out.println(el.getText());
		}
	}
	
	public static long getTemperatureInCelcius(){
		String temperature = driver.findElement(By.xpath("//b[text()='Temp in Degrees: 37']")).getText();
		Object[] arr = temperature.split(": ");
		long temperatureInCelciusFromUI = (long) arr[1];
		return temperatureInCelciusFromUI;
	}
	
	
	
}
