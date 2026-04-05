package com.bank.service;

import com.bank.model.Account;
import com.bank.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BankTest {

    private Bank bank;
    private Account account1;
    private Account account2;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        User user = new User("Ahmad", "test@test.com");

        account1 = new Account("123", 1000, user);
        account2 = new Account("456", 2000, user);
    }

    @Test
    void shouldAddAccount_whenValidAccountProvided() {
        bank.addAccount(account1);
        assertNotNull(bank.findAccount("123"));
    }

    @Test
    void shouldReturnCorrectAccount_whenAccountExists() {
        bank.addAccount(account1);
        Account found = bank.findAccount("123");

        assertEquals("123", found.getAccountNumber());
    }

    @Test
    void shouldReturnNull_whenAccountDoesNotExist() {
        Account found = bank.findAccount("999");

        assertNull(found);
    }

    @Test
    void shouldHandleMultipleAccountsCorrectly() {
        bank.addAccount(account1);
        bank.addAccount(account2);

        assertNotNull(bank.findAccount("123"));
        assertNotNull(bank.findAccount("456"));
    }

    @Test
    void shouldReturnFirstMatchingAccount_whenDuplicateAccountAdded() {
        bank.addAccount(account1);
        bank.addAccount(account1);

        Account found = bank.findAccount("123");

        assertEquals(account1, found);
    }

    @Test
    void shouldReturnNull_whenNoAccountsAdded() {
        assertNull(bank.findAccount("123"));
    }

    @Test
    void shouldFindCorrectAccountAmongMany() {
        bank.addAccount(account1);
        bank.addAccount(account2);

        Account found = bank.findAccount("456");

        assertEquals(2000, found.getBalance());
    }

    @Test
    void shouldNotAffectOtherAccounts_whenAddingNewAccount() {
        bank.addAccount(account1);
        bank.addAccount(account2);

        Account found1 = bank.findAccount("123");
        Account found2 = bank.findAccount("456");

        assertEquals(1000, found1.getBalance());
        assertEquals(2000, found2.getBalance());
    }
}