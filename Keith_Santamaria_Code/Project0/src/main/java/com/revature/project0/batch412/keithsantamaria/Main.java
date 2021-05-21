package com.revature.project0.batch412.keithsantamaria;

import com.revature.project0.batch412.keithsantamaria.input.AlphaNumericScanner;
import com.revature.project0.batch412.keithsantamaria.input.CreditScoreScanner;
import com.revature.project0.batch412.keithsantamaria.input.MenuScanner;
import com.revature.project0.batch412.keithsantamaria.input.MoneyScanner;
import com.revature.project0.batch412.keithsantamaria.screens.*;
import com.revature.project0.batch412.keithsantamaria.screens.login.LoginAsCustomerScreen;
import com.revature.project0.batch412.keithsantamaria.screens.login.LoginAsEmployeeScreen;
import com.revature.project0.batch412.keithsantamaria.screens.login.WelcomeScreen;
import com.revature.project0.batch412.keithsantamaria.BankApp;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;


public class Main {

	public static void main(String[] args) {
		BankApp myApp = new BankApp();
		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.FATAL);
		BasicConfigurator.configure();
		myApp.run();

	}
}

//	one of my biggest grips with Java:
//
//		If I have a reference variable of type parent class and  I set it to an object of a child class, I cant use the child classes
//
//		My code looks so much more messy due to it

//		Scanner testScanner = new Scanner(System.in);
//		System.out.print("enter username >");
//		String inputTest = testScanner.nextLine();
//		System.out.println(inputTest);

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

//		System.out.println(UUID.randomUUID());
//		ScreenManager myManager = new ScreenManager();
//		myManager.setCurrentScreen(new WelcomeScreen());
//		myManager.runCurrentScreen();
//		myManager.setCurrentScreen(new LoginAsCustomerScreen());
//		myManager.runCurrentScreen();
//		myManager.setCurrentScreen(new LoginAsEmployeeScreen());
//		myManager.runCurrentScreen();

//	Integer i;
//	int numberOfOptions = 10;
//	String test = "5";
//		for ( i = 0 ; i <= numberOfOptions; i++ ){
//				System.out.println(i);
//				if (test.equals( i.toString())){
//				System.out.println("yoohoo");
//				}
//				}