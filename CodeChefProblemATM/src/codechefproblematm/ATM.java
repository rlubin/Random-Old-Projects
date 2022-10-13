package codechefproblematm;

public class ATM {
	
	private int withdrawal;
	private double accountBalance;
	private double withdrawalFee = 0.50;
	
	public String getAccountBalance() {
		return String.format("%.2f", accountBalance);
	}
	
	public void withdrawal() {
		Input input = new Input();
		input.setInput();
		withdrawal = input.getInt();
		accountBalance = input.getDouble();
		checkAccountBalance();
		checkWithdrawal();
		checkWithdrawalFee();
		if(accountBalance - withdrawal - withdrawalFee > 0) {
			accountBalance = accountBalance - withdrawal - withdrawalFee;
		}
	}
	
	private void checkWithdrawal() {
		double doubleWithDrawal = (double) withdrawal;
		if(withdrawal % 5 != 0 || withdrawal < 0 || withdrawal > 1999) {
			withdrawal = 0;
		}
	}
	
	private void checkAccountBalance() {
		if(accountBalance < 0.00 || accountBalance > 2000.00) {
			accountBalance = 0.00;
		}
	}
	
	private void checkWithdrawalFee() {
		if(withdrawal == 0) {
			withdrawalFee = 0.00;
		}
	}
	
}
