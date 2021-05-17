package com.revature.batch412.keithsantamaria.unittests;

import com.revature.batch412.keithsantamaria.project1.app.App;
import com.revature.batch412.keithsantamaria.project1.reimbursement.Reimbursement;
import com.revature.batch412.keithsantamaria.project1.users.User;
import com.revature.batch412.keithsantamaria.project1.users.UserRoles;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AppTests {
	App testApp;

	@BeforeClass
	public static void beforeAllTests(){
		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.FATAL);
		BasicConfigurator.configure();
	}

	@Before
	public void beforeEachTest(){
		testApp = new App();
	}

	@Test
	public void shouldLogin(){
		String user = "Admin";
		String password = "123password123";
		UserRoles myRole = UserRoles.MANAGER;
		boolean actualResult = testApp.login(user, password, myRole);
		Assert.assertEquals("Login did not succeed", true , actualResult);
	}

	@Test
	public void shouldNotLogin(){
		String user = "Admin";
		String password = "123password123";
		UserRoles myRole = UserRoles.EMPLOYEE;
		boolean actualResult = testApp.login(user, password , myRole);
		Assert.assertEquals("Expect to fail login, but it passed instead", false, actualResult);
	}

	@Test
	public void shouldParseJson(){
		String test = "{\"username\":\"Admin\",\"password\":\"123password123\"}";
		JSONObject testJson = testApp.parseReceivedData(test);
		JSONObject expectedJson = new JSONObject();
		expectedJson.put("username", "Admin");
		expectedJson.put("password", "123password123");

		Assert.assertEquals("Expected JSONs to match", expectedJson,testJson);
	}

	@Test
	public void shouldPackageCurrentUser(){
		User testUser = new User(
			new ObjectId("60940e89c75e100444348e67"),
			"Admin",
			"123password123",
			UserRoles.MANAGER
		);
		testApp.setCurrentUser(testUser);
		JSONObject testJson = testApp.packageCurrentUser();
		JSONObject jsonToSend = new JSONObject();
		jsonToSend.put("_id", new ObjectId("60940e89c75e100444348e67").toString() );
		jsonToSend.put("username", "Admin");
		jsonToSend.put("password", "123password123");
		jsonToSend.put("role", UserRoles.MANAGER.toString());
		jsonToSend.put("loginStatus", true);
		Assert.assertEquals("Espected matching JSONs", jsonToSend, testJson);
	}

	@Test
	public void shouldPackageFailedLogin (){
		JSONObject jsonToSend = new JSONObject();
		jsonToSend.put("loginStatus", false);
		JSONObject testJson = testApp.packageFailedLogin();
		Assert.assertEquals("Expected matching Jsons", jsonToSend,testJson);
	}


	@Test
	public void shouldRun(){
		this.testApp.run();
	}
}
