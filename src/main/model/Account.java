package model;

public class Account {
    int bal;
    String accountOwnerName;
    String accountId;
    int previousTrans;

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

}
