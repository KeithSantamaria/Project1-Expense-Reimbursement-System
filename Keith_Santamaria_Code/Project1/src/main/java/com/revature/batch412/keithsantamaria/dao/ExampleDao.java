package com.revature.batch412.keithsantamaria.dao;

import org.bson.Document;
import java.util.Arrays;

public class ExampleDao extends GenericDao{

	private Document doc;

	public ExampleDao(){
		super();
		this.collection = database.getCollection("exampleCollection");
		this.doc = new Document("name", "MongoDB")
				.append("type", "database")
				.append("count", 1)
				.append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
				.append("info", new Document("x", 203).append("y", 102));
	}



	@Override
	public boolean create() {
		rootLogger.info("Creating a new example doc for mongoDb");
		try {
			this.collection.insertOne(this.doc);
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	@Override
	public boolean read() {
		return false;
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
