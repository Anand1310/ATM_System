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

    public Account(int account, int pin, double availablebalance, double totalbalance){     //set account details
        this.accountNumber = account;
        this.pin = pin;
        this.availablebalance = availablebalance;
        this.totalbalance = totalbalance;
    }

    int getAccountNumber(){return accountNumber;}       //return account number
    int getPin(){return pin;}                           //return pin
    double getAvailableBalance(){return availablebalance;}      //return available balance
    double getTotalBalance(){return totalbalance;}              //return total balance
    void credit(double amount){this.totalbalance += amount;}        //credit balance
    void debit(double amount){                                      //debit balance
        this.totalbalance -= amount;
        this.availablebalance -= amount;
    }
}
