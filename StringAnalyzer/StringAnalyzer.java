package stringanalyzer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringAnalyzer {
	
	String string;
	int words = 1;
	int numbers = 0;
	List<String> list = new ArrayList();
    
	public void getString() {
		System.out.println("Please input string: ");
		Scanner keyboard = new Scanner(System.in);
		string = keyboard.nextLine();
		this.analyzeString();
	}
	
	private void analyzeString() {
		if(words == 1 && numbers == 0) {
			regexHelper(string, "\\w+");
		}
		else if(words == 0 && numbers == 1) {
			regexHelper(string, "\\d+");
		}
		else if(words == 1 && numbers == 1) {
			regexHelper(string, "\\w+|\\d+");
		}
	}
	
	private void regexHelper(String string, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(string);
		while(matcher.find()) {
			list.add(matcher.group());
		}
	}
	
	public void print() {
		System.out.println(list);
	}
	
}
