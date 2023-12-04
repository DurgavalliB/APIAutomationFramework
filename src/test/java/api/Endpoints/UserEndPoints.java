package api.Endpoints;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import api.Payload.User;
import io.restassured.response.Response;

public class UserEndPoints {

	//this class is created to perform CRUD operations on the user endpoints
	public static Response createUser(User payload) {
		Response resp=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.when()
				.post(Routes.postUserUrl);
		return resp;		
	}
	
	public static Response getUser(String uName)	{
		Response resp = given()
				.pathParam("username", uName)
				.when()
				.get(Routes.getUserUrl);
		return resp;
	}
	
	public static Response updateUser(User payload,String uName) {
		Response resp = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
					.pathParam("username", uName)
					.body(payload)
					.when()
					.put(Routes.putUserUrl);
		return resp;
	}
	
	
	public static Response deleteUser(String uName) {
		Response resp = given()
				.pathParam("username", uName)
				.when()
				.delete(Routes.deleteUserUrl);
		return resp;
	}
	
}
