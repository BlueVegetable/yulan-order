package com.yulan.pojo;

import java.util.Date;

public class AreaRegion {

    private String regionId;
    private String regionName;
    private String parentCity;
    private String parentArea;
    private String parentProvince;
    private Short regionLevel;
    private String country;
    private Date dateCre;
    private Date dateUpdate;
    private String cityType;
    private String parentDistrict;

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId == null ? null : regionId.trim();
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName == null ? null : regionName.trim();
    }

    public String getParentCity() {
        return parentCity;
    }

    public void setParentCity(String parentCity) {
        this.parentCity = parentCity == null ? null : parentCity.trim();
    }

    public String getParentArea() {
        return parentArea;
    }

    public void setParentArea(String parentArea) {
        this.parentArea = parentArea == null ? null : parentArea.trim();
    }

    public String getParentProvince() {
        return parentProvince;
    }

    public void setParentProvince(String parentProvince) {
        this.parentProvince = parentProvince == null ? null : parentProvince.trim();
    }

    public Short getRegionLevel() {
        return regionLevel;
    }

    public void setRegionLevel(Short regionLevel) {
        this.regionLevel = regionLevel;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
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

    public String getCityType() {
        return cityType;
    }

    public void setCityType(String cityType) {
        this.cityType = cityType == null ? null : cityType.trim();
    }

    public String getParentDistrict() {
        return parentDistrict;
    }

    public void setParentDistrict(String parentDistrict) {
        this.parentDistrict = parentDistrict == null ? null : parentDistrict.trim();
    }

    @Override
    public String toString() {
        return "AreaRegion{" +
                "regionId='" + regionId + '\'' +
                ", regionName='" + regionName + '\'' +
                ", parentCity='" + parentCity + '\'' +
                ", parentArea='" + parentArea + '\'' +
                ", parentProvince='" + parentProvince + '\'' +
                ", regionLevel=" + regionLevel +
                ", country='" + country + '\'' +
                ", dateCre=" + dateCre +
                ", dateUpdate=" + dateUpdate +
                ", cityType='" + cityType + '\'' +
                ", parentDistrict='" + parentDistrict + '\'' +
                '}';
    }
}
