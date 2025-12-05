package com.revature.expense.model;

import java.math.BigDecimal;

public final class CategoryTotal {
    private final String category;
    private final BigDecimal total;

    public CategoryTotal(String category, BigDecimal total) {
        this.category = category;
        this.total = total;
    }

    public String getCategory() { return category; }
    public BigDecimal getTotal() { return total; }

    @Override
    public String toString() {
        return category + " | " + total;
    }
}
