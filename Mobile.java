import javax.swing.*;
import java.awt.*;
public class Mobile extends atm{
    JFrame f;
    JTable t;
    void cashWithdrawn(double withdrawn, double charge, double availablebalance, double totalbalance){
        f = new JFrame();       //frame initialisation
        f.setTitle("CASH WITHDRAWN");       //frame title
        String[][] data = {
                {"Cash Withdrawn", ""+withdrawn},
                {"Processing Fees", ""+charge},
                {"Available Balance", ""+availablebalance},
                {"Total Balance", ""+totalbalance}
        };
        String[] column = {"", "Money"};

        t = new JTable(data, column);       //initialising the table
        t.setBounds(30,40,200,300);
        f.setLocation(600, 300);
        JScrollPane sp = new JScrollPane(t);        //adding to JScrollPane
        f.add(sp);
        f.setSize(500,200);
        f.setVisible(true);
    }

    void cashDeposited(double deposited, double charge, double availablebalance, double totalbalance){
        f = new JFrame();       //frame initialisation
        f.setTitle("CASH DEPOSITED");       //frame title
        String[][] data = {
                {"Cash Deposited", ""+deposited},
                {"Processing Fees", ""+charge},
                {"Available Balance", ""+availablebalance},
                {"Total Balance", ""+totalbalance}
        };
        String[] column = {"", "Money"};

        t = new JTable(data, column);       //initialising the table
        t.setBounds(30,40,200,300);
        f.setLocation(600, 300);
        JScrollPane sp = new JScrollPane(t);        //adding to JScrollPane
        f.add(sp);
        f.setSize(500,200);
        f.setVisible(true);
    }

    int otp(){
        int otp=generateOTP();
        f = new JFrame();
        f.setTitle("One Time-Password");
        JLabel l = new JLabel("OTP: " + otp);     // create a label
        JLabel m = new JLabel("Valid till 2 minutes");

        JPanel p = new JPanel();                // create a panel

        p.add(l);                               // add contents to panel
        p.add(m);

        f.add(p);

        f.setSize(110, 100);
        f.setLocation(600, 300);

        f.show();
        return otp;
    }
    int generateOTP(){
        String otp="";
        String x = "0123456789";
        int n = x.length();
        for(int i=0;i<4;i++)
        {
            otp += (x.charAt((int) ((Math.random()*10) % n)));
        }
        return Integer.parseInt(otp);
    }

    public void  miniStatements(int account) {
        String[] transacts = statements[account].getTransacts();
        double[] money = statements[account].getMoney();
        f = new JFrame();
        f.setTitle("Mini-Statements");
        JLabel l = new JLabel("<html>Account Number: " + statements[account].getAccount()+ "<br>Last 3 Transactions:</html>");
        JPanel p = new JPanel();
        p.add(l);
        String[][] data = {
                {transacts[0], "Rs " + money[0]},
                {transacts[1], "Rs " + money[1]},
                {transacts[2], "Rs " + money[2]}
        };
        String[] column = {"Credit/Debit", "Money"};
        t = new JTable(data, column);       //initialising the table
        p.add(t);
        f.add(p);
        f.setSize(250,300);
        f.setLocation(600, 300);
        f.setVisible(true);
    }
}
