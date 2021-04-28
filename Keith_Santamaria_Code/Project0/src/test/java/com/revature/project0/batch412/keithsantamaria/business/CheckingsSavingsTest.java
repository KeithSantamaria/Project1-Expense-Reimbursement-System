package com.revature.project0.batch412.keithsantamaria.business;

import com.revature.project0.batch412.keithsantamaria.business.accounts.CheckingsSavingsAccount;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CheckingsSavingsTest {
	CheckingsSavingsAccount testAccount;

	@Before
	public void beforeEachTest(){
		testAccount = new CheckingsSavingsAccount("123",5000,"TestAccount","checkings");
	}

	@Test
	public void shouldDeposit(){
		int expectedAmount = 5500;
		boolean didDepositWork = this.testAccount.deposit(500);
		Assert.assertEquals("Deposit failed", true, didDepositWork);
		Assert.assertEquals("Expected an equal amount", expectedAmount , this.testAccount.getAmount());
	}

	@Test
	public void willNotDeposit(){
		System.out.println("Testing willNotDeposit()");
		boolean didDepositWork = this.testAccount.deposit(-500);
		Assert.assertEquals("Deposit should have failed", false, didDepositWork);
	}

	@Test
	public void shouldWithdraw(){
		int expectedAmount = 4500;
		boolean didWithdrawWork = this.testAccount.withdraw(500);
		Assert.assertEquals("Withdraw failed", true, didWithdrawWork);
		Assert.assertEquals("Expected an equal amount", expectedAmount , this.testAccount.getAmount());
	}

	@Test
	public void willNotWithdrawDueToNegative(){
		System.out.println("Testing willNotWithdrawDueToNegative()");
		boolean didWithdrawWork = this.testAccount.withdraw(-500);
		Assert.assertEquals("Withdraw should have Failed", false, didWithdrawWork);
	}

	@Test
	public void willNotWithdrawDueToBiggerBalance(){
		System.out.println("Testing willNotWithdrawDueToBiggerBalance()");
		boolean didWithdrawWork = this.testAccount.withdraw(5001);
		Assert.assertEquals("Withdraw should have Failed", false, didWithdrawWork);
	}
}
