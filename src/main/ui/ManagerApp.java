package ui;

import model.Account;
import model.Stocks;
import persistence.Reader;
import persistence.Writer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class ManagerApp extends JFrame implements ActionListener {
    private static final String ACCOUNTS_FILE = "./data/accounts.txt";
    private ArrayList<Stocks> listOfStocks;
    private JLabel label;
    private JTextField field;
    private Account sav;
    private Scanner input;
    Stocks tesla = new Stocks("TESLA", 1500.00);
    Stocks apple = new Stocks("APPLE", 400.00);
    Stocks microsoft = new Stocks("MICROSOFT", 205.00);
    Stocks facebook = new Stocks("FACEBOOK", 253.00);
    private String fieldInput;
    private Boolean enterClicked;
    private JFrame frame;
    private JPanel jpanel;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem m1;
    private JMenuItem m2;
    private Boolean loop;
    private Boolean value;
    private int amount;
    private JTextArea labelTwo;
    private String stringList;
    Stocks newStock;


    // EFFECTS: runs the application
    public ManagerApp() throws IOException, ClassNotFoundException, InterruptedException {
        frame = new JFrame("Bank & Stock Account");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1300, 1300));
        ((JPanel) frame.getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        jpanel = new JPanel();
        BoxLayout boxlayout = new BoxLayout(jpanel, BoxLayout.Y_AXIS);
        jpanel.setLayout(boxlayout);
        label = new JLabel("");
        label.setFont(new Font("Arial", Font.BOLD, 25));
        labelTwo = new JTextArea(" \n \n \n ");
        labelTwo.setFont(new Font("Arial", Font.BOLD, 25));
        JScrollPane scroller = new JScrollPane(labelTwo);
        field = new JTextField(10);
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, field.getMinimumSize().height));
        JButton btn = new JButton("Enter");
        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        m1 = new JMenuItem("New");
        m2 = new JMenuItem("Save");
        m1.addActionListener(this);
        m2.addActionListener(this);
        menu.add(m1);
        menu.add(m2);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);
        btn.setActionCommand("myButton");
        btn.addActionListener(this);
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        scroller.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelTwo.setEditable(false);
        jpanel.add(label, "North");
        jpanel.add(field, "Center");
        jpanel.add(btn, "East");
        jpanel.add(scroller);
        frame.add(jpanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        fieldInput = "";
        stringList = "";
        enterClicked = false;
        value = false;
        loop = false;
        run();
    }

    public void run() throws IOException, ClassNotFoundException, InterruptedException {
        listOfStocks = new ArrayList<>();
        listOfStocks.add(tesla);
        listOfStocks.add(apple);
        listOfStocks.add(microsoft);
        listOfStocks.add(facebook);
        runManager();
        label.setText("Save before closing the application if you haven't yet, thank you. Goodbye.");
    }

    // MODIFIES: this
    // EFFECTS: ui processor
    private void runManager() throws InterruptedException {

        while (!loop) {
            startManager();

            value = false;
            while (!value) {
                label.setText("Would you like to keep going? Type yes or no");
                enterClicked = false;
                delayProgram();
                String done = fieldInput;
                if (done.equalsIgnoreCase("No")) {
                    loop = true;
                    value = true;
                } else if (!done.equalsIgnoreCase("Yes")) {
                    incorrectInput();
                } else {
                    value = true;
                }
            }
        }

    }



    // EFFECTS: ask what the user wnats to do, and print out list of stocks/account info
    private void startManager() throws InterruptedException {
        askToSeeStocks();
        askStock();
        askInput();
    }



    private void askToSeeStocks() throws InterruptedException {
        label.setText(" Welcome user. Would you like to see the list of stocks? Type yes or no");
        Thread.sleep(10);
        enterClicked = false;
        delayProgram();
        enterClicked = false;
        askSeeStocks(fieldInput);
    }

    private void askSeeStocks(String answer) throws InterruptedException {
        enterClicked = false;
        if (answer.equals("yes")) {
            label.setText("Look below to see our stocks that we have");
            printStockList();
        } else if (answer.equals("no")) {
            thatsOkay();
        } else {
            incorrectInput();
        }
    }

    private void printStockList() {
        stringList = "";
        for (Stocks s: listOfStocks) {
            stringList = stringList + " Stock name: " + s.getStockName() + " Price: $" + s.getStockWorth() + "\n";
        }
        labelTwo.setText(stringList);
    }


    private void askStock() throws InterruptedException {
        label.setText("Would you like to add in stocks? (type yes or no)");
        delayProgram();
        enterClicked = false;
        askToAddStocks(fieldInput);
    }

    private void askToAddStocks(String answer) throws InterruptedException {
        enterClicked = false;
        if (answer.equalsIgnoreCase("yes")) {
            label.setText("Type in the name of the stock you want to add to the system");
            delayProgram();
            addandaskValue(fieldInput);
        } else if (answer.equalsIgnoreCase("no")) {
            thatsOkay();
        }
    }

    private void addandaskValue(String answer) throws InterruptedException {
        newStock = new Stocks("",0);
        newStock.addStockName(answer);
        label.setText("Type in the value of the stock you want to add to the system (without the $ sign)");
        delayProgram();
        //perhaps im not asking for user to put a input
        enterClicked = false;
        int ans = Integer.parseInt(fieldInput);
        addValue(ans);
    }

    private void addValue(int answer) {
        newStock.addStockWorth(answer);
        listOfStocks.add(newStock);
        printStockList();
    }


    public void thatsOkay() throws InterruptedException {
        label.setText("That's okay, let us just move on.");
        Thread.sleep(5000);
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


    private void askInput() throws InterruptedException {
        label.setText("  Would you like to deposit money or withdraw money? "
                + "Please type 'deposit' or 'withdraw'");
        Thread.sleep(10);
        enterClicked = false;
        delayProgram();
        enterClicked = false;
        askDepositOrWithdraw(fieldInput);
    }

    private void askDepositOrWithdraw(String answer) throws InterruptedException {

        enterClicked = false;
        if (answer.equals("deposit")) {
            label.setText("Enter the amount you want to deposit: $");
            delayProgram();
            int ans = parseInt(fieldInput);
            doDeposit(ans);
        } else if (answer.equals("withdraw")) {
            doWithdraw();
        } else {
            incorrectInput();
        }
    }


    // REQUIRES: has to be a double
    // MODIFIES: this
    //  EFFECT: deposits into the account
    private void doDeposit(int ans) throws InterruptedException {
        Account selected = selectAccount();
        stringList = "";
        labelTwo.setText(stringList);
        if (ans >= 0.0) {
            selected.deposit(ans);
            stringList = "You have deposited $" + ans + "\n " + "and you current balance is"
                    + selected.getBalance();
            labelTwo.setText(stringList);
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid, p.s. cannot deposit negative values");
        }
    }
//LOADING accounts needs work. then depositing into selected accounts will work, or change to sav.getBalance etc..

    // REQUIRES: has to be a double
    // MODIFIES: this
    //  EFFECT: withdraw from the account
    private void doWithdraw() throws InterruptedException {
        Account selected = selectAccount();
        stringList = "";
        label.setText("Enter the amount to withdraw: $");
        delayProgram();
        int amount = parseInt(fieldInput);
        if (amount < 0.0) {
            JOptionPane.showMessageDialog(frame,"Cannot withdraw negative amounts");
        } else if (selected.getBalance() < amount) {
            JOptionPane.showMessageDialog(frame,"Insufficient balance on your account");
        } else {
            selected.withdraw(amount);
        }
        stringList = "You have deposited $" + amount + "\n " + "and you current balance is"
                + selected.getBalance();
        labelTwo.setText(stringList);
    }


    //EFFECTS: delay the program for the users to have a breathe
    private void delayProgram() throws InterruptedException {
        while (!enterClicked) {
            Thread.sleep(10);
        }
    }

    private void incorrectInput() {
        JOptionPane.showMessageDialog(frame, "Invalid entry!",
                "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void viewAccountOrNot(String ans) {
        enterClicked = false;
        if (ans.equals("yes")) {
            viewAccount();
        } else {
            incorrectInput();
        }
    }

    private void viewAccount() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("myButton")) {
            if (!field.getText().isEmpty()) {
                fieldInput = field.getText();
                field.setText("");
                enterClicked = true;
            }
        } else if (e.getActionCommand().equalsIgnoreCase("Save")) {
            try {
                saveAccounts();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    //EFFECTS: saves saving account to ACCOUNTS_FILE
    private void saveAccounts() throws IOException {
        try {
            Writer writer = new Writer(new File(ACCOUNTS_FILE));
            writer.write(sav);
            writer.close();
            JOptionPane.showMessageDialog(frame, "Accounts saved to file " + ACCOUNTS_FILE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(frame, "Unable to save accounts to " + ACCOUNTS_FILE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // this is due to a programming error
        }
    }

    // MODIFIES: this
    // EFFECTS: loads accounts from ACCOUNTS_FILE, if that file exists;
    // otherwise initializes accounts with default values
    private void loadAccounts() throws IOException, ClassNotFoundException {
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
            //doDeposit();
        } else if (command.equals("w")) {
            //doWithdraw();
        } else if (command.equals("t")) {
            doTransfer();
        } else if (command.equals("s")) {
           // saveAccounts();
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




//    public void addtoListOfStocks() {
//        System.out.println("Type in the name of the stock you want to add to the system");
//        String ssname = input.next();
//        System.out.println("Type in the stock price you want to add along with the name");
//        double stockworth = input.nextDouble();
//        Stocks abc = new Stocks(ssname, stockworth);
//        listOfStocks.add(abc);
//        System.out.println("Your stock " + ssname + " has been added.");
//    }

    // EFFECT: method to choose yes or no for list of stocks
    public void showListOfStocks() {
        listStocks();
    }

    public void listStocks() {
        for (Stocks s: listOfStocks) {
            System.out.println(" Stock name: " + s.getStockName() + " Price: $" + s.getStockWorth());
        }
    }

    private Account selectAccount() {
        return sav;
    }

    /*//This is the method that is called when the the JButton btn is clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("myButton")) {
            label.setText(field.getText());
        }
    }*/

}




