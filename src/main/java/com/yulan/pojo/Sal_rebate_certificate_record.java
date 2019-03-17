package com.yulan.pojo;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Sal_rebate_certificate_record {
    private  String id;
    private  String orderNo;

    private  String lineNo;


    private BigDecimal rebateMoney;

    private Timestamp dateUse;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getLineNo() {
        return lineNo;
    }

    public void setLineNo(String lineNo) {
        this.lineNo = lineNo;
    }

    public BigDecimal getRebateMoney() {
        return rebateMoney;
    }

    public void setRebateMoney(BigDecimal rebateMoney) {
        this.rebateMoney = rebateMoney;
    }

    public Timestamp getDateUse() {
        return dateUse;
    }

    public void setDateUse(Timestamp dateUse) {
        this.dateUse = dateUse;
    }
}