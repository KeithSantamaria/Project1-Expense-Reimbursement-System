package com.revature.project0.batch412.keithsantamaria.input;

import java.util.Scanner;

public abstract class GenericScanner {
	protected Scanner scan;

	public GenericScanner(){
		this.scan = new Scanner(System.in);
	}
}
