package ui;

import exceptions.ValueException;
import model.Account;
import model.Stocks;
import persistence.Reader;
import persistence.Writer;
import sun.awt.SunHints;

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

public class AccountApp extends JFrame implements ActionListener {
    private static final String ACCOUNTS_FILE = "./data/accounts.txt";
    public ArrayList<Stocks> listOfStocks;
    private JLabel label;
    private JTextField field;
    private Account sav;
    public Stocks tesla = new Stocks("TESLA", 1500.00);
    public Stocks apple = new Stocks("APPLE", 400.00);
    public Stocks microsoft = new Stocks("MICROSOFT", 205.00);
    public Stocks facebook = new Stocks("FACEBOOK", 253.00);
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
    private Boolean keepgoingornot;
    private JLabel imageLabel;
    private ImageIcon checkMarkImage;
    private JButton btn;
    private JScrollPane scroller;
    protected Scanner input;


    // EFFECTS: runs the application
    public AccountApp() throws InterruptedException {
        managerAppContinue();
        managerAppContinuee();
        managerAppContinueee();

        run();
    }

    public void managerAppContinue() {
        frame = new JFrame("Bank & Stock Account");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1300, 1300));
        ((JPanel) frame.getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        jpanel = new JPanel();
        BoxLayout boxlayout = new BoxLayout(jpanel, BoxLayout.Y_AXIS);
        jpanel.setLayout(boxlayout);
        checkMarkImage = new ImageIcon(System.getProperty("user.dir") + System.getProperty("file.separator")
                + "data" + System.getProperty("file.separator") + "stockadd.jpg");
        imageLabel = new JLabel("The stock has been added!", checkMarkImage, JLabel.CENTER);
        imageLabel.setHorizontalTextPosition(JLabel.CENTER);
        imageLabel.setVerticalTextPosition(JLabel.BOTTOM);
    }

    public void managerAppContinuee() {
        label = new JLabel("");
        label.setFont(new Font("Arial", Font.BOLD, 25));
        labelTwo = new JTextArea(" \n \n \n ");
        labelTwo.setFont(new Font("Arial", Font.BOLD, 25));
        scroller = new JScrollPane(labelTwo);
        field = new JTextField(10);
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, field.getMinimumSize().height));
        btn = new JButton("Enter");
        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        m1 = new JMenuItem("Load");
        m2 = new JMenuItem("Save");
        m1.addActionListener(this);
        m2.addActionListener(this);
        menu.add(m1);
        menu.add(m2);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);
        btn.setActionCommand("myButton");
        btn.addActionListener(this);
    }

    public void managerAppContinueee() {
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
        keepgoingornot = true;
    }

    public void run() throws InterruptedException {
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
        askToPressLoad();
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

        askViewAccount();
        askToSeeStocks();
        while (keepgoingornot) {
            askStock();
        }
        askInput();
    }

    private void askToPressLoad() throws InterruptedException {
        label.setText("Welcome, please press 'load' from the menu bar on the top left corner. ");
        Thread.sleep(3000);
        label.setText("If you have done so, type in 'continue'");
        enterClicked = false;
        delayProgram();
    }

    private void askToSeeStocks() throws InterruptedException {
        label.setText("Would you like to see the list of stocks? Type yes or no");
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
        for (Stocks s : listOfStocks) {
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
        if (answer.equalsIgnoreCase("yes") && keepgoingornot) {
            label.setText("Type in the name of the stock you want to add to the system");
            delayProgram();
            enterClicked = false;
            addandaskValue(fieldInput);
        } else if (answer.equalsIgnoreCase("no")) {
            thatsOkay();
            keepgoingornot = false;
        }
    }

    private void addandaskValue(String answer) throws InterruptedException {
        newStock = new Stocks("", 0);
        if (true) {
            newStock.addStockName(answer);
            askForValue();
        }
    }

    private void askForValue() throws InterruptedException {
        label.setText("Type in the value of the stock you want to add to the system (without the $ sign)");
        delayProgram();
        int ans = Integer.parseInt(fieldInput);
        addValue(ans);
    }

    private void addValue(int ans) throws InterruptedException {
        if (ans >= 0) {
            newStock.addStockWorth(ans);
            listOfStocks.add(newStock);
            JDialog dd = new JDialog(frame);
            dd.add(imageLabel);
            dd.setSize(150, 150);
            dd.pack();
            dd.setLocationRelativeTo(frame);
            dd.setVisible(true);
            Thread.sleep(2000);
            dd.setVisible(false);
            printStockList();
        }
    }


    public void thatsOkay() throws InterruptedException {
        label.setText("That's okay, let us just move on.");
        Thread.sleep(5000);
    }

    private void askInput() throws InterruptedException {
        label.setText("Would you like to deposit money or withdraw money? "
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
            amount = parseInt(fieldInput);
            doDeposit(amount);
        } else if (answer.equals("withdraw")) {
            doWithdraw();
        } else {
            incorrectInput();
        }
    }


    // REQUIRES: has to be a double
    // MODIFIES: this
    //  EFFECT: deposits into the account
    protected void doDeposit(int ans) throws InterruptedException {
        Account selected = selectAccount();
        stringList = "";
        labelTwo.setText(stringList);
        try {
            selected.deposit(ans);
            stringList = "You have deposited $" + ans + "\n " + "Your current balance is $"
                    + selected.getBalance();
            labelTwo.setText(stringList);
        } catch (ValueException e) {
            JOptionPane.showMessageDialog(frame, "Invalid, p.s. cannot deposit negative values", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            delayProgram();
        }
    }

    // REQUIRES: has to be a double
    // MODIFIES: this
    //  EFFECT: withdraw from the account
    protected void doWithdraw() throws InterruptedException {
        Account selected = selectAccount();
        stringList = "";
        label.setText("Enter the amount to withdraw: $");
        delayProgram();
        int amount = parseInt(fieldInput);
        try {
            selected.withdraw(amount);
            stringList = "You have withdrawn $" + amount + "\n " + "Your current balance is $"
                    + selected.getBalance();
            labelTwo.setText(stringList);
        } catch (ValueException i) {
            JOptionPane.showMessageDialog(frame, "Invalid, p.s. cannot withdraw negative values", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            delayProgram();
        }

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

    private void askViewAccount() throws InterruptedException {
        label.setText("Would you like to view your account data? (type yes or no)");
        Thread.sleep(10);
        enterClicked = false;
        delayProgram();
        enterClicked = false;
        viewAccountOrNot(fieldInput);
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
        Account selected = selectAccount();
        stringList = "";
        stringList = stringList + "Id: " + selected.getId() + "\nAccount holder name is " + selected.getName()
                + "\nBalance is " + selected.getBalance();
        labelTwo.setText(stringList);
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
        } else if (e.getActionCommand().equalsIgnoreCase("Load")) {
            try {
                loadAccounts();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }



    //EFFECTS: saves saving account to ACCOUNTS_FILE
    protected void saveAccounts() throws IOException {
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
    private void loadAccounts() throws IOException {
        List<Account> accounts = Reader.readAccounts(new File(ACCOUNTS_FILE));
        if (accounts.isEmpty()) {
            init();
        } else {
            sav = accounts.get(0);
        }
    }

    // MODIFIES:this
    // EFFECTS: initializes account
    private void init() {
        sav = new Account("User", 1000.00);

    }

    public Account selectAccount() {
        return sav;
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


    public void addtoListOfStocks() {
        System.out.println("Type in the name of the stock you want to add to the system");
        String ssname = input.next();
        System.out.println("Type in the stock price you want to add along with the name");
        double stockworth = input.nextDouble();
        Stocks abc = new Stocks(ssname, stockworth);
        listOfStocks.add(abc);
        System.out.println("Your stock " + ssname + " has been added.");
    }








}

