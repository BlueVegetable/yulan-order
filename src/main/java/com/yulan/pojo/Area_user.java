package com.yulan.pojo;

public class Area_user {
    private String userId;
    private String area_code;
    private String flag;

    public Area_user(String userId, String area_code, String flag) {
        this.userId = userId;
        this.area_code = area_code;
        this.flag = flag;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getArea_code() {
        return area_code;
    }

    public void setArea_code(String area_code) {
        this.area_code = area_code;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
