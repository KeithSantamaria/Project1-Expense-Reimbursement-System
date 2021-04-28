package com.revature.project0.batch412.keithsantamaria.screens.employee;

import com.revature.project0.batch412.keithsantamaria.screens.Screen;

public class CreateNewAccountScreen extends Screen {

	public CreateNewAccountScreen() {
		this.title = "Create a new account";
		this.contents.add("Enter the type(checking/savings), name, and initial amount.");
		this.contents.add("Enter \"back\" to go back.");
	}
}
