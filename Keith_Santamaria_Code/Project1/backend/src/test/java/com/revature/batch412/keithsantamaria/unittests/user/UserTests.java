package com.revature.batch412.keithsantamaria.unittests.user;

import com.mongodb.MongoClientSettings;
import com.revature.batch412.keithsantamaria.project1.users.User;
import com.revature.batch412.keithsantamaria.project1.users.UserDao;
import com.revature.batch412.keithsantamaria.project1.users.UserRoles;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;



public class UserTests {
	UserDao testDao;
	protected org.apache.logging.log4j.Logger rootLogger = LogManager.getRootLogger();

	@BeforeClass
	public static void beforeAllTests(){
		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.FATAL);
		BasicConfigurator.configure();
	}

	@Before
	public void beforeEachTest(){
		testDao = new UserDao("unittest1");
	}

	@Test
	public void shouldQueryAUser(){
		rootLogger.info("Testing the querying of a user");
		Document myDoc = testDao.read("Admin","123password123", UserRoles.MANAGER);
		ObjectId id = new ObjectId("60940e89c75e100444348e67");
		Document expectedDoc = new Document("_id", id)
			.append("username","Admin")
			.append("password", "123password123")
			.append("role", "MANAGER");
		Assert.assertEquals("Document did not match", expectedDoc , myDoc);
	}

	@Test
	public void shouldConvertDocToUser(){
		ObjectId id = new ObjectId("60940e89c75e100444348e67");
		testDao.setUser(
			new Document("_id", id)
				.append("username","Admin")
				.append("password", "123password123")
				.append("role", "MANAGER")
		);
		User testUser = testDao.convertDocToUser();
		User expectedUser = new User(id,"Admin","123password123",UserRoles.MANAGER);
		boolean isEqualObjects = true;

		if ( testUser.get_id() != expectedUser.get_id()){
			System.out.println("Id is false");
			isEqualObjects = false;
		}
		if ( testUser.getUsername() != expectedUser.getUsername()){
			System.out.println("Username is false");
			isEqualObjects = false;
		}
		if ( testUser.getPassword() != expectedUser.getPassword()){
			System.out.println("Password is false");
			isEqualObjects = false;
		}
		if ( testUser.getRole() != expectedUser.getRole()){
			System.out.println("Role is false");
			isEqualObjects = false;
		}

		Assert.assertEquals("Users did not match", true, isEqualObjects);
	}

	@Test
	public void shouldReadAllUsers(){
		int expectSize = 2;
		JSONArray test = this.testDao.readAll();
		System.out.println(test);
		Assert.assertEquals("Expected 2 users", expectSize, test.size());

	}
}
