package apiTesting;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import util.Utility;

public class WeatherDataAPI{

	public static long tempFromAPI = 0;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		String config_file = System.getProperty("user.dir") + "//src//config//config.properties";
		String xpathfile = System.getProperty("user.dir") + "//src//config//object_repo.properties";
		
		RestAssured.baseURI = "https://api.openweathermap.org";
		
		Response response = given().log().all().queryParam("q",getProperty(config_file, "city_to_be_searched")).
		queryParam("appid","7fe67bf08c80ded756e598d6f8fedaea")
		.when().get("/data/2.5/weather").then().log().all().assertThat().statusCode(200).extract().response();
		
		System.out.println("Weather info of city " + getProperty(config_file, "city_to_be_searched") + " is --> ");
//		System.out.println(response.body().asString());
        JsonPath j = new JsonPath(response.asString());
        tempFromAPI = j.getLong("main.temp");
        System.out.println(tempFromAPI);
	}
	
	public static Object getProperty(String filename,String key) throws Exception
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(filename);
		prop.load(fis);
		
		return prop.getProperty(key);
		
	} 

}
