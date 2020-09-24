package apiTesting;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.util.Properties;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import ndtvUIAutomation.NDTV;
import util.Utility;

public class WeatherDataAPI extends NDTV{

	public static long tempFromAPI = 0;
	
	@Test
	public static void getAPIDataAndCompare() throws Exception {
		// TODO Auto-generated method stub
		
		String config_file = System.getProperty("user.dir") + "//src//config//config.properties";
		String xpathfile = System.getProperty("user.dir") + "//src//config//object_repo.properties";
		
		
        
	}
	
	public static Object getProperty(String filename,String key) throws Exception
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(filename);
		prop.load(fis);
		
		return prop.getProperty(key);
		
	} 

}
