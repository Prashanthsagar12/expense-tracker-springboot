package com.example.Tracker.Service;

import com.example.Tracker.entity.Expense;
import com.example.Tracker.repository.ExpenseRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExcelExportService {

    private final ExpenseRepository expenseRepository;

    public ExcelExportService(
            ExpenseRepository expenseRepository) {

        this.expenseRepository = expenseRepository;
    }

    public Workbook exportExpenses() {

        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Expenses");

        Row headerRow = sheet.createRow(0);

        headerRow.createCell(0).setCellValue("Title");
        headerRow.createCell(1).setCellValue("Amount");
        headerRow.createCell(2).setCellValue("Category");
        headerRow.createCell(3).setCellValue("Date");

        String username =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        List<Expense> expenses =
                expenseRepository.findByUserUsername(username);

        int rowNum = 1;

        for (Expense expense : expenses) {

            Row row = sheet.createRow(rowNum++);

            row.createCell(0)
                    .setCellValue(expense.getTitle());

            row.createCell(1)
                    .setCellValue(expense.getAmount());

            row.createCell(2)
                    .setCellValue(expense.getCategory());

            row.createCell(3)
                    .setCellValue(expense.getDate().toString());
        }

        return workbook;
    }
}