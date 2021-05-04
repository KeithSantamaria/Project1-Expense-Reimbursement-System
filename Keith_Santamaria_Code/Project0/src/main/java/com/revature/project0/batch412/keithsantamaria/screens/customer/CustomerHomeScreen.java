package com.revature.project0.batch412.keithsantamaria.screens.customer;

import com.revature.project0.batch412.keithsantamaria.screens.Screen;

public class CustomerHomeScreen extends Screen {

	private String customerName;

	public CustomerHomeScreen (String customerName){
		this.customerName = customerName;
		this.title = "Welcome " + this.customerName + "!";
		this.menuOptions.add("1. Choose account");
		this.menuOptions.add("2. Apply for new credit line");
		this.menuOptions.add("3. View current credit Lines");
		this.menuOptions.add("4. View transaction history");
		this.menuOptions.add("5. Exit and Log out");
	}

}
