package com.bank.service;

import com.bank.model.Account;
import com.bank.model.User;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

public class CSVAccountServiceTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/deposit-data.csv", numLinesToSkip = 1)
    void shouldTestDepositUsingCSV(double amount, String expected) {

        User user = new User("Ahmad", "test@test.com");
        Account account = new Account("123", 1000, user);
        AccountService service = new AccountService();

        if (expected.equals("ERROR")) {
            assertThrows(IllegalArgumentException.class, () -> {
                service.deposit(account, amount);
            });
        } else {
            service.deposit(account, amount);
            assertEquals(Double.parseDouble(expected), account.getBalance());
        }
    }
}