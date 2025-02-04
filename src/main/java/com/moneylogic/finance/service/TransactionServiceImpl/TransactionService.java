package com.moneylogic.finance.service.TransactionServiceImpl;

import com.moneylogic.finance.model.Transaction;
import com.moneylogic.finance.model.dto.CategoryMaxAmount;

import java.util.List;

public interface TransactionService
{
    List<Transaction> getAllTransactions();

    List<Transaction> getAllTransactionsByAccountId(Long id);

    List<CategoryMaxAmount> getMaxIncomePerCategory(long accountId);

    List<CategoryMaxAmount> getMaxExpensePerCategory(long accountId);

    Transaction getTransactionById(Long id);

    Transaction saveTransaction(Transaction transaction);

    Transaction updateTransaction(Long id, Transaction transaction);

    void deleteTransactionById(Long id);
}
