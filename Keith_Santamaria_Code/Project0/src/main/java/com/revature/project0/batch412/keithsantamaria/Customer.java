package com.revature.project0.batch412.keithsantamaria;

import java.security.SecureRandom;
import java.util.UUID;

public class Customer extends User{
	private int creditScore;

	public Customer (String username, String password, int creditScore){
		this._id = UUID.randomUUID().toString();
		this.username = username;
		this.creditScore = creditScore;
		//TODO: get some form of hashing
		this.password = password;
	}

	public int getCreditScore() {
		return this.creditScore;
	}

}
