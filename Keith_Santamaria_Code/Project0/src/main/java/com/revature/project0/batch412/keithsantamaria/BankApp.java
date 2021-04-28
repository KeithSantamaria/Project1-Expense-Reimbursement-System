package com.revature.project0.batch412.keithsantamaria;

import com.revature.project0.batch412.keithsantamaria.business.Customer;
import com.revature.project0.batch412.keithsantamaria.business.Employee;
import com.revature.project0.batch412.keithsantamaria.business.User;
import com.revature.project0.batch412.keithsantamaria.business.accounts.CheckingsSavingsAccount;
import com.revature.project0.batch412.keithsantamaria.business.accounts.CreditLine;
import com.revature.project0.batch412.keithsantamaria.business.accounts.CreditLineStatuses;
import com.revature.project0.batch412.keithsantamaria.input.*;
import com.revature.project0.batch412.keithsantamaria.screens.ScreenManager;
import com.revature.project0.batch412.keithsantamaria.screens.customer.*;
import com.revature.project0.batch412.keithsantamaria.screens.employee.*;
import com.revature.project0.batch412.keithsantamaria.screens.login.LoginAsCustomerScreen;
import com.revature.project0.batch412.keithsantamaria.screens.login.LoginAsEmployeeScreen;
import com.revature.project0.batch412.keithsantamaria.screens.login.WelcomeScreen;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BankApp {
	ScreenManager currentScreen;
	User currentUser;
	Customer currentChosenCustomer;
	CheckingsSavingsAccount currentlySelectedAccount;

	AlphaNumericScanner alphaNumericScanner;
	BinaryScanner binaryScanner;
	CreditScoreScanner creditScoreScanner;
	MenuScanner menuScanner;
	MoneyScanner moneyScanner;

	List<Employee> employees;
	List<Customer> customers;
	List<CreditLine> creditLines;
	List<CheckingsSavingsAccount> otherAccounts;

	org.apache.logging.log4j.Logger rootLogger = LogManager.getRootLogger();
//		rootLogger.info("My message");

	/**
	 * Class Bankapp:
	 * @author Keith_Santamaria
	 *
	 * Purpose: Runs app, holds data in centralized location, and combines screen output, logic, and keyboard input
	 *
	 */
	public BankApp(){
		currentScreen = new ScreenManager();
		alphaNumericScanner= new AlphaNumericScanner();
		binaryScanner = new BinaryScanner();
		creditScoreScanner = new CreditScoreScanner();
		menuScanner = new MenuScanner();
		moneyScanner = new MoneyScanner();
		employees = new ArrayList<>();
		customers = new ArrayList<>();
		creditLines = new ArrayList<>();
		otherAccounts = new ArrayList<>();
		employees.add(new Employee("Admin","123Admin123"));
		customers.add(new Customer("Test","123", 750));
	}

	public User getCurrentUser() {
		return currentUser;
	}
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	public List<Employee> getEmployees() {
		return employees;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public List<CheckingsSavingsAccount> getOtherAccounts() {
		return otherAccounts;
	}

	/**
	 * @author Keith_Santamaria
	 * @param username -given username for login
	 * @param password - given password for password
	 * @param users - is a list of employees used to check against param 'username' and 'password'
	 * Purpose : using the 2 strings, it runs through the list if there is a match, it returns true and
	 *               it sets 'this.current user' to that object
	 */
	public boolean loginAsEmployee(String username, String password, List<Employee> users){
		rootLogger.info("Trying Logging in as Employee");
		String tempUsername;
		String tempPassword;
		for (int i = 0; i < users.size(); i++) {
			tempUsername = users.get(i).getUsername();
			tempPassword = users.get(i).getPassword();
			if (tempUsername.equals(username) && tempPassword.equals(password)){
				this.setCurrentUser(users.get(i));
				return true;
			}
		}
		return false;
	}

	/**
	 *
	 * @param username - given username for login
	 * @param password - given password for password
	 * @param users - is a list of customers used to check against param 'username' and 'password'
	 * Purpose: using the 2 strings, it runs throught the list if there is a match,  it returns true and
	 *              it sets 'this.current.user' to that object
	 * @return
	 */
	public boolean loginAsCustomer(String username, String password, List<Customer> users){
		rootLogger.info("Trying logging in as customer");
		String tempUsername;
		String tempPassword;
		for (int i = 0; i < users.size(); i++) {
			tempUsername = users.get(i).getUsername();
			tempPassword = users.get(i).getPassword();
			if (tempUsername.equals(username) && tempPassword.equals(password)){
				this.setCurrentUser(users.get(i));
				return true;
			}
		}
		return false;
	}

	/**
	 * @author Keith_Santamaria
	 * Purpose : This gives the logged in employee a list of all pending credit Lines. From there they can choose to
	 *              either approve, or deny the line. They can also go back
	 * @return
	 */
	private String runManualReview(){
		rootLogger.info("In Manual Review");
		List<CreditLine> linesForReview = new ArrayList<>();
		for (int i = 0; i < this.creditLines.size(); i++) {
			if(this.creditLines.get(i).getStatus().equals(CreditLineStatuses.PENDING.toString())){
				linesForReview.add(this.creditLines.get(i));
			}
		}
		this.currentScreen.setCurrentScreen(new ListPendingCreditScoresScreen(linesForReview));
		this.currentScreen.runCurrentScreen();
		String menuChoice = this.menuScanner.takeMenuInput("",linesForReview.size()+1);
		Integer choice = Integer.parseInt(menuChoice);
		if(choice == linesForReview.size()+1){
			return "EmployeeHome";
		}
		choice = choice -1;

		CreditLine selectedLine;
		Customer curr;
		if(choice < linesForReview.size()){
			selectedLine = linesForReview.get(choice);
			for (int i = 0; i < customers.size(); i++) {
				if( selectedLine.getOwnerID().equals(this.customers.get(i).get_id())){
					curr = this.customers.get(i);
					this.currentScreen.setCurrentScreen(new ManualReviewScreen(selectedLine, curr.getCreditScore()));
					this.currentScreen.runCurrentScreen();
					String approvalChoice = this.menuScanner.takeMenuInput("",3);
					switch (approvalChoice){
						case "1" :
							selectedLine.setStatus(CreditLineStatuses.APPROVED.toString());
							break;
						case "2":
							selectedLine.setStatus(CreditLineStatuses.DENIED.toString());
							break;
						case "3":
							return "ManualReview";
					}
				}
			}
		}

		return "EmployeeHome";
	}

	/**
	 * @author Keith_Santamaria
	 * Purpose: Prompts logged in customer for various fields for a new credit line.
	 * It also runs the auto approval process, which it can either approve, deny or stay at pending
	 * @return
	 */
	private String runApplyForCreditLine(){
		rootLogger.info("In apply for credit line");
		this.currentScreen.setCurrentScreen(new ApplyForNewCreditScreen());
		this.currentScreen.runCurrentScreen();
		String amount =this.moneyScanner.takeMoneyInput("Enter amount");
		if (amount.toLowerCase(Locale.ROOT).equals("back")) return "CustomerHome";
		Integer amountAsInt = Integer.parseInt(amount);
		System.out.println("amountAsInt = " + amountAsInt);
		String name = this.alphaNumericScanner.takeAlphaNumericInput("Enter a name for the credit line");
		if (name.toLowerCase(Locale.ROOT).equals("back")) return "CustomerHome";
		System.out.println("name = " + name);
		System.out.println("id = " + this.currentUser.get_id());
		CreditLine newLine = new CreditLine(this.currentUser.get_id(), amountAsInt, name, "pending");
		Customer curr = (Customer) this.currentUser;
		newLine.autoApprove(curr.getCreditScore());
		this.creditLines.add(newLine);
		return "CustomerHome";
	}

	/**
	 * @author Keith_Santamaria
	 *
	 * Purpose: Shows all credit lines that share an id with customer
	 * @return
	 */
	private String viewCustomerCreditLines(){
		rootLogger.info("In credit Line View");
		Customer me = (Customer) this.currentUser;
		List<CreditLine> myCreditLines = new ArrayList<>();
		for (int i = 0; i < this.creditLines.size(); i++) {
			if(me.get_id().equals(this.creditLines.get(i).getOwnerID())){
				myCreditLines.add(this.creditLines.get(i));
			}
		}

		this.currentScreen.setCurrentScreen(new ViewCustomerCreditLineScreen(me,myCreditLines));
		this.currentScreen.runCurrentScreen();

		while(true){
			this.menuScanner.takeMenuInput("",1);
			return "CustomerHome";
		}
	}

	/**
	 * @author Keith_Santamaria
	 *
	 * Purpose: Shows the chosen account details for the logged in customer.
	 * From here you can do various functions like deposit and withdrawing.
	 *
	 * @return
	 */
	private String runAccountDetails(){
		rootLogger.info("In account details");
		this.currentScreen.setCurrentScreen(new AccountDetailScreen(currentlySelectedAccount));
		this.currentScreen.runCurrentScreen();
		String menuChoice = this.menuScanner.takeMenuInput("",4);
		switch (menuChoice){
			case "1":
				System.out.println("Old amount " + this.currentlySelectedAccount.getAmount());
				boolean depositCheck = false;
				while(!depositCheck) {
					String amount = this.moneyScanner.takeMoneyInput("Enter amount to deposit");
					Integer amountAsInt = Integer.parseInt(amount);
					depositCheck = this.currentlySelectedAccount.deposit(amountAsInt);
				}
				System.out.println("New amount " + this.currentlySelectedAccount.getAmount());
				this.currentlySelectedAccount = null;
				break;
			case "2":
				System.out.println("Old amount " + this.currentlySelectedAccount.getAmount());
				boolean withdrawCheck = false;
				while (!withdrawCheck){
					String amount = this.moneyScanner.takeMoneyInput("Enter amount to withdraw");
					Integer amountAsInt = Integer.parseInt(amount);
					withdrawCheck = this.currentlySelectedAccount.withdraw(amountAsInt);
				}
				System.out.println("New amount " + this.currentlySelectedAccount.getAmount());
				this.currentlySelectedAccount = null;
				break;
			case "3":

				break;
			case "4":
				return "ChooseAccount";
		}

		return "CustomerHome";
	}


	/**
	 * @author Keith_Santamaria
	 *
	 * Purpose: Shows list of accounts that the user owns. From there the user can choose which account they want to view the details of
	 * @return
	 */
	private String runChooseAccount(){
		rootLogger.info("In choose account");
		List<CheckingsSavingsAccount> userAccounts = new ArrayList<>();
		for (int i = 0; i < this.otherAccounts.size(); i++) {
			if (this.otherAccounts.get(i).getOwnerID().equals(this.currentUser.get_id())) {
				userAccounts.add( this.otherAccounts.get(i));
			}
		}

		this.currentScreen.setCurrentScreen(new ChooseAccountScreen( userAccounts));
		this.currentScreen.runCurrentScreen();

		String menuChoice = menuScanner.takeMenuInput("", userAccounts.size() + 1);
		Integer choice = Integer.parseInt(menuChoice);
		if(choice == userAccounts.size()+1){
			return "CustomerHome";
		}
		choice = choice -1;

		if(choice < userAccounts.size()){
			this.currentlySelectedAccount = userAccounts.get(choice);
		}

		return "AccountDetails";
	}

	/**
	 * @author Keith_Santamaria
	 *
	 * Purpose: Lets the logged in employee create a new user of either type Employee or Customer
	 * @return
	 */
	private String runCreateAccount(){
		rootLogger.info("In Creating Account");
		this.currentScreen.setCurrentScreen(new CreateNewAccountScreen());
		this.currentScreen.runCurrentScreen();

		String typeOfAccount = binaryScanner.takeBinaryInput("Savings or checkings?","savings","checkings");
		if (typeOfAccount.equals("back")) return "ChooseAccount";

		String name = alphaNumericScanner.takeAlphaNumericInput("Enter name");
		if (name.equals("back")) return "ChooseAccount";

		String amount = moneyScanner.takeMoneyInput("Enter initial amount");
		if (amount.equals("back")) return "ChooseAccount";
		Integer amountInt = Integer.parseInt(amount);
		otherAccounts.add(new CheckingsSavingsAccount(currentChosenCustomer.get_id(), amountInt,name, typeOfAccount));
		currentChosenCustomer = null;
		return "EmployeeHome";

	}


	/**
	 * @author Keith_Santamaria
	 *
	 * Purpose: Supports create account, lets the user to choose an user to create an account for
	 * @return
	 */
	private String runChooseCustomer(){
		rootLogger.info("In Choose Customer");
		this.currentScreen.setCurrentScreen(new ChooseCustomerScreen(customers));
		this.currentScreen.runCurrentScreen();
		String menuChoice = this.menuScanner.takeMenuInput("",this.customers.size() + 1);
//		System.out.println("menuChoice " + menuChoice);
		Integer choice = Integer.parseInt(menuChoice);
		if(choice == this.customers.size() +1){
			return "EmployeeHome";
		}
		choice = choice - 1;
//		System.out.println("choice = " + choice);
		if(choice  < this.customers.size()){
			currentChosenCustomer = customers.get(choice);
			System.out.println(currentChosenCustomer.getUsername());

		}


		return "CreateAccount";
	}

	/**
	 * @author Keith_Santamaria
	 *
	 * Purpose: Customer home screen. User can choose what function they want from the menu
	 * @return
	 */
	private String runCustomerHome(){
		rootLogger.info("In customer home as " + this.currentUser.getUsername());
		this.currentScreen.setCurrentScreen(new CustomerHomeScreen(this.currentUser.getUsername()));
		this.currentScreen.runCurrentScreen();
		String menuChoice = this.menuScanner.takeMenuInput("",5);
		switch (menuChoice){
			case "1":
				return "ChooseAccount";
			case "2":
				return "ApplyForCreditLine";
			case "3":
				return "ViewCustomerCreditLines";
			case "4":
				return "TransactionHistory";
			case "5":
				setCurrentUser(null);
				System.out.println("Logging out");
				return "Welcome";
			default:
				return "ERROR";
		}

	}

	/**
	 * @author Keith_Santamaria
	 *
	 * Purpose: Lets an logged in employee create a new User
	 * @return
	 */
	private String runCreateUser(){
		rootLogger.info("In Create user");
		this.currentScreen.setCurrentScreen(new CreateANewUserScreen());
		this.currentScreen.runCurrentScreen();

		String typeOfUser = this.binaryScanner.takeBinaryInput("Enter if Employee or Customer?","employee", "customer");
		if(typeOfUser.toLowerCase(Locale.ROOT).equals("back")) return "EmployeeHome";
		String user = this.alphaNumericScanner.takeAlphaNumericInput("Please enter your username");
		if(user.toLowerCase(Locale.ROOT).equals("back")) return "EmployeeHome";
		String pass = this.alphaNumericScanner.takeAlphaNumericInput("Please enter your pass");
		if(pass.toLowerCase(Locale.ROOT).equals("back")) return "EmployeeHome";
		String creditScore;
		Integer parsedScore;
		if(typeOfUser.toLowerCase(Locale.ROOT).equals("customer")){
			creditScore = this.creditScoreScanner.takeCreditScoreInput("Please enter your credit score");
			if(creditScore.toLowerCase(Locale.ROOT).equals("back")) return "EmployeeHome";
			parsedScore = Integer.parseInt(creditScore);
			this.customers.add(new Customer(user,pass,parsedScore));
		}

		if (typeOfUser.equals("employee")){
			this.employees.add(new Employee(user,pass));
		}

		System.out.println("New user created");
		return "EmployeeHome";
	}

	/**
	 * Purpose: Employee home screen. Logged in user can choose a menu option
	 * @return
	 */
	private String runEmployeeHome(){
		rootLogger.info("In Employee home as " + this.currentUser.getUsername());
		this.currentScreen.setCurrentScreen(new EmployeeHomeScreen(this.currentUser.getUsername()));
		this.currentScreen.runCurrentScreen();
		String menuChoice = this.menuScanner.takeMenuInput("",4);
		switch (menuChoice){
			case "1":
				return "CreateUser";
			case "2":
				return "ChooseCustomer";
			case "3":
				return "ManualReview";
			case "4":
				setCurrentUser(null);
				System.out.println("Logging out");
				return "Welcome";
			default:
				return "ERROR";
		}
	}

	/**
	 * Purpose: Runs welcomes screen
	 * @return
	 */
	private String runWelcomeScreen(){
		rootLogger.info("In Welcome Screen");
		this.currentScreen.setCurrentScreen(new WelcomeScreen());
		this.currentScreen.runCurrentScreen();
		String menuChoice = this.menuScanner.takeMenuInput("", 3);
		switch (menuChoice){
			case "1":
				return "CustomerLogin";
			case "2":
				return "EmployeeLogin";
			case "3":
				return "Exit";
			default:
				return "ERROR";
		}
	}

	private String runLoginAsEmployee(){
		rootLogger.info("In Login Employee");
		this.currentScreen.setCurrentScreen(new LoginAsEmployeeScreen());
		this.currentScreen.runCurrentScreen();

		String user = this.alphaNumericScanner.takeAlphaNumericInput("Please enter your username");
		if(user.toLowerCase(Locale.ROOT).equals("back")) return "Welcome";
		String pass = this.alphaNumericScanner.takeAlphaNumericInput("Please enter your pass");
		if(pass.toLowerCase(Locale.ROOT).equals("back")) return "Welcome";

		if(this.loginAsEmployee(user,pass,employees)){
			return "EmployeeHome";
		}
		System.out.println("Login in failed. Try again");
		return "EmployeeLogin";
	}

	private String runLoginAsCustomer(){
		rootLogger.info("In Login Customer");
		this.currentScreen.setCurrentScreen(new LoginAsCustomerScreen());
		this.currentScreen.runCurrentScreen();
		String user = this.alphaNumericScanner.takeAlphaNumericInput("Please enter your username");
		if(user.toLowerCase(Locale.ROOT).equals("back")) return "Welcome";
		String pass = this.alphaNumericScanner.takeAlphaNumericInput("Please enter your pass");
		if(pass.toLowerCase(Locale.ROOT).equals("back")) return "Welcome";

		if(this.loginAsCustomer(user,pass,customers)){
			return "CustomerHome";
		}
		System.out.println("Login in failed. Try again");
		return "CustomerLogin";
	}

	public void run(){
		String menuNavigation = "Welcome";
		rootLogger.info("Starting app");
		while (true){
			switch (menuNavigation){
				case "AccountDetails":
					menuNavigation = runAccountDetails();
					break;
				case "ApplyForCreditLine":
					menuNavigation = runApplyForCreditLine();
					break;
				case "CreateAccount":
					menuNavigation = runCreateAccount();
					break;
				case "CreateUser":
					menuNavigation = runCreateUser();
					break;
				case "ChooseAccount":
					menuNavigation = runChooseAccount();
					break;
				case "ChooseCustomer":
					menuNavigation = runChooseCustomer();
					break;
				case "CustomerLogin":
					menuNavigation = runLoginAsCustomer();
					break;
				case "CustomerHome":
					menuNavigation = runCustomerHome();
					break;
				case "EmployeeLogin":
					menuNavigation = runLoginAsEmployee();
					break;
				case "EmployeeHome":
					menuNavigation = runEmployeeHome();
					break;
				case "ManualReview":
					menuNavigation = runManualReview();
					break;
				case "TransactionHistory":
					System.out.println("viewing transaction history");
					return;
				case "ViewCustomerCreditLines":
					menuNavigation = viewCustomerCreditLines();
					break;
				case "Welcome":
					menuNavigation =runWelcomeScreen();
					break;
				case "ERROR":
					System.out.println("There was an invalid input. Closing");
					return;
				case "Exit":
				return;
			}
		}

	}
}
