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

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
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
	public void shouldCreateCreditLineDocument () {
		this.testLine.setCollection("createtest");

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

		System.out.println("shouldCreateCreditLineDocument: Check db if creation of 2 docs was successful");
	}

	@Test
	public void shouldReadAllCreditLines (){
		this.testLine.setCollection("readtest");

		List<Document> expectedDocs = new ArrayList<Document>();

		String expected1 = "f2351341-183d-4c76-b0ff-8e755bfc7a6f";
		Document expectedDoc1 = new Document("_id", expected1);
		String expected2 = "de813a0e-4e3e-4169-b27f-b670425567df";
		Document expectedDoc2 = new Document("_id", expected2);
		String expected3 = "fd06c578-93f5-43d3-a362-88a47fd352c8";
		Document expectedDoc3 = new Document( "_id", expected3);

		expectedDocs.add(expectedDoc1);
		expectedDocs.add(expectedDoc2);
		expectedDocs.add(expectedDoc3);

		this.testLine.read();
		Assert.assertEquals("read function did not match expected documents", expectedDocs, this.testLine.getAllDocs());
	}

	@Test
	public void shouldUpdateACreditLine(){
		this.testLine.setCollection("updatetest");
		this.testLine.setStatus(CreditLineStatuses.APPROVED.toString());
		this.testLine.set_id("UPDATE ME");
		Document updateTestDoc = new Document("_id", this.testLine.get_id())
				.append("Status", this.testLine.getStatus())
				.append("Amount", this.testLine.getAmount());

		this.testLine.setDoc(updateTestDoc);
		this.testLine.update();

		System.out.println("Check if Status changed to APPROVED and if Amount changed to default");
	}

	@Test
	public void shouldDeleteACreditLine(){
		this.testLine.setCollection("deletetest");
		this.testLine.set_id("DELETE ME");
		Document deleteTestDoc = new Document("_id", this.testLine.get_id());
		this.testLine.setDoc(deleteTestDoc);
		this.testLine.delete();
		System.out.println("Check if document is deleted");
	}

	private void generateTestJson (JSONObject test){
		test.put("_id", "607fa3f3b038f592cba24c5d");
		test.put("Owner", "TestCustomer");
		test.put("Amount", new Double(1000.0d));
		test.put("Status", CreditLineStatuses.PENDING.toString());
		test.put("ReviewedBy", "TestEmployee");
	}

}
