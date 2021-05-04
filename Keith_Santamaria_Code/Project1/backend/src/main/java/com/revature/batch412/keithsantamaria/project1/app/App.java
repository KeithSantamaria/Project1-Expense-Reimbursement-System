package com.revature.batch412.keithsantamaria.project1.app;

import com.revature.batch412.keithsantamaria.project1.selectlogin.LoginState;
import io.javalin.Javalin;
import org.apache.logging.log4j.LogManager;

public class App {
	String selectedLogin;
	protected 	org.apache.logging.log4j.Logger rootLogger = LogManager.getRootLogger();


	public void App(){
		this.selectedLogin = LoginState.PENDING.toString();
	}

	public void run(){
		Javalin myApp = Javalin.create(config -> {
			config.addStaticFiles("/ui");
		}).start(6969);

		myApp.post("/", ctx -> {
			this.selectedLogin = ctx.formParam("login-type");
			rootLogger.info("param: " + ctx.formParam("login-type"));
		});

	}
}
