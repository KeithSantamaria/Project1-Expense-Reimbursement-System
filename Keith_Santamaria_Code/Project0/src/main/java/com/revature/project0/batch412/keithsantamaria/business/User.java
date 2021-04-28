package com.revature.project0.batch412.keithsantamaria.business;

import java.util.UUID;

public abstract class User {
	protected String _id;
	protected String username;
	protected String password;

	public User(String username, String password){
		this._id = UUID.randomUUID().toString();
		this.username = username;
		this.password = password;
	}

	public String get_id() {
		return _id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
}
