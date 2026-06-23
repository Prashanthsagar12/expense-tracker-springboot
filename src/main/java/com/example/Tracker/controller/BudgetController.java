package com.example.Tracker.controller;

import com.example.Tracker.DTO.BudgetDTO;
import com.example.Tracker.DTO.BudgetDashboardDTO;
import com.example.Tracker.Service.BudgetService;
import com.example.Tracker.entity.Budget;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/budget")
public class BudgetController {

    private final BudgetService budgetService;

    public BudgetController(
            BudgetService budgetService) {

        this.budgetService = budgetService;
    }

    @PostMapping
    public Budget saveBudget(
            @RequestBody BudgetDTO dto) {

        return budgetService.saveBudget(dto);
    }

    @GetMapping("/dashboard")
    public BudgetDashboardDTO getDashboard(
            @RequestParam Integer month,
            @RequestParam Integer year) {

        return budgetService
                .getBudgetDashboard(
                        month,
                        year);
    }
}