package ui;

import exceptions.ValueException;
import model.Account;
import model.Stocks;

import java.io.IOException;
import java.util.ArrayList;

public abstract class DisplayAccount extends AccountApp {
    private Integer ans;

    public DisplayAccount() throws InterruptedException {
    }

    public void printBalance(Account selected) {
        System.out.printf("Balance: $%.2f\n", selected.getBalance());
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
        System.out.println("\tb -> buy stocks");
        System.out.println("\tq -> quit");
    }

    //EFFECT: returns list of sotcks
    public abstract ArrayList<Stocks> showListOfStocks();

    public void listStocks() {
        for (Stocks s: listOfStocks) {
            System.out.println(" Stock name: " + s.getStockName() + " Price: $" + s.getStockWorth());
        }
    }

    //  EFFECT: print the account
    private void printAccount() {
        Account selected = selectAccount();
        System.out.println("Id: " + selected.getId());
        System.out.println("Account holder name is " + selected.getName());
        System.out.print(selected.getBalance());
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

    public void viewIndividualStocks() {
        System.out.println("\nWhich stock would you like to inspect? type it's name");
        String b = input.next();
        showStock(b);
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) throws InterruptedException, IOException, ValueException {
        if (command.equals("d")) {
            doDeposit(ans);
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

    // MODIFIES: this
    // EFFECTS: conducts a transfer transaction
    protected void doTransfer() throws ValueException {
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

}
