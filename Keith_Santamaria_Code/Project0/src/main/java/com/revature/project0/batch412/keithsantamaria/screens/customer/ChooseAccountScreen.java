package com.revature.project0.batch412.keithsantamaria.screens.customer;

import com.revature.project0.batch412.keithsantamaria.business.accounts.CheckingsSavingsAccount;
import com.revature.project0.batch412.keithsantamaria.screens.Screen;

import java.util.List;

public class ChooseAccountScreen extends Screen {

	private List <CheckingsSavingsAccount> myAccounts;
	public ChooseAccountScreen(List<CheckingsSavingsAccount> myAccounts){
		this.title = "Choose which account you wish to view:";
		this.myAccounts = myAccounts;
		if(myAccounts.size() == 0){
			this.contents.add("No Accounts found");
		}
		for (int i = 0; i < myAccounts.size() ; i++) {
			this.menuOptions.add((i+1) + "." + myAccounts.get(i).get_id() + " - " + myAccounts.get(i).getType() + " - "+ myAccounts.get(i).getName() + " - $" + myAccounts.get(i).getAmount());
		}
		this.menuOptions.add(myAccounts.size()+1 + ".Back");
	}
}
