package com.moneylogic.finance.service.AccountServiceImpl;

import com.moneylogic.finance.model.Account;
import com.moneylogic.finance.repository.Account.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService
{
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public List<Account> getAllAccountsByUserId(Long id) {
        return accountRepository.findAccountsByUserId(id);
    }

    @Override
    public Account getAccountById(Long id) {
        try {
            return accountRepository.getById(id);
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    @Override
    public void saveAccount(Account account) {
        accountRepository.save(account);
    }

    @Override
    public Account updateAccount(Long id, Account account) {
        return accountRepository.save(account);
    }

    @Override
    public void deleteAccountById(Long id) {
    accountRepository.deleteById(id);
    }
}
