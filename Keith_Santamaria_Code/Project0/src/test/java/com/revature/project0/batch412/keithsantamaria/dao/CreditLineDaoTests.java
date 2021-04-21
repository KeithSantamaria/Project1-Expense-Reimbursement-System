package com.revature.project0.batch412.keithsantamaria.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.revature.project0.batch412.keithsantamaria.business.CreditLineStatuses;
import org.bson.Document;
import org.json.simple.parser.JSONParser;
import org.junit.*;
import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import org.json.simple.JSONObject;

import java.util.UUID;

public class CreditLineDaoTests {
	CreditLineDao testLine;

	@Before
	public void beforeEachTest(){
		this.testLine = new CreditLineDao(
				"mongodb://localhost",
				"unittests",
				"paints"
		);
	}

	@Test
	public void shouldConvertToJson () {
		JSONObject expectedJson = new JSONObject();
		this.generateTestJson(expectedJson);

		this.testLine = new CreditLineDao(
				"mongodb://localhost",
				"unittests",
				"jsontest"
		);

		Document bson = this.testLine.getDatabase().getCollection("jsontest").find().first();

		this.testLine.setDoc(bson);
		JSONObject bsonToJson = this.testLine.bsonToJson();
		Assert.assertEquals("Bson to Json did not match expected json", expectedJson, bsonToJson);
	}

	@Test
	public void shouldCRUDCreditLineDocument () {
		this.testLine.setCollection("CRUDtest");

		String ownerID = UUID.randomUUID().toString();
		String reviewedById = UUID.randomUUID().toString();
		this.testLine.setOwnerID(ownerID);
		this.testLine.setReviewedByID(reviewedById);
		this.testLine.create();

		ownerID = UUID.randomUUID().toString();
		reviewedById = UUID.randomUUID().toString();
		this.testLine.setOwnerID(ownerID);
		this.testLine.setReviewedByID(reviewedById);
		this.testLine.create();

		System.out.println("shouldCRUDCreditLineDocument: Check db if creation of 2 docs was successful");


	}


	private void generateTestJson (JSONObject test){
		test.put("_id", "607fa3f3b038f592cba24c5d");
		test.put("Owner", "TestCustomer");
		test.put("Amount", new Double(1000.0d));
		test.put("Status", CreditLineStatuses.PENDING.toString());
		test.put("ReviewedBy", "TestEmployee");
	}

}
