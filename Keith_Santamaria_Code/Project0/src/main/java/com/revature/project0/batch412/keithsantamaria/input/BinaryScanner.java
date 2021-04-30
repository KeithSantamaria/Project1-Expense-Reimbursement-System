package com.revature.project0.batch412.keithsantamaria.input;

import java.util.Locale;

public class BinaryScanner extends GenericScanner implements IBinaryInput {

	private boolean isValidInput (String result, String choice1, String choice2) {
		if (
				result.toLowerCase(Locale.ROOT).equals(choice1) ||
				result.toLowerCase(Locale.ROOT).equals(choice2) ||
				result.toLowerCase(Locale.ROOT).equals("back")
		){
			return true;
		}
		return false;
	}

	@Override
	public String takeBinaryInput(String prompt, String choice1, String choice2) {
		System.out.print(prompt + ">");
		String result = scan.nextLine();
		while(!isValidInput(result, choice1, choice2)){
			System.out.print("Expecting \"" + choice1 + "\" or \"" + choice2 + "\">");
			result = scan.nextLine();
		}
		return result;
	}
}
