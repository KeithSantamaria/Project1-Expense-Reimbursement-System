package com.revature.project0.batch412.keithsantamaria.screens.customer;

import com.revature.project0.batch412.keithsantamaria.business.Customer;
import com.revature.project0.batch412.keithsantamaria.business.accounts.CreditLine;
import com.revature.project0.batch412.keithsantamaria.screens.Screen;

import java.util.List;

public class ViewCustomerCreditLineScreen extends Screen {
	private  Customer myCustomer;
  private List<CreditLine> myCreditLines;
	public ViewCustomerCreditLineScreen(Customer myCustomer , List<CreditLine> creditLines){
		this.myCustomer = myCustomer;
		this.myCreditLines = creditLines;
		this.title = "Your Credit Scores";
		for (int i = 0; i < this.myCreditLines.size(); i++) {
			this.contents.add(this.myCreditLines.get(i).get_id() + " - " + this.myCreditLines.get(i).getName() + " - " + this.myCreditLines.get(i).getAmount() + " - " + this.myCreditLines.get(i).getStatus() );
		}
		this.menuOptions.add("1. back");

	}
}
