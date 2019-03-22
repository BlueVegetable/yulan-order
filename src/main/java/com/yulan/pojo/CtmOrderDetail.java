package com.yulan.pojo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class CtmOrderDetail {

    private String orderNo;
    private Long lineNo;
    private String itemNo;
    private String itemNoSample;
    private String productionVersion;
    private BigDecimal qtyRequired;
    private Date dateDeliver;
    private Date dateUpdate;
    private String notes;
    private String statusId;
    private BigDecimal discount;
    private String discountFlag;
    private String curtainKs;
    private String curtainRoomName;
    private Short curtainRoomId;
    private Short curtainRoomLineno;
    private BigDecimal curtainWidth;
    private BigDecimal curtainHeight;
    private BigDecimal curtainHeight2;
    private String curtainItemMenuNotes;
    private String curtainMenuGroupId;
    private BigDecimal curtainSizeTimes;
    private BigDecimal curtainSizeTimes2;
    private Short mlNonStandard;
    private BigDecimal curtainWbhSize;
    private String transType;
    private String transId;
    private Date dateDeliverKf;
    private Date dateDeliverNb;
    private Date dateDeliverFh;
    private BigDecimal unitPrice;
    private BigDecimal promotionCost;
    private String promotion;
    private String partSendId;
    private BigDecimal backY;
    private BigDecimal backM;
    private BigDecimal finalCost;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getLineNo() {
        return lineNo;
    }

    public void setLineNo(Long lineNo) {
        this.lineNo = lineNo;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemNoSample() {
        return itemNoSample;
    }

    public void setItemNoSample(String itemNoSample) {
        this.itemNoSample = itemNoSample;
    }

    public String getProductionVersion() {
        return productionVersion;
    }

    public void setProductionVersion(String productionVersion) {
        this.productionVersion = productionVersion;
    }

    public BigDecimal getQtyRequired() {
        return qtyRequired;
    }

    public void setQtyRequired(BigDecimal qtyRequired) {
        this.qtyRequired = qtyRequired;
    }

    public Date getDateDeliver() {
        return dateDeliver;
    }

    public void setDateDeliver(Date dateDeliver) {
        this.dateDeliver = dateDeliver;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getDiscountFlag() {
        return discountFlag;
    }

    public void setDiscountFlag(String discountFlag) {
        this.discountFlag = discountFlag;
    }

    public String getCurtainKs() {
        return curtainKs;
    }

    public void setCurtainKs(String curtainKs) {
        this.curtainKs = curtainKs;
    }

    public String getCurtainRoomName() {
        return curtainRoomName;
    }

    public void setCurtainRoomName(String curtainRoomName) {
        this.curtainRoomName = curtainRoomName;
    }

    public Short getCurtainRoomId() {
        return curtainRoomId;
    }

    public void setCurtainRoomId(Short curtainRoomId) {
        this.curtainRoomId = curtainRoomId;
    }

    public Short getCurtainRoomLineno() {
        return curtainRoomLineno;
    }

    public void setCurtainRoomLineno(Short curtainRoomLineno) {
        this.curtainRoomLineno = curtainRoomLineno;
    }

    public BigDecimal getCurtainWidth() {
        return curtainWidth;
    }

    public void setCurtainWidth(BigDecimal curtainWidth) {
        this.curtainWidth = curtainWidth;
    }

    public BigDecimal getCurtainHeight() {
        return curtainHeight;
    }

    public void setCurtainHeight(BigDecimal curtainHeight) {
        this.curtainHeight = curtainHeight;
    }

    public BigDecimal getCurtainHeight2() {
        return curtainHeight2;
    }

    public void setCurtainHeight2(BigDecimal curtainHeight2) {
        this.curtainHeight2 = curtainHeight2;
    }

    public String getCurtainItemMenuNotes() {
        return curtainItemMenuNotes;
    }

    public void setCurtainItemMenuNotes(String curtainItemMenuNotes) {
        this.curtainItemMenuNotes = curtainItemMenuNotes;
    }

    public String getCurtainMenuGroupId() {
        return curtainMenuGroupId;
    }

    public void setCurtainMenuGroupId(String curtainMenuGroupId) {
        this.curtainMenuGroupId = curtainMenuGroupId;
    }

    public BigDecimal getCurtainSizeTimes() {
        return curtainSizeTimes;
    }

    public void setCurtainSizeTimes(BigDecimal curtainSizeTimes) {
        this.curtainSizeTimes = curtainSizeTimes;
    }

    public BigDecimal getCurtainSizeTimes2() {
        return curtainSizeTimes2;
    }

    public void setCurtainSizeTimes2(BigDecimal curtainSizeTimes2) {
        this.curtainSizeTimes2 = curtainSizeTimes2;
    }

    public Short getMlNonStandard() {
        return mlNonStandard;
    }

    public void setMlNonStandard(Short mlNonStandard) {
        this.mlNonStandard = mlNonStandard;
    }

    public BigDecimal getCurtainWbhSize() {
        return curtainWbhSize;
    }

    public void setCurtainWbhSize(BigDecimal curtainWbhSize) {
        this.curtainWbhSize = curtainWbhSize;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public Date getDateDeliverKf() {
        return dateDeliverKf;
    }

    public void setDateDeliverKf(Date dateDeliverKf) {
        this.dateDeliverKf = dateDeliverKf;
    }

    public Date getDateDeliverNb() {
        return dateDeliverNb;
    }

    public void setDateDeliverNb(Date dateDeliverNb) {
        this.dateDeliverNb = dateDeliverNb;
    }

    public Date getDateDeliverFh() {
        return dateDeliverFh;
    }

    public void setDateDeliverFh(Date dateDeliverFh) {
        this.dateDeliverFh = dateDeliverFh;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getPromotionCost() {
        return promotionCost;
    }

    public void setPromotionCost(BigDecimal promotionCost) {
        this.promotionCost = promotionCost;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public String getPartSendId() {
        return partSendId;
    }

    public void setPartSendId(String partSendId) {
        this.partSendId = partSendId;
    }

    public BigDecimal getBackY() {
        return backY;
    }

    public void setBackY(BigDecimal backY) {
        this.backY = backY;
    }

    public BigDecimal getBackM() {
        return backM;
    }

    public void setBackM(BigDecimal backM) {
        this.backM = backM;
    }

    public BigDecimal getFinalCost() {
        return finalCost;
    }

    public void setFinalCost(BigDecimal finalCost) {
        this.finalCost = finalCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CtmOrderDetail)) return false;
        CtmOrderDetail that = (CtmOrderDetail) o;
        return Objects.equals(getOrderNo(), that.getOrderNo()) &&
                Objects.equals(getLineNo(), that.getLineNo()) &&
                Objects.equals(getItemNo(), that.getItemNo()) &&
                Objects.equals(getItemNoSample(), that.getItemNoSample()) &&
                Objects.equals(getProductionVersion(), that.getProductionVersion()) &&
                Objects.equals(getQtyRequired(), that.getQtyRequired()) &&
                Objects.equals(getDateDeliver(), that.getDateDeliver()) &&
                Objects.equals(getDateUpdate(), that.getDateUpdate()) &&
                Objects.equals(getNotes(), that.getNotes()) &&
                Objects.equals(getStatusId(), that.getStatusId()) &&
                Objects.equals(getDiscount(), that.getDiscount()) &&
                Objects.equals(getDiscountFlag(), that.getDiscountFlag()) &&
                Objects.equals(getCurtainKs(), that.getCurtainKs()) &&
                Objects.equals(getCurtainRoomName(), that.getCurtainRoomName()) &&
                Objects.equals(getCurtainRoomId(), that.getCurtainRoomId()) &&
                Objects.equals(getCurtainRoomLineno(), that.getCurtainRoomLineno()) &&
                Objects.equals(getCurtainWidth(), that.getCurtainWidth()) &&
                Objects.equals(getCurtainHeight(), that.getCurtainHeight()) &&
                Objects.equals(getCurtainHeight2(), that.getCurtainHeight2()) &&
                Objects.equals(getCurtainItemMenuNotes(), that.getCurtainItemMenuNotes()) &&
                Objects.equals(getCurtainMenuGroupId(), that.getCurtainMenuGroupId()) &&
                Objects.equals(getCurtainSizeTimes(), that.getCurtainSizeTimes()) &&
                Objects.equals(getCurtainSizeTimes2(), that.getCurtainSizeTimes2()) &&
                Objects.equals(getMlNonStandard(), that.getMlNonStandard()) &&
                Objects.equals(getCurtainWbhSize(), that.getCurtainWbhSize()) &&
                Objects.equals(getTransType(), that.getTransType()) &&
                Objects.equals(getTransId(), that.getTransId()) &&
                Objects.equals(getDateDeliverKf(), that.getDateDeliverKf()) &&
                Objects.equals(getDateDeliverNb(), that.getDateDeliverNb()) &&
                Objects.equals(getDateDeliverFh(), that.getDateDeliverFh()) &&
                Objects.equals(getUnitPrice(), that.getUnitPrice()) &&
                Objects.equals(getPromotionCost(), that.getPromotionCost()) &&
                Objects.equals(getPromotion(), that.getPromotion()) &&
                Objects.equals(getPartSendId(), that.getPartSendId()) &&
                Objects.equals(getBackY(), that.getBackY()) &&
                Objects.equals(getBackM(), that.getBackM()) &&
                Objects.equals(getFinalCost(), that.getFinalCost());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderNo(), getLineNo(), getItemNo(), getItemNoSample(), getProductionVersion(), getQtyRequired(), getDateDeliver(), getDateUpdate(), getNotes(), getStatusId(), getDiscount(), getDiscountFlag(), getCurtainKs(), getCurtainRoomName(), getCurtainRoomId(), getCurtainRoomLineno(), getCurtainWidth(), getCurtainHeight(), getCurtainHeight2(), getCurtainItemMenuNotes(), getCurtainMenuGroupId(), getCurtainSizeTimes(), getCurtainSizeTimes2(), getMlNonStandard(), getCurtainWbhSize(), getTransType(), getTransId(), getDateDeliverKf(), getDateDeliverNb(), getDateDeliverFh(), getUnitPrice(), getPromotionCost(), getPromotion(), getPartSendId(), getBackY(), getBackM(), getFinalCost());
    }
}