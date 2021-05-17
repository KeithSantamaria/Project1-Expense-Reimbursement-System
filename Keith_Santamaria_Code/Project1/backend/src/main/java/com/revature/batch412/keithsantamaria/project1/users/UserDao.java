package com.revature.batch412.keithsantamaria.project1.users;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.revature.batch412.keithsantamaria.project1.reimbursement.ReimbursementStatuses;
import org.apache.logging.log4j.LogManager;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

public class UserDao{
	Document user;

	protected MongoClient mongoClient;
	protected MongoDatabase database;
	protected MongoCollection<Document> collection;
	protected org.apache.logging.log4j.Logger rootLogger = LogManager.getRootLogger();

	public UserDao(){
		this.mongoClient = MongoClients.create();
		this.database = mongoClient.getDatabase("project1");
		this.collection = database.getCollection("users");
	}
	public UserDao(String databaseString){
		this.mongoClient = MongoClients.create();
		this.database = mongoClient.getDatabase(databaseString);
		this.collection = database.getCollection("users");
	}
	public MongoClient getMongoClient() {
		return mongoClient;
	}

	public void setDatabase(MongoDatabase database) {
		this.database = database;
	}

	public Document getUser() {
		return user;
	}

	public void setUser(Document user) {
		this.user = user;
	}

	/**
	 * @name convertDocToUser
	 * @author Keith Santamaria
	 * @return
	 * this returns takes a bson doc from the db to a user we can use in server
	 */
	public User convertDocToUser(){
		if(this.user == null){
			return null;
		}

		ObjectId _id = this.user.getObjectId("_id");
		String username = this.user.getString("username");
		String password = this.user.getString("password");
		String roleAsString = this.user.getString("role");
		UserRoles role = UserRoles.EMPLOYEE;
		if (roleAsString.equals("MANAGER")){
			role = UserRoles.MANAGER;
		}

		User convertedUser = new User(_id,username,password,role);

		return convertedUser;
	}


	/**
	 * @name read
	 * @author Keith Santamaria
	 * @param username
	 * @param password
	 * @param role
	 * @return
	 * this reads a single user
	 */
	public Document read(String username, String password, UserRoles role) {
		String message = "Fetching user: " + username + " with password: " + password;
		this.rootLogger.info(message);
		this.user = this.collection.find(
			and(
				eq("username",username),
				eq("password",password),
				eq("role", role.toString())
			)
		).first();
		return this.user;
	}

	/**
	 * @name readAll
	 * @author Keith santamaria
	 * @return
	 * This reads all users that exist
	 */
	public JSONArray readAll(){
		List<Document> docs = new ArrayList<>();
		this.rootLogger.info("Fetching all employees");
		this.collection.find(

		).forEach(doc -> docs.add(doc));
		JSONArray result = new JSONArray();
		docs.forEach(doc -> {
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = null;
			try {
				jsonObject = (JSONObject) parser.parse(doc.toJson());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			result.add(jsonObject);
		});
		return result;
	}
}
