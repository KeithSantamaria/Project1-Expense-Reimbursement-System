package com.revature.project0.batch412.keithsantamaria.screens;

import com.revature.project0.batch412.keithsantamaria.screens.login.WelcomeScreen;
import org.junit.*;

public class ScreenTests {
	ScreenManager testManager;
	Screen welcomeScreen;

	@Before
	public void beforeEachTest(){
		testManager = new ScreenManager();
		 welcomeScreen = new WelcomeScreen();
	}

	@Test
	public void shouldLoadScreen(){
		testManager.setCurrentScreen(welcomeScreen);
		Assert.assertEquals("Screen did not load properly", welcomeScreen, testManager.getCurrentScreen());
	}
}
