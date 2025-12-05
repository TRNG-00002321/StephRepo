package com.revature.expense.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public final class DateTotal {
    private final LocalDate date;
    private final BigDecimal total;

    public DateTotal(LocalDate date, BigDecimal total) {
        this.date = date;
        this.total = total;
    }

    public LocalDate getDate() { return date; }
    public BigDecimal getTotal() { return total; }

    @Override
    public String toString() {
        return date + " | " + total;
    }
}
