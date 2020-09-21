package comparator;
import org.testng.Assert;
import org.testng.annotations.Test;

import apiTesting.WeatherDataAPI;
import util.Utility;

public class ComparisonUIandAPI extends WeatherDataAPI{

	@Test
	public static void comparator() throws Exception {
		// TODO Auto-generated method stub
		String confFile = System.getProperty("user.dir") + "src//comparator//conf.properties";
		
		long tempIinCelcius = Utility.getTemperatureInCelcius();
		long difference = WeatherDataAPI.tempFromAPI - tempIinCelcius;
		
		Assert.assertTrue(difference > (long)WeatherDataAPI.getProperty(confFile, "threshold_value_min") && (difference < (long)WeatherDataAPI.getProperty(confFile, "threshold_value_max")));
		
	}
		 

}
