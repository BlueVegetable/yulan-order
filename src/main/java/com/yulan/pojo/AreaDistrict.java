package com.yulan.pojo;

public class AreaDistrict {

    private String districtId;
    private String districtName;
    private String parentDistrict;
    private Short districtLevel;
    private String notes;
    private String useId;
    private String areaCode;

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId == null ? null : districtId.trim();
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName == null ? null : districtName.trim();
    }

    public String getParentDistrict() {
        return parentDistrict;
    }

    public void setParentDistrict(String parentDistrict) {
        this.parentDistrict = parentDistrict == null ? null : parentDistrict.trim();
    }

    public Short getDistrictLevel() {
        return districtLevel;
    }

    public void setDistrictLevel(Short districtLevel) {
        this.districtLevel = districtLevel;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes == null ? null : notes.trim();
    }

    public String getUseId() {
        return useId;
    }

    public void setUseId(String useId) {
        this.useId = useId == null ? null : useId.trim();
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
}