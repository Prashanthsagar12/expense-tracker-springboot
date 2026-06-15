package com.example.Tracker.Service;

import com.example.Tracker.entity.Expense;
import com.example.Tracker.exception.ResourceNotFoundException;
import com.example.Tracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public Expense saveExpense(Expense expense) {

        if (expense.getDate() == null) {
            expense.setDate(LocalDate.now());
        }

        return expenseRepository.save(expense);
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }


    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Expense not found with id " + id));
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }


    public Expense updateExpense(Long id, Expense expense) {

        Expense existingExpense =
                expenseRepository.findById(id).orElse(null);

        if (existingExpense != null) {

            existingExpense.setTitle(expense.getTitle());
            existingExpense.setAmount(expense.getAmount());
            existingExpense.setCategory(expense.getCategory());

            return expenseRepository.save(existingExpense);
        }

        return null;
    }

    public List<Expense> getExpensesByCategory(String category) {
        return expenseRepository.findByCategory(category);

    }
    public List<Expense> getExpensesByDate(LocalDate date) {
        return expenseRepository.findByDate(date);
    }

    public List<Expense> getExpensesByAmountRange(
            Double min,
            Double max) {

        return expenseRepository.findByAmountBetween(min, max);
    }
    public Double getTotalExpenses() {
        return expenseRepository.getTotalExpenses();
    }

    public Double getTotalExpensesByMonth(int month) {
        return expenseRepository.getTotalExpensesByMonth(month);
    }

    public Long getExpenseCount() {
        return expenseRepository.getExpenseCount();
    }

    public List<Expense> getExpensesSortedByAmount() {
        return expenseRepository.findAllByOrderByAmountDesc();
    }

    public Page<Expense> getExpensesWithPagination(
            Pageable pageable) {

        return expenseRepository.findAll(pageable);
    }
    public List<Expense> searchExpensesByTitle(String title) {
        return expenseRepository.findByTitleContainingIgnoreCase(title);
    }

}
