package model;

import persistence.Reader;
import persistence.Saveable;

import java.io.PrintWriter;

public class Account implements Saveable {
    private static int nextAccountId = 1;
    private double balance;
    private String name;
    private int id;
    private double previousTrans;


    //REQUIRES: account name is a non-zero length,initialBal >= 0
    // EFFECTS: call Account method with name, balance and set name to accountOwnerName, set initialBal to actual bal
    //          unless initial bal is 0 or below (negative), initialbal should be the new bal
    public Account(String accountName, double initialBal) {
        name = accountName;
        id = nextAccountId++;
        if (initialBal >= 0) {
            balance = initialBal;
        } else {
            balance = 0;
        }
    }

     //* REQUIRES: name has a non-zero length, balance >= 0
     //       * EFFECTS: constructs an account with id, name and balance;
     //* nextAccountId is the id of the next account to be constructed
     //* NOTE: this constructor is to be used only when constructing
     //* an account from data stored in file
    public Account(int nextId, int id, String name, double balance) {
        nextAccountId = nextId;
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    // REQUIRES: inserted integer amount is not 0 and > 0
    // MODIFIES: this
    //  EFFECTS: deposits money into account bal, and set previoustransaction

    public double deposit(double amount) {
        balance = balance + amount;
        previousTrans = +amount;
        return balance;
    }

    // REQUIRES: amount to be not 0 and greater than 0
    // MODIFIES: this
    //  EFFECTS:  withdraws money from bal and set previous transaction
    public double withdraw(double amount) {
        balance = balance - amount;
        previousTrans = amount;
        return balance;
    }

    //EFFECT: gets previous transaction and displays it
    public double getPrevTransaction() {
        return previousTrans;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public int getId() {
        return id;
    }

    /*
     * EFFECTS: returns a string representation of account
     */

    @Override
    public String toString() {
        String balanceStr = String.format("%.2f", balance);  // get balance to 2 decimal places as a string
        return "[ id = " + id + ", name = " + name + ", "
                + "balance = $" + balanceStr + "]";
    }

    @Override
    public void save(PrintWriter printWriter) {
        printWriter.print(nextAccountId);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(id);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(name);
        printWriter.print(Reader.DELIMITER);
        printWriter.println(balance);
    }


}
