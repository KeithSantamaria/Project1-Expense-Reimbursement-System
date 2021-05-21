package com.revature.batch412.keithsantamaria.project1.users;

import org.bson.types.ObjectId;

public class User {
	private ObjectId _id;
	private String username;
	private String password;
	private UserRoles role;

	public User(ObjectId _id, String username, String password, UserRoles role) {
		this._id = _id;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRoles getRole() {
		return role;
	}

	public void setRole(UserRoles role) {
		this.role = role;
	}
}
