package TestExecution;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreatePostRequest {

	@Test(priority=1, enabled=true)
public void createPostRequest() {
		
		 RestAssured.baseURI = "https://reqres.in/api";  // Sample API
	        JSONObject data= new JSONObject();
	        
	        data.put("name","Advait");
	        data.put("job", "leader");
	        
	        Response response = given()
	                .contentType(ContentType.JSON)
	                .body(data.toString())
	                                
	        .when()
	                .post("/users")
	        .then()
	                .statusCode(201)
	                .body("name", equalTo("Advait"))
	                .body("job", equalTo("leader"))
	                .extract().response();

	        System.out.println("Response: " + response.prettyPrint());      
	}
	
	@Test(priority = 2, enabled =true)
	public void deleteRequest() {
		
		//RestAssured.baseURI="https://reqres.in/api";
		
		given()
		.when()
		.delete("https://reqres.in/api/992")
		.then()
		.statusCode(204);
		
	}

}
