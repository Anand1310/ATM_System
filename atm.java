import java.io.UnsupportedEncodingException;
import java.security.*;
import javax.crypto.*;

public class atm {

	static Account[] accounts;
	static double moneyInATM = 1200000.0;
	static String[] pins = new String[5];
	static MiniStatement[] statements;
    public static void main(String[] args) throws NoSuchPaddingException, UnsupportedEncodingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
		keypad in = new keypad();
		transactions t = new transactions();
		display dp = new display();
		Admin a = new Admin();
		MiniStatement ms = new MiniStatement();
		Encrypt_Decrypt ed = new Encrypt_Decrypt();
	    bankData();
	    pins();
	    miniStatements();
	    int account, pin;
	    ed.crypt(0);
	    ed.crypt(1);
	    ed.crypt(2);
	    ed.crypt(3);
	    ed.crypt(4);
	    while(true){
	         dp.displayWelcomeNote();		//Displays Welcome Note and ask for account no.
	         if(moneyInATM<=0.0){			//Check whether Atm has cash or not.
	         	dp.outOfCash();				//Display Out of cash
			 }
	         account = in.getInputInt();		//Inputs account number.
	         if(account == a.number){			//For admin Input 12345678
					moneyInATM = a.admin(moneyInATM);
					if(moneyInATM==-1){			//System Breakdown
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
						 if (ed.getDecryptPin(acc)==pin) {		//Check PIN
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
		accounts = new Account[5];
		accounts[0] = new Account(12345,"SBI0123", 38000.25, 43000.0);
		accounts[1] = new Account(23456, "BOI0234",345.75, 345.75);
		accounts[2] = new Account(34567,"SBI0345", 1234567.25, 1400000.0);
		accounts[3] = new Account(45678,"BOI0456", 5678.80, 90000.0);
		accounts[4] = new Account(56789,"SBI0567", 1230099.25, 1230099.25);
	}
	public static void miniStatements(){
    	statements = new MiniStatement[5];
    	statements[0] = new MiniStatement(10045, "credit", 1300.0);
		statements[1] = new MiniStatement(11156, "debit", 1100.0);
		statements[2] = new MiniStatement(10267, "credit", 3100.0);
		statements[3] = new MiniStatement(10378, "debit", 100.0);
		statements[4] = new MiniStatement(12489, "credit", 3000.0);
	}
	public static void pins(){
		pins[0] = "67890";
		pins[1] = "78901";
		pins[2] = "89012";
		pins[3] = "90123";
		pins[4] = "12345";
	}
	static int checkAccount(int account){		//Checks account number and return id
		for (int i=0;i<3;i++){
			if(accounts[i].getAccountNumber()==account) {
				return i;
			}
		}
		return -1;
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
