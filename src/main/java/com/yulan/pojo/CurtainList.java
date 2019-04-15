package com.yulan.pojo;

import java.util.List;
import java.util.Objects;

public class CurtainList {
    private String partName;
    private List<CurtainCommodity> curtainCommodities;

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public List<CurtainCommodity> getCurtainCommodities() {
        return curtainCommodities;
    }

    public void setCurtainCommodities(List<CurtainCommodity> curtainCommodities) {
        this.curtainCommodities = curtainCommodities;
    }

    public CurtainList() {}

    public CurtainList(String partName, List<CurtainCommodity> curtainCommodities) {
        this.partName = partName;
        this.curtainCommodities = curtainCommodities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CurtainList)) return false;
        CurtainList that = (CurtainList) o;
        return Objects.equals(getPartName(), that.getPartName()) &&
                Objects.equals(getCurtainCommodities(), that.getCurtainCommodities());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPartName(), getCurtainCommodities());
    }

    @Override
    public String toString() {
        return "CurtainList{" +
                "partName='" + partName + '\'' +
                ", curtainCommodities=" + curtainCommodities +
                '}';
    }
}