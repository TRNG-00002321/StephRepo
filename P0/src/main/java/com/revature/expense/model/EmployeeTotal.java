package com.revature.expense.model;

import java.math.BigDecimal;

public final class EmployeeTotal {
    private final int userId;
    private final String username;
    private final BigDecimal total;

    public EmployeeTotal(int userId, String username, BigDecimal total) {
        this.userId = userId;
        this.username = username;
        this.total = total;
    }

    public int getUserId() { return userId; }
    public String getUsername() { return username; }
    public BigDecimal getTotal() { return total; }

    @Override
    public String toString() {
        return username + " | " + total;
    }
}
