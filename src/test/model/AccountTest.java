package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


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
        testAccount.deposit(5.00);
        assertEquals(1005.00, testAccount.getBalance());
    }

    @Test
    void testWithdraw() {
        testAccount.withdraw(105.00);
        assertEquals(895.00, testAccount.getBalance());
    }

    @Test
    void testMultipleDeposits() {
        testAccount.deposit(150.00);
        testAccount.deposit(150.00);
        assertEquals(1300.00, testAccount.getBalance());
    }

    @Test
    void testMultipleWithdrawals() {
        testAccount.withdraw(150.00);
        testAccount.withdraw(100.00);
        assertEquals(750.00, testAccount.getBalance());
    }

    @Test
    void testWithdrawDeposit() {
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
        assertEquals(7,testAccount.getId());
        assertEquals(8,testAccount2.getId());
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

}

