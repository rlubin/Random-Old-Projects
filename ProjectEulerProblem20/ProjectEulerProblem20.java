/*Ryan Lubin
 *July 4 2018
 *
 *https://projecteuler.net/problem=2
 *Problem 20
 *Factorial digit sum
 *
 *n! means n × (n − 1) × ... × 3 × 2 × 1
 *For example, 10! = 10 × 9 × ... × 3 × 2 × 1 = 3628800,
 *and the sum of the digits in the number 10! is 3 + 6 + 2 + 8 + 8 + 0 + 0 = 27.
 *Find the sum of the digits in the number 100!
 */

package projecteulerproblem20;

import java.math.BigInteger;
import java.util.*;

public class ProjectEulerProblem20 {

    public static void main(String[] args) {
        
		int num = 100;
		
		BigInteger factorial = factorial(num);
		List<Integer> list = splitIntoDigitList(factorial);
		int sum = sumOfDigits(list);
		System.out.println("\nThe sum of all the digits of !100 = " + sum);
    }
	
	private static BigInteger factorial(int num) {
		System.out.println("\n---factorial---");
		int count = num;
		BigInteger total = new BigInteger("1");
		while(count >= 2) {
			String number = Integer.toString(count);
			total = total.multiply(new BigInteger(number));
			System.out.println("Number=" + number + ", total=" + total);
			count--;
		}
		System.out.println("Total=" + total);
		return total;
	}
	
	private static List<Integer> splitIntoDigitList(BigInteger num) {
		System.out.println("\n---splitIntoDigitList---");
		List<Integer> list = new ArrayList();
		BigInteger number = num;
		BigInteger ten = new BigInteger("10");
		while(number.signum() != -1 && number.signum() != 0) {
			int remainder = number.remainder(ten).intValue();
			System.out.println(remainder);
			BigInteger newNumber = number.divide(ten);
			list.add(remainder);
			number = newNumber;
		}
		System.out.println(list);
		return list;
	}
	
	private static int sumOfDigits(List<Integer> list) {
		System.out.println("\n---sumOfDigits---");
		int total = 0;
		for(Integer number: list) {
			total = total + number;
			System.out.println("Number=" + number + ", total=" + total);
		}
		System.out.println("Total=" + total);
		return total;
	}
	
}
