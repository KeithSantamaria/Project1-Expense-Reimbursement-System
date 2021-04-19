package com.revature.project0.batch412.keithsantamaria;

import java.util.ArrayList;
import java.util.List;

//This class is the main application
public class Bank {

	private User currentlyLoggedInUser;
	private List<User> listOfCustomers = new ArrayList<User>();

	public User getCurrentlyLoggedInUser() {
		return currentlyLoggedInUser;
	}

	public List<User> getListOfCustomers() {
		return listOfCustomers;
	}

	private String evaluateCreditLineApplication (CreditLine line) {

		return "false";
	}

	public void setCurrentlyLoggedInUser(User currentlyLoggedInUser) {
		this.currentlyLoggedInUser = currentlyLoggedInUser;
	}

	public void addCustomer(String username, String password, int creditScore ){
		listOfCustomers.add( new Customer(username, password, creditScore));
	}

	public void login(String username, String password){
		User user;
		for( int i = 0; i < listOfCustomers.size(); i++){
			user = listOfCustomers.get(i);
			if( (username.equals(user.getUsername())) && (password.equals(user.password)) ){
				setCurrentlyLoggedInUser(user);
			}
		}
	}

	public void run(){
		System.out.println("Im Running!");
	}
}
