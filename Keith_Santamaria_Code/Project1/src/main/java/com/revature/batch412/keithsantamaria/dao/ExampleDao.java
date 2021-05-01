package com.revature.batch412.keithsantamaria.dao;

import org.bson.Document;
import org.bson.types.ObjectId;
import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;

import java.util.Arrays;

public class ExampleDao extends GenericDao{

	private Document doc;

	public ExampleDao(){
		super();
		this.collection = database.getCollection("exampleCollection");

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
	public boolean update() {
		return false;
	}

	@Override
	public boolean delete() {
		return false;
	}
}
