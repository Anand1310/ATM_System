public class Account {
    private int accountNumber;
    private int pin;
    private double availablebalance;
    private double totalbalance;

    public Account(){
        this.accountNumber = 0;
        this.pin = 0;
        this.availablebalance = 0.0;
        this.totalbalance = 0.0;
    }

    public Account(int account, int pin, double availablebalance, double totalbalance){
        this.accountNumber = account;
        this.pin = pin;
        this.availablebalance = availablebalance;
        this.totalbalance = totalbalance;
    }

    int getAccountNumber(){return accountNumber;}
    int getPin(){return pin;}
    double getAvailableBalance(){return availablebalance;}
    double getTotalBalance(){return totalbalance;}
    void credit(double amount){this.totalbalance += amount;}
    void debit(double amount){
        this.totalbalance -= amount;
        this.availablebalance -= amount;
    }
}
