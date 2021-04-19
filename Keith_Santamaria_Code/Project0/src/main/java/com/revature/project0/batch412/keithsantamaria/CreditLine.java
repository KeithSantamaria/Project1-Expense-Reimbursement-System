package com.revature.project0.batch412.keithsantamaria;

import java.util.UUID;

public class CreditLine {
	private String _id;
	private int amount;
	private CreditLineStatuses currentStatus;

	public String get_id() {
		return _id;
	}

	public int getAmount() {
		return amount;
	}

	public CreditLineStatuses getCurrentStatus() {
		return currentStatus;
	}

	public CreditLine (){
		this._id = UUID.randomUUID().toString();
		this.amount = 5000;
		this.currentStatus = CreditLineStatuses.DENIED;

	}



}
