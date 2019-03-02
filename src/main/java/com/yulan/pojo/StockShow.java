package com.yulan.pojo;

/**
 * 查看墙纸库存信息的类
 */
public class StockShow {

    private String stockNo;
    private String batchNo;
    private Double qty;
    private String itemNo;

    public StockShow() {
    }

    public String getStockNo() {
        return stockNo;
    }

    public void setStockNo(String stockNo) {
        this.stockNo = stockNo;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    @Override
    public String toString() {
        return "StockShow{" +
                "stockNo='" + stockNo + '\'' +
                ", batchNo='" + batchNo + '\'' +
                ", qty=" + qty +
                ", itemNo='" + itemNo + '\'' +
                '}';
    }
}
