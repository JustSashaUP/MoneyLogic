package com.moneylogic.finance;

import com.moneylogic.finance.model.Category;
import com.moneylogic.finance.model.TransactionType;
import com.moneylogic.finance.model.User;
import com.moneylogic.finance.repository.Account.AccountRepository;
import com.moneylogic.finance.repository.Category.CategoryRepository;
import com.moneylogic.finance.repository.Transaction.TransactionRepository;
import com.moneylogic.finance.repository.User.UserRepository;
import com.moneylogic.finance.service.AccountServiceImpl.AccountService;
import com.moneylogic.finance.service.CategoryServiceImpl.CategoryService;
import com.moneylogic.finance.service.TransactionServiceImpl.TransactionService;
import com.moneylogic.finance.service.UserServiceImpl.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

    /*@Test
    void CategoryTest() {
        User user = userService.getUserById(1L);
        user.getUsername();
        Category category = Category.createCategory(user, "testCategory", TransactionType.INCOME);
        categoryService.saveCategory(category);
    }*/

  /*  @Test
    void UserTest()
    {
        User user = User.createUserWithCurrentDate("test2","test@gmail.com","1234");
        userService.saveUser(user);

    }*/
}
