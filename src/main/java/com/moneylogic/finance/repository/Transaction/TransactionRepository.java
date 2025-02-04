package com.moneylogic.finance.repository.Transaction;

import com.moneylogic.finance.model.Transaction;
import com.moneylogic.finance.model.dto.CategoryMaxAmount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findTransactionsByAccountId(Long id);

    @Query("SELECT new com.moneylogic.finance.model.dto.CategoryMaxAmount(t.category.name, SUM(t.amount)) " +
            "FROM Transaction t " +
            "WHERE t.transactionType = 'income' AND t.account.id = :accountId " +
            "GROUP BY t.category.name")
    List<CategoryMaxAmount> findMaxAmountPerCategoryForIncome(@Param("accountId") Long accountId);

    @Query("SELECT new com.moneylogic.finance.model.dto.CategoryMaxAmount(t.category.name, SUM(t.amount)) " +
            "FROM Transaction t " +
            "WHERE t.transactionType = 'expense' AND t.account.id = :accountId " +
            "GROUP BY t.category.name")
    List<CategoryMaxAmount> findMaxAmountPerCategoryForExpense(@Param("accountId") Long accountId);
}
