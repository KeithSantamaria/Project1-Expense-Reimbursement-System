package com.revature.batch412.keithsantamaria.project1.app;

import com.revature.batch412.keithsantamaria.project1.selectlogin.LoginState;
import com.revature.batch412.keithsantamaria.project1.users.User;
import com.revature.batch412.keithsantamaria.project1.users.UserDao;
import com.revature.batch412.keithsantamaria.project1.users.UserRoles;
import io.javalin.Javalin;
import org.apache.logging.log4j.LogManager;
import org.bson.Document;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class App {
	String selectedLogin;
	protected org.apache.logging.log4j.Logger rootLogger = LogManager.getRootLogger();
	User currentUser;

	public void App() {
		this.selectedLogin = LoginState.PENDING.toString();
	}

	public boolean login(String username, String password, UserRoles role){
		UserDao userDao = new UserDao();
		Document userDoc = userDao.read( username, password, role);
		if (userDoc != null){
			this.currentUser = userDao.convertDocToUser();
			return true;
		}
		return false;
	};

	public void run() {
		Javalin myApp = Javalin.create(config -> {
			config.enableCorsForAllOrigins();
		}).start(6969);

		myApp.post("/loginmanager", ctx -> {
			String body = ctx.body();
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) parser.parse(body);
			String username = jsonObject.get("username").toString();
			String password = jsonObject.get("password").toString();
			boolean result = this.login(username,password, UserRoles.MANAGER);

			if(result){
				rootLogger.info("Logging in as manager, result : " +
					"_id  " + this.currentUser.get_id() +
					"username " + this.currentUser.getUsername() +
					"password " + this.currentUser.getPassword() +
					"role " + this.currentUser.getRole()
				);

				JSONObject jsonToSend = new JSONObject();
				jsonToSend.put("_id", this.currentUser.get_id().toString() );
				jsonToSend.put("username", this.currentUser.getUsername());
				jsonToSend.put("password", this.currentUser.getPassword());
				jsonToSend.put("role", this.currentUser.getRole().toString());
				jsonToSend.put("loginStatus", true);
				ctx.result(jsonToSend.toJSONString());
			}
			else{
				JSONObject jsonToSend = new JSONObject();
				jsonToSend.put("loginStatus", false);
				ctx.result(jsonToSend.toJSONString());
			}
			this.currentUser = null;
		});

		myApp.post("/loginemployee", ctx -> {
			String body = ctx.body();
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) parser.parse(body);
			String username = jsonObject.get("username").toString();
			String password = jsonObject.get("password").toString();
			boolean result = this.login(username,password, UserRoles.EMPLOYEE);
			if(result){
				rootLogger.info("Logging in as manager, result : " +
					"_id  " + this.currentUser.get_id() +
					"username " + this.currentUser.getUsername() +
					"password " + this.currentUser.getPassword() +
					"role " + this.currentUser.getRole()
				);
				JSONObject jsonToSend = new JSONObject();
				jsonToSend.put("_id", this.currentUser.get_id().toString() );
				jsonToSend.put("username", this.currentUser.getUsername());
				jsonToSend.put("password", this.currentUser.getPassword());
				jsonToSend.put("role", this.currentUser.getRole().toString());
				jsonToSend.put("loginStatus", true);
				ctx.result(jsonToSend.toJSONString());
			}
			else{
				JSONObject jsonToSend = new JSONObject();
				jsonToSend.put("loginStatus", false);
				ctx.result(jsonToSend.toJSONString());
			}
			this.currentUser = null;
		});

	}
}
