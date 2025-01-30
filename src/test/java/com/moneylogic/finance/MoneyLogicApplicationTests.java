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

    //@Test
    void contextLoads() {
    }

}
