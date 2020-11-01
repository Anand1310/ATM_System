import java.util.*;
public class atm {
	static Account[] accounts;
	static double moneyInATM = 12000000.0;
    public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
		transactions t = new transactions();
		Admin a = new Admin();
	    bankData();
		Account accounts[];
	    int account, pin;
	    while(true){
	         System.out.println("*******************************************");
	         System.out.println("Welcome to the Bank Atm\nAll Cards are accepted here\n\nEnter Account Number");
	         if(moneyInATM<=0.0){
	         	System.out.println("OUT OF CASH");
			 }
	         account = sc.nextInt();
	         if(account == 12345678){
					moneyInATM = a.admin(moneyInATM);
					if(moneyInATM==-1){
						System.out.println("System Breakdown");
						break;
					}
			 }
	         else {
				 int acc = checkAccount(account);
				if (acc != -1) {
					 int tryLogin = 0;
					 while(true) {
						 System.out.println("Enter pin");
						 pin = sc.nextInt();
						 if (checkAccount(account, pin)) {
							 t.transacts(acc);
							 break;
						 }
						 else {
							 System.out.println("Login Failed\nInvalid Pin");
							 tryLogin++;
							 if (tryLogin >= 3) {
								 System.out.println("Card is temporarily blocked\nTo unblock contact the bank");
								 break;
							 }
						 }
					 }
				 }
				 else {
					 System.out.println("Invalid Account Number");
				 }
			 }
        }
    }

	public static void bankData() {
		accounts = new Account[3];
		accounts[0] = new Account(12345, 67890, 38000.25, 43000.0);
		accounts[1] = new Account(23456, 78901, 345.75, 345.75);
		accounts[2] = new Account(34567, 89012, 1234567.25, 1400000.0);
	}

	static int checkAccount(int account){
		for (int i=0;i<3;i++){
			if(accounts[i].getAccountNumber()==account) {
				return i;
			}
		}
		return -1;
	}

	static boolean checkAccount(int account, int pin){
		for (Account i: accounts){
			if(i.getAccountNumber()==account && i.getPin()==pin) {
				return true;
			}
		}
		return false;
	}

	public double getAvailableBalance(int acc) {
		double balance = accounts[acc].getAvailableBalance();
		return balance;
	}
	
	public double getTotalBalance(int acc) {
		double balance = accounts[acc].getTotalBalance();
		return balance;
	}
}
