package com.moneylogic.finance.service.AccountServiceImpl;

import com.moneylogic.finance.model.Account;

import java.util.List;

public interface AccountService
{
    List<Account> getAllAccounts();

    Account getAccountById(Long id);

    Account saveAccount(Account account);

    Account updateAccount(Long id, Account account);

    void deleteAccountById(Long id);
}
