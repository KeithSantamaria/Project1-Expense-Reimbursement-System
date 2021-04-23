package com.revature.project0.batch412.keithsantamaria.screens.employee;

import com.revature.project0.batch412.keithsantamaria.screens.Screen;

public class EmployeeHomeScreen extends Screen {
	private String name;
	public EmployeeHomeScreen(String name){
		this.name = name;
		this.title = "Welcome " + this.name + "!";
		this.menuOptions.add("1. Create new user");
		this.menuOptions.add("2. Review pending credit lines");
		this.menuOptions.add("3. Exit");
	}
}
