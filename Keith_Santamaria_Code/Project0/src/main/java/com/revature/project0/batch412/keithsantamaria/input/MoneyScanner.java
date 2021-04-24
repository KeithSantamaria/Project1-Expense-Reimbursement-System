package com.revature.project0.batch412.keithsantamaria.input;

public class MoneyScanner extends GenericScanner implements IMoneyInput {

	private boolean isValidNumber(String input){
		if(input.matches("[0-9]+")) {
			return true;
		}

		return false;
	}

	@Override
	public String takeMoneyInput(String prompt) {
		System.out.print(prompt+"> $");
		String result = scan.nextLine();
		while(!isValidNumber(result)){
			System.out.print("Please enter a valid positive whole number> $");
			result = scan.nextLine();
		}
		return result;
	}
}
