import java.util.*;
public class transactions extends atm{
    Scanner sc = new Scanner(System.in);
    int account;
    void transacts(int acc){
        this.account = acc;
        menu();
    }

    private void menu() {
        while(true) {
            System.out.println("Press 1: Balance Enquiry\nPress 2: Withdrawal\nPress 3: Deposit\nPress 4: EXIT");
            switch (sc.nextInt()) {
                case 1:
                    balanceEnquiry(account);
                    break;
                case 2:
                    withdrawal(account);
                    break;
                case 3:
                    deposit(account);
                    break;
                case 4:
                    exit();
                    return;
                default:
                    System.out.println("Invalid Input");
            }
        }
    }

    private void exit() {
        System.out.println("Thank You!!!...");
    }

    public void balanceEnquiry(int acc) {
        System.out.println("Available Balance: Rs " + getAvailableBalance(acc));
        System.out.println("Total Balance: Rs " + getTotalBalance(acc));
    }

    public void withdrawal(int acc) {
        System.out.print("Enter amount (in multiple of Rs 100)\nRs ");
        double amnt = sc.nextDouble();
        if(amnt%100==0) {
            if (getAvailableBalance(acc) >= amnt && moneyInATM >= amnt) {
                accounts[acc].debit(amnt);
                moneyInATM -= amnt;
                System.out.println("Your cash has been dispensed\nPlease take your cash now");
                receipt(acc);
            } else if (getAvailableBalance(acc) < amnt) {
                System.out.println("Insufficient fund in your account\nPlease choose a smaller amount");
            } else {
                System.out.println("ATM is out of cash\nPlease choose a smaller amount");
            }
        }
        else{
            System.out.println("Invalid Input\nPlease enter amount in the multiple of Rs 100");
        }
    }

    public void deposit(int acc) {
        System.out.print("Enter amount (in multiple of Rs 100)\nRs ");
        double amnt = sc.nextDouble();
        if(amnt%100==0) {
            System.out.println("Enter cash or signed check");
            accounts[acc].credit(amnt);
            receipt(acc);
            System.out.println("Cash Deposited");
        }
        else{
            System.out.println("Invalid Input\nPlease enter amount in the multiple of Rs 100");
        }
    }

    private void receipt(int acc) {
        System.out.println("Want a receipt?(yes/no)");
        String ans = sc.next();
        if(ans.equals("yes")){
            balanceEnquiry(acc);
            System.out.println("After seeing it, tear it properly and throw it in nearby dustbin");
        }
    }
}