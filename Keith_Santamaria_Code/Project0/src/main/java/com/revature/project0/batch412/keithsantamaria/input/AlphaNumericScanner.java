package com.revature.project0.batch412.keithsantamaria.input;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AlphaNumericScanner extends GenericScanner implements IAlphanumericInput{

	private boolean isAlphaNumeric(String input){
		if(input.matches("[a-zA-Z0-9]+")) {
			return true;
		}

		return false;
	}

	@Override
	public String takeAlphaNumericInput(String prompt) {
		System.out.print(prompt+">");
		String result = this.scan.nextLine();
		while( !isAlphaNumeric(result)){
			System.out.print("Invalid input. PLease only use alphanumeric characters>");
			result = this.scan.nextLine();
		}
		return result;
	}
}
