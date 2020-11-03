public class atm {

	static Account[] accounts;
	static double moneyInATM = 1200000.0;

    public static void main(String[] args) {
		keypad in = new keypad();
		transactions t = new transactions();
		display dp = new display();
		Admin a = new Admin();
	    bankData();
		Account accounts[];
	    int account, pin;
	    while(true){
	         dp.displayWelcomeNote();		//Displays Welcome Note and ask for account no.
	         if(moneyInATM<=0.0){			//Check whether Atm has cash or not.
	         	dp.outOfCash();				//Display Out of cash
			 }
	         account = in.getInputInt();		//Inputs account number.
	         if(account == a.number){			//For admin Input 12345678
					moneyInATM = a.admin(moneyInATM);
					if(moneyInATM==-1){			//System Break-down
						dp.systemBreakdown();
						break;
					}
			 }
	         else {
				 int acc = checkAccount(account);		//checks account number and returns account id
				if (acc != -1) {
					 int tryLogin = 0;
					 while(true) {
						 dp.enterPIN();					//Input PIN
						 pin = in.getInputInt();
						 if (checkAccount(account, pin)) {		//Check PIN
							 t.transacts(acc);			//call Transactions
							 break;
						 }
						 else {
							 dp.loginFailed();		//Display Login Fail
							 tryLogin++;
							 if (tryLogin >= 3) {		//If login failed 3 times, card gets blocked
								 dp.cardBlocked();
								 break;
							 }
						 }
					 }
				 }
				 else {
					 dp.invalidAccountNumber();		//Displays Invalid Account Number
				 }
			 }
        }
    }

	public static void bankData() {		//Dummy accounts
		accounts = new Account[3];
		accounts[0] = new Account(12345, 67890, 38000.25, 43000.0);
		accounts[1] = new Account(23456, 78901, 345.75, 345.75);
		accounts[2] = new Account(34567, 89012, 1234567.25, 1400000.0);
	}

	static int checkAccount(int account){		//Checks account number and return id
		for (int i=0;i<3;i++){
			if(accounts[i].getAccountNumber()==account) {
				return i;
			}
		}
		return -1;
	}

	static boolean checkAccount(int account, int pin){		//Checks account number
		for (Account i: accounts){
			if(i.getAccountNumber()==account && i.getPin()==pin) {
				return true;
			}
		}
		return false;
	}

	public double getAvailableBalance(int acc) {		//get available balance
		double balance = accounts[acc].getAvailableBalance();
		return balance;
	}
	
	public double getTotalBalance(int acc) {		//get total balance
		double balance = accounts[acc].getTotalBalance();
		return balance;
	}
}
