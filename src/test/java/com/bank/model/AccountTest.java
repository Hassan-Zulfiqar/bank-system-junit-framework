package com.bank.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    private Account account;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User("Ahmad", "test@test.com");
        account = new Account("123", 1000, user);
    }

    @Test
    void shouldInitializeAccountCorrectly_whenValidDataProvided() {
        assertEquals("123", account.getAccountNumber());
        assertEquals(1000, account.getBalance());
        assertEquals(user, account.getUser());
    }

    @Test
    void shouldUpdateBalance_whenSetBalanceIsCalled() {
        account.setBalance(2000);
        assertEquals(2000, account.getBalance());
    }

    @Test
    void shouldAllowZeroBalance_whenAccountIsEmpty() {
        account.setBalance(0);
        assertEquals(0, account.getBalance());
    }

    @Test
    void shouldHandleLargeBalanceValues() {
        account.setBalance(1_000_000);
        assertEquals(1_000_000, account.getBalance());
    }

    @Test
    void shouldMaintainUserReference_whenAccountIsUsed() {
        assertEquals("Ahmad", account.getUser().getName());
    }

    @Test
    void shouldNotChangeAccountNumber_whenBalanceChanges() {
        account.setBalance(5000);
        assertEquals("123", account.getAccountNumber());
    }

    @Test
    void shouldReflectLatestBalance_afterMultipleUpdates() {
        account.setBalance(2000);
        account.setBalance(3000);

        assertEquals(3000, account.getBalance());
    }

    
    @Test
    void shouldMaintainConsistentState_whenMultipleOperationsPerformed() {
        account.setBalance(account.getBalance() + 500); // deposit
        account.setBalance(account.getBalance() - 200); // withdraw

        assertEquals(1300, account.getBalance());
    }

    // Edge Case
    @Test
    void shouldHandleNegativeBalance_ifSetExplicitly() {
        account.setBalance(-500);

        assertEquals(-500, account.getBalance());
    }

    // Object Integrity Test
    @Test
    void shouldKeepSameUserInstance_whenAssigned() {
        User retrievedUser = account.getUser();

        assertSame(user, retrievedUser);
    }
}