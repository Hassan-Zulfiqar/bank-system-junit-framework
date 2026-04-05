package com.bank.service;

import com.bank.model.Account;

public class AccountService {

    public void deposit(Account account, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid amount");
        }
        account.setBalance(account.getBalance() + amount);
    }

    public void withdraw(Account account, double amount) {
        if (amount <= 0 || amount > account.getBalance()) {
            throw new IllegalArgumentException("Invalid withdrawal");
        }
        account.setBalance(account.getBalance() - amount);
    }
}