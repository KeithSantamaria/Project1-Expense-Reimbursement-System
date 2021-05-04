package com.revature.batch412.keithsantamaria.dao;

import org.bson.Document;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

import java.util.Arrays;

public class ExampleDao extends GenericDao{

	private Document doc;

	public ExampleDao(){
		super();
		this.collection = database.getCollection("exampleCollection");

	}

	public Document getDoc() {
		return doc;
	}

	@Override
	public boolean create() {
		rootLogger.info("Creating a new example doc for mongoDb");
		this.doc = new Document("name", "MongoDB")
				.append("type", "database")
				.append("count", 1)
				.append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
				.append("info", new Document("x", 203).append("y", 102));
		try {
			this.collection.insertOne(this.doc);
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	@Override
	public Document read(ObjectId id) {
		this.rootLogger.info("Reading the example doc for mongoDb");
		this.doc = this.collection.find(eq("_id", id)).first();
		return this.doc;
	}

	@Override
	public boolean update(ObjectId id) {
		this.rootLogger.info("Updating example doc for mongoDb");
		try {
			this.collection.updateOne(
					eq("_id", id),
					set("count",1000)
			);
			return true;
		}catch (Exception e){
			return false;
		}

	}

	@Override
	public void delete(ObjectId id) {
		this.collection.deleteOne(
				eq("_id", id)
		);

		return;
	}
}
