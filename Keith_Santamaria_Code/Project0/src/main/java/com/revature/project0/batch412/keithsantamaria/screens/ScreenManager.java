package com.revature.project0.batch412.keithsantamaria.screens;

public class ScreenManager {
	private Screen currentScreen;

	public Screen getCurrentScreen() {
		return currentScreen;
	}

	public void setCurrentScreen(Screen currentScreen) {
		this.currentScreen = currentScreen;
	}
	public void runCurrentScreen(){
		System.out.println();
		System.out.println("---------------");
		System.out.println();
		this.currentScreen.show();
		System.out.println();
		System.out.println("---------------");

	};
}
