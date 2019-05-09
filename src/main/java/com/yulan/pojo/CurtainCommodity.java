package com.yulan.pojo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

public class CurtainCommodity extends Commodity {

    private String curtainItemName;
    private String curtainPartName;
    private BigDecimal dosage;
    private String manufacturingInstructions;
    private Integer certainHeightWidth;
    private BigDecimal specification;
    private String illustrate;
    private Timestamp saveTime;
    private String deleteFlag;
    private String modifyFlag;
    private String changeFlag;

    public String getCurtainItemName() {
        return curtainItemName;
    }

    public void setCurtainItemName(String curtainItemName) {
        this.curtainItemName = curtainItemName;
    }

    public String getCurtainPartName() {
        return curtainPartName;
    }

    public void setCurtainPartName(String curtainPartName) {
        this.curtainPartName = curtainPartName;
    }

    public BigDecimal getDosage() {
        return dosage;
    }

    public void setDosage(BigDecimal dosage) {
        this.dosage = dosage;
    }

    public String getManufacturingInstructions() {
        return manufacturingInstructions;
    }

    public void setManufacturingInstructions(String manufacturingInstructions) {
        this.manufacturingInstructions = manufacturingInstructions;
    }

    public Integer getCertainHeightWidth() {
        return certainHeightWidth;
    }

    public void setCertainHeightWidth(Integer certainHeightWidth) {
        this.certainHeightWidth = certainHeightWidth;
    }

    public BigDecimal getSpecification() {
        return specification;
    }

    public void setSpecification(BigDecimal specification) {
        this.specification = specification;
    }

    public String getIllustrate() {
        return illustrate;
    }

    public void setIllustrate(String illustrate) {
        this.illustrate = illustrate;
    }

    public Timestamp getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(Timestamp saveTime) {
        this.saveTime = saveTime;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CurtainCommodity)) return false;
        if (!super.equals(o)) return false;
        CurtainCommodity that = (CurtainCommodity) o;
        return Objects.equals(getCurtainItemName(), that.getCurtainItemName()) &&
                Objects.equals(getCurtainPartName(), that.getCurtainPartName()) &&
                Objects.equals(getDosage(), that.getDosage()) &&
                Objects.equals(getManufacturingInstructions(), that.getManufacturingInstructions()) &&
                Objects.equals(getCertainHeightWidth(), that.getCertainHeightWidth()) &&
                Objects.equals(getSpecification(), that.getSpecification()) &&
                Objects.equals(getIllustrate(), that.getIllustrate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCurtainItemName(), getCurtainPartName(), getDosage(), getManufacturingInstructions(), getCertainHeightWidth(), getSpecification(), getIllustrate());
    }

    @Override
    public String toString() {
        return "CurtainCommodity{" +
                "curtainItemName='" + curtainItemName + '\'' +
                ", curtainPartName='" + curtainPartName + '\'' +
                ", dosage=" + dosage +
                ", manufacturingInstructions='" + manufacturingInstructions + '\'' +
                ", certainHeightWidth=" + certainHeightWidth +
                ", specification=" + specification +
                ", illustrate='" + illustrate + '\'' +
                '}';
    }
}