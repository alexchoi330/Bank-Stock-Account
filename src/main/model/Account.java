package model;

import exceptions.IllegalBalException;
import exceptions.IllegalNameException;
import persistence.Reader;
import persistence.Saveable;

import java.io.PrintWriter;

public class Account implements Saveable {
    private static int nextAccountId = 1;
    double bal;
    String accountOwnerName;
    int accountId;
    double previousTrans;


    //REQUIRES: account name is a non-zero length,initialBal >= 0
    // EFFECTS: call Account method with name, balance and set name to accountOwnerName, set initialBal to actual bal
    //          unless initial bal is 0 or below (negative), initialbal should be the new bal
    public Account(String accountName, double initialBal) throws IllegalNameException, IllegalBalException {
        accountOwnerName = accountName;
        accountId = nextAccountId++;
        if (initialBal > 0) {
            bal = initialBal;
        } else {
            bal = 0;
        }
    }

     //* REQUIRES: name has a non-zero length, balance >= 0
     //       * EFFECTS: constructs an account with id, name and balance;
     //* nextAccountId is the id of the next account to be constructed
     //* NOTE: this constructor is to be used only when constructing
     //* an account from data stored in file
    public Account(int nextId, int id, String name, double balance) {
        nextAccountId = nextId;
        this.accountId = id;
        this.accountOwnerName = name;
        this.bal = balance;
    }

    // REQUIRES: inserted integer amount is not 0 and > 0
    // MODIFIES: this
    //  EFFECTS: deposits money into account bal, and set previoustransaction
    public void deposit(double amount) {
        if (amount > 0 && amount != 0) {
            bal = bal + amount;
            previousTrans = +amount;
        }
    }

    // REQUIRES: amount to be not 0 and greater than 0
    // MODIFIES: this
    //  EFFECTS:  withdraws money from bal and set previous transaction
    public void withdraw(double amount) {
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

    public double getBal() {
        return bal;
    }

    public int getId() {
        return accountId;
    }

    @Override
    public void save(PrintWriter printWriter) {
        printWriter.print(nextAccountId);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(accountId);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(accountOwnerName);
        printWriter.print(Reader.DELIMITER);
        printWriter.println(bal);

    }
}
