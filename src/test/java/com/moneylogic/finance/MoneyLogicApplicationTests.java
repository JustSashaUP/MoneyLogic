package com.moneylogic.finance;

import com.moneylogic.finance.model.*;
import com.moneylogic.finance.repository.Account.AccountRepository;
import com.moneylogic.finance.repository.Category.CategoryRepository;
import com.moneylogic.finance.repository.Transaction.TransactionRepository;
import com.moneylogic.finance.repository.User.UserRepository;
import com.moneylogic.finance.service.AccountServiceImpl.AccountService;
import com.moneylogic.finance.service.CategoryServiceImpl.CategoryService;
import com.moneylogic.finance.service.TransactionServiceImpl.TransactionService;
import com.moneylogic.finance.service.UserServiceImpl.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.moneylogic.finance.model.Category.createCategory;

@SpringBootTest

class MoneyLogicApplicationTests {

    @Autowired
    public CategoryService categoryService;

    @Autowired
    public AccountService accountService;

    @Autowired
    public TransactionService transactionService;

    @Autowired
    public UserService userService;

    //@Test
    void contextLoads() {
    }
    // В тестах я использовал записи из базы данных, которые есть
    // у меня. Поэтому, если кто-то будет тестировать, замените значение 10L на существующие у вас записи.!
    @Test
    void CategoryTest() {
        User user = userService.getUserById(10L);

        Category category = Category.createCategory(user,"testFor4Another");

        categoryService.saveCategory(category);
    }

    @Test
    void AccountTest()
    {
        User user = userService.getUserById(10L);
        Account account = new Account(user,"Card",new BigDecimal(120.0),LocalDate.now());

        accountService.saveAccount(account);
    }
    @Test
    void UserTest()
    {
        User user = User.createUserWithCurrentDate("hello","hello@gmail.com","123");

        userService.saveUser(user);
    }

    @Test
    void TransactionTest()
    {
        User user = userService.getUserById(10L);
        Account account = accountService.getAccountById(1L);
        Category category = categoryService.getCategoryById(1L);
        //Transaction transaction = Transaction.createTransaction(user,account,category,new BigDecimal(100.0),TransactionType.INCOME,"test");
        Transaction transaction = new Transaction(user,account,category,new BigDecimal(100.0),TransactionType.INCOME,"test",LocalDate.now());
        transactionService.saveTransaction(transaction);
    }

}
