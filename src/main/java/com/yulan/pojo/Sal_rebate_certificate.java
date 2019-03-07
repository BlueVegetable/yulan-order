package com.yulan.pojo;

import java.math.BigDecimal;
import java.sql.Date;

public class Sal_rebate_certificate {
    private String id;

    private String rebateType;

    private String customerCode;

    private BigDecimal rebateMoney;

    private BigDecimal rebateMoneyOver;

    private Date dateStart;

    private Date dateEnd;

    private String status;

    private String dateId;//过期标志，0过期，1没过期

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    private  String notes;

    public String getDateId() {
        return dateId;
    }

    public void setDateId(String dateId) {
        this.dateId = dateId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRebateType() {
        return rebateType;
    }

    public void setRebateType(String rebateType) {
        this.rebateType = rebateType == null ? null : rebateType.trim();
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode == null ? null : customerCode.trim();
    }

    public BigDecimal getRebateMoney() {
        return rebateMoney;
    }

    public void setRebateMoney(BigDecimal rebateMoney) {
        this.rebateMoney = rebateMoney;
    }

    public BigDecimal getRebateMoneyOver() {
        return rebateMoneyOver;
    }

    public void setRebateMoneyOver(BigDecimal rebateMoneyOver) {
        this.rebateMoneyOver = rebateMoneyOver;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}