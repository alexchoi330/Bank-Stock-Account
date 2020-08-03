package persistence;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import model.Account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ReaderTest {

    @Test
    void testParseAccountsFile1() {
    try {
        List<Account> accounts = Reader.readAccounts(new File("./data/testAccountFile1.txt"));

        Account savings = accounts.get(0);
        assertEquals(43, savings.getId());
        assertEquals("Mo", savings.getName());
        assertEquals(141.42, savings.getBalance());

        // check that nextAccountId has been set correctly
        Account nextAccount = new Account("Chris", 54.12);
        assertEquals(44, nextAccount.getId());
    } catch (IOException e) {
        System.err.println("IOException should not have been thrown");
    }
}

    @Test
    void testParseAccountsFile2() {
        try {
            List<Account> accounts = Reader.readAccounts(new File("./data/testAccountFile2.txt"));

            Account savings = accounts.get(0);
            assertEquals(2, savings.getId());
            assertEquals("Pat", savings.getName());
            assertEquals(11.40, savings.getBalance());

            // check that nextAccountId has been set correctly
            Account nextAccount = new Account("Chris", 54.12);
            assertEquals(3, nextAccount.getId());
        } catch (IOException e) {
            System.err.println("IOException should not have been thrown");
        }
    }

    @Test
    void testIOException() {
        try {
            Reader.readAccounts(new File("./path/does/not/exist/testAccount.txt"));
        } catch (IOException e) {
            // expected
        }
    }
}