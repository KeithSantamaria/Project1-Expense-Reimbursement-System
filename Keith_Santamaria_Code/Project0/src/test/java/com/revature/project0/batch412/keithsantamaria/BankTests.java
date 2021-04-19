package com.revature.project0.batch412.keithsantamaria;

import org.junit.*;

import java.util.List;

public class BankTests {
	private Bank bank;

	@Before
	public void beforeEachTest(){
		bank = new Bank();
		bank.addCustomer("Test1", "123", 600);
		bank.addCustomer("Test2", "123", 750);
		bank.addCustomer("Test3", "123", 500);
	}

	@Test
	public void shouldAddThreeCustomers(){
		int expectedSize = 3;
		List<User> users = bank.getListOfCustomers();
		int actualSize = users.size();
		String message = "Bank should have " +expectedSize + " customers";
		Assert.assertEquals(message,expectedSize,actualSize);
	}

	@Test
	public void shouldLoginUser(){
		bank.login("Test2", "123");
		List<User> users = bank.getListOfCustomers();
		User expectedUser = users.get(1);
		Assert.assertEquals("Expected matching user Ids", expectedUser.get_id(), bank.getCurrentlyLoggedInUser().get_id());
	}
}
