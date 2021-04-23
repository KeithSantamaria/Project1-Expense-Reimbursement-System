package com.revature.project0.batch412.keithsantamaria;

import com.revature.project0.batch412.keithsantamaria.screens.*;
import com.revature.project0.batch412.keithsantamaria.screens.login.LoginAsCustomerScreen;
import com.revature.project0.batch412.keithsantamaria.screens.login.LoginAsEmployeeScreen;
import com.revature.project0.batch412.keithsantamaria.screens.login.WelcomeScreen;


public class Main {

	public static void main(String[] args) {

//		System.out.println(UUID.randomUUID());
		ScreenManager myManager = new ScreenManager();
		myManager.setCurrentScreen(new WelcomeScreen());
		myManager.runCurrentScreen();
		myManager.setCurrentScreen(new LoginAsCustomerScreen());
		myManager.runCurrentScreen();
		myManager.setCurrentScreen(new LoginAsEmployeeScreen());
		myManager.runCurrentScreen();


	}
}


//		MongoClient mongoClient = MongoClients.create("mongodb://localhost");
//		MongoDatabase database = mongoClient.getDatabase("paintstore");
//		MongoCollection<Document> collection = database.getCollection("paints");
//		Document doc = new Document( "name", "MongoDB")
//				.append("type", "database")
//				.append("count", 1)
//				.append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
//				.append("info", new Document("x", 203).append("y", 102));
//		collection.insertOne(doc);
//	Document doc = collection.find().first();
//	System.out.println(doc.toJson());