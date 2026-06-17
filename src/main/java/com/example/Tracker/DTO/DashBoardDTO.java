package com.example.Tracker.DTO;

public class DashBoardDTO {

    private Double totalExpense;
    private Long totalRecords;
    private Double highestExpense;
    private Double lowestExpense;
    private Double averageExpense;
    
    public Double getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(Double totalExpense) {
        this.totalExpense = totalExpense;
    }

    public Long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public Double getHighestExpense() {
        return highestExpense;
    }

    public void setHighestExpense(Double highestExpense) {
        this.highestExpense = highestExpense;
    }

    public Double getLowestExpense() {
        return lowestExpense;
    }

    public void setLowestExpense(Double lowestExpense) {
        this.lowestExpense = lowestExpense;
    }

    public Double getAverageExpense() {
        return averageExpense;
    }

    public void setAverageExpense(Double averageExpense) {
        this.averageExpense = averageExpense;
    }
}