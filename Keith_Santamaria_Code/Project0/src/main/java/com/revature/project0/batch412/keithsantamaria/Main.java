package com.revature.project0.batch412.keithsantamaria;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.UUID;


public class Main {

	public static void main(String[] args) {

		System.out.println(UUID.randomUUID());

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