package com.moneylogic.finance;

import com.moneylogic.finance.model.*;
import com.moneylogic.finance.model.Category;
import com.moneylogic.finance.service.AccountServiceImpl.AccountService;
import com.moneylogic.finance.service.CategoryServiceImpl.CategoryService;
import com.moneylogic.finance.service.TransactionServiceImpl.TransactionService;
import com.moneylogic.finance.service.UserServiceImpl.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CRUDTest {

    @Autowired
    public CategoryService categoryService;

    @Autowired
    public AccountService accountService;

    @Autowired
    public TransactionService transactionService;

    @Autowired
    public UserService userService;

    public final User testUser = User.createUserWithCurrentDate(
            "testCreate", "testCreate@gmail.com", "testCreatePass1234");

    private void createUserAndReadFromDB_ThanNotNull() {
        try {
            userService.saveUser(testUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNotNull(userService.getUserByEmail(testUser.getEmail()));
    }

    private void createEmptyAccountAndReadFromDB_ThanNotNull() {
        User user = userService.getUserByEmail(testUser.getEmail());
        Account account = Account.createEmptyAccountWithCurrentDate(user,
                "testAccount", BigDecimal.valueOf(100.0));
        Account existAccount = null;
        try {
            accountService.saveAccount(account);
            existAccount = accountService.getAllAccountsByUserId(user.getId())
                    .stream()
                    .filter(x -> x.getName().equals(account.getName())).findFirst().orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertNotNull(existAccount);
    }

    private void updateAccountAndReadFromDB_ThanNotEquals() {
        long id = userService.getUserByEmail(testUser.getEmail()).getId();
        Account account = accountService.getAllAccountsByUserId(id).getFirst();
        String oldName = account.getName();
        account.setName(account.getName() + " Updated");
        try {
            accountService.updateAccount(id, account);
        } catch (Exception e) {
            e.printStackTrace();
        }
        account = accountService.getAllAccountsByUserId(id).getFirst();
        assertNotEquals(account.getName(), oldName);
    }

    private void createCategoryAndReadFromDB_ThanNotNull() {
        Category category = Category.createCategory(
                userService.getUserByEmail(testUser.getEmail()),
                "testCategory");
        long id = userService.getUserByEmail(testUser.getEmail()).getId();
        try {
            categoryService.saveCategory(category);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertNotNull(categoryService.getCategoryByUserId(id));
    }

    private void createTransactionAndReadFromDB_ThanNotNull() {
        User user = userService.getUserByEmail(testUser.getEmail());
        Account account = accountService.getAllAccountsByUserId(user.getId())
                .stream()
                .filter(x -> x.getName().equals("testAccount Updated")).findFirst().orElse(null);
        Transaction transaction = Transaction.createTransactionWithoutCategory(user, account, BigDecimal.valueOf(100.0),
                TransactionType.INCOME, String.valueOf(LocalDate.now()), "test Transaction");
        Transaction existTransaction = null;
        try {
            existTransaction = transactionService.saveTransaction(transaction);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNotNull(existTransaction);
    }

    @Test
    public void testCRUDMethods() {
        createUserAndReadFromDB_ThanNotNull();
        createEmptyAccountAndReadFromDB_ThanNotNull();
        updateAccountAndReadFromDB_ThanNotEquals();
        createCategoryAndReadFromDB_ThanNotNull();
        createTransactionAndReadFromDB_ThanNotNull();
        userService.deleteUserById(userService.getUserByEmail(testUser.getEmail()).getId());
    }
}
