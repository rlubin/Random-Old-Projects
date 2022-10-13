/*Ryan Lubin
 *April 19 2018
 *
 *https://projecteuler.net/problem=7
 *Problem 7
 *10 001st Prime
 *
 *By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
 *What is the 10 001st prime number?
 */
 
#include <stdio.h>

#include <cstdlib>

using namespace std;
 
 static bool isPrime(int);
 
 int main(int argc, char** argv) {
 
		int prime = 0;
		int count = 0;
		for(int i = 1; count <= 10001; ++i) {
			if(isPrime(i)) {
				++count;
				prime = i;
			}
		}
		printf("%d/n", prime);
		
	return 0;
	
 }
 
 static bool isPrime(int num) {
	 for(int i = 2; i < num; ++i) {
		 if(num % i == 0) {
			 return false;
		 }
	 }
	 return true;
 }