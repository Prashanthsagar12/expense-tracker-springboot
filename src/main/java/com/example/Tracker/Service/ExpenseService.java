package com.example.Tracker.Service;

import com.example.Tracker.entity.Expense;
import com.example.Tracker.exception.ResourceNotFoundException;
import com.example.Tracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public Expense saveExpense(Expense expense) {
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
}
