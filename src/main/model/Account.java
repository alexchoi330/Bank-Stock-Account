package model;

public class Account {
    int bal;
    String accountOwnerName;
    int accountId;
    int previousTrans;


    //REQUIRES: account name is a non-zero length
    // EFFECTS: call Account method with name, balance and set name to accountOwnerName, set initialBal to actual bal
    //          unless initial bal is 0 or below (negative), initialbal should be the new bal
    public Account(String accountName, int initialBal) {
        accountOwnerName = accountName;
        if (initialBal > 0) {
            bal = initialBal;
        } else {
            bal = 0;
        }

    }

    // REQUIRES: inserted integer amount is not 0 and > 0
    // MODIFIES: this
    //  EFFECTS: deposits money into account bal, and set previoustransaction
    public void deposit(int amount) {
        if (amount > 0 && amount != 0) {
            bal = bal + amount;
            previousTrans = +amount;
        }
    }

    // REQUIRES: amount to be not 0 and greater than 0
    // MODIFIES: this
    //  EFFECTS:  withdraws money from bal and set previoustransaction
    public void withdraw(int amount) {
        if (amount > 0 && amount != 0) {
            bal = bal - amount;
            previousTrans = -amount;
        }
    }

    //EFFECT: gets previous transaction and displays it
    public void getPrevTransaction() {
        if (previousTrans > 0) {
            System.out.println("Previous amount deposited was " + previousTrans);
        } else if (previousTrans < 0) {
            System.out.println("Previous amount withdrawn was " + previousTrans);
        } else {
            System.out.println("There was no previous transaction!");
        }
    }

    public String getName() {
        return accountOwnerName;
    }

    public int getBal() {
        return bal;
    }

    public int getId() {
        return accountId;
    }

}
