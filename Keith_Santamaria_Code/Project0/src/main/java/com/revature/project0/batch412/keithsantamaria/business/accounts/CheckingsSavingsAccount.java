package com.revature.project0.batch412.keithsantamaria.business.accounts;

public class CheckingsSavingsAccount extends Account{
	private String type;
	public CheckingsSavingsAccount(String ownerID, int amount, String name, String type){
		super(ownerID,amount,name);
		this.type = type;
	}

	public boolean deposit(int amount){
		if(amount < 0){
			System.out.println(amount + " is invalid amount to deposit");
			return false;
		}
		this.amount = this.amount + amount;
		return true;
	}
	public boolean withdraw(int amount){
		if(amount > this.getAmount()){
			System.out.println(amount + " is invalid amount to withdraw");
			return false;
		}
		if(amount < 0){
			System.out.println(amount + " is invalid amount to withdraw");
			return false;
		}

		this.amount = this.amount - amount;
		return true;
	}

	public String getType() {
		return type;
	}
}
