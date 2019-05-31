package com.yulan.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class CustomerBalancePeriodDetail {

    private String saleNo;

    private String customerCode;

    private String billNo;

    private Date dateOutStock;

    private BigDecimal qty;

    private BigDecimal money;

    private BigDecimal freight;

    private BigDecimal gatherMoneyFax;

    private String notes;

    private String transFlag;

    private BigDecimal moneyRebate;

    private Integer total;


    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public CustomerBalancePeriodDetail() {
    }

    public String getSaleNo() {
        return saleNo;
    }

    public void setSaleNo(String saleNo) {
        this.saleNo = saleNo == null ? null : saleNo.trim();
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode == null ? null : customerCode.trim();
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo == null ? null : billNo.trim();
    }

    public Date getDateOutStock() {
        return dateOutStock;
    }

    public void setDateOutStock(Date dateOutStock) {
        this.dateOutStock = dateOutStock;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public BigDecimal getGatherMoneyFax() {
        return gatherMoneyFax;
    }

    public void setGatherMoneyFax(BigDecimal gatherMoneyFax) {
        this.gatherMoneyFax = gatherMoneyFax;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes == null ? null : notes.trim();
    }

    public String getTransFlag() {
        return transFlag;
    }

    public void setTransFlag(String transFlag) {
        this.transFlag = transFlag == null ? null : transFlag.trim();
    }

    public BigDecimal getMoneyRebate() {
        return moneyRebate;
    }

    public void setMoneyRebate(BigDecimal moneyRebate) {
        this.moneyRebate = moneyRebate;
    }
}
