package com.yulan.pojo;

import java.sql.Date;

/*
*协议任务
 */
public class YLcontract_v2015 {

    private Integer ccyear;

    private String ccid;

    private Double aRetailing;

    private Double bEngineering;

    private Double cMatching;

    private Double m1;

    private Double m2;

    private Double m3;

    private Double m4;

    private Double m5;

    private Double m6;

    private Double m7;

    private Double m8;

    private Double m9;

    private Double m10;

    private Double m11;

    private Double m12;

    private Double stockpercent;

    private Double rewordpercent;

    private Double payminimum;

    private String memo2;

    private String preferedbrand;

    private String privateAccountAuthed;

    private Double rewordpercent2;

    private java.sql.Date startDate;

    private java.sql.Date endDate;

    public Integer getCcyear() {
        return ccyear;
    }

    public void setCcyear(Integer ccyear) {
        this.ccyear = ccyear;
    }

    public String getCcid() {
        return ccid;
    }

    public void setCcid(String ccid) {
        this.ccid = ccid == null ? null : ccid.trim();
    }

    public Double getaRetailing() {
        return aRetailing;
    }

    public void setaRetailing(Double aRetailing) {
        this.aRetailing = aRetailing;
    }

    public Double getbEngineering() {
        return bEngineering;
    }

    public void setbEngineering(Double bEngineering) {
        this.bEngineering = bEngineering;
    }

    public Double getcMatching() {
        return cMatching;
    }

    public void setcMatching(Double cMatching) {
        this.cMatching = cMatching;
    }

    public Double getM1() {
        return m1;
    }

    public void setM1(Double m1) {
        this.m1 = m1;
    }

    public Double getM2() {
        return m2;
    }

    public void setM2(Double m2) {
        this.m2 = m2;
    }

    public Double getM3() {
        return m3;
    }

    public void setM3(Double m3) {
        this.m3 = m3;
    }

    public Double getM4() {
        return m4;
    }

    public void setM4(Double m4) {
        this.m4 = m4;
    }

    public Double getM5() {
        return m5;
    }

    public void setM5(Double m5) {
        this.m5 = m5;
    }

    public Double getM6() {
        return m6;
    }

    public void setM6(Double m6) {
        this.m6 = m6;
    }

    public Double getM7() {
        return m7;
    }

    public void setM7(Double m7) {
        this.m7 = m7;
    }

    public Double getM8() {
        return m8;
    }

    public void setM8(Double m8) {
        this.m8 = m8;
    }

    public Double getM9() {
        return m9;
    }

    public void setM9(Double m9) {
        this.m9 = m9;
    }

    public Double getM10() {
        return m10;
    }

    public void setM10(Double m10) {
        this.m10 = m10;
    }

    public Double getM11() {
        return m11;
    }

    public void setM11(Double m11) {
        this.m11 = m11;
    }

    public Double getM12() {
        return m12;
    }

    public void setM12(Double m12) {
        this.m12 = m12;
    }

    public Double getStockpercent() {
        return stockpercent;
    }

    public void setStockpercent(Double stockpercent) {
        this.stockpercent = stockpercent;
    }

    public Double getRewordpercent() {
        return rewordpercent;
    }

    public void setRewordpercent(Double rewordpercent) {
        this.rewordpercent = rewordpercent;
    }

    public Double getPayminimum() {
        return payminimum;
    }

    public void setPayminimum(Double payminimum) {
        this.payminimum = payminimum;
    }

    public String getMemo2() {
        return memo2;
    }

    public void setMemo2(String memo2) {
        this.memo2 = memo2 == null ? null : memo2.trim();
    }

    public String getPreferedbrand() {
        return preferedbrand;
    }

    public void setPreferedbrand(String preferedbrand) {
        this.preferedbrand = preferedbrand == null ? null : preferedbrand.trim();
    }

    public String getPrivateAccountAuthed() {
        return privateAccountAuthed;
    }

    public void setPrivateAccountAuthed(String privateAccountAuthed) {
        this.privateAccountAuthed = privateAccountAuthed == null ? null : privateAccountAuthed.trim();
    }

    public Double getRewordpercent2() {
        return rewordpercent2;
    }

    public void setRewordpercent2(Double rewordpercent2) {
        this.rewordpercent2 = rewordpercent2;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "YLcontract_v2015{" +
                "ccyear=" + ccyear +
                ", ccid='" + ccid + '\'' +
                ", aRetailing=" + aRetailing +
                ", bEngineering=" + bEngineering +
                ", cMatching=" + cMatching +
                ", m1=" + m1 +
                ", m2=" + m2 +
                ", m3=" + m3 +
                ", m4=" + m4 +
                ", m5=" + m5 +
                ", m6=" + m6 +
                ", m7=" + m7 +
                ", m8=" + m8 +
                ", m9=" + m9 +
                ", m10=" + m10 +
                ", m11=" + m11 +
                ", m12=" + m12 +
                ", stockpercent=" + stockpercent +
                ", rewordpercent=" + rewordpercent +
                ", payminimum=" + payminimum +
                ", memo2='" + memo2 + '\'' +
                ", preferedbrand='" + preferedbrand + '\'' +
                ", privateAccountAuthed='" + privateAccountAuthed + '\'' +
                ", rewordpercent2=" + rewordpercent2 +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
