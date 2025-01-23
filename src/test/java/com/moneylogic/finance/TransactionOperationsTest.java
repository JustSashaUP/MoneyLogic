package com.moneylogic.finance;

import com.moneylogic.finance.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TransactionOperationsTest {
    private final User user = User.createUserWithCurrentDate("test", "test@gmail.com", "test1234");
    private final Category ShopingCategory = Category.createCategory(user, "Shopping🛒");
    private final Category SalaryCategory = Category.createCategory(user, "Salary💰");


    @Test
    public void AccountBalanceAddIncomeAmount_ThanNotEquals() {
        Account account = Account.createAccountWithCurrentDate(user, "cash", BigDecimal.valueOf(1000.0),
                null, null);
        System.out.println("Balance: " + account.getBalance());

        Transaction incomeTransaction = Transaction.createTransaction(user, account, SalaryCategory,
                BigDecimal.valueOf(100.0), TransactionType.INCOME, "description...");

        List<Transaction> transactions = List.of(incomeTransaction);

        account.setTransactions(transactions);
        System.out.println("Balance After Operation: " + account.getBalance());
        assertNotEquals(account.getBalance(), incomeTransaction.getAmount());
    }

    @Test
    public void AccountBalanceAddExpenseAmount_ThanNotEquals() {
        Account account = Account.createAccountWithCurrentDate(user, "cash", BigDecimal.valueOf(1000.0),
                null, null);
        System.out.println("Balance: " + account.getBalance());
        Transaction expenseTransaction = Transaction.createTransaction(user, account, ShopingCategory,
                BigDecimal.valueOf(100.0), TransactionType.EXPENSE, "description...");

        List<Transaction> transactions = List.of(expenseTransaction);

        account.setTransactions(transactions);
        System.out.println("Balance After Operation: " + account.getBalance());
        assertNotEquals(account.getBalance(), expenseTransaction.getAmount());
    }

    @Test
    public void totalBalanceLessThanExpenseAmount_ThanNegativeTotalBalance() {
        final BigDecimal negativeResult = BigDecimal.valueOf(-100.0);
        Account account = Account.createAccountWithCurrentDate(user, "cash", BigDecimal.valueOf(0.0),
                null, null);
        System.out.println("Balance: " + account.getBalance());
        Transaction expenseTransaction = Transaction.createTransaction(user, account, ShopingCategory,
                BigDecimal.valueOf(100.0), TransactionType.EXPENSE, "description...");

        List<Transaction> transactions = List.of(expenseTransaction);

        account.setTransactions(transactions);
        System.out.println("Balance After Operation: " + account.getBalance());
        assertEquals(account.getBalance(), negativeResult);
    }
}
