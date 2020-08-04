package ui;

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
        input = new Scanner(System.in);
        listOfStocks = new ArrayList<>();
        listOfStocks.add(tesla);
        listOfStocks.add(apple);
        listOfStocks.add(microsoft);
        listOfStocks.add(facebook);
        loadAccounts();
        startManager();
        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nHave a nice day! Goodbye.");
    }

    private void startManager() {
        System.out.println("Welcome to Alex's app!");
        System.out.println("\nEnter a name");
        String a = input.next();
        System.out.println("Welcome " + a);


       // System.out.println("\nWould you like to see a list of stocks?");
       // String d = input.next();
       // chooseStock(d);
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

    // MODIFIES:this
    // EFFECTS: initializes account
    private void init() {
        sav = new Account("Alex", 1000.00);
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("d")) {
            doDeposit();
        } else if (command.equals("w")) {
            doWithdraw();
        } else if (command.equals("t")) {
            doTransfer();
        } else if (command.equals("s")) {
            saveAccounts();
        } else if (command.equals("p")) {
            printAccount();
        } else if (command.equals("g")) {
            getPrevTransactions();
        } else if (command.equals("v")) {
            viewIndividualStocks();
        } else if (command.equals("l")) {
            showListOfStocks();
        } else if (command.equals("a")) {
            addtoListOfStocks();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\td -> deposit");
        System.out.println("\tw -> withdraw");
        System.out.println("\tt -> transfer");
        System.out.println("\ts -> save accounts to file");
        System.out.println("\tp -> print to screen");
        System.out.println("\tg -> get previous transaction");
        System.out.println("\tv -> view individual stocks");
        System.out.println("\tl -> list all the stocks");
        System.out.println("\ta -> add to list of stocks");
        //System.out.println("\tb -> buy stocks");
        System.out.println("\tq -> quit");
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
        printBalance(selected);
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
        printBalance(selected);
    }

    // MODIFIES: this
    // EFFECTS: conducts a transfer transaction
    private void doTransfer() {
        System.out.println("\nTransfer from?");
        Account source = selectAccount();
        System.out.println("Transfer to?");
        Account destination = selectAccount();

        System.out.print("Enter amount to transfer: $");
        double amount = input.nextDouble();

        if (amount < 0.0) {
            System.out.println("Cannot transfer negative amount...\n");
        } else if (source.getBalance() < amount) {
            System.out.println("Insufficient balance on source account...\n");
        } else {
            source.withdraw(amount);
            destination.deposit(amount);
        }

        System.out.print("Source ");
        printBalance(source);
        System.out.print("Destination ");
        printBalance(destination);
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

    //  EFFECT: print the account
    private void printAccount() {
        Account selected = selectAccount();
        System.out.println("Id: " + selected.getId());
        System.out.println("Account holder name is " + selected.getName());
        System.out.print(selected.getBalance());
    }

    private void printBalance(Account selected) {
        System.out.printf("Balance: $%.2f\n", selected.getBalance());
    }

    public void getPrevTransactions() {
        System.out.println("Confirming to check transactions? yes/no");
        String e = input.next();
        if (e.equals("yes")) {
            if (sav.getPrevTransaction() > 0) {
                System.out.println("Previous amount deposited was " + sav.getPrevTransaction());
            } else if (sav.getPrevTransaction() < 0) {
                System.out.println("Previous amount withdrawn was " + sav.getPrevTransaction());
            } else {
                System.out.println("There was no previous transaction!");
            }
        }
    }

    public void viewIndividualStocks() {
        System.out.println("\nWhich stock would you like to inspect? type it's name");
        String b = input.next();
        showStock(b);
    }

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

    // EFFECT: method to choose yes or no for list of stocks
    public void showListOfStocks() {
        listStocks();
    }

   // System.out.println(" Stock name: " + tesla.getStockName()
     //       + " Price: $" + tesla.getStockWorth());
       // System.out.println(" Stock name: " + apple.getStockName()
        //        + " Price: $" + apple.getStockWorth());
       // System.out.println(" Stock name: " + microsoft.getStockName()
       //         + " Price: $" + microsoft.getStockWorth());
        //System.out.println(" Stock name: " + facebook.getStockName()
         //       + " Price: $" + facebook.getStockWorth());

    public void listStocks() {
        for (Stocks s: listOfStocks) {
            System.out.println(" Stock name: " + s.getStockName() + " Price: $" + s.getStockWorth());
        }
    }


    public void addtoListOfStocks() {
        System.out.println("Type in the name of the stock you want to add to the system");
        String ssname = input.next();
        System.out.println("Type in the stock price you want to add along with the name");
        double stockworth = input.nextDouble();
        Stocks abc = new Stocks(ssname, stockworth);
        listOfStocks.add(abc);
        System.out.println("Your stock " + ssname + " has been added.");
    }

    private Account selectAccount() {
        return sav;
    }


}




