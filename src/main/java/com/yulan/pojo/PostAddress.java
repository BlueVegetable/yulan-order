package com.yulan.pojo;

public class PostAddress {

    private String cid;

    private String postAddress;

    private String wlContacts;

    private String wlTel;

    private Integer addressId;

    private String province;

    private String city;

    private String country;

    private String provinceID;

    private String cityID;

    private String countryID;

    public PostAddress() {
    }

    public String getProvinceID() {
        return provinceID;
    }

    public void setProvinceID(String provinceID) {
        this.provinceID = provinceID;
    }

    public String getCityID() {
        return cityID;
    }

    public void setCityID(String cityID) {
        this.cityID = cityID;
    }

    public String getCountryID() {
        return countryID;
    }

    public void setCountryID(String countryID) {
        this.countryID = countryID;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid == null ? null : cid.trim();
    }

    public String getPostAddress() {
        return postAddress;
    }

    public void setPostAddress(String postAddress) {
        this.postAddress = postAddress == null ? null : postAddress.trim();
    }

    public String getWlContacts() {
        return wlContacts;
    }

    public void setWlContacts(String wlContacts) {
        this.wlContacts = wlContacts == null ? null : wlContacts.trim();
    }

    public String getWlTel() {
        return wlTel;
    }

    public void setWlTel(String wlTel) {
        this.wlTel = wlTel == null ? null : wlTel.trim();
    }
}
