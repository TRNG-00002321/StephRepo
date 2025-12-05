package com.revature.expense.model;

public final class Expense {
    private final int id;
    private final int userId;
    private final double amount;
    private final String description;
    private final String date; // ISO yyyy-MM-dd

    public Expense(int id, int userId, double amount, String description, String date) {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.description = description;
        this.date = date;
    }

    public Expense(int userId, double amount, String description, String date) {
        this(-1, userId, amount, description, date);
    }

    public int getId(){ return id; }
    public int getUserId(){ return userId; }
    public double getAmount(){ return amount; }
    public String getDescription(){ return description; }
    public String getDate(){ return date; }

    @Override
    public String toString() {
        return " " + id + " | " + description + " | " + date + " | " + amount;
    }
}
