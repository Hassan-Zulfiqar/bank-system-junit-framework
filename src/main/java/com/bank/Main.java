package com.bank;

import com.bank.model.*;
import com.bank.service.*;

public class Main {
    public static void main(String[] args) {
        User user = new User("Ahmad", "ahmad@example.com");
        Account account = new Account("123", 1000, user);

        AccountService service = new AccountService();
        service.deposit(account, 500);

        System.out.println("Balance: " + account.getBalance());
    }
}