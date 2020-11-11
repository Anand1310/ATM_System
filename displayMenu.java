public interface displayMenu {              //Works as screen of ATM
    void displayWelcomeNote();
    void outOfCash();
    void systemBreakdown();
    void loginFailed();
    void cardBlocked();
    void enterPIN();
    void invalidAccountNumber();
    void messages(String messages);
    void messages(String messages, int n);
    void invalidInput();
    void adminMenu();
    void transactionMenu();
    void displayCash(double cash);
    void enterAmount();
    void takeCash();
    void insufficientFund();
    void lessCashInATM();
    void cashDeposit();
    void depositInOtherAccountMenu();
}

class display implements displayMenu{           //Display all required messages and menu

    public void displayWelcomeNote(){
        System.out.println("*******************************************");
        System.out.println("Welcome to the SBI Bank Atm\nAll Cards are accepted here\n\nEnter Account Number");
    }

    public void outOfCash(){
        System.out.println("OUT OF CASH");
    }

    public void systemBreakdown(){
        System.out.println("System Breakdown");
    }

    public void loginFailed(){
        System.out.println("Login Failed\nInvalid Pin");
    }

    public void cardBlocked(){
        System.out.println("Card is temporarily blocked\nTo unblock contact the bank");
    }

    public void enterPIN(){
        System.out.println("Enter PIN");
    }

    public void invalidAccountNumber(){
        System.out.println("Invalid Account Number");
    }

    public void messages(String messages){
        System.out.println(messages);
    }

    public void messages(String messages, int n){
        System.out.print(messages);
    }

    public void invalidInput(){
        System.out.println("Invalid Input");
    }

    public void adminMenu(){
        System.out.println("Press 1: Display Total Cash in ATM\nPress 2: Put Cash in ATM\nPress 3: EXIT\nPress 4: System Lockdown");
    }

    public void transactionMenu(){
        System.out.println("Press 1: Balance Enquiry\nPress 2: Withdrawal\nPress 3: Deposit\nPress 4: Deposit in other account\nPress 5: Change Pin\nPress 6: Generate Mini Statements\nPress 7: EXIT");
    }

    public void displayCash(double cash){
        System.out.println("Rs " + cash);
    }

    public void enterAmount(){
        System.out.print("Enter amount (in multiple of Rs 100)\nRs ");
    }

    public void takeCash(){
        System.out.println("Your cash has been dispensed\nPlease take your cash now");
    }

    public void insufficientFund(){
        System.out.println("Insufficient fund in your account\nPlease choose a smaller amount");
    }

    public void lessCashInATM(){
        System.out.println("ATM is out of cash\nPlease choose a smaller amount");
    }

    public void cashDeposit(){
        System.out.println("Enter cash or signed check");
        System.out.println("Cash Deposited");
    }

    public void depositInOtherAccountMenu() {
        System.out.println("Press 1: Cash/Cheque\nPress 2: Through Account");
    }
}