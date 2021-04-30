package com.revature.batch412.keithsantamaria.dao;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.ServerAddress;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.apache.logging.log4j.LogManager;
import org.bson.Document;
import java.util.Arrays;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;

public abstract class GenericDao implements IDao{

	protected MongoClient mongoClient;
	protected MongoDatabase database;
	protected MongoCollection<Document> collection;
	protected 	org.apache.logging.log4j.Logger rootLogger = LogManager.getRootLogger();

	public GenericDao(){
		//Should connect to localhost on port 27017 by default
		this.mongoClient = MongoClients.create();
		//if Db does not exist, this will create the db
		this.database = mongoClient.getDatabase("testdb");
	};

	public MongoCollection<Document> getCollection() {
		return collection;
	}

	public void setCollection(String collectionName) {
		this.collection = database.getCollection(collectionName);;
	}


}
