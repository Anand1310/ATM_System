# ATM_System
**PM: Case Study 2 - To design an ATM System** <br>
    It has 5 public class and 1 interface. <br>
    Public class: atm, Account, Admin, transactions, keypad <br>
    Interface: displayMenu <br>
    **atm:** <br>
        1) It has main method. <br>
        2) It checks account number and pin and if correct then, call transactions <br>
        3) If account no. is correct and user inputs pin wrong 3 consecutive times, then the card gets blocked. <br>
        4) It has dummy account of users. <br>
        5) If ATM has no cash left then it shows "OUT OF CASH" but continue transactions as user can see money or deposit money in their account.<br>
    **Account:** <br>
        1) It stores account details of a person. <br>
    **Admin:** <br>
        1) It can only be called by putting account no. 12345678.
            Since, account number is of 5 digits, it can be used as special number. <br>
        2) It has admin options: <br>
            i) Display Cash in ATM <br>
            ii) Deposit Cash in ATM <br>
            iii) Log out <br>
            iv) Shut Down ATM. This can be done if only if user provide special code (here: "SHUT"). <br>
            v) Special code will not be asked.
    **transactions:** <br>
        1) It basically does all jobs of an ATM. <br>
            i) Display Cash in account <br>
                It will show available and total cash. <br>
            ii) Withdrawal of Cash <br>
                It will take cash to be withdrawn. Then it will check if account has sufficient balance
                (if not, then shows insufficient balance) and Atm has the sufficient cash or not (If not, it shows OutOfCash) <br>
            iii) Deposits <br>
                It accepts both cash and signed cheque. Amount is added in Total Cash and after checking it will be added in Available Cash. <br>
            iv) LogOut <br>
                It will show a thank you message <br>
        2) After withdrawal and deposits, it will ask whether receipt is required or not. If requires then it prints. <br>
    **keypad** <br>
        1) It works as a keypad of ATM. <br>
        2) It is responsible for all input. <br>
    **displayMenu** <br>
        1) It works as a screen of ATM. <br>
        2) It is responsible for displaying all messages. <br>