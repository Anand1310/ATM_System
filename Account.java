public class Account {

    private final int accountNumber;
    private String ifsc;
    private double availablebalance;
    private double totalbalance;

    public Account(){
        this.accountNumber = 0;
        this.availablebalance = 0.0;
        this.totalbalance = 0.0;
    }

    public Account(int account,String ifsc, double availablebalance, double totalbalance){     //set account details
        this.accountNumber = account;
        this.ifsc = ifsc;
        this.availablebalance = availablebalance;
        this.totalbalance = totalbalance;
    }

    int getAccountNumber(){return accountNumber;}       //return account number
    String getIFSC(){return ifsc;}                      //return ifsc
    double getAvailableBalance(){return availablebalance;}      //return available balance
    double getTotalBalance(){return totalbalance;}              //return total balance
    void credit(double amount, double amnt){
        this.totalbalance += amount;
        this.availablebalance +=amnt;
    }
    void credit(double amount){this.totalbalance += amount;}        //credit balance
    void debit(double amount){                                      //debit balance
        this.totalbalance -= amount;
        this.availablebalance -= amount;
    }
}
