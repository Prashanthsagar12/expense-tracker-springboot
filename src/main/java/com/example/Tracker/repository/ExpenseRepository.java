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
    @Query("""
       SELECT COALESCE(SUM(e.amount),0)
       FROM Expense e
       WHERE e.user.username = :username
       """)
    Double getTotalExpenses(String username);
    @Query("SELECT SUM(e.amount) FROM Expense e WHERE MONTH(e.date) = :month")
    Double getTotalExpensesByMonth(int month);
    @Query("""
       SELECT COUNT(e)
       FROM Expense e
       WHERE e.user.username = :username
       """)
    Long getExpenseCount(String username);
    List<Expense> findAllByOrderByAmountDesc();
    List<Expense> findByTitleContainingIgnoreCase(String title);
    @Query("SELECT e.category, COUNT(e) FROM Expense e GROUP BY e.category ORDER BY COUNT(e) ASC")
    List<Object[]> countExpensesByCategoryASC();
    @Query("""
       SELECT e.category, SUM(e.amount)
       FROM Expense e
       GROUP BY e.category
       ORDER BY SUM(e.amount) DESC
       """)
    List<Object[]> getCategoryTotal();
    @Query("""
       SELECT MAX(e.amount)
       FROM Expense e
       WHERE e.user.username = :username
       """)
    Double getHighestExpense(String username);

    @Query("""
       SELECT MIN(e.amount)
       FROM Expense e
       WHERE e.user.username = :username
       """)
    Double getLowestExpense(String username);

    @Query("""
       SELECT AVG(e.amount)
       FROM Expense e
       WHERE e.user.username = :username
       """)
    Double getAverageExpense(String username);

    List<Expense> findByUserUsername(String username);
    long countByUserUsername(String username);
    @Query("""
       SELECT COALESCE(SUM(e.amount),0)
       FROM Expense e
       WHERE e.user.username = :username
       """)
    Double getTotalAmountByUsername(String username);


    @Query("""
       SELECT e.date, SUM(e.amount)
       FROM Expense e
       WHERE e.user.username = :username
       GROUP BY e.date
       ORDER BY e.date
       """)
    List<Object[]> getdailyExpenseSummary(String username);


}