package com.revature.project0.batch412.keithsantamaria;

import org.junit.*;

public class CreditLineTests {
	private CreditLine creditLine;

	@Before
	public void beforeEachTest(){
		creditLine = new CreditLine();
	}

	@Test
	public void shouldInitializeDefaultCreditLine (){
		int expectedAmount = 5000;
		CreditLineStatuses expectedStatus = CreditLineStatuses.DENIED;

		//TODO I need to still check if unique ID works correctly. I believe it will always be same length
		System.out.println("Testing if credit line ID is unique: " + creditLine.get_id());

		String message = "Credit Line amount did not match expected amount of " + 5000;
		Assert.assertEquals(message, expectedAmount, creditLine.getAmount());

		message = "Credit Line status did not match expected status of " + expectedStatus;
		Assert.assertEquals(message, expectedStatus, creditLine.getCurrentStatus());
	}
}
