package com.moneylogic.finance.service.TransactionServiceImpl;

import com.moneylogic.finance.model.Transaction;

import java.util.List;

public interface TransactionService
{
    List<Transaction> getAllTransactions();
    Transaction getTransactionById(Long id);

    Transaction saveTransaction(Transaction transaction);

    Transaction updateTransaction(Long id, Transaction transaction);

    void deleteTransactionById(Long id);
}
