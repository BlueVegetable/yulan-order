package com.yulan.pojo;

public class PostAddress {

    private String cid;

    private String postAddress;

    private String wlContacts;

    private String wlTel;

    private Integer addressId;

    public PostAddress() {
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
