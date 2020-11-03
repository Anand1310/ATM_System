public class Admin {

    int number = 12345678;
    keypad in = new keypad();
    display dp = new display();

    public double admin(double cash) {          //run admin menu
        dp.messages("Hello Admin...");
        while(true){
            dp.adminMenu();
            switch(in.getInputInt()) {
                case 1:
                    dp.displayCash(cash);       //display atm cash
                    break;
                case 2:
                    cash = depositCash(cash);       //call depositCash()
                    break;
                case 3:
                    dp.messages("Bye Admin...");        //log out
                    return cash;
                case 4:
                    if(in.getInput().equals("SHUT")){       //Initiate break-down only be done if input == "SHUT"
                        return -1;
                    }
                default:
                    dp.invalidInput();
            }
        }
    }

    private double depositCash(double cash) {           //Deposits Cash
        dp.messages("Put Cash\nRs ");
        cash += in.getInputDouble();
        return cash;
    }
}
