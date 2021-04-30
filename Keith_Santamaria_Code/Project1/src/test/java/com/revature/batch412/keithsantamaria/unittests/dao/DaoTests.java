package com.revature.batch412.keithsantamaria.unittests.dao;

import com.revature.batch412.keithsantamaria.dao.ExampleDao;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
		testDao.create();
		long newSize = testDao.getCollection().countDocuments();
		long testDifference = newSize - this.collectionSize;
		Assert.assertEquals("Collection size difference did not match expected value.",1,testDifference);
	}

	@Test
	public void shouldUpdateOneDocument(){
		System.out.println("blah");
	}
}
