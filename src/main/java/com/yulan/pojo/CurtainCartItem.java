package com.yulan.pojo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class CurtainCartItem extends CartItem {

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
    }

    private BigDecimal width;
    private BigDecimal height;
    private BigDecimal drape;
    private Integer outsourcingBoxExist;
    private BigDecimal outsourcingBoxWidth;
    private List<CurtainList> curtainLists;

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public BigDecimal getDrape() {
        return drape;
    }

    public void setDrape(BigDecimal drape) {
        this.drape = drape;
    }

    public Integer getOutsourcingBoxExist() {
        return outsourcingBoxExist;
    }

    public void setOutsourcingBoxExist(Integer outsourcingBoxExist) {
        this.outsourcingBoxExist = outsourcingBoxExist;
    }

    public BigDecimal getOutsourcingBoxWidth() {
        return outsourcingBoxWidth;
    }

    public void setOutsourcingBoxWidth(BigDecimal outsourcingBoxWidth) {
        this.outsourcingBoxWidth = outsourcingBoxWidth;
    }

    public List<CurtainList> getCurtainLists() {
        return curtainLists;
    }

    public void setCurtainLists(List<CurtainList> curtainLists) {
        this.curtainLists = curtainLists;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CurtainCartItem)) return false;
        if (!super.equals(o)) return false;
        CurtainCartItem that = (CurtainCartItem) o;
        return Objects.equals(getWidth(), that.getWidth()) &&
                Objects.equals(getHeight(), that.getHeight()) &&
                Objects.equals(getDrape(), that.getDrape()) &&
                Objects.equals(getOutsourcingBoxExist(), that.getOutsourcingBoxExist()) &&
                Objects.equals(getOutsourcingBoxWidth(), that.getOutsourcingBoxWidth()) &&
                Objects.equals(getCurtainLists(), that.getCurtainLists());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getWidth(), getHeight(), getDrape(), getOutsourcingBoxExist(), getOutsourcingBoxWidth(), getCurtainLists());
    }

    @Override
    public String toString() {
        return "CurtainCartItem{" +
                "width=" + width +
                ", height=" + height +
                ", drape=" + drape +
                ", outsourcingBoxExist=" + outsourcingBoxExist +
                ", outsourcingBoxWidth=" + outsourcingBoxWidth +
                ", curtainLists=" + curtainLists +
                '}';
    }
}
