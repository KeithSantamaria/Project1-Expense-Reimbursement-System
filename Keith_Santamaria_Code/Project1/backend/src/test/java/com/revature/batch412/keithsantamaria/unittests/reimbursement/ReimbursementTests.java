package com.revature.batch412.keithsantamaria.unittests.reimbursement;

import com.revature.batch412.keithsantamaria.project1.reimbursement.Reimbursement;
import com.revature.batch412.keithsantamaria.project1.reimbursement.ReimbursementDao;
import com.revature.batch412.keithsantamaria.project1.reimbursement.ReimbursementStatuses;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.bson.types.ObjectId;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class ReimbursementTests {
	Reimbursement test;
	ReimbursementDao testDao;
	long collectionSize;

	@BeforeClass
	public static void beforeAllTests(){
		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.FATAL);
		BasicConfigurator.configure();
	}

	@Before
	public void beforeEachTest() {
		test = new Reimbursement(
			new ObjectId(),
			new ObjectId("609496e7c75e100444348e68"),
			"BillyBatson",
			"Test",
			10,
			ReimbursementStatuses.PENDING,
			""
		);
		testDao = new ReimbursementDao();
		testDao.setDatabase(testDao.getMongoClient().getDatabase("unittest1"));
		collectionSize = testDao.getCollection().countDocuments();
	}

	@Test
	public void shouldWriteNewRequest(){
		testDao.addReimbursement(test);
		long newSize = testDao.getCollection().countDocuments();
		long testDifference = newSize - this.collectionSize;
		Assert.assertEquals("Collection size difference did not match expected value.",1,testDifference);
	}

	@Test
	public void shouldReadPendingEmployeeRequests(){
		int expectedSize = 3;
		List<JSONObject> testList = testDao.readAllEmployee(new ObjectId("609d3ea1bdc25278d20c9dd6"), "test", ReimbursementStatuses.PENDING);
		Assert.assertEquals("Expected 3 requests", expectedSize , testList.size());
	}

	@Test
	public void shouldReadResolvedEmployeeRequests(){
		int expectedSize =2;
		List<JSONObject> testList = testDao.readAllEmployee(new ObjectId("609d3ea1bdc25278d20c9dd6"), "test", ReimbursementStatuses.APPROVED);
		Assert.assertEquals("Expected 2 requests", expectedSize , testList.size());
	}
}
