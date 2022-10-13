package projecteulerproblem10;

/*Ryan Lubim
 *April 28 2018
 *
 *https://projecteuler.net/problem=10
 *Summation of Primes
 *
 *The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
 *Find the sum of all the primes below two million.
 */

public class ProjectEulerProblem10 {

	public static void main(String[] args) {
	
		//must use a double because the number you get from summing the primes below two million is greater than what an int can hold
		double sum = 0;
		
		//1 is not a prime number
		for(int i = 2; i < 2000000; ++i) {
			if(isPrime(i)) {
				sum += i;
			}
		}
		
		System.out.println(sum);
	
	}
	
	public static boolean isPrime(int num) {
		for(int i = 2; i < num; ++i) {
			if(num % i == 0) {
				return false;
			}
		}
		return true;
	}

}
