package com.yulan.pojo;

import java.math.BigDecimal;
import java.sql.Date;

public class Ctm_order_detail  {
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

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo == null ? null : itemNo.trim();
    }

    public String getItemNoSample() {
        return itemNoSample;
    }

    public void setItemNoSample(String itemNoSample) {
        this.itemNoSample = itemNoSample == null ? null : itemNoSample.trim();
    }

    public String getProductionVersion() {
        return productionVersion;
    }

    public void setProductionVersion(String productionVersion) {
        this.productionVersion = productionVersion == null ? null : productionVersion.trim();
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
        this.notes = notes == null ? null : notes.trim();
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId == null ? null : statusId.trim();
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
        this.discountFlag = discountFlag == null ? null : discountFlag.trim();
    }

    public String getCurtainKs() {
        return curtainKs;
    }

    public void setCurtainKs(String curtainKs) {
        this.curtainKs = curtainKs == null ? null : curtainKs.trim();
    }

    public String getCurtainRoomName() {
        return curtainRoomName;
    }

    public void setCurtainRoomName(String curtainRoomName) {
        this.curtainRoomName = curtainRoomName == null ? null : curtainRoomName.trim();
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
        this.curtainItemMenuNotes = curtainItemMenuNotes == null ? null : curtainItemMenuNotes.trim();
    }

    public String getCurtainMenuGroupId() {
        return curtainMenuGroupId;
    }

    public void setCurtainMenuGroupId(String curtainMenuGroupId) {
        this.curtainMenuGroupId = curtainMenuGroupId == null ? null : curtainMenuGroupId.trim();
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
        this.transType = transType == null ? null : transType.trim();
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId == null ? null : transId.trim();
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
}