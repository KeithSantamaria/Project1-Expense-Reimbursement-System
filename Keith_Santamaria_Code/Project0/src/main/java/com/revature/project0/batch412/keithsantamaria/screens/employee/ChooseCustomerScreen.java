package com.revature.project0.batch412.keithsantamaria.screens.employee;

import com.revature.project0.batch412.keithsantamaria.business.Customer;
import com.revature.project0.batch412.keithsantamaria.screens.Screen;

import java.util.List;

public class ChooseCustomerScreen extends Screen {
	List<Customer> customers;
	public ChooseCustomerScreen(List<Customer> customers){
		super();
		this.customers = customers;
		this.title = "Choose a customer to create an account for:";
		if (customers.size() == 0){
			this.contents.add("No customers to choose from");
		}
		for (int i = 0; i < customers.size(); i++) {
			this.menuOptions.add((i+1) + "." + customers.get(i).get_id() + " - " + customers.get(i).getUsername());
		}
		this.menuOptions.add(customers.size()+1 + ".Back");
	}
}
