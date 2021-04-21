package com.revature.project0.batch412.keithsantamaria.screens;

import java.util.ArrayList;
import java.util.List;

public class WelcomeScreen extends Screen{

	public WelcomeScreen(){
		super();
		this.title = "Welcome!";
		this.contents.add("This is Keith Santamaria's bank app!");
		this.menuOptions.add("1. Customer Log in");
		this.menuOptions.add("2. Employee Log in");
		this.menuOptions.add("3. Exit");
	}

}
