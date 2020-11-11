public class Account {

    private final int accountNumber;
    private String ifsc;
    private double availablebalance;
    private double totalbalance;
    private String[] transacts = new String[3];
    private double[] money = new double[3];

    private int i;
    public Account(){
        this.accountNumber = 0;
        this.availablebalance = 0.0;
        this.totalbalance = 0.0;
        for(i=0;i<3;i++)
        {
            this.transacts[i] = null;
            this.money[i] = 0.0;
        }
    }

    public Account(int account,String ifsc, double availablebalance, double totalbalance, String transacts, double money){     //set account details
        this.accountNumber = account;
        this.ifsc = ifsc;
        this.availablebalance = availablebalance;
        this.totalbalance = totalbalance;
        this.transacts[2] = transacts;
        this.money[2] = money;
    }

    void setTransactsMoney(String transacts, double money){
        this.transacts[0] = this.transacts[1];
        this.transacts[1] = this.transacts[2];
        this.transacts[2] = transacts;
        this.money[0] = this.money[1];
        this.money[1] = this.money[2];
        this.money[2] = money;
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
    String[] getTransacts(){
        return transacts;
    }
    double[] getMoney(){
        return money;
    }
    int getAccount(){
        return accountNumber;
    }
}
