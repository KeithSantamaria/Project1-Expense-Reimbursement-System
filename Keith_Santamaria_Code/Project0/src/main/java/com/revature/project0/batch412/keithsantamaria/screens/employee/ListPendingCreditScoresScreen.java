package com.revature.project0.batch412.keithsantamaria.screens.employee;

import com.revature.project0.batch412.keithsantamaria.business.accounts.CreditLine;
import com.revature.project0.batch412.keithsantamaria.screens.Screen;

import java.util.List;

public class ListPendingCreditScoresScreen extends Screen {
	private List<CreditLine> pendingLines;

	public ListPendingCreditScoresScreen(List<CreditLine> lines){
		this.pendingLines = lines;
		this.title = "All pending credit Lines for manual review";
		for (int i = 0; i < this.pendingLines.size(); i++) {
			this.menuOptions.add( i + 1 + ". Id: " + this.pendingLines.get(i).get_id()  +  "owner: " + this.pendingLines.get(i).getOwnerID() + "amount: " + this.pendingLines.get(i).getAmount());
		}
		this.menuOptions.add(this.pendingLines.size() + 1 + ". back");

	}
}
