package com.revature.project0.batch412.keithsantamaria.input;

import java.util.Locale;

public class CreditScoreScanner extends GenericScanner implements ICreditScoreInput{

	private boolean isValidCreditScores(String input){
		boolean isValid = false;

		if(input.matches("[0-9]+")) {
			Integer inputAsInt = Integer.parseInt(input);
			if (inputAsInt >= 300 && inputAsInt <= 850 ){
				isValid = true;
			}
		}

		if(input.toLowerCase(Locale.ROOT).equals("back")){
			isValid = true;
		}

		return isValid;
	}

	@Override
	public String takeCreditScoreInput(String prompt) {
		System.out.print(prompt+">");
		String result = scan.nextLine();
		while(!isValidCreditScores(result)){
			System.out.print("Please enter a valid credit score (300-850)>");
			result = scan.nextLine();
		}
		return result;
	}
}
