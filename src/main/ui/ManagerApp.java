package ui;

import exceptions.AccountCreationException;
import exceptions.CouldNotStartException;
import model.Account;
import model.Stocks;
import persistence.Reader;
import persistence.Writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManagerApp {
    private static final String ACCOUNTS_FILE = "./data/accounts.txt";
    private ArrayList<Stocks> listOfStocks;
    private Account sav;
    private Scanner input;

    Stocks tesla = new Stocks("TESLA", 1500.00);
    Stocks apple = new Stocks("APPLE", 400.00);
    Stocks microsoft = new Stocks("MICROSOFT", 205.00);
    Stocks facebook = new Stocks("FACEBOOK", 253.00);


    // EFFECTS: runs the application
    public ManagerApp() {
        runManager();
    }

    // MODIFIES: this
    // EFFECTS: ui processor
    private void runManager() {
        boolean keepGoing = true;
        String command = null;
        listOfStocks = new ArrayList<>();
        input = new Scanner(System.in);

        listOfStocks.add(tesla);
        listOfStocks.add(apple);
        listOfStocks.add(microsoft);
        listOfStocks.add(facebook);


        System.out.println("Welcome summoner");
        System.out.println("Enter a name");
        String a = input.next();
        System.out.println("Welcome " + a);
        makeNewAccount(a);
        System.out.println("Would you like to inspect a stock?");
        String b = input.next();
        choose(b);
        System.out.println("\nWould you like to see a list of stocks?");
        String d = input.next();
        chooseStock(d);
        doDeposit();
        doWithdraw();

    }

    //would you like to buy stocks? yes or no option, and make a method to add
    //stocks into a new stock list that can be saved in data file

    // EFFECT: take a string and show its name and price
    private void showStock(String c) {
        switch (c) {
            case "tesla":
                System.out.println("Stock name: " + tesla.getStockName()
                        + " Stock worth: $" + (tesla.getStockWorth()));
                break;
            case "apple":
                System.out.println("Stock name: " + apple.getStockName()
                        + " Stock worth: $" + (apple.getStockWorth()));
                break;
            case "microsoft":
                System.out.println("Stock name: " + microsoft.getStockName()
                        + " Stock worth: $" + (microsoft.getStockWorth()));
                break;
            case "facebook":
                System.out.println("Stock name: " + facebook.getStockName()
                        + " Stock worth: $" + facebook.getStockWorth());
                break;
            default:
                System.err.println("That stock is not in the system.");
                break;
        }
    }

    // EFFECT: make a new account with given string name
    private void makeNewAccount(String a) {
        sav = new Account(a,0);
    }

    // MODIFIES: this
    // EFFECTS: loads accounts from ACCOUNTS_FILE, if that file exists;
    // otherwise initializes accounts with default values
    private void loadAccounts() {
        try {
            List<Account> accounts = Reader.readAccounts(new File(ACCOUNTS_FILE));
            sav = accounts.get(0);
        } catch (IOException e) {
            init();
        }
    }


    //EFFECTS: saves saving account to ACCOUNTS_FILE
    private void saveAccounts() {
        try {
            Writer writer = new Writer(new File(ACCOUNTS_FILE));
            writer.write(sav);
            writer.close();
            System.out.println("Accounts saved to file " + ACCOUNTS_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save accounts to " + ACCOUNTS_FILE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // this is due to a programming error
        }
    }



    // EFFECT: method to choose yes or no for list of stocks
    public void chooseStock(String answer) {
        boolean yesorno = false;
        if (answer.equals("yes")) {
            yesorno = true;
            System.out.println("Ok, let me list them out");
        } else {
            System.out.print("That's fine. ");
        }
        if (yesorno) {
            System.out.println("asdf");
        }
    }

    //EFFECT: method to choose yes or no to continue
    public void choose(String answer) {
        boolean yesorno = false;
        if (answer.equals("yes")) {
            yesorno = true;
            System.out.println("You chose yes.");
        } else if (answer.equals("no")) {
            System.out.println("You chose no.");
        } else {
            System.out.println("Invalid entry, please make sure to choose yes or no and type it");
        }

        if (yesorno) {
            System.out.println("Type in the stock name you want to inspect");
            String c = input.next();
            showStock(c);
        } else {
            System.out.println("That's okay, let us move on. ");

        }
    }

    // REQUIRES: has to be a double
    // MODIFIES: this
    //  EFFECT: withdraw from the account
    private void doWithdraw() {
        Account selected = selectAccount();
        System.out.print("Enter the amount to withdraw: $");
        double amount = input.nextDouble();

        if (amount < 0.0) {
            System.out.println("Cannot withdraw negative amounts");
        } else if (selected.getBalance() < amount) {
            System.out.println("Insufficient balance on your account");
        } else {
            selected.withdraw(amount);
        }
    }

    // REQUIRES: has to be a double
    // MODIFIES: this
    //  EFFECT: deposits into the account
    private void doDeposit() {
        Account selected = selectAccount();
        System.out.print("Enter the amount you want to despot: $");
        double amount = input.nextDouble();

        if (amount >= 0.0) {
            selected.deposit(amount);
        } else {
            System.out.println("Cannot deposit negative amounts");
        }
    }

    // MODIFIES:this
    // EFFECTS: initializes accounts
    private void init() throws CouldNotStartException {
        try {
            sav = new Account("Joe", 1000.00);
        } catch (AccountCreationException e) {
            System.err.println("Something is wrong...");
            throw new CouldNotStartException();
        }
    }

    private Account selectAccount() {
        return sav;
    }

    //  EFFECT: print the account
    private void printAccount() {
        Account selected = selectAccount();
        System.out.println("Id: " + selected.getId());
        System.out.println("Account holder name is " + selected.getName());
        System.out.print(selected.getBalance());
    }



}




