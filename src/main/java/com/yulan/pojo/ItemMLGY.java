package com.yulan.pojo;

import java.math.BigDecimal;
import java.sql.Date;

public class ItemMLGY {

    private String itemNo;

    private String itemType;

    private Double no;

    private String parentItemNo;

    private String creator;

    private Date dateCre;

    private String productType;

    private BigDecimal lsmlRate;

    private String status;

    private String notes;

    private String fixType;

    private String deleteFlag;

    private String modifyFlag;

    private String changeFlag;

    public ItemMLGY() {
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo == null ? null : itemNo.trim();
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType == null ? null : itemType.trim();
    }

    public Double getNo() {
        return no;
    }

    public void setNo(Double no) {
        this.no = no;
    }

    public String getParentItemNo() {
        return parentItemNo;
    }

    public void setParentItemNo(String parentItemNo) {
        this.parentItemNo = parentItemNo == null ? null : parentItemNo.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getDateCre() {
        return dateCre;
    }

    public void setDateCre(Date dateCre) {
        this.dateCre = dateCre;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType == null ? null : productType.trim();
    }

    public BigDecimal getLsmlRate() {
        return lsmlRate;
    }

    public void setLsmlRate(BigDecimal lsmlRate) {
        this.lsmlRate = lsmlRate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes == null ? null : notes.trim();
    }

    public String getFixType() {
        return fixType;
    }

    public void setFixType(String fixType) {
        this.fixType = fixType;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getModifyFlag() {
        return modifyFlag;
    }

    public void setModifyFlag(String modifyFlag) {
        this.modifyFlag = modifyFlag;
    }

    public String getChangeFlag() {
        return changeFlag;
    }

    public void setChangeFlag(String changeFlag) {
        this.changeFlag = changeFlag;
    }

    @Override
    public String toString() {
        return "ItemMLGY{" +
                "itemNo='" + itemNo + '\'' +
                ", itemType='" + itemType + '\'' +
                ", no=" + no +
                ", parentItemNo='" + parentItemNo + '\'' +
                ", creator='" + creator + '\'' +
                ", dateCre=" + dateCre +
                ", productType='" + productType + '\'' +
                ", lsmlRate=" + lsmlRate +
                ", status='" + status + '\'' +
                ", notes='" + notes + '\'' +
                ", fixType='" + fixType + '\'' +
                ", deleteFlag='" + deleteFlag + '\'' +
                ", modifyFlag='" + modifyFlag + '\'' +
                ", changeFlag='" + changeFlag + '\'' +
                '}';
    }
}
