package com.bank.service;

import com.bank.model.Account;
import com.bank.model.User;
import org.apache.poi.ss.usermodel.*;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class ExcelAccountServiceTest {

    @Test
    void shouldTestDepositUsingExcel() throws Exception {

        InputStream is = getClass().getClassLoader()
                .getResourceAsStream("deposit-data.xlsx");

        Workbook workbook = WorkbookFactory.create(is);
        Sheet sheet = workbook.getSheetAt(0);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {

            Row row = sheet.getRow(i);

            double amount = row.getCell(0).getNumericCellValue();
            Cell expectedCell = row.getCell(1);

            User user = new User("Ahmad", "test@test.com");
            Account account = new Account("123", 1000, user);
            AccountService service = new AccountService();

            if (expectedCell.getCellType() == CellType.STRING) {
                assertThrows(IllegalArgumentException.class, () -> {
                    service.deposit(account, amount);
                });
            } else {
                double expected = expectedCell.getNumericCellValue();
                service.deposit(account, amount);
                assertEquals(expected, account.getBalance());
            }
        }

        workbook.close();
    }
}