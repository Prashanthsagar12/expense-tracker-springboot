package com.example.Tracker.repository;

import com.example.Tracker.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
List<Expense> findByCategory(String category);
List<Expense> findByDate(LocalDate date);
List<Expense> getExpenseById(int id);
    List<Expense> findByAmountBetween(
            Double min,
            Double max);
    @Query("SELECT SUM(e.amount) FROM Expense e")
    Double getTotalExpenses();
    @Query("SELECT SUM(e.amount) FROM Expense e WHERE MONTH(e.date) = :month")
    Double getTotalExpensesByMonth(int month);
    @Query("SELECT COUNT(e) FROM Expense e")
    Long getExpenseCount();
    List<Expense> findAllByOrderByAmountDesc();
    List<Expense> findByTitleContainingIgnoreCase(String title);

}