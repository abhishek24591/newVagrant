package ndtvUIAutomation;

import static io.restassured.RestAssured.given;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import apiTesting.WeatherDataAPI;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import util.Utility;

public class NDTV {
	
	public static WebDriver driver;
	public static String xpathfile,config_file;

	@Test
	public static void UIandAPIComparator() throws Exception {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "F://chromedriver2.34_win32//chromedriver.exe");
		driver = new ChromeDriver();
		
		config_file = System.getProperty("user.dir") + "//src//config//config.properties";
		xpathfile = System.getProperty("user.dir") + "//src//config//object_repo.properties";
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		
		driver.get((String) Utility.getProperty(config_file,"url"));
		
		Utility.clickUsingXpath(driver, xpathfile,"no_thanks_link");
		
	    Utility.clickUsingXpath(driver, xpathfile,"three_dots_on_UI");
	    
	    Utility.waitForVisibilityOfElementLocated("weather_button_xpath");
	    
	    Utility.clickUsingXpath(driver, xpathfile,"weather_button_xpath");
		
//	    Utility.waitForVisibilityOfElementLocated("search_input_box_pin_your_city_xpath");
	    
	    Utility.typeUsingXpath(driver, xpathfile, "search_input_box_pin_your_city_xpath", config_file, "city_to_be_searched");
	    
	    Utility.moveToElementAndClickAutoSuggestiveDropdown(driver, (String) Utility.getProperty(config_file,"city_to_be_searched"));
	    
	    String city = (String) Utility.getProperty(config_file,"city_to_be_searched");
	    	    
//        driver.findElement(By.xpath("//input[@id='"+city+"']")).click();
	    
        Utility.assertCityPresenceWithTemperature(driver, Utility.getProperty(config_file,"city_to_be_searched"), Utility.getProperty(config_file,"temp_RedText"),Utility.getProperty(config_file,"temp_whiteText"));        

        Utility.moveToElementAndClickOnMap(driver,Utility.getProperty(config_file,"city_to_be_searched"));
        
        System.out.println("Clicked on city on Map " + (String)Utility.getProperty(config_file,"city_to_be_searched"));
        
        System.out.println("*********** UI Part **********");
        
        Utility.displayWeatherInformationOfDesiredCity(driver, Utility.getProperty(config_file,"city_to_be_searched"));
        
        String temperature = driver.findElement(By.xpath("(//div[@class='leaflet-popup-content']//span/b)[4]")).getText();
		String[] arr = temperature.split(": ");
		double temperatureInCelciusFromUI = Double.parseDouble(arr[1]);
		
        System.out.println();
		System.out.println("************ API Part ************");
        RestAssured.baseURI = "https://api.openweathermap.org";
		
		Response response = given().log().all().queryParam("q",Utility.getProperty(config_file,"city_to_be_searched")).
		queryParam("appid","7fe67bf08c80ded756e598d6f8fedaea")
		.when().get("/data/2.5/weather").then().log().all().assertThat().statusCode(200).extract().response();
		
//		System.out.println("Weather info of city " + Utility.getProperty(config_file,"city_to_be_searched") + " is --> ");
//		System.out.println(response.body().asString());
        JsonPath j = new JsonPath(response.asString());
        double temperatureFromAPI = Double.parseDouble(j.getString("main.temp"));
        System.out.println(temperatureFromAPI);
        int difference = (int)temperatureFromAPI - (int)temperatureInCelciusFromUI;
        System.out.println("Temperature from UI in  celcius is ---> " + (int)temperatureInCelciusFromUI);
        System.out.println("Temperature from API in celcius is ---> " + (int)temperatureFromAPI);
        System.out.println("**********************************");
        System.out.println("Difference between temperature from API and UI is --> " + difference);	
        System.out.println("**********************************");
        String thresh_max = (String) Utility.getProperty(config_file,"threshold_max");
        String thresh_min = (String) Utility.getProperty(config_file,"threshold_min");
		Assert.assertTrue(difference > Integer.parseInt(thresh_min.trim()));
		Assert.assertTrue(difference < Integer.parseInt(thresh_max.trim()));       
		driver.quit();
	}

}
