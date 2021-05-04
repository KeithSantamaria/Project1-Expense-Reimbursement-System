package com.revature.project0.batch412.keithsantamaria.business.accounts;

import java.util.UUID;

public abstract class Account {
	protected String _id;
	protected String ownerID;
	protected String name;
	protected int amount;

	public Account(String ownerID, int amount, String name){
		this._id = UUID.randomUUID().toString();
		this.ownerID = ownerID;
		this.amount = amount;
		this.name = name;
	}

	public String get_id() {
		return _id;
	}

	public String getOwnerID() {
		return ownerID;
	}

	public String getName() {
		return name;
	}

	public int getAmount() {
		return amount;
	}
}
