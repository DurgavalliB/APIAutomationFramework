package api.Tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.Endpoints.UserEndPoints;
import api.Payload.User;
import io.restassured.response.Response;

public class UserTests {
	Faker faker;
	User userPayload;
	Response response;

	@BeforeClass
	public void setupData() {
		faker=new Faker();
		userPayload= new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
	}
	
	@Test(priority=1)
	public void testAddUser()
	{
		response=UserEndPoints.createUser(userPayload);
		//response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	
	}
	
	@Test(priority=2)
	public void testGetUser()
	{
		response=UserEndPoints.getUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=3)
	public void testPutUser()
	{
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		response=UserEndPoints.updateUser(userPayload,this.userPayload.getUsername());
		//response.then().log().all();
	  	Assert.assertEquals(response.getStatusCode(), 200);
		//checking data after updating
	  	System.out.println("<<<<After update>>>");
		testGetUser();
			
	}
	
	@Test(priority=4)
	public void testDeleteUser() {
		response=UserEndPoints.deleteUser(this.userPayload.getUsername());
		//response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
}