package com.bank.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TransactionTest {

    @Test
    void shouldInitializeTransactionCorrectly_whenValidDataProvided() {
        Transaction transaction = new Transaction("DEPOSIT", 500);

        assertEquals("DEPOSIT", transaction.getType());
        assertEquals(500, transaction.getAmount());
    }

    @Test
    void shouldAllowZeroAmountTransaction() {
        Transaction transaction = new Transaction("WITHDRAW", 0);

        assertEquals(0, transaction.getAmount());
    }

    @Test
    void shouldHandleNegativeTransactionAmount() {
        Transaction transaction = new Transaction("WITHDRAW", -100);

        assertEquals(-100, transaction.getAmount());
    }

    @Test
    void shouldHandleLargeTransactionAmount() {
        Transaction transaction = new Transaction("DEPOSIT", 1_000_000);

        assertEquals(1_000_000, transaction.getAmount());
    }

    @Test
    void shouldStoreCorrectTransactionType() {
        Transaction transaction = new Transaction("TRANSFER", 300);

        assertEquals("TRANSFER", transaction.getType());
    }

    // multiple transaction objects
    @Test
    void shouldCreateMultipleTransactionsIndependently() {
        Transaction t1 = new Transaction("DEPOSIT", 100);
        Transaction t2 = new Transaction("WITHDRAW", 50);

        assertNotEquals(t1.getType(), t2.getType());
        assertNotEquals(t1.getAmount(), t2.getAmount());
    }

    @Test
    void shouldNotMixDataBetweenTransactions() {
        Transaction t1 = new Transaction("DEPOSIT", 100);
        Transaction t2 = new Transaction("DEPOSIT", 200);

        assertEquals(100, t1.getAmount());
        assertEquals(200, t2.getAmount());
    }

    @Test
    void shouldMaintainImmutableValues_afterCreation() {
        Transaction transaction = new Transaction("DEPOSIT", 500);

        assertEquals("DEPOSIT", transaction.getType());
        assertEquals(500, transaction.getAmount());
    }
}