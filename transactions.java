import java.io.UnsupportedEncodingException;
import java.security.*;
import javax.crypto.*;

public class transactions extends atm{

    keypad in = new keypad();
    display dp = new display();
    Mobile m = new Mobile();
    int account;

    void transacts(int acc) throws NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        this.account = acc;
        menu();
    }

    private void menu() throws NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException {
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
                    depositInOtherAccount(account);
                    break;
                case 5:
                    changePin(account);
                    break;
                case 6:
                    miniStatement(account);
                    break;
                case 7:
                    exit();
                    return;
                default:
                    dp.invalidInput();
            }
        }
    }

    private void miniStatement(int account) {
        String[] transacts = statements[account].getTransacts();
        double[] money = statements[account].getMoney();
        dp.messages("Account Number: " + statements[account].getAccount());
        dp.messages("Last 3 Transactions: ");
        for(int i=0;i<3;i++){
            System.out.println(transacts[i] + " " + money[i]);
        }
        m.miniStatements(account);
    }

    private void changePin(int account) throws NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        Encrypt_Decrypt ed = new Encrypt_Decrypt();
        int otp = m.otp();
        dp.messages("Enter otp sent to connected mobile");
        if(otp==in.getInputInt()) {
            dp.messages("Enter new PIN\nPIN length should be of 5 digits and all numeric");
            int pin = in.getInputInt();
            if(pin>9999 && pin<100000) {
                ed.changePin(account, pin);
                dp.messages("PIN changed successfully");
            }
            else {
                dp.invalidInput();
            }
        }
        else{
            dp.messages("Invalid OTP");
        }
    }

    private void depositInOtherAccount(int account) {
        dp.messages("Enter Account Number in which you want to deposit");
        int acc = in.getInputInt();
        dp.messages("Enter IFSC code");
        String ifsc = in.getInput();
        String getifsc = accounts[checkAccount(acc)].getIFSC();
        if((checkAccount(acc)!=-1) && (ifsc.equals(getifsc))  && (acc!=account)){        //Check once
            int otp = m.otp();
            dp.messages("Enter otp sent to connected mobile");
            if(otp==in.getInputInt()) {
                dp.depositInOtherAccountMenu();
                int x = in.getInputInt();
                if (x == 1) {                           //Deposit in other account via cash or cheque
                    dp.enterAmount();
                    double amnt = in.getInputDouble();
                    if (amnt % 100 == 0) {                               //Check if money to be deposited is multiple of Rs 100 or not
                        checkBank(accounts[account].getAccountNumber());
                        checkBank(ifsc, acc);
                        dp.cashDeposit();
                        accounts[checkAccount(acc)].credit(amnt);       //Credit money in other account
                        statements[checkAccount(acc)].setTransactsMoney("credit", amnt);
                    } else {
                        dp.invalidInput();
                    }
                } else if (x == 2) {                                //Transfer money from one account to another
                    dp.enterAmount();
                    double amnt = in.getInputDouble();
                    if (amnt % 100 == 0) {                               //Check if money to be deposited is multiple of Rs 100 or not
                        if (getAvailableBalance(account) >= amnt) {
                            checkBank(accounts[account].getAccountNumber());
                            checkBank(ifsc, acc);
                            dp.messages("Transaction Completed");
                            accounts[checkAccount(acc)].credit(amnt, amnt);                 //Credit money in account
                            statements[checkAccount(acc)].setTransactsMoney("credit", amnt);
                            accounts[account].debit(amnt);                              //Debit money in account
                            statements[account].setTransactsMoney("debit", amnt);
                        } else {
                            dp.insufficientFund();
                        }
                    } else {
                        dp.invalidInput();
                    }
                }
                else{
                    dp.invalidInput();
                }
            }
            else
            {
                dp.invalidInput();
            }
        }
        else{
            dp.messages("Invalid Account Number or IFSC code");
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
        double charge;
        double amnt = in.getInputDouble();
        int otp = m.otp();
        dp.messages("Enter otp sent to connected mobile");
        if(amnt%100==0 && otp==in.getInputInt()) {                               //Accepts cash only in multiple of Rs 100
            if (getAvailableBalance(acc) >= amnt && moneyInATM >= amnt) {       //check if balance is sufficient in account and ATM
                charge = checkBank(accounts[account].getAccountNumber());
                accounts[acc].debit(amnt);                               //debit money in both account and ATM
                moneyInATM -= amnt;
                dp.takeCash();
                m.cashWithdrawn(amnt,charge, getAvailableBalance(acc),getTotalBalance(acc));
                statements[account].setTransactsMoney("debit", amnt);
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
        int otp = m.otp();
        dp.messages("Enter otp sent to connected mobile");
        if(amnt%100==0 && otp==in.getInputInt()) {                            //Check if money to be deposited is multiple of Rs 100 or not
            double charge = checkBank(accounts[acc].getAccountNumber());
            dp.cashDeposit();
            accounts[acc].credit(amnt);                 //Credit money in account
            m.cashDeposited(amnt, charge, getAvailableBalance(acc),getTotalBalance(acc));
            statements[account].setTransactsMoney("credit", amnt);
            receipt(acc);                               //call receipt()
        }
        else{
            dp.invalidInput();
        }
    }

    private double checkBank(int acc) {
        String ifsc = accounts[checkAccount(acc)].getIFSC();
        String bank = ifsc.substring(0,3);
        double bankcharge = 0.0;
        if(!bank.equals("SBI")){
            bankcharge = 20.0;
            accounts[checkAccount(acc)].debit(bankcharge);
        }
        return bankcharge;
    }
    private double checkBank(String ifsc, int acc) {
        String bank = ifsc.substring(0,3);
        double bankcharge = 0.0;
        if(!bank.equals("SBI")){
            bankcharge = 20.0;
            accounts[checkAccount(acc)].debit(bankcharge);
        }
        return bankcharge;
    }
    private void receipt(int acc) {
        dp.messages("Want a receipt?(yes/no)");     //Ask for receipt
        String ans = in.getInput();
        if(ans.equals("yes")){
            String ifsc = accounts[account].getIFSC();
            String bank = ifsc.substring(0,3);
            if(bank.equals("SBI"))
            {
                dp.messages("Processing Fees: Rs 0.0");
            }
            else
            {
                dp.messages("Processing Fees: Rs 20.0");
            }
            balanceEnquiry(acc);                    //If yes, then shows both balances
            dp.messages("After seeing it, tear it properly and throw it in nearby dustbin");
        }
    }
}
