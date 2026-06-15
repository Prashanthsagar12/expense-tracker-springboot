package com.example.Tracker.controller;


import com.example.Tracker.entity.Expense;
import com.example.Tracker.Service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import jakarta.validation.Valid;
@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping
    public Expense saveExpense(@Valid @RequestBody Expense expense) {

        return expenseService.saveExpense(expense);
    }

    @GetMapping
    public List<Expense> getAllExpenses() {

        return expenseService.getAllExpenses();
    }

    @GetMapping("/{id}")
    public Expense getExpenseById(@PathVariable Long id) {

        return expenseService.getExpenseById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteExpenseById(@PathVariable Long id) {

        expenseService.deleteExpense(id);
    }

    @PutMapping("/{id}")
    public Expense updateExpense(
            @PathVariable Long id,
            @RequestBody Expense expense) {

        return expenseService.updateExpense(id, expense);
    }

    @GetMapping("/category/{category}")
    public List<Expense> getExpensesByCategory(
            @PathVariable String category) {

        return expenseService.getExpensesByCategory(category);
    }
    @GetMapping("/date/{date}")
    public List<Expense> getExpensesByDate
            ( @PathVariable LocalDate date) {
        return expenseService.getExpensesByDate(date);
    }
    @GetMapping("/amount")
    public List<Expense> getExpensesByAmountRange(
            @RequestParam Double min,
            @RequestParam Double max) {

        return expenseService.getExpensesByAmountRange(min, max);
    }

    @GetMapping("/total")
        public Double getTotalExpenses(){
            return expenseService.getTotalExpenses();
        }
    @GetMapping("/month/{month}")
    public Double getMonthExpenses(@PathVariable int month){
        return expenseService.getTotalExpensesByMonth(month);
    }

    @GetMapping("/count")
    public Long getExpenseCount() {
        return expenseService.getExpenseCount();
    }

    @GetMapping("/sorted")
    public List<Expense> getExpensesSortedByAmount() {
        return expenseService.getExpensesSortedByAmount();
    }

    @GetMapping("/page")
    public Page<Expense> getExpensesWithPagination(
            Pageable pageable) {

        return expenseService.getExpensesWithPagination(pageable);
    }

    @GetMapping("/search")
    public List<Expense> searchExpensesByTitle(
            @RequestParam String title) {

        return expenseService.searchExpensesByTitle(title);
    }

    }