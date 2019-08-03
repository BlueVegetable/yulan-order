package com.yulan.pojo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class PackDetail{

    private String saleNo;
    private Long saleLineNo;
    private String customerCode;
    private String itemNo;
    private String batchNo;
    private String stockNo;
    private BigDecimal qtyDeliver;
    private Date dateOutStock;
    private String transId;
    private String transcompany;
    private BigDecimal salePrice;
    private BigDecimal money;
    private String itemVersion;
    private String customerCode2;
    private String manager;
    private BigDecimal transPrice;
    private BigDecimal rateAgio;
    private BigDecimal discountRate;
    private String accountMonth;
    private String customerCodeO2o;
    private String itemNote;
    private String invoiceNo;
    private String customerMain;
    private Date billDate;
    private String accouType;
    private String webOrderNo;
    private BigDecimal freight;
    private BigDecimal lineNo;

    public String getSaleNo() {
        return saleNo;
    }

    public void setSaleNo(String saleNo) {
        this.saleNo = saleNo;
    }

    public Long getSaleLineNo() {
        return saleLineNo;
    }

    public void setSaleLineNo(Long saleLineNo) {
        this.saleLineNo = saleLineNo;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getStockNo() {
        return stockNo;
    }

    public void setStockNo(String stockNo) {
        this.stockNo = stockNo;
    }

    public BigDecimal getQtyDeliver() {
        return qtyDeliver;
    }

    public void setQtyDeliver(BigDecimal qtyDeliver) {
        this.qtyDeliver = qtyDeliver;
    }

    public Date getDateOutStock() {
        return dateOutStock;
    }

    public void setDateOutStock(Date dateOutStock) {
        this.dateOutStock = dateOutStock;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getTranscompany() {
        return transcompany;
    }

    public void setTranscompany(String transcompany) {
        this.transcompany = transcompany;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getItemVersion() {
        return itemVersion;
    }

    public void setItemVersion(String itemVersion) {
        this.itemVersion = itemVersion;
    }

    public String getCustomerCode2() {
        return customerCode2;
    }

    public void setCustomerCode2(String customerCode2) {
        this.customerCode2 = customerCode2;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public BigDecimal getTransPrice() {
        return transPrice;
    }

    public void setTransPrice(BigDecimal transPrice) {
        this.transPrice = transPrice;
    }

    public BigDecimal getRateAgio() {
        return rateAgio;
    }

    public void setRateAgio(BigDecimal rateAgio) {
        this.rateAgio = rateAgio;
    }

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    public String getAccountMonth() {
        return accountMonth;
    }

    public void setAccountMonth(String accountMonth) {
        this.accountMonth = accountMonth;
    }

    public String getCustomerCodeO2o() {
        return customerCodeO2o;
    }

    public void setCustomerCodeO2o(String customerCodeO2o) {
        this.customerCodeO2o = customerCodeO2o;
    }

    public String getItemNote() {
        return itemNote;
    }

    public void setItemNote(String itemNote) {
        this.itemNote = itemNote;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getCustomerMain() {
        return customerMain;
    }

    public void setCustomerMain(String customerMain) {
        this.customerMain = customerMain;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public String getAccouType() {
        return accouType;
    }

    public void setAccouType(String accouType) {
        this.accouType = accouType;
    }

    public String getWebOrderNo() {
        return webOrderNo;
    }

    public void setWebOrderNo(String webOrderNo) {
        this.webOrderNo = webOrderNo;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public BigDecimal getLineNo() {
        return lineNo;
    }

    public void setLineNo(BigDecimal lineNo) {
        this.lineNo = lineNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PackDetail)) return false;
        PackDetail that = (PackDetail) o;
        return Objects.equals(getSaleNo(), that.getSaleNo()) &&
                Objects.equals(getSaleLineNo(), that.getSaleLineNo()) &&
                Objects.equals(getCustomerCode(), that.getCustomerCode()) &&
                Objects.equals(getItemNo(), that.getItemNo()) &&
                Objects.equals(getBatchNo(), that.getBatchNo()) &&
                Objects.equals(getStockNo(), that.getStockNo()) &&
                Objects.equals(getQtyDeliver(), that.getQtyDeliver()) &&
                Objects.equals(getDateOutStock(), that.getDateOutStock()) &&
                Objects.equals(getTransId(), that.getTransId()) &&
                Objects.equals(getTranscompany(), that.getTranscompany()) &&
                Objects.equals(getSalePrice(), that.getSalePrice()) &&
                Objects.equals(getMoney(), that.getMoney()) &&
                Objects.equals(getItemVersion(), that.getItemVersion()) &&
                Objects.equals(getCustomerCode2(), that.getCustomerCode2()) &&
                Objects.equals(getManager(), that.getManager()) &&
                Objects.equals(getTransPrice(), that.getTransPrice()) &&
                Objects.equals(getRateAgio(), that.getRateAgio()) &&
                Objects.equals(getDiscountRate(), that.getDiscountRate()) &&
                Objects.equals(getAccountMonth(), that.getAccountMonth()) &&
                Objects.equals(getCustomerCodeO2o(), that.getCustomerCodeO2o()) &&
                Objects.equals(getItemNote(), that.getItemNote()) &&
                Objects.equals(getInvoiceNo(), that.getInvoiceNo()) &&
                Objects.equals(getCustomerMain(), that.getCustomerMain()) &&
                Objects.equals(getBillDate(), that.getBillDate()) &&
                Objects.equals(getAccouType(), that.getAccouType()) &&
                Objects.equals(getWebOrderNo(), that.getWebOrderNo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSaleNo(), getSaleLineNo(), getCustomerCode(), getItemNo(), getBatchNo(), getStockNo(), getQtyDeliver(), getDateOutStock(), getTransId(), getTranscompany(), getSalePrice(), getMoney(), getItemVersion(), getCustomerCode2(), getManager(), getTransPrice(), getRateAgio(), getDiscountRate(), getAccountMonth(), getCustomerCodeO2o(), getItemNote(), getInvoiceNo(), getCustomerMain(), getBillDate(), getAccouType(), getWebOrderNo());
    }

    @Override
    public String toString() {
        return "PackDetail{" +
                "saleNo='" + saleNo + '\'' +
                ", saleLineNo=" + saleLineNo +
                ", customerCode='" + customerCode + '\'' +
                ", itemNo='" + itemNo + '\'' +
                ", batchNo='" + batchNo + '\'' +
                ", stockNo='" + stockNo + '\'' +
                ", qtyDeliver=" + qtyDeliver +
                ", dateOutStock=" + dateOutStock +
                ", transId='" + transId + '\'' +
                ", transcompany='" + transcompany + '\'' +
                ", salePrice=" + salePrice +
                ", money=" + money +
                ", itemVersion='" + itemVersion + '\'' +
                ", customerCode2='" + customerCode2 + '\'' +
                ", manager='" + manager + '\'' +
                ", transPrice=" + transPrice +
                ", rateAgio=" + rateAgio +
                ", discountRate=" + discountRate +
                ", accountMonth='" + accountMonth + '\'' +
                ", customerCodeO2o='" + customerCodeO2o + '\'' +
                ", itemNote='" + itemNote + '\'' +
                ", invoiceNo='" + invoiceNo + '\'' +
                ", customerMain='" + customerMain + '\'' +
                ", billDate=" + billDate +
                ", accouType='" + accouType + '\'' +
                ", webOrderNo='" + webOrderNo + '\'' +
                '}';
    }
}