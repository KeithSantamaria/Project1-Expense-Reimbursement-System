package com.revature.project0.batch412.keithsantamaria.business;

import com.revature.project0.batch412.keithsantamaria.business.accounts.CreditLine;
import com.revature.project0.batch412.keithsantamaria.business.accounts.CreditLineStatuses;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CreditScoreTest {
	CreditLine myCredit;

	@Before
	public void beforeEachTest(){
		this.myCredit = new CreditLine("123",5000,"Credit1","Admin");
	}

	@Test
	public void shouldAutoApproveBest(){
		this.myCredit.autoApprove(751);
		Assert.assertEquals("Expected approved and low interest rate of 3", 3, this.myCredit.getInterestRate());
	}

	@Test
	public void shouldAutoApproveOK(){
		this.myCredit.autoApprove(651);
		Assert.assertEquals("Expected approved and a high interest rate of 6",6, this.myCredit.getInterestRate());
	}

	@Test
	public void shouldAutoDeny(){
		this.myCredit.autoApprove(349);
		Assert.assertEquals("Expected to deny score", CreditLineStatuses.DENIED.toString(), this.myCredit.getStatus());
	}
}
