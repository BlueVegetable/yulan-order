package com.yulan.pojo;

import java.math.BigDecimal;
import java.sql.Date;

public class Ctm_order_detail  {
    private String orderNo;

    private String itemNo;
    private Integer lineNo;

    private String itemNoSample;

    public Integer getLineNo() {
        return lineNo;
    }

    public void setLineNo(Integer lineNo) {
        this.lineNo = lineNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    private String productionVersion;

    private BigDecimal qtyRequired;

    private java.util.Date dateDeliver;

    private java.util.Date dateUpdate;

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

    private java.util.Date dateDeliverKf;

    private java.util.Date dateDeliverNb;

    private java.util.Date dateDeliverFh;

    private BigDecimal unitPrice;
    private BigDecimal promotionCost;

    private String promotion;

    private String promotionType;

    private String ljSuggestion;

    private String flagFlType;

    private BigDecimal onlineSalesAmount;

    public BigDecimal getOnlineSalesAmount() {
        return onlineSalesAmount;
    }

    public void setOnlineSalesAmount(BigDecimal onlineSalesAmount) {
        this.onlineSalesAmount = onlineSalesAmount;
    }

    public String getFlagFlType() {
        return flagFlType;
    }

    public void setFlagFlType(String flagFlType) {
        this.flagFlType = flagFlType;
    }



    public Ctm_order_detail(String orderNo, Integer lineNo, String ljSuggestion) {
        this.orderNo = orderNo;
        this.lineNo = lineNo;
        this.ljSuggestion = ljSuggestion;
    }

    public String getLjSuggestion() {
        return ljSuggestion;
    }

    public void setLjSuggestion(String ljSuggestion) {
        this.ljSuggestion = ljSuggestion;
    }

    public String getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(String promotionType) {
        this.promotionType = promotionType;
    }

    private String partSendId;

    private BigDecimal backY;//年返利

    private BigDecimal backM;//月放利

    private BigDecimal finalCost;//最终花费（活动加券后）

    public BigDecimal getFinalCost() {
        return finalCost;
    }

    public void setFinalCost(BigDecimal finalCost) {
        this.finalCost = finalCost;
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

    public String getPartSendId() {
        return partSendId;
    }

    public void setPartSendId(String partSendId) {
        this.partSendId = partSendId;
    }

    public Ctm_order_detail(String orderNo, String itemNo, Integer lineNo, String itemNoSample, String productionVersion, BigDecimal qtyRequired, java.util.Date dateDeliver, java.util.Date dateUpdate, String notes, String statusId, BigDecimal discount, String discountFlag, String curtainKs, String curtainRoomName, Short curtainRoomId, Short curtainRoomLineno, BigDecimal curtainWidth, BigDecimal curtainHeight, BigDecimal curtainHeight2, String curtainItemMenuNotes, String curtainMenuGroupId, BigDecimal curtainSizeTimes, BigDecimal curtainSizeTimes2, Short mlNonStandard, BigDecimal curtainWbhSize, String transType, String transId, java.util.Date dateDeliverKf, java.util.Date dateDeliverNb, java.util.Date dateDeliverFh, BigDecimal unitPrice, BigDecimal promotionCost, String promotion, String partSendId) {
        this.orderNo = orderNo;
        this.itemNo = itemNo;
        this.lineNo = lineNo;
        this.itemNoSample = itemNoSample;
        this.productionVersion = productionVersion;
        this.qtyRequired = qtyRequired;
        this.dateDeliver = dateDeliver;
        this.dateUpdate = dateUpdate;
        this.notes = notes;
        this.statusId = statusId;
        this.discount = discount;
        this.discountFlag = discountFlag;
        this.curtainKs = curtainKs;
        this.curtainRoomName = curtainRoomName;
        this.curtainRoomId = curtainRoomId;
        this.curtainRoomLineno = curtainRoomLineno;
        this.curtainWidth = curtainWidth;
        this.curtainHeight = curtainHeight;
        this.curtainHeight2 = curtainHeight2;
        this.curtainItemMenuNotes = curtainItemMenuNotes;
        this.curtainMenuGroupId = curtainMenuGroupId;
        this.curtainSizeTimes = curtainSizeTimes;
        this.curtainSizeTimes2 = curtainSizeTimes2;
        this.mlNonStandard = mlNonStandard;
        this.curtainWbhSize = curtainWbhSize;
        this.transType = transType;
        this.transId = transId;
        this.dateDeliverKf = dateDeliverKf;
        this.dateDeliverNb = dateDeliverNb;
        this.dateDeliverFh = dateDeliverFh;
        this.unitPrice = unitPrice;
        this.promotionCost = promotionCost;
        this.promotion = promotion;
        this.partSendId = partSendId;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public Ctm_order_detail(String itemNo, String itemNoSample, String productionVersion, BigDecimal qtyRequired, java.util.Date dateDeliver, java.util.Date dateUpdate, String notes, String statusId, BigDecimal discount, String discountFlag, String curtainKs, String curtainRoomName, Short curtainRoomId, Short curtainRoomLineno, BigDecimal curtainWidth, BigDecimal curtainHeight, BigDecimal curtainHeight2, String curtainItemMenuNotes, String curtainMenuGroupId, BigDecimal curtainSizeTimes, BigDecimal curtainSizeTimes2, Short mlNonStandard, BigDecimal curtainWbhSize, String transType, String transId, java.util.Date dateDeliverKf, java.util.Date dateDeliverNb, java.util.Date dateDeliverFh, BigDecimal unitPrice, BigDecimal promotionCost, String promotion, BigDecimal promotionCost1) {
        this.itemNo = itemNo;
        this.itemNoSample = itemNoSample;
        this.productionVersion = productionVersion;
        this.qtyRequired = qtyRequired;
        this.dateDeliver = dateDeliver;
        this.dateUpdate = dateUpdate;
        this.notes = notes;
        this.statusId = statusId;
        this.discount = discount;
        this.discountFlag = discountFlag;
        this.curtainKs = curtainKs;
        this.curtainRoomName = curtainRoomName;
        this.curtainRoomId = curtainRoomId;
        this.curtainRoomLineno = curtainRoomLineno;
        this.curtainWidth = curtainWidth;
        this.curtainHeight = curtainHeight;
        this.curtainHeight2 = curtainHeight2;
        this.curtainItemMenuNotes = curtainItemMenuNotes;
        this.curtainMenuGroupId = curtainMenuGroupId;
        this.curtainSizeTimes = curtainSizeTimes;
        this.curtainSizeTimes2 = curtainSizeTimes2;
        this.mlNonStandard = mlNonStandard;
        this.curtainWbhSize = curtainWbhSize;
        this.transType = transType;
        this.transId = transId;
        this.dateDeliverKf = dateDeliverKf;
        this.dateDeliverNb = dateDeliverNb;
        this.dateDeliverFh = dateDeliverFh;
        this.unitPrice = unitPrice;
        this.promotionCost = promotionCost;
        this.promotion = promotion;
        this.promotionCost = promotionCost1;
    }

    public Ctm_order_detail() {
    }

    public void setDateDeliverFh(java.util.Date dateDeliverFh) {
        this.dateDeliverFh = dateDeliverFh;
    }

    public BigDecimal getPromotionCost() {
        return promotionCost;
    }

    public void setPromotionCost(BigDecimal promotionCost) {
        this.promotionCost = promotionCost;
    }



    public Ctm_order_detail(String itemNo, String itemNoSample, String productionVersion, BigDecimal qtyRequired, java.util.Date dateDeliver, java.util.Date dateUpdate, String notes, String statusId, BigDecimal discount, String discountFlag, String curtainKs, String curtainRoomName, Short curtainRoomId, Short curtainRoomLineno, BigDecimal curtainWidth, BigDecimal curtainHeight, BigDecimal curtainHeight2, String curtainItemMenuNotes, String curtainMenuGroupId, BigDecimal curtainSizeTimes, BigDecimal curtainSizeTimes2, Short mlNonStandard, BigDecimal curtainWbhSize, String transType, String transId, java.util.Date dateDeliverKf, java.util.Date dateDeliverNb, java.util.Date dateDeliverFh, BigDecimal unitPrice, BigDecimal promotionCost) {
        this.itemNo = itemNo;
        this.itemNoSample = itemNoSample;
        this.productionVersion = productionVersion;
        this.qtyRequired = qtyRequired;
        this.dateDeliver = dateDeliver;
        this.dateUpdate = dateUpdate;
        this.notes = notes;
        this.statusId = statusId;
        this.discount = discount;
        this.discountFlag = discountFlag;
        this.curtainKs = curtainKs;
        this.curtainRoomName = curtainRoomName;
        this.curtainRoomId = curtainRoomId;
        this.curtainRoomLineno = curtainRoomLineno;
        this.curtainWidth = curtainWidth;
        this.curtainHeight = curtainHeight;
        this.curtainHeight2 = curtainHeight2;
        this.curtainItemMenuNotes = curtainItemMenuNotes;
        this.curtainMenuGroupId = curtainMenuGroupId;
        this.curtainSizeTimes = curtainSizeTimes;
        this.curtainSizeTimes2 = curtainSizeTimes2;
        this.mlNonStandard = mlNonStandard;
        this.curtainWbhSize = curtainWbhSize;
        this.transType = transType;
        this.transId = transId;
        this.dateDeliverKf = dateDeliverKf;
        this.dateDeliverNb = dateDeliverNb;
        this.dateDeliverFh = dateDeliverFh;
        this.unitPrice = unitPrice;
        this.promotionCost = promotionCost;
    }

    public Ctm_order_detail(BigDecimal promotionCost) {
        this.promotionCost = promotionCost;
    }

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

    public java.util.Date getDateDeliver() {
        return dateDeliver;
    }

    public void setDateDeliver(java.util.Date dateDeliver) {
        this.dateDeliver = dateDeliver;
    }

    public java.util.Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(java.util.Date dateUpdate) {
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

    public java.util.Date getDateDeliverKf() {
        return dateDeliverKf;
    }

    public void setDateDeliverKf(java.util.Date dateDeliverKf) {
        this.dateDeliverKf = dateDeliverKf;
    }

    public java.util.Date getDateDeliverNb() {
        return dateDeliverNb;
    }

    public void setDateDeliverNb(java.util.Date dateDeliverNb) {
        this.dateDeliverNb = dateDeliverNb;
    }

    public java.util.Date getDateDeliverFh() {
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

    public Ctm_order_detail(String orderNo, String itemNo, String itemNoSample, String productionVersion, BigDecimal qtyRequired, java.util.Date dateDeliver, java.util.Date dateUpdate, String notes, String statusId, BigDecimal discount, String discountFlag, String curtainKs, String curtainRoomName, Short curtainRoomId, Short curtainRoomLineno, BigDecimal curtainWidth, BigDecimal curtainHeight, BigDecimal curtainHeight2, String curtainItemMenuNotes, String curtainMenuGroupId, BigDecimal curtainSizeTimes, BigDecimal curtainSizeTimes2, Short mlNonStandard, BigDecimal curtainWbhSize, String transType, String transId, java.util.Date dateDeliverKf, java.util.Date dateDeliverNb, java.util.Date dateDeliverFh, BigDecimal unitPrice, BigDecimal promotionCost, String promotion) {
        this.orderNo = orderNo;
        this.itemNo = itemNo;
        this.itemNoSample = itemNoSample;
        this.productionVersion = productionVersion;
        this.qtyRequired = qtyRequired;
        this.dateDeliver = dateDeliver;
        this.dateUpdate = dateUpdate;
        this.notes = notes;
        this.statusId = statusId;
        this.discount = discount;
        this.discountFlag = discountFlag;
        this.curtainKs = curtainKs;
        this.curtainRoomName = curtainRoomName;
        this.curtainRoomId = curtainRoomId;
        this.curtainRoomLineno = curtainRoomLineno;
        this.curtainWidth = curtainWidth;
        this.curtainHeight = curtainHeight;
        this.curtainHeight2 = curtainHeight2;
        this.curtainItemMenuNotes = curtainItemMenuNotes;
        this.curtainMenuGroupId = curtainMenuGroupId;
        this.curtainSizeTimes = curtainSizeTimes;
        this.curtainSizeTimes2 = curtainSizeTimes2;
        this.mlNonStandard = mlNonStandard;
        this.curtainWbhSize = curtainWbhSize;
        this.transType = transType;
        this.transId = transId;
        this.dateDeliverKf = dateDeliverKf;
        this.dateDeliverNb = dateDeliverNb;
        this.dateDeliverFh = dateDeliverFh;
        this.unitPrice = unitPrice;
        this.promotionCost = promotionCost;
        this.promotion = promotion;
    }
}