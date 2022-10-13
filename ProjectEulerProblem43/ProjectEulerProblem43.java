/*Ryan Lubin
 *July 4 2018
 *
 *https://projecteuler.net/problem=43
 *Problem 43
 *Sub-string divisibility
 *
 *The number, 1406357289, is a 0 to 9 pandigital number because it is made up of each of the digits 0 to 9 in some order, but it also has a rather interesting sub-string divisibility property.
 *
 *Let d1 be the 1st digit, d2 be the 2nd digit, and so on. In this way, we note the following:
 *
 *d2d3d4=406 is divisible by 2
 *d3d4d5=063 is divisible by 3
 *d4d5d6=635 is divisible by 5
 *d5d6d7=357 is divisible by 7
 *d6d7d8=572 is divisible by 11
 *d7d8d9=728 is divisible by 13
 *d8d9d10=289 is divisible by 17
 *Find the sum of all 0 to 9 pandigital numbers with this property.
 */

package projecteulerproblem43;

import java.math.BigInteger;

public class ProjectEulerProblem43 {

	BigInteger total = new BigInteger("0");
	
    public static void main(String[] args) {
		
		ProjectEulerProblem43 thing = new ProjectEulerProblem43();
		String number = "9876543210";
		int n = number.length();
		thing.nextPermutation(number, 0, n-1);
		
	}
	
	//borrowed from https://www.geeksforgeeks.org/write-a-c-program-to-print-all-permutations-of-a-given-string/
	private void nextPermutation(String number, int start, int end) {
		if(start == end) {
			if(isDivisable(new BigInteger(number))) {//original
				total = total.add(new BigInteger(number));
				System.out.println("number=" + number);
				System.out.println("total=" + total);//end of original
			}
		}
		else {
			for(int i = start; i <= end; i++) {
				number = swap(number, start, i);
				nextPermutation(number, start+1, end);
				number = swap(number, start, i);
			}
		}
	}
	
	private static String swap(String string, int i, int j) {
		char temp;
		char[] charArray = string.toCharArray();
		temp = charArray[i];
		charArray[i] = charArray[j];
		charArray[j] = temp;
		return String.valueOf(charArray);
	}
	//end of borrowed code
	
	private static boolean isDivisable(BigInteger number) {
		String strNumber = number.toString();
		int[] divisors = {2,3,5,7,11,13,17};
		int divisor = 0;
		for(int i = 1; i <= 7; i++) {
			String substrNumber = strNumber.substring(i, i+3);
			int subintNumber = Integer.parseInt(substrNumber);
			if(subintNumber % divisors[divisor] != 0) {
				return false;
			}
			divisor++;
		}
		return true;
	}

}
