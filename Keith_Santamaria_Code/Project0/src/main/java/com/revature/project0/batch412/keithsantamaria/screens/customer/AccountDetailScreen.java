package com.revature.project0.batch412.keithsantamaria.screens.customer;

import com.revature.project0.batch412.keithsantamaria.business.accounts.CheckingsSavingsAccount;
import com.revature.project0.batch412.keithsantamaria.screens.Screen;

public class AccountDetailScreen extends Screen {

	CheckingsSavingsAccount myAccount;

	public AccountDetailScreen(CheckingsSavingsAccount myAccount){
		this.myAccount = myAccount;
		this.title = "Here are the details for account " + this.myAccount.getName() + ":";
		this.contents.add("Id: " + this.myAccount.get_id());
		this.contents.add("Type: " + this.myAccount.getType());
		this.contents.add("Amount: " + this.myAccount.getAmount());
		this.menuOptions.add("Options:");
		this.menuOptions.add("1. Deposit");
		this.menuOptions.add("2. Withdraw");
		this.menuOptions.add("3. Transfer");
		this.menuOptions.add("4. Back");
	}
}
