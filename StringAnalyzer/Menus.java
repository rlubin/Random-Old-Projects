package stringanalyzer;

import java.util.Scanner;

public class Menus {
	
	StringAnalyzer stringAnalyzer = new StringAnalyzer();

	public void mainMenu() {
		System.out.println("\n---Menu---");
		System.out.println("1. Input String");
		System.out.println("2. Options");
		System.out.println("3. Exit");
		int result = getInput();
		if(result == 1) {
			stringAnalyzer.getString();
			stringAnalyzer.print();
		}
		else if(result == 2) {
			this.optionsMenu();
		}
		else if(result == 3) {
			System.exit(0);
		}
	}
	
	public void optionsMenu() {
		System.out.println("\n---Options---");
		System.out.println("1. Toggle Words : " + stringAnalyzer.words);
		System.out.println("2. Toggle Numbers : " + stringAnalyzer.numbers);
		System.out.println("3. Back");
		int result = getInput();
		if(result == 1) {
			if(stringAnalyzer.words == 0) {
				stringAnalyzer.words = 1;
			}
			else{
				stringAnalyzer.words = 0;
			}
			this.optionsMenu();
		}
		else if(result == 2) {
			if(stringAnalyzer.numbers == 0) {
				stringAnalyzer.numbers = 1;
			}
			else{
				stringAnalyzer.numbers = 0;
			}
			this.optionsMenu();
		}
		else if(result == 3) {
			this.mainMenu();
		}
	}
	
	private static int getInput() {
		Scanner keyboard = new Scanner(System.in);
		int result;
		do {
			System.out.println("User: ");
			result = keyboard.nextInt();
		} while(!(result >= 1 && result <= 3));
		return result;
	}
	
}
