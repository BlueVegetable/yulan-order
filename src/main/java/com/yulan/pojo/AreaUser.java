package com.yulan.pojo;

public class AreaUser {

    private String userid;

    private String areaCode;

    private String flag;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "AreaUser{" +
                "userid='" + userid + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", flag='" + flag + '\'' +
                '}';
    }
}
