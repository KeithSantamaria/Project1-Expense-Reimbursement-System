package com.revature.batch412.keithsantamaria.project1.app;

import com.revature.batch412.keithsantamaria.project1.reimbursement.Reimbursement;
import com.revature.batch412.keithsantamaria.project1.reimbursement.ReimbursementDao;
import com.revature.batch412.keithsantamaria.project1.reimbursement.ReimbursementStatuses;
import com.revature.batch412.keithsantamaria.project1.users.User;
import com.revature.batch412.keithsantamaria.project1.users.UserDao;
import com.revature.batch412.keithsantamaria.project1.users.UserRoles;
import io.javalin.Javalin;
import org.apache.logging.log4j.LogManager;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.List;

public class App {
	protected org.apache.logging.log4j.Logger rootLogger = LogManager.getRootLogger();
	private User currentUser;

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public boolean login(String username, String password, UserRoles role){
		UserDao userDao = new UserDao();
		Document userDoc = userDao.read( username, password, role);
		if (userDoc != null){
			this.currentUser = userDao.convertDocToUser();
			return true;
		}
		return false;
	}

	public static JSONObject parseReceivedData(String body) {
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
		try {
			jsonObject = (JSONObject) parser.parse(body);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	public JSONObject packageCurrentUser(){
		rootLogger.info("Sending in user info, result : " +
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
		return jsonToSend;
	}

	public JSONObject packageFailedLogin(){
		JSONObject jsonToSend = new JSONObject();
		jsonToSend.put("loginStatus", false);
		return jsonToSend;
	}

	public Reimbursement packageNewReimbursement( JSONObject clientInput){
		String reason = clientInput.get("reason").toString();
		int amount = Integer.parseInt( clientInput.get("amount").toString() );
		String ownerId = clientInput.get("ownerId").toString();
		String username = clientInput.get("username").toString();
		Reimbursement newReim = new Reimbursement(
			new ObjectId(),
			new ObjectId(ownerId),
			username,
			reason,
			amount,
			ReimbursementStatuses.PENDING,
			""
		);
		return newReim;
	}

	public void run() {
		Javalin myApp = Javalin.create(config -> {
			config.enableCorsForAllOrigins();
		}).start(6969);

		myApp.post("/loginmanager", ctx -> {
			String body = ctx.body();
			JSONObject clientInput = this.parseReceivedData(body);
			String username = clientInput.get("username").toString();
			String password = clientInput.get("password").toString();
			boolean result = this.login(username,password, UserRoles.MANAGER);

			JSONObject jsonToSend;
			if(result){
				jsonToSend = this.packageCurrentUser();
			}
			else{
				jsonToSend = this.packageFailedLogin();
			}
			ctx.result(jsonToSend.toJSONString());
			this.currentUser = null;
		});

		myApp.post("/loginemployee", ctx -> {
			String body = ctx.body();
			JSONObject clientInput = this.parseReceivedData(body);
			String username = clientInput.get("username").toString();
			String password = clientInput.get("password").toString();
			boolean result = this.login(username,password, UserRoles.EMPLOYEE);
			JSONObject jsonToSend;
			if(result){
				jsonToSend = this.packageCurrentUser();
			}
			else{
				jsonToSend = this.packageFailedLogin();
			}
			ctx.result(jsonToSend.toJSONString());
			this.currentUser = null;
		});

		myApp.post("/createreimbursement", ctx -> {
			String body = ctx.body();
			JSONObject clientInput = this.parseReceivedData(body);
			Reimbursement newReim = this.packageNewReimbursement(clientInput);
			ReimbursementDao dao = new ReimbursementDao();
			dao.addReimbursement(newReim);
		});

		myApp.post("/viewemployeerequests", ctx -> {
			String body = ctx.body();
			rootLogger.info(body);
			JSONObject clientInput = this.parseReceivedData(body);
			String ownerId = clientInput.get("_id").toString();
			String username = clientInput.get("username").toString();
			ReimbursementDao dao = new ReimbursementDao();
			List<JSONObject> requests = dao.readAllEmployee(new ObjectId(ownerId), username, ReimbursementStatuses.PENDING);
			JSONObject list = new JSONObject();
			list.put("pendingRequests", requests);
			requests = dao.readAllEmployee(new ObjectId(ownerId), username, ReimbursementStatuses.APPROVED);
			this.rootLogger.info(requests);
			list.put("resolvedRequests", requests);
			ctx.result(list.toJSONString());
		});

		myApp.post("/viewall", ctx -> {
			ReimbursementDao dao = new ReimbursementDao();
			List<JSONObject> requests = dao.readAll(ReimbursementStatuses.PENDING);
			JSONObject list = new JSONObject();
			list.put("pendingRequests", requests);
			requests = dao.readAll(ReimbursementStatuses.APPROVED);
			list.put("resolvedRequests", requests);
			ctx.result(list.toJSONString());
		});

		myApp.post("/review", ctx -> {
			this.rootLogger.info(ctx.body());
			ReimbursementDao dao = new ReimbursementDao();
			String body = ctx.body();
			JSONObject clientInput = this.parseReceivedData(body);
			String _id = clientInput.get("_id").toString();
			String status = clientInput.get("currentStatus").toString();
			String approvedBy = clientInput.get("approvedByName").toString();
			if ( status.equals("APPROVE")){
				dao.updateStatus(new ObjectId(_id), ReimbursementStatuses.APPROVED, approvedBy);
			}
			if (status.equals("DENY")){
				dao.updateStatus(new ObjectId(_id), ReimbursementStatuses.DENIED,approvedBy);
			}
		});

//		myApp.post("/viewemployees", ctx -> {
//			UserDao dao = new UserDao();
//			dao.
//		});
	}
}
