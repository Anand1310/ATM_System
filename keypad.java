import java.util.*;

public class keypad {           //Works as KeyPad of ATM
    private Scanner in;         //Inputs all required data
    private String y;
    public keypad(){
        in = new Scanner(System.in);
    }

    public int getInputInt(){
        int x = 0;
        try {
            x = in.nextInt();
        }catch (InputMismatchException e){
            y = in.next();
            System.out.println("Invalid Input: " + y);
        }finally {
            return x;
        }
    }

    public String getInput(){
        return in.next();
    }

    public double getInputDouble(){
        double x = 0;
        try {
            x = in.nextDouble();
        }catch (InputMismatchException e){
            y = in.next();
            System.out.println("Invalid Input: " + y);
        }finally {
            return x;
        }
    }

}
