package projecteulerproblem4;

/*Ryan Lubin
 *April 17 2018
 *
 *https://projecteuler.net/problem=4
 *Problem 4
 *Largest Palindrome Product
 *
 *A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.
 *Find the largest palindrome made from the product of two 3-digit numbers.
 */

 
public class ProjectEulerProblem4 {

	public static void main(String[] args) {
		
		//first i want to start at the largest 3 digits numbers then multiply them by each other until i find the largest and second largest palindromes
		int bigPal = 0;
		for(int i = 999; i > 0; i--) {
			for(int j = 999; j > 0; j--) {//or j = i
				int k = i * j;
				int count = 0;
				if(isPalindrome(k) == true) {
					if(bigPal < k) {
						bigPal = k;
						count++;
					}
					if(count == 2) {
						break;
					}
				}
			}
		}
		System.out.println(bigPal);
		
	}
	
	public static boolean isPalindrome(int product) {
		String num = Integer.toString(product);
		int len = num.length();
		int list[] = new int[len];
		for(int i = 0; i < len; i++) {
			list[i] = num.charAt(i) - '0';
		}
		//is list symmetric? aka is it a palindrome
		for(int i = 0; i < len / 2; i++) {
			if(list[i] != list[len-i-1]) {
				return false;
			}
		}
		return true;
	}

}
