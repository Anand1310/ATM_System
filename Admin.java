import java.util.Scanner;
public class Admin {
    Scanner sc = new Scanner(System.in);
    public double admin(double cash) {
        System.out.println("Hello Admin...");
        while(true){
            System.out.println("Press 1: Display Total Cash in ATM\nPress 2: Put Cash in ATM\nPress 3: EXIT\nPress 4: System Lockdown");
            switch(sc.nextInt()) {
                case 1:
                    System.out.println("Rs " + cash);
                    break;
                case 2:
                    cash = depositCash(cash);
                    break;
                case 3:
                    System.out.println("Bye Admin...");
                    return cash;
                case 4:
                    if(sc.next().equals("SHUT")){
                        return -1;
                    }
                default:
                    System.out.println("Invalid Input");
            }
        }
    }

    private double depositCash(double cash) {
        System.out.print("Put Cash\nRs ");
        cash += sc.nextDouble();
        return cash;
    }
}
