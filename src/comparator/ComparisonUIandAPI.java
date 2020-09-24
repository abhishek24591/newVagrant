package comparator;
import java.io.FileInputStream;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;

import apiTesting.WeatherDataAPI;
import ndtvUIAutomation.*;
import util.Utility;

public class ComparisonUIandAPI extends WeatherDataAPI{

	/*@Test
	public static void comparator() throws Exception {
		
		// TODO Auto-generated method stub
		String confFile = System.getProperty("user.dir") + "src//comparator//conf.properties";
		
		long tempIinCelcius = NDTV.getTemperatureInCelcius(NDTV.driver);
		long difference = WeatherDataAPI.tempFromAPI - tempIinCelcius;
		
		Assert.assertTrue(difference > (long)getProperty(confFile, "threshold_value_min") && (difference < (long)getProperty(confFile, "threshold_value_max")));
		
	}
		 
	public static Object getProperty(String filename,String key) throws Exception
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(filename);
		prop.load(fis);
		
		return prop.getProperty(key);
		
	}  */

}
