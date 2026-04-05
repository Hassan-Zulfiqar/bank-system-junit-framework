
package com.bank.service;
import com.bank.model.Account;
import com.bank.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AccountServiceTest {
	
	private Account account;
	private AccountService service;

	@BeforeEach
	void setUp() {
	    User user = new User("Ahmad", "test@test.com");
	    account = new Account("123", 1000, user);
	    service = new AccountService();
	}

    @Test
    void shouldIncreaseBalance_whenDepositIsValid() {
        service.deposit(account, 500);
        assertEquals(1500, account.getBalance());
    }
    
    //Negative Test (Invalid Deposit)
    @Test
    void shouldThrowException_whenDepositIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.deposit(account, -100);
        });
    }
    
    @Test
    void shouldThrowException_whenWithdrawIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.withdraw(account, -50);
        });
    }
    
    // Withdraw success
    @Test
    void shouldDecreaseBalance_whenWithdrawIsValid() {

        service.withdraw(account, 300);

        assertEquals(700, account.getBalance());
    }
    
    // Withdraw more than balance
    @Test
    void shouldThrowException_whenWithdrawExceedsBalance() {

        assertThrows(IllegalArgumentException.class, () -> {
            service.withdraw(account, 2000);
        });
    }
    
    // Zero Amount
    @Test
    void shouldThrowException_whenDepositIsZero() {

        assertThrows(IllegalArgumentException.class, () -> {
            service.deposit(account, 0);
        });
    }
    
    @Test
    void shouldThrowException_whenWithdrawIsZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.withdraw(account, 0);
        });
    }
    
    // Deposit Large Amount (Edge / Stress Case)

    @Test
    void shouldHandleLargeDeposit_whenAmountIsVeryHigh() {
        service.deposit(account, 1_000_000);

        assertEquals(1001000, account.getBalance());
    }
    
    // Multiple Deposits (State Change Test)

    @Test
    void shouldAccumulateBalance_whenMultipleDepositsAreMade() {
        service.deposit(account, 200);
        service.deposit(account, 300);

        assertEquals(1500, account.getBalance());
    }
    
    // Deposit Then Withdraw (Flow Test)

    @Test
    void shouldMaintainCorrectBalance_whenDepositThenWithdraw() {
        service.deposit(account, 500);   // 1500
        service.withdraw(account, 200);  // 1300

        assertEquals(1300, account.getBalance());
    }
}

