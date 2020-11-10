public class MiniStatement extends transactions{
    private int account;
    private String[] transacts = new String[3];
    private double[] money = new double[3];
    int i;
    MiniStatement(){
        for(i=0;i<3;i++)
        {
            this.transacts[i] = null;
            this.money[i] = 0.0;
        }
    }
    MiniStatement(int account, String transacts, double money){
        this.account = account;
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
    String[] getTransacts(){
        return transacts;
    }
    double[] getMoney(){
        return money;
    }
    int getAccount(){
        return account;
    }
}
