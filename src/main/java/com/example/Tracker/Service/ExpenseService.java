package com.example.Tracker.Service;
import com.example.Tracker.DTO.DashBoardDTO;
import com.example.Tracker.entity.Expense;
import com.example.Tracker.entity.User;
import com.example.Tracker.exception.ResourceNotFoundException;
import com.example.Tracker.repository.ExpenseRepository;
import com.example.Tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;import java.util.List;
import java.util.Map;

@Service
public class ExpenseService {


    public ExpenseService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final UserRepository userRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    public Expense saveExpense(Expense expense) {

        if (expense.getDate() == null) {
            expense.setDate(LocalDate.now());
        }

        String username =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        User user =
                userRepository
                        .findByUsername(username)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "User not found"));

        expense.setUser(user);
        return expenseRepository.save(expense);
    }

    public List<Expense> getAllExpenses() {
        String username =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        return expenseRepository.findByUserUsername(username);
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

        String username =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        return expenseRepository.getTotalExpenses(username);
    }

    public Long getExpenseCount() {

        String username =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        return expenseRepository.getExpenseCount(username);
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

    public Map<String, Long> getCategorySummary() {

        List<Object[]> results =
                expenseRepository.countExpensesByCategoryASC();

        Map<String, Long> summary = new LinkedHashMap<>();

        for (Object[] row : results) {

            String category = (String) row[0];
            Long count = (Long) row[1];

            summary.put(category, count);
        }

        return summary;
    }


    public Map<String, Double> getCategoryTotal() {

        List<Object[]> results =
                expenseRepository.getCategoryTotal();

        Map<String, Double> totals = new LinkedHashMap<>();

        for (Object[] row : results) {

            String category = (String) row[0];
            Double total = ((Number) row[1]).doubleValue();

            totals.put(category, total);
        }

        return totals;
    }


    public DashBoardDTO getDashboard() {

        DashBoardDTO dashboard = new DashBoardDTO();

        String username =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        dashboard.setTotalExpense(
                expenseRepository.getTotalExpenses(username));

        dashboard.setTotalRecords(
                expenseRepository.getExpenseCount(username));

        dashboard.setHighestExpense(
                expenseRepository.getHighestExpense(username));

        dashboard.setLowestExpense(
                expenseRepository.getLowestExpense(username));

        dashboard.setAverageExpense(
                expenseRepository.getAverageExpense(username));;

        return dashboard;
    }

    public Double getTotalExpensesByMonth(int month) {
        return expenseRepository.getTotalExpensesByMonth(month);
    }

    public Map<LocalDate, Double> getDailyExpenseSummary() {
        String username =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();
        List<Object[]> results=expenseRepository.getdailyExpenseSummary(username);
        Map<LocalDate, Double> summary = new LinkedHashMap<>();

        for (Object[] row : results) {

            LocalDate date = (LocalDate) row[0];
            Double total = ((Number) row[1]).doubleValue();

            summary.put(date, total);
        }

        return summary;
    }
}
