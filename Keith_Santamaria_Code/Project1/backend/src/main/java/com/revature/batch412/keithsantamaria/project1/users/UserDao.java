package com.revature.batch412.keithsantamaria.project1.users;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.revature.batch412.keithsantamaria.dao.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.bson.Document;
import org.bson.types.ObjectId;
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

	public Document getUser() {
		return user;
	}

	public void setUser(Document user) {
		this.user = user;
	}

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

}
