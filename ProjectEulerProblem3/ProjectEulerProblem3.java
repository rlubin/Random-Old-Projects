package projecteulerproblem3;

/*Ryan Lubin
 *April 14 2018
 *
 *https://projecteuler.net/problem=3
 *Problem 3
 *Largest Prime Factor
 *
 *The prime factors of 13195 are 5, 7, 13 and 29.
 *What is the largest prime factor of the number 600851475143 ?
 */

public class ProjectEulerProblem3 {

	public static void main(String[] args) {

		double num = 600851475143.0;
		double prime = 1.0;

		for (double i = 2.0; i < num; i++) {
			if (num % i == 0.0) {
				if (isPrime(i)) {
					prime = i;
				}
			}
		}

		System.out.println(prime);

	}

	public static boolean isPrime(double num) {

		for (double i = 2.0; i < num; i++) {
			if (num % i == 0.0) {
				return false;
			}
		}

		return true;

	}

}
