package com.revature.project0.batch412.keithsantamaria.input;

import java.util.Scanner;

public class MenuScanner extends GenericScanner implements IMenuInput {

	private boolean isValidMenuInput(String checkedString, int numberOfOptions){
		Integer i;
		for ( i = 1 ; i <= numberOfOptions; i++ ){
			if(checkedString.equals(i.toString())){
				return true;
			}
		}
		return false;
	}

	@Override
	public String takeMenuInput(String prompt, int numberOfOptions) {
		System.out.print(prompt+">");
		String result = this.scan.nextLine();
		while (!isValidMenuInput(result,numberOfOptions)){
			System.out.print("Invalid input. Try again>");
			result = this.scan.nextLine();
		}
		return result;
	}
}
