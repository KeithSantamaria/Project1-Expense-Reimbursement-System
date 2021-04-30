package com.revature.project0.batch412.keithsantamaria.screens.employee;

import com.revature.project0.batch412.keithsantamaria.business.accounts.CreditLine;
import com.revature.project0.batch412.keithsantamaria.screens.Screen;

public class ManualReviewScreen extends Screen {
	private String creditLineNumber;
	private String creditLineOwner;
	private int amount;
	private int creditScore;

	public ManualReviewScreen(CreditLine line, int creditScore){
		this.creditLineNumber = line.get_id();
		this.creditLineOwner = line.getOwnerID();
		this.creditScore = creditScore;
		this.amount = line.getAmount();
		this.title = "Manually reviewing credit line " + this.creditLineNumber;
		this.contents.add("Owner: " + this.creditLineOwner);
		this.contents.add("Amount: " + this.amount);
		this.contents.add("Credit Score: " + this.creditScore);
		this.menuOptions.add("OPTIONS: ");
		this.menuOptions.add("1. Approve");
		this.menuOptions.add("2. Deny");
		this.menuOptions.add("3. Back");
	}

}
