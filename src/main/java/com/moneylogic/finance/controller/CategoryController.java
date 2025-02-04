package com.moneylogic.finance.controller;

import com.moneylogic.finance.model.Category;
import com.moneylogic.finance.model.dto.CategoryMaxAmount;
import com.moneylogic.finance.repository.Transaction.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/profile/categories")
public class CategoryController {

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryMaxAmount>> getDefaultIncomeCategories(long accountId) {

        Optional<List<CategoryMaxAmount>> categoriesMaxAmount =
                Optional.ofNullable(transactionRepository.findMaxAmountPerCategoryForIncome(accountId));
        if (categoriesMaxAmount.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categoriesMaxAmount.get());
    }

    @GetMapping("/max-income")
    public ResponseEntity<?> getIncomeCategories() {
        Optional<List<CategoryMaxAmount>> categoryMaxAmounts =
                Optional.ofNullable(transactionRepository.findMaxAmountPerCategoryForIncome(1L));
        if (categoryMaxAmounts.isEmpty()) {
            System.out.println(categoryMaxAmounts.get());
            return ResponseEntity.notFound().build();
        }
        System.out.println(categoryMaxAmounts.get());
        return ResponseEntity.ok(categoryMaxAmounts.get());
    }

    @GetMapping("/max-expense/{id}")
    public ResponseEntity<?> getExpenseCategories(@PathVariable("id") long id) {
        Optional<List<CategoryMaxAmount>> categoryMaxAmounts =
                Optional.ofNullable(transactionRepository.findMaxAmountPerCategoryForExpense(id));
        if (categoryMaxAmounts.isPresent()) {
            return ResponseEntity.ok(categoryMaxAmounts);
        }
        return ResponseEntity.notFound().build();
    }
}
