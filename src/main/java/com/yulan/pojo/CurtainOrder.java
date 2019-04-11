package com.yulan.pojo;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class CurtainOrder{
    private String orderNo;

    private String lineNo;

    private java.sql.Timestamp dateUpdate;

    public Timestamp getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Timestamp dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    private String curtainMenuGroupId;

    private String itemNo;

    private BigDecimal qtyRequired;

    private String curtainItemMenuNotes;

    private Short mlNonStandard;

    private String notes;

    private String lanAuditing;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getLineNo() {
        return lineNo;
    }

    public void setLineNo(String lineNo) {
        this.lineNo = lineNo;
    }



    public String getCurtainMenuGroupId() {
        return curtainMenuGroupId;
    }

    public void setCurtainMenuGroupId(String curtainMenuGroupId) {
        this.curtainMenuGroupId = curtainMenuGroupId == null ? null : curtainMenuGroupId.trim();
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo == null ? null : itemNo.trim();
    }

    public BigDecimal getQtyRequired() {
        return qtyRequired;
    }

    public void setQtyRequired(BigDecimal qtyRequired) {
        this.qtyRequired = qtyRequired;
    }

    public String getCurtainItemMenuNotes() {
        return curtainItemMenuNotes;
    }

    public void setCurtainItemMenuNotes(String curtainItemMenuNotes) {
        this.curtainItemMenuNotes = curtainItemMenuNotes == null ? null : curtainItemMenuNotes.trim();
    }

    public Short getMlNonStandard() {
        return mlNonStandard;
    }

    public void setMlNonStandard(Short mlNonStandard) {
        this.mlNonStandard = mlNonStandard;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes == null ? null : notes.trim();
    }

    public String getLanAuditing() {
        return lanAuditing;
    }

    public void setLanAuditing(String lanAuditing) {
        this.lanAuditing = lanAuditing == null ? null : lanAuditing.trim();
    }
}