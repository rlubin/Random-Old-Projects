package projecteulerproblem5;

/*Ryan Lubin
 *April 18 2018
 *
 *https://projecteuler.net/problem=5
 *Problem 5
 *Smallest Multiple
 *
 *2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
 *What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
 */

 
public class ProjectEulerProblem5 {

	public static void main(String[] args) {
		
		int num = 1000000000;
		for(int i = num; i > 0; i--) {
			if(isDivis(i) == true && i < num) {
				num = i;
			}
		}
		System.out.println(num);
	
	}
	
	
	public static boolean isDivis(int num) {
		for(int i = 20; i > 0; i--) {
			if(num % i != 0) {
				return false;
			}
		}
		return true;
	}
	
}
