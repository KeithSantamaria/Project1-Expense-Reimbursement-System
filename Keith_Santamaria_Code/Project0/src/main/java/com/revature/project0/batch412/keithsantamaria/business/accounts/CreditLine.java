package com.revature.project0.batch412.keithsantamaria.business.accounts;

public class CreditLine extends Account {
	private String status;
	private String reviewedByID;
	private int interestRate;

	public CreditLine(String ownerID,  int amount, String name, String reviewedByID ){
		super(ownerID, amount, name);
		this.status = CreditLineStatuses.PENDING.toString();
		this.reviewedByID = reviewedByID;
		this.interestRate = 0;

	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReviewedByID() {
		return reviewedByID;
	}

	public void setReviewedByID(String reviewedByID) {
		this.reviewedByID = reviewedByID;
	}

	public int getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(int interestRate) {
		if(interestRate < 100 && interestRate > 0){
			this.interestRate = interestRate;
		}
	}

	public void autoApprove(int creditScore){
		if(creditScore < 300 || creditScore > 850){
			return;
		}

		if(creditScore < 550){
			System.out.println("Sorry your score is too low");
			this.status = CreditLineStatuses.DENIED.toString();
			return;
		}
		if (creditScore > 650 && creditScore < 750){
			System.out.println("Auto approved for an interest rate of 6%");
			this.interestRate = 6;
			this.status = CreditLineStatuses.APPROVED.toString();
			return;
		}

		if (creditScore > 750 ){
			System.out.println("Auto approved for an interest rate of 3%");
			this.interestRate = 3;
			this.status= CreditLineStatuses.APPROVED.toString();
			return;
		}

		System.out.println("Needs to be manually approved");
	}

}
