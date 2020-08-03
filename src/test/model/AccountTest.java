package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class AccountTest {
    // delete or rename this class!
    private Account testAccount;
    private Stocks testStocks;
    private Stocks testStocks2;

    @BeforeEach
    void runBefore() {
        testAccount = new Account("Alex",1000);
        testStocks = new Stocks("babo", 100.00);
        testStocks2 = new Stocks("yay", 5000.00);
        double testprevioustrans = 0;

    }

    @Test
    void testConstructor() {
        assertEquals("Alex", testAccount.getName());
        assertEquals(1000, testAccount.getBal());
    }

    @Test
    void testDeposit() {
        testAccount.deposit(5);
        assertEquals(1005, testAccount.getBal());
    }

    @Test
    void testWithdraw() {
        testAccount.withdraw(105);
        assertEquals(895, testAccount.getBal());
    }

    @Test
    void testMultipleDeposits() {
        testAccount.deposit(150);
        testAccount.deposit(150);
        assertEquals(1300, testAccount.getBal());
    }

    @Test
    void testMultipleWithdrawals() {
        testAccount.withdraw(150);
        testAccount.withdraw(100);
        assertEquals(750, testAccount.getBal());
    }

    @Test
    void testWithdrawDeposit() {
        testAccount.withdraw(200);
        testAccount.deposit(300);
        assertEquals(1100, testAccount.getBal());
    }

    @Test
    void testStocksConstruct() {
        assertEquals("babo", testStocks.getStockName());
        assertEquals(100.00, testStocks.getStockWorth());
        assertEquals("yay", testStocks2.getStockName());
        assertEquals(5000.00, testStocks2.getStockWorth());
    }


}