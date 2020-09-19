package ndtvUIAutomation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import util.Utility;

public class NDTV {
	
	public static WebDriver driver = null;
	public static String xpathfile,config_file;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.gecko.driver", "F://Gecko//geckodriver//geckodriver.exe");
		driver = new FirefoxDriver();
		
		config_file = System.getProperty("user.dir") + "//src//config//config.properties";
		xpathfile = System.getProperty("user.dir") + "//src//config//object_repo.properties";
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		
		driver.get(Utility.getProperty(config_file,"url"));
		
	    Utility.clickUsingXpath(driver, xpathfile,"three_dots_on_UI");
	    
	    Utility.waitForVisibilityOfElementLocated("weather_button_xpath");
	    
	    Utility.clickUsingXpath(driver, xpathfile,"weather_button_xpath");
		
	    Utility.waitForVisibilityOfElementLocated("search_input_box_pin_your_city_xpath");
	    
	    Utility.typeUsingXpath(driver, xpathfile, "search_input_box_pin_your_city_xpath", config_file, "city_to_be_searched");
	    
	    String city = Utility.getProperty(config_file,"city_to_be_searched");
	    	    
        driver.findElement(By.xpath("//input[@id='"+city+"']")).click();
        
        Utility.assertCityPresenceWithTemperature(driver, Utility.getProperty(config_file,"city_to_be_searched"), Utility.getProperty(config_file,"temp_RedText"),Utility.getProperty(config_file,"temp_whiteText"));        

	}
	

}
