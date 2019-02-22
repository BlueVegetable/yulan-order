package com.yulan.pojo;

public class AreaCode {

    private String areaCode;

    private String areaBelong;

    private String areaName;

    private Integer levelNo;

    private String useId;

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public String getAreaBelong() {
        return areaBelong;
    }

    public void setAreaBelong(String areaBelong) {
        this.areaBelong = areaBelong == null ? null : areaBelong.trim();
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    public Integer getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(Integer levelNo) {
        this.levelNo = levelNo;
    }

    public String getUseId() {
        return useId;
    }

    public void setUseId(String useId) {
        this.useId = useId == null ? null : useId.trim();
    }

    @Override
    public String toString() {
        return "AreaCode{" +
                "areaCode='" + areaCode + '\'' +
                ", areaBelong='" + areaBelong + '\'' +
                ", areaName='" + areaName + '\'' +
                ", levelNo=" + levelNo +
                ", useId='" + useId + '\'' +
                '}';
    }
}
