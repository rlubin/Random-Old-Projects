package codechefproblematm;

public class CodeChefProblemATM {

    public static void main(String[] args) {
		
		ATM atm = new ATM();
		System.out.println("Input:");
		atm.withdrawal();
		System.out.println("\nOutput:");
		System.out.println(atm.getAccountBalance());
		
    }
	
}
