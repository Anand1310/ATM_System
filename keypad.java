import java.util.*;

public class keypad {           //Works as KeyPad of ATM
    private Scanner in;         //Inputs all required data

    public keypad(){
        in = new Scanner(System.in);
    }

    public int getInputInt(){
        return in.nextInt();
    }

    public String getInput(){
        return in.next();
    }

    public double getInputDouble(){
        return in.nextDouble();
    }
}
