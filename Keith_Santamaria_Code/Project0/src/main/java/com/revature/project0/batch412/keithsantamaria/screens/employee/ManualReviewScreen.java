package com.revature.project0.batch412.keithsantamaria.screens.employee;

import com.revature.project0.batch412.keithsantamaria.screens.Screen;

public class ManualReviewScreen extends Screen {
	private String creditLineNumber;
	private String creditLineOwner;
	private int amount;
	private int creditScore;

	public ManualReviewScreen(String creditLineNumber, String creditLineOwner, int creditScore, int amount){
		this.creditLineNumber = creditLineNumber;
		this.creditLineOwner = creditLineOwner;
		this.creditScore = creditScore;
		this.amount = amount;
		this.title = "Manually reviewing credit line " + this.creditLineNumber;
		this.contents.add("Owner: " + this.creditLineOwner);
		this.contents.add("Amount: " + this.amount);
		this.contents.add("Credit Score: " + this. amount);
		this.menuOptions.add("OPTIONS: ");
		this.menuOptions.add("1. Approve");
		this.menuOptions.add("2. Deny");
		this.menuOptions.add("3. Back");
	}

}
