package com.yulan.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class CustomerAssignments {
    private String customerCode;

    private String year;

    private String month;
    private BigDecimal assignments;

    private BigDecimal assignmentsTarget;

    private String status;

    private Date dateCre;


    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public BigDecimal getAssignments() {
        return assignments;
    }

    public void setAssignments(BigDecimal assignments) {
        this.assignments = assignments;
    }

    public BigDecimal getAssignmentsTarget() {
        return assignmentsTarget;
    }

    public void setAssignmentsTarget(BigDecimal assignmentsTarget) {
        this.assignmentsTarget = assignmentsTarget;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getDateCre() {
        return dateCre;
    }

    public void setDateCre(Date dateCre) {
        this.dateCre = dateCre;
    }
}