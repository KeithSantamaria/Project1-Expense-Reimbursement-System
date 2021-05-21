package com.revature.project0.batch412.keithsantamaria.business;

import com.revature.project0.batch412.keithsantamaria.BankApp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CustomerLoginTests {
	BankApp test;
	@Before
	public void beforeEachTest(){
		test = new BankApp();
		test.getCustomers().add(new Customer("user","123", 750));
	}

	@Test
	public void	shouldLoginCustomer(){
		boolean didPass = test.loginAsCustomer("user","123", test.getCustomers());
		Assert.assertEquals("Should have returned true", true, didPass);
	}

	@Test
	public void shouldNotLoginCustomer(){
		boolean didPass = test.loginAsCustomer("notMe", "123", test.getCustomers());
		Assert.assertEquals("Should have been false", false, didPass);
	}
}

