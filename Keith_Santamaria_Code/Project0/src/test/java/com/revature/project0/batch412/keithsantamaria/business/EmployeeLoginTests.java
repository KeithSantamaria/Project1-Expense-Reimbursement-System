package com.revature.project0.batch412.keithsantamaria.business;

import com.revature.project0.batch412.keithsantamaria.BankApp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class EmployeeLoginTests {
	BankApp test;
	@Before
	public void beforeEachTest(){
		test = new BankApp();
		test.getEmployees().add(new Employee("user", "123") );
	}

	@Test
	public void	shouldLoginEmployee(){
		boolean didPass = test.loginAsEmployee("user","123", test.getEmployees());
		Assert.assertEquals("Should have returned true", true, didPass);
	}

	@Test
	public void shouldNotLoginEmployee(){
		boolean didPass = test.loginAsEmployee("notMe", "123", test.getEmployees());
		Assert.assertEquals("Should have been false", false, didPass);
	}
}
