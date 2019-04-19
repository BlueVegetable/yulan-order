package com.yulan.pojo;

import java.math.BigDecimal;
import java.util.Objects;

public class Item {
    private String itemNo;

    private String oldItemNo;

    private String itemVersion;

    private String productType;

    private String note;

    private String saleId;

    private String unit;

    private String useId;

    private String productBrand;

    private String productFlag;

    private String itemFlag;

    private String itemDz;

    private String fixType;

    private BigDecimal fixGrade;

    private String rzStyle;

    private String rzGrade;

    private BigDecimal highJia;

    private String wbhFlag;

    private String deleteFlag;

    private String mlModifyFlag;

    private BigDecimal widthHh;

    private BigDecimal highHh;

    private Object duihua;

    private String oao;

    private BigDecimal salePrice;

    private BigDecimal priceSale;

    private BigDecimal priceFx;

    private BigDecimal priceHome;

    private String groupType;
    //统计返回页数的时候一共有多少条
    private Integer total;

    private BigDecimal duihuaLoss;

    private ItemType itemType;
    //窗帘需要的信息
    private ItemMLGY itemMLGY;

    public ItemMLGY getItemMLGY() {
        return itemMLGY;
    }

    public void setItemMLGY(ItemMLGY itemMLGY) {
        this.itemMLGY = itemMLGY;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public BigDecimal getDuihuaLoss() {
        return duihuaLoss;
    }

    public void setDuihuaLoss(BigDecimal duihuaLoss) {
        this.duihuaLoss = duihuaLoss;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo == null ? null : itemNo.trim();
    }

    public String getOldItemNo() {
        return oldItemNo;
    }

    public void setOldItemNo(String oldItemNo) {
        this.oldItemNo = oldItemNo == null ? null : oldItemNo.trim();
    }

    public String getItemVersion() {
        return itemVersion;
    }

    public void setItemVersion(String itemVersion) {
        this.itemVersion = itemVersion == null ? null : itemVersion.trim();
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType == null ? null : productType.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId == null ? null : saleId.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getUseId() {
        return useId;
    }

    public void setUseId(String useId) {
        this.useId = useId == null ? null : useId.trim();
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand == null ? null : productBrand.trim();
    }

    public String getProductFlag() {
        return productFlag;
    }

    public void setProductFlag(String productFlag) {
        this.productFlag = productFlag == null ? null : productFlag.trim();
    }

    public String getItemFlag() {
        return itemFlag;
    }

    public void setItemFlag(String itemFlag) {
        this.itemFlag = itemFlag == null ? null : itemFlag.trim();
    }

    public String getItemDz() {
        return itemDz;
    }

    public void setItemDz(String itemDz) {
        this.itemDz = itemDz == null ? null : itemDz.trim();
    }

    public String getFixType() {
        return fixType;
    }

    public void setFixType(String fixType) {
        this.fixType = fixType == null ? null : fixType.trim();
    }

    public BigDecimal getFixGrade() {
        return fixGrade;
    }

    public void setFixGrade(BigDecimal fixGrade) {
        this.fixGrade = fixGrade;
    }

    public String getRzStyle() {
        return rzStyle;
    }

    public void setRzStyle(String rzStyle) {
        this.rzStyle = rzStyle == null ? null : rzStyle.trim();
    }

    public String getRzGrade() {
        return rzGrade;
    }

    public void setRzGrade(String rzGrade) {
        this.rzGrade = rzGrade == null ? null : rzGrade.trim();
    }

    public BigDecimal getHighJia() {
        return highJia;
    }

    public void setHighJia(BigDecimal highJia) {
        this.highJia = highJia;
    }

    public String getWbhFlag() {
        return wbhFlag;
    }

    public void setWbhFlag(String wbhFlag) {
        this.wbhFlag = wbhFlag == null ? null : wbhFlag.trim();
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag == null ? null : deleteFlag.trim();
    }

    public String getMlModifyFlag() {
        return mlModifyFlag;
    }

    public void setMlModifyFlag(String mlModifyFlag) {
        this.mlModifyFlag = mlModifyFlag == null ? null : mlModifyFlag.trim();
    }

    public BigDecimal getWidthHh() {
        return widthHh;
    }

    public void setWidthHh(BigDecimal widthHh) {
        this.widthHh = widthHh;
    }

    public BigDecimal getHighHh() {
        return highHh;
    }

    public void setHighHh(BigDecimal highHh) {
        this.highHh = highHh;
    }

    public Object getDuihua() {
        return duihua;
    }

    public void setDuihua(Object duihua) {
        this.duihua = duihua;
    }

    public String getOao() {
        return oao;
    }

    public void setOao(String oao) {
        this.oao = oao == null ? null : oao.trim();
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public BigDecimal getPriceSale() {
        return priceSale;
    }

    public void setPriceSale(BigDecimal priceSale) {
        this.priceSale = priceSale;
    }

    public BigDecimal getPriceFx() {
        return priceFx;
    }

    public void setPriceFx(BigDecimal priceFx) {
        this.priceFx = priceFx;
    }

    public BigDecimal getPriceHome() {
        return priceHome;
    }

    public void setPriceHome(BigDecimal priceHome) {
        this.priceHome = priceHome;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType == null ? null : groupType.trim();
    }

    public Item() {
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemNo='" + itemNo + '\'' +
                ", oldItemNo='" + oldItemNo + '\'' +
                ", itemVersion='" + itemVersion + '\'' +
                ", productType='" + productType + '\'' +
                ", note='" + note + '\'' +
                ", saleId='" + saleId + '\'' +
                ", unit='" + unit + '\'' +
                ", useId='" + useId + '\'' +
                ", productBrand='" + productBrand + '\'' +
                ", productFlag='" + productFlag + '\'' +
                ", itemFlag='" + itemFlag + '\'' +
                ", itemDz='" + itemDz + '\'' +
                ", fixType='" + fixType + '\'' +
                ", fixGrade=" + fixGrade +
                ", rzStyle='" + rzStyle + '\'' +
                ", rzGrade='" + rzGrade + '\'' +
                ", highJia=" + highJia +
                ", wbhFlag='" + wbhFlag + '\'' +
                ", deleteFlag='" + deleteFlag + '\'' +
                ", mlModifyFlag='" + mlModifyFlag + '\'' +
                ", widthHh=" + widthHh +
                ", highHh=" + highHh +
                ", duihua=" + duihua +
                ", oao='" + oao + '\'' +
                ", salePrice=" + salePrice +
                ", priceSale=" + priceSale +
                ", priceFx=" + priceFx +
                ", priceHome=" + priceHome +
                ", groupType='" + groupType + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(itemNo, item.itemNo) &&
                Objects.equals(oldItemNo, item.oldItemNo) &&
                Objects.equals(itemVersion, item.itemVersion) &&
                Objects.equals(productType, item.productType) &&
                Objects.equals(note, item.note) &&
                Objects.equals(saleId, item.saleId) &&
                Objects.equals(unit, item.unit) &&
                Objects.equals(useId, item.useId) &&
                Objects.equals(productBrand, item.productBrand) &&
                Objects.equals(productFlag, item.productFlag) &&
                Objects.equals(itemFlag, item.itemFlag) &&
                Objects.equals(itemDz, item.itemDz) &&
                Objects.equals(fixType, item.fixType) &&
                Objects.equals(fixGrade, item.fixGrade) &&
                Objects.equals(rzStyle, item.rzStyle) &&
                Objects.equals(rzGrade, item.rzGrade) &&
                Objects.equals(highJia, item.highJia) &&
                Objects.equals(wbhFlag, item.wbhFlag) &&
                Objects.equals(deleteFlag, item.deleteFlag) &&
                Objects.equals(mlModifyFlag, item.mlModifyFlag) &&
                Objects.equals(widthHh, item.widthHh) &&
                Objects.equals(highHh, item.highHh) &&
                Objects.equals(duihua, item.duihua) &&
                Objects.equals(oao, item.oao) &&
                Objects.equals(salePrice, item.salePrice) &&
                Objects.equals(priceSale, item.priceSale) &&
                Objects.equals(priceFx, item.priceFx) &&
                Objects.equals(priceHome, item.priceHome) &&
                Objects.equals(groupType, item.groupType) &&
                Objects.equals(total, item.total) &&
                Objects.equals(duihuaLoss, item.duihuaLoss) &&
                Objects.equals(itemType, item.itemType) &&
                Objects.equals(itemMLGY, item.itemMLGY);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemNo, oldItemNo, itemVersion, productType, note
                , saleId, unit, useId, productBrand, productFlag, itemFlag,
                itemDz, fixType, fixGrade, rzStyle, rzGrade, highJia, wbhFlag, deleteFlag, mlModifyFlag, widthHh, highHh, duihua, oao, salePrice, priceSale, priceFx, priceHome, groupType, total, duihuaLoss, itemType, itemMLGY);
    }
}
