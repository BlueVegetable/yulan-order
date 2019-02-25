package com.yulan.pojo;

import java.util.Date;
import java.util.Objects;

public class CustomerType {

    private String customerTypeId;
    private String customerTypeName;
    private String customerTypeNote;
    private String priceType;
    private String useId;
    private String creator;
    private String updater;
    private Date dateCre;
    private Date dateUpdate;

    public String getCustomerTypeId() {
        return customerTypeId;
    }

    public void setCustomerTypeId(String customerTypeId) {
        this.customerTypeId = customerTypeId == null ? null : customerTypeId.trim();
    }

    public String getCustomerTypeName() {
        return customerTypeName;
    }

    public void setCustomerTypeName(String customerTypeName) {
        this.customerTypeName = customerTypeName == null ? null : customerTypeName.trim();
    }

    public String getCustomerTypeNote() {
        return customerTypeNote;
    }

    public void setCustomerTypeNote(String customerTypeNote) {
        this.customerTypeNote = customerTypeNote == null ? null : customerTypeNote.trim();
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType == null ? null : priceType.trim();
    }

    public String getUseId() {
        return useId;
    }

    public void setUseId(String useId) {
        this.useId = useId == null ? null : useId.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater == null ? null : updater.trim();
    }

    public Date getDateCre() {
        return dateCre;
    }

    public void setDateCre(Date dateCre) {
        this.dateCre = dateCre;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerType)) return false;
        CustomerType that = (CustomerType) o;
        return Objects.equals(getCustomerTypeId(), that.getCustomerTypeId()) &&
                Objects.equals(getCustomerTypeName(), that.getCustomerTypeName()) &&
                Objects.equals(getCustomerTypeNote(), that.getCustomerTypeNote()) &&
                Objects.equals(getPriceType(), that.getPriceType()) &&
                Objects.equals(getUseId(), that.getUseId()) &&
                Objects.equals(getCreator(), that.getCreator()) &&
                Objects.equals(getUpdater(), that.getUpdater()) &&
                Objects.equals(getDateCre(), that.getDateCre()) &&
                Objects.equals(getDateUpdate(), that.getDateUpdate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomerTypeId(), getCustomerTypeName(), getCustomerTypeNote(), getPriceType(), getUseId(), getCreator(), getUpdater(), getDateCre(), getDateUpdate());
    }

    @Override
    public String toString() {
        return "CustomerType{" +
                "customerTypeId='" + customerTypeId + '\'' +
                ", customerTypeName='" + customerTypeName + '\'' +
                ", customerTypeNote='" + customerTypeNote + '\'' +
                ", priceType='" + priceType + '\'' +
                ", useId='" + useId + '\'' +
                ", creator='" + creator + '\'' +
                ", updater='" + updater + '\'' +
                ", dateCre=" + dateCre +
                ", dateUpdate=" + dateUpdate +
                '}';
    }
}