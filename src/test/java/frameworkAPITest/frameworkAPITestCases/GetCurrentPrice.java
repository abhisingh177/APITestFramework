package frameworkAPITest.frameworkAPITestCases;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;


public class GetCurrentPrice {
	@Test
    public void testGetCurrentPrice() {
        
        RestAssured.baseURI = "https://api.coindesk.com/v1/bpi";
        
        // Send the get request for the resource currentprice.json
        Response response = given().when().get("/currentprice.json").then().statusCode(200).extract().response();
        // Verify the response
        response.then().body("bpi", hasKey("USD"))
        				.body("bpi", hasKey("GBP"))
        				.body("bpi", hasKey("EUR"));
                

        // Verify The GBP ‘description’ equals ‘British Pound Sterling’
        
        response.then()
                .body("bpi.GBP.description", equalTo("British Pound Sterling"));
        //System.out.println(response.getBody().asString());
    }

}
