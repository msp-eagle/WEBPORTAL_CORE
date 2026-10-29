package com.msp.webportal.businesslogic.dto;

public class Pageable {

    private Integer size;
    private Integer start;
    private String sortBy;


    public Pageable(Integer size, Integer start) {
        this.size = size;
        this.start = start;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }
}
