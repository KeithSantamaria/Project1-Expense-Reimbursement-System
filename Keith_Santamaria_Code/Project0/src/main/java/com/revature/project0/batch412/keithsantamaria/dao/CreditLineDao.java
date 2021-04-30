//package com.revature.project0.batch412.keithsantamaria.dao;
//
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoClients;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;
//import com.mongodb.client.model.Updates;
//import com.revature.project0.batch412.keithsantamaria.business.accounts.CreditLineStatuses;
//import jdk.nashorn.internal.parser.JSONParser;
//import org.bson.Document;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import static com.mongodb.client.model.Filters.*;
//import static com.mongodb.client.model.Updates.*;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//
//public class CreditLineDao implements IDao{
//	private MongoClient mongoClient;
//	private MongoDatabase database;
//	private MongoCollection<Document> collection;
//	private Document doc;
//	private List<Document> allDocs;
//
//	private String _id;
//	private String ownerID;
//	private double amount;
//	private String status;
//	private String reviewedByID;
//
//
//	public CreditLineDao(String connectionString, String databaseString, String collectionString  ){
//		this.mongoClient = MongoClients.create( connectionString);
//		this.database =  mongoClient.getDatabase(databaseString);
//		this.collection = database.getCollection(collectionString);
//
//		this._id = UUID.randomUUID().toString();
//		this.ownerID = null;
//		this.amount = 1500d;
//		this.status = CreditLineStatuses.PENDING.toString();
//		this.reviewedByID = null;
//		this.allDocs = new ArrayList<Document>();
//	}
//
//	public String get_id() {
//		return _id;
//	}
//
//	public void set_id(String _id) {
//		this._id = _id;
//	}
//
//	public List<Document> getAllDocs() {
//		return allDocs;
//	}
//
//	public double getAmount() {
//		return amount;
//	}
//
//	public void setAmount(double amount) {
//		this.amount = amount;
//	}
//
//	public String getStatus() {
//		return status;
//	}
//
//	public void setStatus(String status) {
//		this.status = status;
//	}
//
//	public String getReviewedByID() {
//		return reviewedByID;
//	}
//
//	public void setReviewedByID(String reviewedByID) {
//		this.reviewedByID = reviewedByID;
//	}
//
//	public String getOwnerID() {
//		return ownerID;
//	}
//
//	public void setOwnerID(String ownerID) {
//		this.ownerID = ownerID;
//	}
//
//	public MongoClient getMongoClient() {
//		return mongoClient;
//	}
//
//	public MongoDatabase getDatabase() {
//		return database;
//	}
//
//	public MongoCollection<Document> getCurrentCollection() {
//		return collection;
//	}
//
//	public void setCollection(String collectionString) {
//		this.collection = this.database.getCollection(collectionString);
//	}
//
//	public Document getDoc() {
//		return doc;
//	}
//
//	public void setDoc(Document doc) {
//		this.doc = doc;
//	}
//
//	public JSONObject bsonToJson () {
//		String bsonToString = this.doc.toJson();
//		JSONParser parser = new JSONParser();
//		JSONObject bsonToJsonObj = new JSONObject();
//
//		if ( bsonToString != null && !(bsonToString.isEmpty())){
//			try {
//				bsonToJsonObj = (JSONObject) parser.parse(bsonToString);
//			} catch ( org.json.simple.parser.ParseException e) {
//				e.printStackTrace();
//				return null;
//			}
//		}
//
//		return bsonToJsonObj;
//	}
//
//	private void generateNewCreditLineDoc () {
//		this.doc = new Document("_id", UUID.randomUUID().toString())
//				.append("Owner",this.ownerID)
//				.append("Amount", this.amount)
//				.append("Status", this.status)
//				.append("ReviewedBy", this.reviewedByID);
//	}
//
//	@Override
//	public void create() {
//		this.generateNewCreditLineDoc();
//		this.collection.insertOne(this.doc);
//	}
//
//	@Override
//	public void read() {
//		this.collection.find().forEach(doc -> allDocs.add(doc));
//
//	}
//
//	@Override
//	public void update() {
//		this.collection.updateOne(
//				eq("_id",this.doc.get("_id".toString())) ,
//				Updates.combine(
//						Updates.set("Status", this.status),
//						Updates.set("Amount", this.amount)
//				)
//		);
//	}
//
//	@Override
//	public void delete() {
//		this.collection.deleteOne(eq("_id",this.doc.get("_id".toString())));
//	}
//}
