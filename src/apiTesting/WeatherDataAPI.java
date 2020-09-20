package apiTesting;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import util.Utility;

public class WeatherDataAPI extends Utility{

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		RestAssured.baseURI = "https://api.openweathermap.org";
		
		Response response = given().log().all().queryParam("q","Ajmer").
		queryParam("appid","7fe67bf08c80ded756e598d6f8fedaea")
		.when().get("/data/2.5/weather").then().log().all().assertThat().statusCode(200).extract().response();
		
        System.out.println(response);

	}

}
