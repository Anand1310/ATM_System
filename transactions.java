public class transactions extends atm{

    keypad in = new keypad();
    display dp = new display();
    int account;

    void transacts(int acc){
        this.account = acc;
        menu();
    }

    private void menu() {
        while(true) {
            dp.transactionMenu();           //Display Transaction Menu
            switch (in.getInputInt()) {     //run transaction menu
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
                    dp.invalidInput();
            }
        }
    }

    private void exit() {                                           //log out
        dp.messages("Thank You!!!...");
    }

    public void balanceEnquiry(int acc) {                              //Display Balance
        dp.messages("Available Balance: ", 1);
        dp.displayCash(getAvailableBalance(acc));
        dp.messages("Total Balance: ", 1);
        dp.displayCash(getTotalBalance(acc));
    }

    public void withdrawal(int acc) {                                   //Withdraws Cash
        dp.enterAmount();
        double amnt = in.getInputDouble();
        if(amnt%100==0) {                                               //Accepts cash only in multiple of Rs 100
            if (getAvailableBalance(acc) >= amnt && moneyInATM >= amnt) {       //check if balance is sufficient in account and ATM
                accounts[acc].debit(amnt);                               //debit money in both account and ATM
                moneyInATM -= amnt;
                dp.takeCash();
                receipt(acc);                                           //call receipt()
            }
            else if (getAvailableBalance(acc) < amnt) {       //If cash demanded is more than cah in his/her account
                dp.insufficientFund();
            }
            else {                                             //If ATM is out of Cash
                dp.lessCashInATM();
            }
        }
        else{
            dp.invalidInput();
        }
    }

    public void deposit(int acc) {                      //Deposits cash
        dp.enterAmount();
        double amnt = in.getInputDouble();
        if(amnt%100==0) {                               //Check if money to be deposited is multiple of Rs 100 or not
            dp.cashDeposit();
            accounts[acc].credit(amnt);                 //Credit money in account
            receipt(acc);                               //call receipt()
        }
        else{
            dp.invalidInput();
        }
    }

    private void receipt(int acc) {
        dp.messages("Want a receipt?(yes/no)");     //Ask for receipt
        String ans = in.getInput();
        if(ans.equals("yes")){
            balanceEnquiry(acc);                    //If yes, then shows both balances
            dp.messages("After seeing it, tear it properly and throw it in nearby dustbin");
        }
    }
}
