package com.msp.webportal.businesslogic.dto.faq;

public class FaqCount {

    private Long count;

    private Long weeklycount;

    private Long monthlycount;

    public FaqCount() {
    }

    public FaqCount(Long count, Long weeklycount, Long monthlycount) {
        this.count = count;
        this.weeklycount = weeklycount;
        this.monthlycount = monthlycount;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getWeeklycount() {
        return weeklycount;
    }

    public void setWeeklycount(Long weeklycount) {
        this.weeklycount = weeklycount;
    }

    public Long getMonthlycount() {
        return monthlycount;
    }

    public void setMonthlycount(Long monthlycount) {
        this.monthlycount = monthlycount;
    }
}
