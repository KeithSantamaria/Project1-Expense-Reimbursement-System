package com.revature.batch412.keithsantamaria.project1.reimbursement;

import org.bson.types.ObjectId;

public class Reimbursement {
	ObjectId _id;
	ObjectId ownerId;
	String username;
	String reason;
	int amount;
	ReimbursementStatuses currentStatus;
	String approvedByName;

	public Reimbursement(
		ObjectId _id, ObjectId ownerId, String username,
		String reason, int amount, ReimbursementStatuses currentStatus, String approvedByName
	) {
		this._id = _id;
		this.ownerId = ownerId;
		this.username = username;
		this.reason = reason;
		this.amount = amount;
		this.currentStatus = currentStatus;
		this.approvedByName = approvedByName;
	}

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public ObjectId getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(ObjectId ownerId) {
		this.ownerId = ownerId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public ReimbursementStatuses getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(ReimbursementStatuses currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getApprovedByName() {
		return approvedByName;
	}

	public void setApprovedByName(String approvedByName) {
		this.approvedByName = approvedByName;
	}
}
