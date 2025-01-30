package com.moneylogic.finance.service.TransactionServiceImpl;

import com.moneylogic.finance.model.Transaction;
import com.moneylogic.finance.repository.Transaction.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService
{
    private final TransactionRepository transactionRepository;
    public TransactionServiceImpl(TransactionRepository transactionRepository)
    {
        this.transactionRepository = transactionRepository;
    }
    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> getAllTransactionsByAccountId(Long id) {
        return transactionRepository.findTransactionsByAccountId(id);
    }

    @Override
    public Transaction getTransactionById(Long id) {

        try {
            return transactionRepository.getById(id);
        }
        catch (EntityNotFoundException e)
        {
            return null;
        }
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction updateTransaction(Long id, Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public void deleteTransactionById(Long id) {
    transactionRepository.deleteById(id);
    }
}
