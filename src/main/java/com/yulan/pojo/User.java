package com.yulan.pojo;


public class User {

    private String customerId;
    private String customerName;
    private String district;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Override
    public String toString() {
        return "User{" +
                "customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", district='" + district + '\'' +
                '}';
    }
}
