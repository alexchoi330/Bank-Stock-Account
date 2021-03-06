package model;

import static org.junit.jupiter.api.Assertions.*;

import exceptions.ValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

import javax.swing.*;


class AccountTest {
    // delete or rename this class!
    private Account testAccount;
    private Account testAccount2;
    private Stocks testStocks;
    private Stocks testStocks2;

    @BeforeEach
    void runBefore() {
        testAccount = new Account("Alex",1000.00);
        testAccount2 = new Account("oof",0.00);
        testStocks = new Stocks("babo", 100.00);
        testStocks2 = new Stocks("yay", 5000.00);
        double testprevioustrans;

    }

    @Test
    void testConstructor() {
        assertEquals("Alex", testAccount.getName());
        assertEquals(1000.00, testAccount.getBalance());
    }

    @Test
    void testStock() {
        assertEquals("babo",testStocks.getStockName());
        assertEquals(100.00,testStocks.getStockWorth());
    }

    @Test
    void testConstructorNegBalance() {
        testAccount = new Account("Matt", -25.0);
        assertEquals("Matt", testAccount.getName());
        assertEquals(0, testAccount.getBalance());
        assertTrue(testAccount.getId() > 0);
    }

    @Test
    void testDeposit() {
        try {
            testAccount.deposit(5.00);
        } catch (ValueException e) {
            e.printStackTrace();
        }
        assertEquals(1005.00, testAccount.getBalance());
    }

    @Test
    void testDepositTwo() {
        try {
            testAccount.deposit(-5.00);
        } catch (ValueException e) {
            //expected case
        }
    }

    @Test
    void testDepositThree() {
        try {
            testAccount.deposit(5.00);
        } catch (ValueException e) {
            fail("ValueException shouldn't have been thrown here"); //unexpected case
        }
    }

    @Test
    void testWithdraw() {
        try {
            testAccount.withdraw(105.00);
        } catch (ValueException e) {
            e.printStackTrace();
        }
        assertEquals(895.00, testAccount.getBalance());
    }

    @Test
    void testMultipleDeposits() throws ValueException {
        testAccount.deposit(150.00);
        testAccount.deposit(150.00);
        assertEquals(1300.00, testAccount.getBalance());
    }

    @Test
    void testMultipleWithdrawals() throws ValueException {
        testAccount.withdraw(150.00);
        testAccount.withdraw(100.00);
        assertEquals(750.00, testAccount.getBalance());
    }

    @Test
    void testWithdrawDeposit() throws ValueException {
        testAccount.withdraw(200.00);
        testAccount.deposit(300.00);
        assertEquals(1100.00, testAccount.getBalance());
    }

    @Test
    void testStocksConstruct() {
        assertEquals("babo", testStocks.getStockName());
        assertEquals(100.00, testStocks.getStockWorth());
        assertEquals("yay", testStocks2.getStockName());
        assertEquals(5000.00, testStocks2.getStockWorth());
    }

    @Test
    void testgetId() {
        assertEquals(9,testAccount.getId());
        assertEquals(10,testAccount2.getId());
    }

    @Test
    void testgetName() {
        assertEquals("Alex",testAccount.getName());
        assertEquals("oof",testAccount2.getName());
    }

    @Test
    void testgetBalance() {
        assertEquals(1000.00, testAccount.getBalance());
        assertEquals(0.00,testAccount2.getBalance());
    }

    @Test
    void testgetStockName() {
        assertEquals("babo", testStocks.getStockName());
        assertEquals("yay", testStocks2.getStockName());
    }

    @Test
    void testgetStockWorth() {
        assertEquals(100.00, testStocks.getStockWorth());
        assertEquals(5000.00, testStocks2.getStockWorth());
    }

    @Test
    void testToString() {
        assertTrue(testAccount.toString().contains("name = Alex, balance = $1000.00"));
    }

    @Test
    void testgetPreviousTransaction() throws ValueException {
        testAccount.deposit(500.00);
        assertEquals(500.00,testAccount.getPrevTransaction());
        testAccount.withdraw(300.00);
        assertEquals(300.00,testAccount.getPrevTransaction());
    }
}

