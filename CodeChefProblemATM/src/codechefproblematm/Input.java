package codechefproblematm;

import java.util.Scanner;

public class Input {
	
	private int a;
	private double b;
	
	public void setInput() {
		String[] input;
		Scanner keyboard = new Scanner(System.in);
		input = keyboard.nextLine().split("\\s");
		a = Integer.parseInt(input[0]);
		b = Double.parseDouble(input[1]);
	}
	
	public int getInt() {
		return a;
	}
	
	public double getDouble() {
		return b;
	}
	
	/*
	public static void main(String[] args) {
		
		Input input = new Input();
		input.setInput();
		System.out.println("int = " + input.getInt());
		System.out.println("double = " + input.getDouble());
		
	}
	*/
	
}
