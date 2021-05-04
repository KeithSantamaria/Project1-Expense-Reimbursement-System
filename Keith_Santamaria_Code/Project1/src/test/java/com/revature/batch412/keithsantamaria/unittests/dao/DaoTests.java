package com.revature.batch412.keithsantamaria.unittests.dao;

import com.revature.batch412.keithsantamaria.dao.ExampleDao;
import com.revature.batch412.keithsantamaria.dao.MongoField;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

public class DaoTests {
	ExampleDao testDao;
	long collectionSize;
	protected org.apache.logging.log4j.Logger rootLogger = LogManager.getRootLogger();

	@BeforeClass
	public static void beforeClass(){
		org.apache.logging.log4j.Logger rootLogger = LogManager.getRootLogger();
		rootLogger.info("Now in \"DaoUnitTests\"");
		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.FATAL);
		BasicConfigurator.configure();
	}

	@Before
	public void beforeEachTest(){
		this.testDao = new ExampleDao();
		this.collectionSize = testDao.getCollection().countDocuments();
	}

	@Test
	public void shouldCreateOneDocument(){
		this.rootLogger.info("Starting \"shouldCreateOneDocument()\" test");
		this.testDao.create();
		long newSize = testDao.getCollection().countDocuments();
		long testDifference = newSize - this.collectionSize;
		Assert.assertEquals("Collection size difference did not match expected value.",1,testDifference);
	}

	@Test
	public void shouldReadOneDocument(){
		this.rootLogger.info("Starting \"shouldCreateOneDocument()\" test");
		ObjectId id= new ObjectId("608b8513f17b1679fd68fc21");
		Document docToTest = this.testDao.read(id);
		Document expectedDoc = new Document("_id", id)
				.append("name", "MongoDB")
				.append("type", "database")
				.append("count", 1)
				.append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
				.append("info", new Document("x", 203).append("y", 102));
		Assert.assertEquals("Expected read doucment to match example", expectedDoc, docToTest);

	}

	@Test
	public void shouldUpdateOneDocument(){
		this.rootLogger.info("Starting \"shouldUpdateOneDocument()\" test");
		ObjectId id = new ObjectId("608dc77ce3d312bc74a39a46");
		this.testDao.update(id);
	}

	@Test
	public void shouldDeleteOneDocument(){

	}

}
