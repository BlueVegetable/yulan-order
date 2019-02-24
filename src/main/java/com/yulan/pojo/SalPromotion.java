package com.yulan.pojo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class SalPromotion {

    private String customerCode;
    private String customerType;
    private String orderType;
    private String orderName;
    private String itemNo;
    private String itemVersion;
    private String productType;
    private String productBrand;
    private String type;
    private BigDecimal discount;
    private BigDecimal price;
    private String falgFl;
    private BigDecimal qtyOrder;
    private Short qtyUse;
    private String flagXh;
    private String flagZx;
    private String useId;
    private Date dateStart;
    private Date dateEnd;
    private String groupType;

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode == null ? null : customerCode.trim();
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType == null ? null : customerType.trim();
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType == null ? null : orderType.trim();
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName == null ? null : orderName.trim();
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo == null ? null : itemNo.trim();
    }

    public String getItemVersion() {
        return itemVersion;
    }

    public void setItemVersion(String itemVersion) {
        this.itemVersion = itemVersion == null ? null : itemVersion.trim();
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType == null ? null : productType.trim();
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand == null ? null : productBrand.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getFalgFl() {
        return falgFl;
    }

    public void setFalgFl(String falgFl) {
        this.falgFl = falgFl == null ? null : falgFl.trim();
    }

    public BigDecimal getQtyOrder() {
        return qtyOrder;
    }

    public void setQtyOrder(BigDecimal qtyOrder) {
        this.qtyOrder = qtyOrder;
    }

    public Short getQtyUse() {
        return qtyUse;
    }

    public void setQtyUse(Short qtyUse) {
        this.qtyUse = qtyUse;
    }

    public String getFlagXh() {
        return flagXh;
    }

    public void setFlagXh(String flagXh) {
        this.flagXh = flagXh == null ? null : flagXh.trim();
    }

    public String getFlagZx() {
        return flagZx;
    }

    public void setFlagZx(String flagZx) {
        this.flagZx = flagZx == null ? null : flagZx.trim();
    }

    public String getUseId() {
        return useId;
    }

    public void setUseId(String useId) {
        this.useId = useId == null ? null : useId.trim();
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

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType == null ? null : groupType.trim();
    }

    @Override
    public String toString() {
        return "SalPromotion{" +
                "customerCode='" + customerCode + '\'' +
                ", customerType='" + customerType + '\'' +
                ", orderType='" + orderType + '\'' +
                ", orderName='" + orderName + '\'' +
                ", itemNo='" + itemNo + '\'' +
                ", itemVersion='" + itemVersion + '\'' +
                ", productType='" + productType + '\'' +
                ", productBrand='" + productBrand + '\'' +
                ", type='" + type + '\'' +
                ", discount=" + discount +
                ", price=" + price +
                ", falgFl='" + falgFl + '\'' +
                ", qtyOrder=" + qtyOrder +
                ", qtyUse=" + qtyUse +
                ", flagXh='" + flagXh + '\'' +
                ", flagZx='" + flagZx + '\'' +
                ", useId='" + useId + '\'' +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", groupType='" + groupType + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SalPromotion)) return false;
        SalPromotion that = (SalPromotion) o;
        return Objects.equals(getCustomerCode(), that.getCustomerCode()) &&
                Objects.equals(getCustomerType(), that.getCustomerType()) &&
                Objects.equals(getOrderType(), that.getOrderType()) &&
                Objects.equals(getOrderName(), that.getOrderName()) &&
                Objects.equals(getItemNo(), that.getItemNo()) &&
                Objects.equals(getItemVersion(), that.getItemVersion()) &&
                Objects.equals(getProductType(), that.getProductType()) &&
                Objects.equals(getProductBrand(), that.getProductBrand()) &&
                Objects.equals(getType(), that.getType()) &&
                Objects.equals(getDiscount(), that.getDiscount()) &&
                Objects.equals(getPrice(), that.getPrice()) &&
                Objects.equals(getFalgFl(), that.getFalgFl()) &&
                Objects.equals(getQtyOrder(), that.getQtyOrder()) &&
                Objects.equals(getQtyUse(), that.getQtyUse()) &&
                Objects.equals(getFlagXh(), that.getFlagXh()) &&
                Objects.equals(getFlagZx(), that.getFlagZx()) &&
                Objects.equals(getUseId(), that.getUseId()) &&
                Objects.equals(getDateStart(), that.getDateStart()) &&
                Objects.equals(getDateEnd(), that.getDateEnd()) &&
                Objects.equals(getGroupType(), that.getGroupType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomerCode(), getCustomerType(), getOrderType(), getOrderName(), getItemNo(), getItemVersion(), getProductType(), getProductBrand(), getType(), getDiscount(), getPrice(), getFalgFl(), getQtyOrder(), getQtyUse(), getFlagXh(), getFlagZx(), getUseId(), getDateStart(), getDateEnd(), getGroupType());
    }
}