package com.yulan.pojo;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public class Ctm_order {
    private String orderNo;

    private String customerCode;

    private String customerCode2;

    private String currencyId;

    private String linkperson;

    private String telephone;

    private String statusId;

    private String deliveryType;

    private String deliveryNotes;

    private String postAddress;

    private String notes;

    private Timestamp dateCre;

    private String operator;

    private Timestamp dateUpdate;

    private String personUpdate;

    private String contractNo;

    private String personAccept;

    private Date dateAccept;

    private String yulanNotes;

    private Timestamp webTjTime;

    private Date dateDeal;

    private String flag;

    private String userNo;

    private String projectNo;

    private String customerCodeO2o;

    private String orderFlag;

    private Short postAddressModified;

    private String dsLinkman;

    private String dsTel;

    private String dsUserName;

    private String dsOrderNo;

    private String isDsOrder;

    private String erpProcessNote;

    private String invoiceFlag;

    private String invoiceTitle;

    private String wlContacts;

    private String wlTel;

    private String reciverArea1;

    private String reciverArea2;

    private String reciverArea3;

    private String billTypeX;

    private String deliveryFlag;

    private String oaoFlag;

    private String oaoStatus;

    private String oaoPerson;

    private Date oaoTime;
    private BigDecimal allSpend;

    private String buyUser;
    private String buyUserPhone;
    private BigDecimal allBackY;
    private BigDecimal allBackM;
    private String curtainStatusId;

    private BigDecimal rebateMoney;
    private String rebateNotes;

    public BigDecimal getRebateMoney() {
        return rebateMoney;
    }

    public void setRebateMoney(BigDecimal rebateMoney) {
        this.rebateMoney = rebateMoney;
    }

    public String getRebateNotes() {
        return rebateNotes;
    }

    public void setRebateNotes(String rebateNotes) {
        this.rebateNotes = rebateNotes;
    }

    public String getCurtainStatusId() {
        return curtainStatusId;
    }

    public void setCurtainStatusId(String curtainStatusId) {
        this.curtainStatusId = curtainStatusId;
    }

    public BigDecimal getAllBackY() {
        return allBackY;
    }

    public void setAllBackY(BigDecimal allBackY) {
        this.allBackY = allBackY;
    }

    public BigDecimal getAllBackM() {
        return allBackM;
    }

    public void setAllBackM(BigDecimal allBackM) {
        this.allBackM = allBackM;
    }

    public String getBuyUser() {
        return buyUser;
    }

    public void setBuyUser(String buyUser) {
        this.buyUser = buyUser;
    }

    public String getBuyUserPhone() {
        return buyUserPhone;
    }

    public void setBuyUserPhone(String buyUserPhone) {
        this.buyUserPhone = buyUserPhone;
    }

    public BigDecimal getAllSpend() {
        return allSpend;
    }

    public void setAllSpend(BigDecimal allSpend) {
        this.allSpend = allSpend;
    }


    public Ctm_order(String orderNo) {
        this.orderNo = orderNo;
    }

    public Ctm_order() {
    }


    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode == null ? null : customerCode.trim();
    }

    public String getCustomerCode2() {
        return customerCode2;
    }

    public Ctm_order(String orderNo, String customerCode, String customerCode2, String currencyId, String linkperson, String telephone, String statusId, String deliveryType, String deliveryNotes, String postAddress, String notes, Timestamp dateCre, String operator, Timestamp dateUpdate, String personUpdate, String contractNo, String personAccept, Date dateAccept, String yulanNotes, Timestamp webTjTime, Date dateDeal, String flag, String userNo, String projectNo, String customerCodeO2o, String orderFlag, Short postAddressModified, String dsLinkman, String dsTel, String dsUserName, String dsOrderNo, String isDsOrder, String erpProcessNote, String invoiceFlag, String invoiceTitle, String wlContacts, String wlTel, String reciverArea1, String reciverArea2, String reciverArea3, String billTypeX, String deliveryFlag, String oaoFlag, String oaoStatus, String oaoPerson, Date oaoTime, BigDecimal allSpend) {
        this.orderNo = orderNo;
        this.customerCode = customerCode;
        this.customerCode2 = customerCode2;
        this.currencyId = currencyId;
        this.linkperson = linkperson;
        this.telephone = telephone;
        this.statusId = statusId;
        this.deliveryType = deliveryType;
        this.deliveryNotes = deliveryNotes;
        this.postAddress = postAddress;
        this.notes = notes;
        this.dateCre = dateCre;
        this.operator = operator;
        this.dateUpdate = dateUpdate;
        this.personUpdate = personUpdate;
        this.contractNo = contractNo;
        this.personAccept = personAccept;
        this.dateAccept = dateAccept;
        this.yulanNotes = yulanNotes;
        this.webTjTime = webTjTime;
        this.dateDeal = dateDeal;
        this.flag = flag;
        this.userNo = userNo;
        this.projectNo = projectNo;
        this.customerCodeO2o = customerCodeO2o;
        this.orderFlag = orderFlag;
        this.postAddressModified = postAddressModified;
        this.dsLinkman = dsLinkman;
        this.dsTel = dsTel;
        this.dsUserName = dsUserName;
        this.dsOrderNo = dsOrderNo;
        this.isDsOrder = isDsOrder;
        this.erpProcessNote = erpProcessNote;
        this.invoiceFlag = invoiceFlag;
        this.invoiceTitle = invoiceTitle;
        this.wlContacts = wlContacts;
        this.wlTel = wlTel;
        this.reciverArea1 = reciverArea1;
        this.reciverArea2 = reciverArea2;
        this.reciverArea3 = reciverArea3;
        this.billTypeX = billTypeX;
        this.deliveryFlag = deliveryFlag;
        this.oaoFlag = oaoFlag;
        this.oaoStatus = oaoStatus;
        this.oaoPerson = oaoPerson;
        this.oaoTime = oaoTime;
        this.allSpend = allSpend;
    }

    public void setCustomerCode2(String customerCode2) {
        this.customerCode2 = customerCode2 == null ? null : customerCode2.trim();
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId == null ? null : currencyId.trim();
    }

    public String getLinkperson() {
        return linkperson;
    }

    public void setLinkperson(String linkperson) {
        this.linkperson = linkperson == null ? null : linkperson.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId == null ? null : statusId.trim();
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType == null ? null : deliveryType.trim();
    }

    public String getDeliveryNotes() {
        return deliveryNotes;
    }

    public void setDeliveryNotes(String deliveryNotes) {
        this.deliveryNotes = deliveryNotes == null ? null : deliveryNotes.trim();
    }

    public String getPostAddress() {
        return postAddress;
    }

    public void setPostAddress(String postAddress) {
        this.postAddress = postAddress == null ? null : postAddress.trim();
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes == null ? null : notes.trim();
    }

    public Timestamp getDateCre() {
        return dateCre;
    }

    public void setDateCre(Timestamp dateCre) {
        this.dateCre = dateCre;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Timestamp getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Timestamp dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public String getPersonUpdate() {
        return personUpdate;
    }

    public void setPersonUpdate(String personUpdate) {
        this.personUpdate = personUpdate == null ? null : personUpdate.trim();
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo == null ? null : contractNo.trim();
    }

    public String getPersonAccept() {
        return personAccept;
    }

    public void setPersonAccept(String personAccept) {
        this.personAccept = personAccept == null ? null : personAccept.trim();
    }

    public Date getDateAccept() {
        return dateAccept;
    }

    public void setDateAccept(Date dateAccept) {
        this.dateAccept = dateAccept;
    }

    public String getYulanNotes() {
        return yulanNotes;
    }

    public void setYulanNotes(String yulanNotes) {
        this.yulanNotes = yulanNotes == null ? null : yulanNotes.trim();
    }

    public Timestamp getWebTjTime() {
        return webTjTime;
    }

    public void setWebTjTime(Timestamp webTjTime) {
        this.webTjTime = webTjTime;
    }

    public Date getDateDeal() {
        return dateDeal;
    }

    public void setDateDeal(Date dateDeal) {
        this.dateDeal = dateDeal;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo == null ? null : userNo.trim();
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo == null ? null : projectNo.trim();
    }

    public String getCustomerCodeO2o() {
        return customerCodeO2o;
    }

    public void setCustomerCodeO2o(String customerCodeO2o) {
        this.customerCodeO2o = customerCodeO2o == null ? null : customerCodeO2o.trim();
    }

    public String getOrderFlag() {
        return orderFlag;
    }

    public void setOrderFlag(String orderFlag) {
        this.orderFlag = orderFlag == null ? null : orderFlag.trim();
    }

    public Short getPostAddressModified() {
        return postAddressModified;
    }

    public void setPostAddressModified(Short postAddressModified) {
        this.postAddressModified = postAddressModified;
    }

    public String getDsLinkman() {
        return dsLinkman;
    }

    public void setDsLinkman(String dsLinkman) {
        this.dsLinkman = dsLinkman == null ? null : dsLinkman.trim();
    }

    public String getDsTel() {
        return dsTel;
    }

    public void setDsTel(String dsTel) {
        this.dsTel = dsTel == null ? null : dsTel.trim();
    }

    public String getDsUserName() {
        return dsUserName;
    }

    public void setDsUserName(String dsUserName) {
        this.dsUserName = dsUserName == null ? null : dsUserName.trim();
    }

    public String getDsOrderNo() {
        return dsOrderNo;
    }

    public void setDsOrderNo(String dsOrderNo) {
        this.dsOrderNo = dsOrderNo == null ? null : dsOrderNo.trim();
    }

    public String getIsDsOrder() {
        return isDsOrder;
    }

    public void setIsDsOrder(String isDsOrder) {
        this.isDsOrder = isDsOrder == null ? null : isDsOrder.trim();
    }

    public String getErpProcessNote() {
        return erpProcessNote;
    }

    public void setErpProcessNote(String erpProcessNote) {
        this.erpProcessNote = erpProcessNote == null ? null : erpProcessNote.trim();
    }

    public String getInvoiceFlag() {
        return invoiceFlag;
    }

    public void setInvoiceFlag(String invoiceFlag) {
        this.invoiceFlag = invoiceFlag == null ? null : invoiceFlag.trim();
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle == null ? null : invoiceTitle.trim();
    }

    public String getWlContacts() {
        return wlContacts;
    }

    public void setWlContacts(String wlContacts) {
        this.wlContacts = wlContacts == null ? null : wlContacts.trim();
    }

    public String getWlTel() {
        return wlTel;
    }

    public void setWlTel(String wlTel) {
        this.wlTel = wlTel == null ? null : wlTel.trim();
    }

    public String getReciverArea1() {
        return reciverArea1;
    }

    public void setReciverArea1(String reciverArea1) {
        this.reciverArea1 = reciverArea1 == null ? null : reciverArea1.trim();
    }

    public String getReciverArea2() {
        return reciverArea2;
    }

    public void setReciverArea2(String reciverArea2) {
        this.reciverArea2 = reciverArea2 == null ? null : reciverArea2.trim();
    }

    public String getReciverArea3() {
        return reciverArea3;
    }

    public void setReciverArea3(String reciverArea3) {
        this.reciverArea3 = reciverArea3 == null ? null : reciverArea3.trim();
    }

    public String getBillTypeX() {
        return billTypeX;
    }

    public void setBillTypeX(String billTypeX) {
        this.billTypeX = billTypeX == null ? null : billTypeX.trim();
    }

    public String getDeliveryFlag() {
        return deliveryFlag;
    }

    public void setDeliveryFlag(String deliveryFlag) {
        this.deliveryFlag = deliveryFlag == null ? null : deliveryFlag.trim();
    }

    public String getOaoFlag() {
        return oaoFlag;
    }

    public void setOaoFlag(String oaoFlag) {
        this.oaoFlag = oaoFlag == null ? null : oaoFlag.trim();
    }

    public String getOaoStatus() {
        return oaoStatus;
    }

    public void setOaoStatus(String oaoStatus) {
        this.oaoStatus = oaoStatus == null ? null : oaoStatus.trim();
    }

    public String getOaoPerson() {
        return oaoPerson;
    }

    public void setOaoPerson(String oaoPerson) {
        this.oaoPerson = oaoPerson == null ? null : oaoPerson.trim();
    }

    public Date getOaoTime() {
        return oaoTime;
    }

    public void setOaoTime(Date oaoTime) {
        this.oaoTime = oaoTime;
    }
}