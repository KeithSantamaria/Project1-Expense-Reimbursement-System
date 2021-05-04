package com.revature.project0.batch412.keithsantamaria.business;

import com.revature.project0.batch412.keithsantamaria.business.accounts.CheckingsSavingsAccount;

public class Customer extends User{
	private int creditScore;
	private CheckingsSavingsAccount currentSelectedAccount;

	public Customer(String username, String password,int creditScore){
		super(username, password);
		this.creditScore = creditScore;
	}

	public CheckingsSavingsAccount getCurrentSelectedAccount() {
		return currentSelectedAccount;
	}

	public void setCurrentSelectedAccount(CheckingsSavingsAccount currentSelectedAccount) {
		this.currentSelectedAccount = currentSelectedAccount;
	}

	public int getCreditScore() {
		return creditScore;
	}
}
