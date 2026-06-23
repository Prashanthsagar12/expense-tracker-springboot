package com.example.Tracker.Service;

import com.example.Tracker.DTO.BudgetDTO;
import com.example.Tracker.DTO.BudgetDashboardDTO;
import com.example.Tracker.entity.Budget;
import com.example.Tracker.entity.User;
import com.example.Tracker.repository.BudgetRepository;
import com.example.Tracker.repository.ExpenseRepository;
import com.example.Tracker.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final UserRepository userRepository;
    private final ExpenseRepository expenseRepository;

    public BudgetService(
            BudgetRepository budgetRepository,
            UserRepository userRepository, ExpenseRepository expenseRepository) {

        this.budgetRepository = budgetRepository;
        this.userRepository = userRepository;
        this.expenseRepository = expenseRepository;
    }

    public Budget saveBudget(BudgetDTO dto) {

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

        Budget budget =
                budgetRepository
                        .findByUserUsernameAndMonthAndYear(
                                username,
                                dto.getMonth(),
                                dto.getYear())
                        .orElse(new Budget());

        budget.setMonth(dto.getMonth());
        budget.setYear(dto.getYear());
        budget.setAmount(dto.getAmount());
        budget.setUser(user);

        return budgetRepository.save(budget);
    }


    public BudgetDashboardDTO getBudgetDashboard(
            Integer month,
            Integer year) {

        String username =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        Budget budget =
                budgetRepository
                        .findByUserUsernameAndMonthAndYear(
                                username,
                                month,
                                year)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Budget not found"));

        Double spent =
                expenseRepository
                        .getTotalExpensesByMonth(
                                username,
                                month);

        if (spent == null) {
            spent = 0.0;
        }

        BudgetDashboardDTO dto =
                new BudgetDashboardDTO();

        dto.setBudget(budget.getAmount());
        dto.setSpent(spent);
        dto.setRemaining(
                budget.getAmount() - spent);

        dto.setPercentageUsed(
                (spent / budget.getAmount()) * 100);

        return dto;
    }
}