package com.revature.batch412.keithsantamaria.project1.reimbursement;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.logging.log4j.LogManager;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

public class ReimbursementDao {
	private List<Reimbursement> currentReimbursements;

	protected MongoClient mongoClient;
	protected MongoDatabase database;
	protected MongoCollection<Document> collection;
	protected org.apache.logging.log4j.Logger rootLogger = LogManager.getRootLogger();


	public ReimbursementDao(){
		this.mongoClient = MongoClients.create();
		this.database = mongoClient.getDatabase("project1");
		this.collection = database.getCollection("reimbursements");
		this.currentReimbursements = new ArrayList<>();
	}

	public MongoCollection<Document> getCollection() {
		return collection;
	}

	public List<Reimbursement> getCurrentReimbursements() {
		return currentReimbursements;
	}

	public void addReimbursement(Reimbursement reimbursement) {
		Document doc;
		this.rootLogger.info("Writing new reimbursement " + reimbursement.get_id());
		doc = new Document("_id", reimbursement.get_id())
			.append("ownerId", reimbursement.getOwnerId())
			.append("username", reimbursement.getUsername())
			.append("currentStatus", reimbursement.getCurrentStatus().toString())
			.append("approvedByName", reimbursement.getApprovedByName())
			.append("reason", reimbursement.getReason())
			.append("amount", reimbursement.getAmount());
		this.collection.insertOne(doc);

	}

	public List<Document> read(){
		String message = "Fetching all reimbursements";
		return null;
	}
}
