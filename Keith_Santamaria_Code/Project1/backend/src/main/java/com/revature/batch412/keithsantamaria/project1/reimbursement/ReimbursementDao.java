package com.revature.batch412.keithsantamaria.project1.reimbursement;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.revature.batch412.keithsantamaria.project1.app.App;
import org.apache.logging.log4j.LogManager;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.*;

public class ReimbursementDao {
	private List<Document> currentReimbursements;

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

	public List<Document> getCurrentReimbursements() {
		return currentReimbursements;
	}

	public List<JSONObject> convertDocsToReimbursement(){
		List<JSONObject> reimbursements = new ArrayList<>();
		for (int i = 0; i < this.currentReimbursements.size(); i++) {
			String asString = this.currentReimbursements.get(i).toJson();
			JSONObject asJson = App.parseReceivedData(asString);
			reimbursements.add(asJson);
		}
		return reimbursements;
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

	public List<JSONObject> readAllEmployee( ObjectId ownerId, String username, ReimbursementStatuses statuses){
		String message = "Fetching all reimbursements for employee: " + ownerId.toString();
		this.rootLogger.info(message);
		if(statuses == ReimbursementStatuses.PENDING){
			this.collection.find(
				and(
					eq(
						"ownerId",ownerId),
					and(
						eq("username", username),
						eq("currentStatus", ReimbursementStatuses.PENDING.toString())
					)
				)
			).forEach(doc -> this.currentReimbursements.add(doc));
		}
		else{
			this.collection.find(
				and(
					eq("ownerId",ownerId),
					eq("username",username)
				)
			);
		}
		List<JSONObject> requests = this.convertDocsToReimbursement();
		return requests;
	}

	public List<JSONObject> readAll(ReimbursementStatuses status){
		String message = "Fetching all Reimbursements";
		this.rootLogger.info(message);
		if(status == ReimbursementStatuses.PENDING){
			this.collection.find(
				eq("currentStatus",ReimbursementStatuses.PENDING.toString())
			).forEach(doc -> this.currentReimbursements.add(doc));
		}
		else{
			this.collection.find(
				or(
					eq("currentStatus",ReimbursementStatuses.APPROVED.toString()),
					eq("currentStatus",ReimbursementStatuses.DENIED.toString())
				)
			);
		}
		List<JSONObject> requests = this.convertDocsToReimbursement();
		return requests;
	}
}
