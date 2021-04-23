package com.revature.project0.batch412.keithsantamaria.screens.customer;

import com.revature.project0.batch412.keithsantamaria.screens.Screen;

public class AccountDetailScreen extends Screen {

	String accountName;

	public AccountDetailScreen(String accountName){
		this.accountName = accountName;
		this.title = "Here are the details for account  " + this.accountName + ":";
		this.menuOptions.add("Options:");
		this.menuOptions.add("1. Deposit");
		this.menuOptions.add("2. Withdraw");
		this.menuOptions.add("3. Transfer");
		this.menuOptions.add("4. Back");
	}
}
