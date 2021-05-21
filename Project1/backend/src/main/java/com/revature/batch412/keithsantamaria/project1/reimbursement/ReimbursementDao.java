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
import java.util.Locale;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

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

	public ReimbursementDao(String databaseString){
		this.mongoClient = MongoClients.create();
		this.database = mongoClient.getDatabase(databaseString);
		this.collection = database.getCollection("reimbursements");
		this.currentReimbursements = new ArrayList<>();
	}

	public MongoClient getMongoClient() {
		return mongoClient;
	}

	public void setDatabase(MongoDatabase database) {
		this.database = database;
	}

	public MongoCollection<Document> getCollection() {
		return collection;
	}

	public List<Document> getCurrentReimbursements() {
		return currentReimbursements;
	}

	/**
	 * @name convertDocsToReimbursement
	 * @author Keith Santamaria
	 * @return
	 * takes the docs from db and makes into a usbale reimbursement for server
	 */
	public List<JSONObject> convertDocsToReimbursement(){
		List<JSONObject> reimbursements = new ArrayList<>();
		for (int i = 0; i < this.currentReimbursements.size(); i++) {
			String asString = this.currentReimbursements.get(i).toJson();
			JSONObject asJson = App.parseReceivedData(asString);
			reimbursements.add(asJson);
		}
		return reimbursements;
	}

	/**
	 * @name addReimbursement
	 * @author Keith Santamaria
	 * @return
	 * this writes a reimbursement to the db
	 * @param reimbursement
	 */
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

	/**
	 * @name readAllEmployee
	 * @author Keith Santamaria
	 * @param ownerId
	 * @param username
	 * @param statuses
	 * @return
	 * this will get all the reimbursements from an employee with a certain status
	 */
	public List<JSONObject> readAllEmployee( ObjectId ownerId, String username, ReimbursementStatuses statuses){
		this.currentReimbursements.clear();
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
					and(
						or(
							eq("currentStatus",ReimbursementStatuses.APPROVED.toString()),
							eq("currentStatus",ReimbursementStatuses.DENIED.toString())
						),
						eq("username",username)
					)

				)
			).forEach(doc -> this.currentReimbursements.add(doc));
		}
		List<JSONObject> requests = this.convertDocsToReimbursement();
		return requests;
	}

	/**
	 * @name readAll
	 * @author Keith Santamaria
	 * @param status
	 * @return
	 * This grabs every reimbursement from the db that contains a certain status
	 */
	public List<JSONObject> readAll(ReimbursementStatuses status){
		this.currentReimbursements.clear();
		String message = "Fetching all " + status.toString().toLowerCase(Locale.ROOT) +  " Reimbursements";
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
			).forEach(doc -> this.currentReimbursements.add(doc));
		}
		List<JSONObject> requests = this.convertDocsToReimbursement();
		return requests;
	}

	/**
	 * @name updateStatus
	 * @author Keith Santamaria
	 * @param id
	 * @param status
	 * @param approvedBy
	 * @return
	 *
	 */
	public  JSONObject updateStatus(ObjectId id,ReimbursementStatuses status, String approvedBy){
		this.rootLogger.info("updating request: id- " + id.toString() + " to status - " + status.toString());
		this.currentReimbursements.clear();
		Document Reimbursement = this.collection.findOneAndUpdate(
			eq("_id",id),
			combine(
				set("currentStatus",status.toString()),
				set("approvedByName", approvedBy)
			));
		this.currentReimbursements.add(Reimbursement);
		List<JSONObject> requests = this.convertDocsToReimbursement();
		JSONObject result = requests.get(0);
		return result;
	}
//	public  List<JSONObject> readAllByEmployee(ObjectId employee){
//		this.currentReimbursements.clear();
//		String message = "Fetching all Reimbursements by employee " + employee.toString();
//		this.rootLogger.info(message);
//		this.collection.find(
//			eq("ownerId")
//		);
//	}
}
