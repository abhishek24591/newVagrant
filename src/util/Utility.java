package util;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import ndtvUIAutomation.NDTV;

public class Utility extends NDTV {

	public static void main(String[] args) throws Exception {
		
		String file = System.getProperty("user.dir") + "//src//config//config.properties";
//		System.out.println(getProperty(file,"url"));
	}
	
	public static Object getProperty(Object filename,Object key) throws Exception
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream((String) filename);
		prop.load(fis);
		
		return prop.getProperty((String) key);
		
	} 
	
	
	public static void waitForVisibilityOfElementLocated(Object key) throws Exception{
		WebDriverWait w = new WebDriverWait(driver, 10);
	    w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath((String) Utility.getProperty(xpathfile,key))));
	}
	
	
	//wrapper method
	
	public static void clickUsingXpath(WebDriver dr,String xpathFile,String xpathKey) throws Exception
	{
		Thread.sleep(2000);
		dr.findElement(By.xpath((String) getProperty(xpathFile, xpathKey))).click();
	}
	
	public static void typeUsingXpath(WebDriver dr,String xpathFile,String xpathKey,String dataFile,String dataToEnter) throws Exception
	{
		Thread.sleep(2000);
		Actions act = new Actions(dr);
		Action series = (Action)act.moveToElement(dr.findElement(By.xpath((String) getProperty(xpathFile, xpathKey)))).sendKeys((String)getProperty(dataFile, dataToEnter)).build();
//		dr.findElement(By.xpath((String) getProperty(xpathFile, xpathKey))).sendKeys((String)getProperty(dataFile, dataToEnter));
	    series.perform();
	}
	
	public static String getTextUsingXpath(WebDriver dr,String xpathFile,String xpathKey) throws Exception
	{
		return dr.findElement(By.xpath((String) getProperty(xpathFile, xpathKey))).getText();
	}
	
	public static void assertCityPresenceWithTemperature(WebDriver dr,Object cityName,Object temp_redTxt,Object temp_whiteTxt) throws Exception{
		Thread.sleep(3000);
		WebElement city = dr.findElement(By.xpath("//div[@class='outerContainer' and @title='"+cityName+"']"));
		Thread.sleep(1000);
		WebElement cityTemperatureRedText = dr.findElement(By.xpath("//div[@class='outerContainer' and @title='"+cityName+"']//div[@class='temperatureContainer']/span[1]"));
		Thread.sleep(1000);
		WebElement cityTemperatureWhiteText = dr.findElement(By.xpath("//div[@class='outerContainer' and @title='"+cityName+"']//div[@class='temperatureContainer']/span[2]"));
		temp_redTxt  = cityTemperatureRedText.getText();
		temp_whiteTxt = cityTemperatureWhiteText.getText();
		
	   	Assert.assertTrue(city.isDisplayed(), ""+cityName+" is not on Map");
	   	Assert.assertTrue(cityTemperatureRedText.isDisplayed(), "Red temperature is not on Map");
	   	Assert.assertTrue(cityTemperatureWhiteText.isDisplayed(), "White temperature is not on Map");
	   	
	}
	
	public static void displayWeatherInformationOfDesiredCity(WebDriver dr,Object cityNm) throws Exception{
		WebElement WeatherPopUp = dr.findElement(By.xpath("//div[text()='"+cityNm+"']//following::div[@class='leaflet-popup-content']/div[1]"));
		List<WebElement> weatherInformation = WeatherPopUp.findElements(By.tagName("span"));
		String cityNameText = WeatherPopUp.findElement(By.tagName("div")).getText();
		System.out.println("Weather information of " + cityNm + " is as below: ");
		System.out.println(cityNameText);
		for(WebElement el:weatherInformation){
			System.out.println(el.getText());
			Thread.sleep(1000);
		}
	}
	
	/*public static void moveToElementAndClick(WebDriver dr,Object xpathfile,Object xpathkey) throws Exception{
		Thread.sleep(1000);
		Actions action = new Actions(dr);
		action.moveToElement(dr.findElement(By.xpath((String) getProperty(xpathfile, xpathkey)))).click();
	} */
	
	public static void moveToElementAndClickAutoSuggestiveDropdown(WebDriver dr,Object cityName) throws Exception{
		Thread.sleep(2000);
		List<WebElement> allCities = dr.findElements(By.xpath("//div[@class='messages']//label/input"));
		for(WebElement element:allCities){
			if(element.getAttribute("id").equalsIgnoreCase((String) cityName)){
				element.click();
			}
		}
	}
	
	public static void moveToElementAndClickOnMap(WebDriver dr,Object cityName) throws Exception{
		Thread.sleep(1000);
//		Actions action = new Actions(dr);
        WebElement element = dr.findElement(By.xpath("//div[@title='"+cityName+"']"));
//		((JavascriptExecutor) dr).executeScript("arguments[0].click();", element);
        element.click();
	}
	
	
	
	
	
	
}
