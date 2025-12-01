package com.revature.expense.model;

public final class Approval {
    private final int id;
    private final int expenseId;
    private final String status;
    private final Integer reviewer;
    private final String comment;
    private final String reviewDate;

    public Approval(int id, int expenseId, String status, Integer reviewer, String comment, String reviewDate) {
        this.id = id;
        this.expenseId = expenseId;
        this.status = status;
        this.reviewer = reviewer;
        this.comment = comment;
        this.reviewDate = reviewDate;
    }

    public int getId(){ return id; }
    public int getExpenseId(){ return expenseId; }
    public String getStatus(){ return status; }
    public Integer getReviewer(){ return reviewer; }
    public String getComment(){ return comment; }
    public String getReviewDate(){ return reviewDate; }

    @Override
    public String toString() {
        return "Approval{id=" + id + ", expenseId=" + expenseId + ", status=" + status + ", reviewer=" + reviewer + "}";
    }
}
