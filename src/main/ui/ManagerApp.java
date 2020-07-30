package ui;

import model.Stocks;
import model.Account;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

// Stock Manager Application
public class ManagerApp {
    public ArrayList<Stocks> listofstocks;
    //private Account cheq;

    // EFFECTS: runs the application
    public ManagerApp() {
        runManager();
    }

    // MODIFIES: this
    // EFFECTS: ui processor
    private void runManager() {
        boolean keepGoing = true;
        String command = null;
        listofstocks = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        Stocks tesla = new Stocks("TSLA", 1500);
        String answer;
        boolean yesorno;

        System.out.println("Welcome ");
        System.out.println("Enter a name");
        String a = input.next();
        System.out.println("Welcome " + a);
        System.out.println("Would you like to inspect a stock?");
        String b = input.next();
        choose(b);


    }

    //EFFECT: method to choose yes or no to continue
    public String choose(String answer) {
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

        } else {
            return "Goodbye";
        }
        return "Goodbye";
    }


}




