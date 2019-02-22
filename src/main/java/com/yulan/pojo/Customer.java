package com.yulan.pojo;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

public class Customer {
    private String customerCode;

    private String customerName;

    private String statusId;

    private String customerBelong;

    private String areaCode;

    private String shortName;

    private String customerType;

    private String customerCode3;

    private String district;

    private String city;

    private String cityType;

    private String agencyStatus;

    private String customerZt;

    private String o2oFlag;

    private String areaDistrict;

    private BigDecimal balanceReceive;

    private String creditCode;

    private String currencyId;

    private Date dateCre;

    private Date dateUpdate;

    private String deptNo;

    private String email;

    private String fax;

    private String financeCode;

    private String intranet;

    private String juridicPerson;

    private String notes;

    private String officeTel;

    private String postAddress;

    private BigDecimal quotaSale;

    private String salesman;

    private String scale;

    private String taxNo;

    private String tradeDesc;

    private String tAccount;

    private String zipCode;

    private String importantFlag;

    private String deliveryAdress;

    private String customerClassify;

    private String followCode;

    private BigDecimal invoiceRecieveStart;

    private Date dateChangeStatus;

    private String operaterChangeStatus;

    private String reasonChangeStatus;

    private BigDecimal balanceReceiveZt;

    private BigDecimal receiveMonthStart;

    private BigDecimal receiveMonthStartZt;

    private String areaDistrict2;

    private String creator;

    private String updater;

    private String consignmentType;

    private String pricePolicy;

    private Integer levelNo;

    private String customerAccount;

    private String customerGroup;

    private String customerDmsl;

    private String customerFxssl;

    private Short dateCrts;

    private String customerAgent;

    private String areaDistrict3;

    private String officeTel1;

    private String handset;

    private String customerLevel;

    private String transcompanyId;

    private String manager;

    private String stockNo;

    private String customerBelong2;

    private String manager1;

    private String invoice;

    private BigDecimal receiveMonthStartSal;

    private BigDecimal receiveMonthStartZtSal;

    private String handset2;

    private String transFlag;

    private Date passDate;

    private String signFlag;

    private BigDecimal moneyRebate;

    private String juridicPersonHandsetD;

    private String qqD;

    private String faxWlD;

    private String zipCodeWlD;

    private Integer shopAreaD;

    private String businessWay;

    private String businessLicenseNo;

    private String organizationNo;

    private String generaltaxpayerStatus;

    private String customerNameOld;

    private String examineFlag;

    private String dgzh;

    private String customerCode4;

    private String idcardNo;

    private String flagSalItem;

    private String businesslicenseDateEnd;

    private String customerPpFlag;

    private String taxAddress;

    private String taxTel;

    private String taxBank;

    private String taxBankNo;

    private String customerAgent1;

    private String importantLevel;

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode == null ? null : customerCode.trim();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId == null ? null : statusId.trim();
    }

    public String getCustomerBelong() {
        return customerBelong;
    }

    public void setCustomerBelong(String customerBelong) {
        this.customerBelong = customerBelong == null ? null : customerBelong.trim();
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName == null ? null : shortName.trim();
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType == null ? null : customerType.trim();
    }

    public String getCustomerCode3() {
        return customerCode3;
    }

    public void setCustomerCode3(String customerCode3) {
        this.customerCode3 = customerCode3 == null ? null : customerCode3.trim();
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getCityType() {
        return cityType;
    }

    public void setCityType(String cityType) {
        this.cityType = cityType == null ? null : cityType.trim();
    }

    public String getAgencyStatus() {
        return agencyStatus;
    }

    public void setAgencyStatus(String agencyStatus) {
        this.agencyStatus = agencyStatus == null ? null : agencyStatus.trim();
    }

    public String getCustomerZt() {
        return customerZt;
    }

    public void setCustomerZt(String customerZt) {
        this.customerZt = customerZt == null ? null : customerZt.trim();
    }

    public String getO2oFlag() {
        return o2oFlag;
    }

    public void setO2oFlag(String o2oFlag) {
        this.o2oFlag = o2oFlag == null ? null : o2oFlag.trim();
    }

    public String getAreaDistrict() {
        return areaDistrict;
    }

    public void setAreaDistrict(String areaDistrict) {
        this.areaDistrict = areaDistrict == null ? null : areaDistrict.trim();
    }

    public BigDecimal getBalanceReceive() {
        return balanceReceive;
    }

    public void setBalanceReceive(BigDecimal balanceReceive) {
        this.balanceReceive = balanceReceive;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode == null ? null : creditCode.trim();
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId == null ? null : currencyId.trim();
    }

    public Date getDateCre() {
        return dateCre;
    }

    public void setDateCre(Date dateCre) {
        this.dateCre = dateCre;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo == null ? null : deptNo.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    public String getFinanceCode() {
        return financeCode;
    }

    public void setFinanceCode(String financeCode) {
        this.financeCode = financeCode == null ? null : financeCode.trim();
    }

    public String getIntranet() {
        return intranet;
    }

    public void setIntranet(String intranet) {
        this.intranet = intranet == null ? null : intranet.trim();
    }

    public String getJuridicPerson() {
        return juridicPerson;
    }

    public void setJuridicPerson(String juridicPerson) {
        this.juridicPerson = juridicPerson == null ? null : juridicPerson.trim();
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes == null ? null : notes.trim();
    }

    public String getOfficeTel() {
        return officeTel;
    }

    public void setOfficeTel(String officeTel) {
        this.officeTel = officeTel == null ? null : officeTel.trim();
    }

    public String getPostAddress() {
        return postAddress;
    }

    public void setPostAddress(String postAddress) {
        this.postAddress = postAddress == null ? null : postAddress.trim();
    }

    public BigDecimal getQuotaSale() {
        return quotaSale;
    }

    public void setQuotaSale(BigDecimal quotaSale) {
        this.quotaSale = quotaSale;
    }

    public String getSalesman() {
        return salesman;
    }

    public void setSalesman(String salesman) {
        this.salesman = salesman == null ? null : salesman.trim();
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale == null ? null : scale.trim();
    }

    public String getTaxNo() {
        return taxNo;
    }

    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo == null ? null : taxNo.trim();
    }

    public String getTradeDesc() {
        return tradeDesc;
    }

    public void setTradeDesc(String tradeDesc) {
        this.tradeDesc = tradeDesc == null ? null : tradeDesc.trim();
    }

    public String gettAccount() {
        return tAccount;
    }

    public void settAccount(String tAccount) {
        this.tAccount = tAccount == null ? null : tAccount.trim();
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode == null ? null : zipCode.trim();
    }

    public String getImportantFlag() {
        return importantFlag;
    }

    public void setImportantFlag(String importantFlag) {
        this.importantFlag = importantFlag == null ? null : importantFlag.trim();
    }

    public String getDeliveryAdress() {
        return deliveryAdress;
    }

    public void setDeliveryAdress(String deliveryAdress) {
        this.deliveryAdress = deliveryAdress == null ? null : deliveryAdress.trim();
    }

    public String getCustomerClassify() {
        return customerClassify;
    }

    public void setCustomerClassify(String customerClassify) {
        this.customerClassify = customerClassify == null ? null : customerClassify.trim();
    }

    public String getFollowCode() {
        return followCode;
    }

    public void setFollowCode(String followCode) {
        this.followCode = followCode == null ? null : followCode.trim();
    }

    public BigDecimal getInvoiceRecieveStart() {
        return invoiceRecieveStart;
    }

    public void setInvoiceRecieveStart(BigDecimal invoiceRecieveStart) {
        this.invoiceRecieveStart = invoiceRecieveStart;
    }

    public Date getDateChangeStatus() {
        return dateChangeStatus;
    }

    public void setDateChangeStatus(Date dateChangeStatus) {
        this.dateChangeStatus = dateChangeStatus;
    }

    public String getOperaterChangeStatus() {
        return operaterChangeStatus;
    }

    public void setOperaterChangeStatus(String operaterChangeStatus) {
        this.operaterChangeStatus = operaterChangeStatus == null ? null : operaterChangeStatus.trim();
    }

    public String getReasonChangeStatus() {
        return reasonChangeStatus;
    }

    public void setReasonChangeStatus(String reasonChangeStatus) {
        this.reasonChangeStatus = reasonChangeStatus == null ? null : reasonChangeStatus.trim();
    }

    public BigDecimal getBalanceReceiveZt() {
        return balanceReceiveZt;
    }

    public void setBalanceReceiveZt(BigDecimal balanceReceiveZt) {
        this.balanceReceiveZt = balanceReceiveZt;
    }

    public BigDecimal getReceiveMonthStart() {
        return receiveMonthStart;
    }

    public void setReceiveMonthStart(BigDecimal receiveMonthStart) {
        this.receiveMonthStart = receiveMonthStart;
    }

    public BigDecimal getReceiveMonthStartZt() {
        return receiveMonthStartZt;
    }

    public void setReceiveMonthStartZt(BigDecimal receiveMonthStartZt) {
        this.receiveMonthStartZt = receiveMonthStartZt;
    }

    public String getAreaDistrict2() {
        return areaDistrict2;
    }

    public void setAreaDistrict2(String areaDistrict2) {
        this.areaDistrict2 = areaDistrict2 == null ? null : areaDistrict2.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater == null ? null : updater.trim();
    }

    public String getConsignmentType() {
        return consignmentType;
    }

    public void setConsignmentType(String consignmentType) {
        this.consignmentType = consignmentType == null ? null : consignmentType.trim();
    }

    public String getPricePolicy() {
        return pricePolicy;
    }

    public void setPricePolicy(String pricePolicy) {
        this.pricePolicy = pricePolicy == null ? null : pricePolicy.trim();
    }

    public Integer getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(Integer levelNo) {
        this.levelNo = levelNo;
    }

    public String getCustomerAccount() {
        return customerAccount;
    }

    public void setCustomerAccount(String customerAccount) {
        this.customerAccount = customerAccount == null ? null : customerAccount.trim();
    }

    public String getCustomerGroup() {
        return customerGroup;
    }

    public void setCustomerGroup(String customerGroup) {
        this.customerGroup = customerGroup == null ? null : customerGroup.trim();
    }

    public String getCustomerDmsl() {
        return customerDmsl;
    }

    public void setCustomerDmsl(String customerDmsl) {
        this.customerDmsl = customerDmsl == null ? null : customerDmsl.trim();
    }

    public String getCustomerFxssl() {
        return customerFxssl;
    }

    public void setCustomerFxssl(String customerFxssl) {
        this.customerFxssl = customerFxssl == null ? null : customerFxssl.trim();
    }

    public Short getDateCrts() {
        return dateCrts;
    }

    public void setDateCrts(Short dateCrts) {
        this.dateCrts = dateCrts;
    }

    public String getCustomerAgent() {
        return customerAgent;
    }

    public void setCustomerAgent(String customerAgent) {
        this.customerAgent = customerAgent == null ? null : customerAgent.trim();
    }

    public String getAreaDistrict3() {
        return areaDistrict3;
    }

    public void setAreaDistrict3(String areaDistrict3) {
        this.areaDistrict3 = areaDistrict3 == null ? null : areaDistrict3.trim();
    }

    public String getOfficeTel1() {
        return officeTel1;
    }

    public void setOfficeTel1(String officeTel1) {
        this.officeTel1 = officeTel1 == null ? null : officeTel1.trim();
    }

    public String getHandset() {
        return handset;
    }

    public void setHandset(String handset) {
        this.handset = handset == null ? null : handset.trim();
    }

    public String getCustomerLevel() {
        return customerLevel;
    }

    public void setCustomerLevel(String customerLevel) {
        this.customerLevel = customerLevel == null ? null : customerLevel.trim();
    }

    public String getTranscompanyId() {
        return transcompanyId;
    }

    public void setTranscompanyId(String transcompanyId) {
        this.transcompanyId = transcompanyId == null ? null : transcompanyId.trim();
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager == null ? null : manager.trim();
    }

    public String getStockNo() {
        return stockNo;
    }

    public void setStockNo(String stockNo) {
        this.stockNo = stockNo == null ? null : stockNo.trim();
    }

    public String getCustomerBelong2() {
        return customerBelong2;
    }

    public void setCustomerBelong2(String customerBelong2) {
        this.customerBelong2 = customerBelong2 == null ? null : customerBelong2.trim();
    }

    public String getManager1() {
        return manager1;
    }

    public void setManager1(String manager1) {
        this.manager1 = manager1 == null ? null : manager1.trim();
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice == null ? null : invoice.trim();
    }

    public BigDecimal getReceiveMonthStartSal() {
        return receiveMonthStartSal;
    }

    public void setReceiveMonthStartSal(BigDecimal receiveMonthStartSal) {
        this.receiveMonthStartSal = receiveMonthStartSal;
    }

    public BigDecimal getReceiveMonthStartZtSal() {
        return receiveMonthStartZtSal;
    }

    public void setReceiveMonthStartZtSal(BigDecimal receiveMonthStartZtSal) {
        this.receiveMonthStartZtSal = receiveMonthStartZtSal;
    }

    public String getHandset2() {
        return handset2;
    }

    public void setHandset2(String handset2) {
        this.handset2 = handset2 == null ? null : handset2.trim();
    }

    public String getTransFlag() {
        return transFlag;
    }

    public void setTransFlag(String transFlag) {
        this.transFlag = transFlag == null ? null : transFlag.trim();
    }

    public Date getPassDate() {
        return passDate;
    }

    public void setPassDate(Date passDate) {
        this.passDate = passDate;
    }

    public String getSignFlag() {
        return signFlag;
    }

    public void setSignFlag(String signFlag) {
        this.signFlag = signFlag == null ? null : signFlag.trim();
    }

    public BigDecimal getMoneyRebate() {
        return moneyRebate;
    }

    public void setMoneyRebate(BigDecimal moneyRebate) {
        this.moneyRebate = moneyRebate;
    }

    public String getJuridicPersonHandsetD() {
        return juridicPersonHandsetD;
    }

    public void setJuridicPersonHandsetD(String juridicPersonHandsetD) {
        this.juridicPersonHandsetD = juridicPersonHandsetD == null ? null : juridicPersonHandsetD.trim();
    }

    public String getQqD() {
        return qqD;
    }

    public void setQqD(String qqD) {
        this.qqD = qqD == null ? null : qqD.trim();
    }

    public String getFaxWlD() {
        return faxWlD;
    }

    public void setFaxWlD(String faxWlD) {
        this.faxWlD = faxWlD == null ? null : faxWlD.trim();
    }

    public String getZipCodeWlD() {
        return zipCodeWlD;
    }

    public void setZipCodeWlD(String zipCodeWlD) {
        this.zipCodeWlD = zipCodeWlD == null ? null : zipCodeWlD.trim();
    }

    public Integer getShopAreaD() {
        return shopAreaD;
    }

    public void setShopAreaD(Integer shopAreaD) {
        this.shopAreaD = shopAreaD;
    }

    public String getBusinessWay() {
        return businessWay;
    }

    public void setBusinessWay(String businessWay) {
        this.businessWay = businessWay == null ? null : businessWay.trim();
    }

    public String getBusinessLicenseNo() {
        return businessLicenseNo;
    }

    public void setBusinessLicenseNo(String businessLicenseNo) {
        this.businessLicenseNo = businessLicenseNo == null ? null : businessLicenseNo.trim();
    }

    public String getOrganizationNo() {
        return organizationNo;
    }

    public void setOrganizationNo(String organizationNo) {
        this.organizationNo = organizationNo == null ? null : organizationNo.trim();
    }

    public String getGeneraltaxpayerStatus() {
        return generaltaxpayerStatus;
    }

    public void setGeneraltaxpayerStatus(String generaltaxpayerStatus) {
        this.generaltaxpayerStatus = generaltaxpayerStatus == null ? null : generaltaxpayerStatus.trim();
    }

    public String getCustomerNameOld() {
        return customerNameOld;
    }

    public void setCustomerNameOld(String customerNameOld) {
        this.customerNameOld = customerNameOld == null ? null : customerNameOld.trim();
    }

    public String getExamineFlag() {
        return examineFlag;
    }

    public void setExamineFlag(String examineFlag) {
        this.examineFlag = examineFlag == null ? null : examineFlag.trim();
    }

    public String getDgzh() {
        return dgzh;
    }

    public void setDgzh(String dgzh) {
        this.dgzh = dgzh == null ? null : dgzh.trim();
    }

    public String getCustomerCode4() {
        return customerCode4;
    }

    public void setCustomerCode4(String customerCode4) {
        this.customerCode4 = customerCode4 == null ? null : customerCode4.trim();
    }

    public String getIdcardNo() {
        return idcardNo;
    }

    public void setIdcardNo(String idcardNo) {
        this.idcardNo = idcardNo == null ? null : idcardNo.trim();
    }

    public String getFlagSalItem() {
        return flagSalItem;
    }

    public void setFlagSalItem(String flagSalItem) {
        this.flagSalItem = flagSalItem == null ? null : flagSalItem.trim();
    }

    public String getBusinesslicenseDateEnd() {
        return businesslicenseDateEnd;
    }

    public void setBusinesslicenseDateEnd(String businesslicenseDateEnd) {
        this.businesslicenseDateEnd = businesslicenseDateEnd == null ? null : businesslicenseDateEnd.trim();
    }

    public String getCustomerPpFlag() {
        return customerPpFlag;
    }

    public void setCustomerPpFlag(String customerPpFlag) {
        this.customerPpFlag = customerPpFlag == null ? null : customerPpFlag.trim();
    }

    public String getTaxAddress() {
        return taxAddress;
    }

    public void setTaxAddress(String taxAddress) {
        this.taxAddress = taxAddress == null ? null : taxAddress.trim();
    }

    public String getTaxTel() {
        return taxTel;
    }

    public void setTaxTel(String taxTel) {
        this.taxTel = taxTel == null ? null : taxTel.trim();
    }

    public String getTaxBank() {
        return taxBank;
    }

    public void setTaxBank(String taxBank) {
        this.taxBank = taxBank == null ? null : taxBank.trim();
    }

    public String getTaxBankNo() {
        return taxBankNo;
    }

    public void setTaxBankNo(String taxBankNo) {
        this.taxBankNo = taxBankNo == null ? null : taxBankNo.trim();
    }

    public String getCustomerAgent1() {
        return customerAgent1;
    }

    public void setCustomerAgent1(String customerAgent1) {
        this.customerAgent1 = customerAgent1 == null ? null : customerAgent1.trim();
    }

    public String getImportantLevel() {
        return importantLevel;
    }

    public void setImportantLevel(String importantLevel) {
        this.importantLevel = importantLevel == null ? null : importantLevel.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(getCustomerCode(), customer.getCustomerCode()) &&
                Objects.equals(getCustomerName(), customer.getCustomerName()) &&
                Objects.equals(getStatusId(), customer.getStatusId()) &&
                Objects.equals(getCustomerBelong(), customer.getCustomerBelong()) &&
                Objects.equals(getAreaCode(), customer.getAreaCode()) &&
                Objects.equals(getShortName(), customer.getShortName()) &&
                Objects.equals(getCustomerType(), customer.getCustomerType()) &&
                Objects.equals(getCustomerCode3(), customer.getCustomerCode3()) &&
                Objects.equals(getDistrict(), customer.getDistrict()) &&
                Objects.equals(getCity(), customer.getCity()) &&
                Objects.equals(getCityType(), customer.getCityType()) &&
                Objects.equals(getAgencyStatus(), customer.getAgencyStatus()) &&
                Objects.equals(getCustomerZt(), customer.getCustomerZt()) &&
                Objects.equals(getO2oFlag(), customer.getO2oFlag()) &&
                Objects.equals(getAreaDistrict(), customer.getAreaDistrict()) &&
                Objects.equals(getBalanceReceive(), customer.getBalanceReceive()) &&
                Objects.equals(getCreditCode(), customer.getCreditCode()) &&
                Objects.equals(getCurrencyId(), customer.getCurrencyId()) &&
                Objects.equals(getDateCre(), customer.getDateCre()) &&
                Objects.equals(getDateUpdate(), customer.getDateUpdate()) &&
                Objects.equals(getDeptNo(), customer.getDeptNo()) &&
                Objects.equals(getEmail(), customer.getEmail()) &&
                Objects.equals(getFax(), customer.getFax()) &&
                Objects.equals(getFinanceCode(), customer.getFinanceCode()) &&
                Objects.equals(getIntranet(), customer.getIntranet()) &&
                Objects.equals(getJuridicPerson(), customer.getJuridicPerson()) &&
                Objects.equals(getNotes(), customer.getNotes()) &&
                Objects.equals(getOfficeTel(), customer.getOfficeTel()) &&
                Objects.equals(getPostAddress(), customer.getPostAddress()) &&
                Objects.equals(getQuotaSale(), customer.getQuotaSale()) &&
                Objects.equals(getSalesman(), customer.getSalesman()) &&
                Objects.equals(getScale(), customer.getScale()) &&
                Objects.equals(getTaxNo(), customer.getTaxNo()) &&
                Objects.equals(getTradeDesc(), customer.getTradeDesc()) &&
                Objects.equals(gettAccount(), customer.gettAccount()) &&
                Objects.equals(getZipCode(), customer.getZipCode()) &&
                Objects.equals(getImportantFlag(), customer.getImportantFlag()) &&
                Objects.equals(getDeliveryAdress(), customer.getDeliveryAdress()) &&
                Objects.equals(getCustomerClassify(), customer.getCustomerClassify()) &&
                Objects.equals(getFollowCode(), customer.getFollowCode()) &&
                Objects.equals(getInvoiceRecieveStart(), customer.getInvoiceRecieveStart()) &&
                Objects.equals(getDateChangeStatus(), customer.getDateChangeStatus()) &&
                Objects.equals(getOperaterChangeStatus(), customer.getOperaterChangeStatus()) &&
                Objects.equals(getReasonChangeStatus(), customer.getReasonChangeStatus()) &&
                Objects.equals(getBalanceReceiveZt(), customer.getBalanceReceiveZt()) &&
                Objects.equals(getReceiveMonthStart(), customer.getReceiveMonthStart()) &&
                Objects.equals(getReceiveMonthStartZt(), customer.getReceiveMonthStartZt()) &&
                Objects.equals(getAreaDistrict2(), customer.getAreaDistrict2()) &&
                Objects.equals(getCreator(), customer.getCreator()) &&
                Objects.equals(getUpdater(), customer.getUpdater()) &&
                Objects.equals(getConsignmentType(), customer.getConsignmentType()) &&
                Objects.equals(getPricePolicy(), customer.getPricePolicy()) &&
                Objects.equals(getLevelNo(), customer.getLevelNo()) &&
                Objects.equals(getCustomerAccount(), customer.getCustomerAccount()) &&
                Objects.equals(getCustomerGroup(), customer.getCustomerGroup()) &&
                Objects.equals(getCustomerDmsl(), customer.getCustomerDmsl()) &&
                Objects.equals(getCustomerFxssl(), customer.getCustomerFxssl()) &&
                Objects.equals(getDateCrts(), customer.getDateCrts()) &&
                Objects.equals(getCustomerAgent(), customer.getCustomerAgent()) &&
                Objects.equals(getAreaDistrict3(), customer.getAreaDistrict3()) &&
                Objects.equals(getOfficeTel1(), customer.getOfficeTel1()) &&
                Objects.equals(getHandset(), customer.getHandset()) &&
                Objects.equals(getCustomerLevel(), customer.getCustomerLevel()) &&
                Objects.equals(getTranscompanyId(), customer.getTranscompanyId()) &&
                Objects.equals(getManager(), customer.getManager()) &&
                Objects.equals(getStockNo(), customer.getStockNo()) &&
                Objects.equals(getCustomerBelong2(), customer.getCustomerBelong2()) &&
                Objects.equals(getManager1(), customer.getManager1()) &&
                Objects.equals(getInvoice(), customer.getInvoice()) &&
                Objects.equals(getReceiveMonthStartSal(), customer.getReceiveMonthStartSal()) &&
                Objects.equals(getReceiveMonthStartZtSal(), customer.getReceiveMonthStartZtSal()) &&
                Objects.equals(getHandset2(), customer.getHandset2()) &&
                Objects.equals(getTransFlag(), customer.getTransFlag()) &&
                Objects.equals(getPassDate(), customer.getPassDate()) &&
                Objects.equals(getSignFlag(), customer.getSignFlag()) &&
                Objects.equals(getMoneyRebate(), customer.getMoneyRebate()) &&
                Objects.equals(getJuridicPersonHandsetD(), customer.getJuridicPersonHandsetD()) &&
                Objects.equals(getQqD(), customer.getQqD()) &&
                Objects.equals(getFaxWlD(), customer.getFaxWlD()) &&
                Objects.equals(getZipCodeWlD(), customer.getZipCodeWlD()) &&
                Objects.equals(getShopAreaD(), customer.getShopAreaD()) &&
                Objects.equals(getBusinessWay(), customer.getBusinessWay()) &&
                Objects.equals(getBusinessLicenseNo(), customer.getBusinessLicenseNo()) &&
                Objects.equals(getOrganizationNo(), customer.getOrganizationNo()) &&
                Objects.equals(getGeneraltaxpayerStatus(), customer.getGeneraltaxpayerStatus()) &&
                Objects.equals(getCustomerNameOld(), customer.getCustomerNameOld()) &&
                Objects.equals(getExamineFlag(), customer.getExamineFlag()) &&
                Objects.equals(getDgzh(), customer.getDgzh()) &&
                Objects.equals(getCustomerCode4(), customer.getCustomerCode4()) &&
                Objects.equals(getIdcardNo(), customer.getIdcardNo()) &&
                Objects.equals(getFlagSalItem(), customer.getFlagSalItem()) &&
                Objects.equals(getBusinesslicenseDateEnd(), customer.getBusinesslicenseDateEnd()) &&
                Objects.equals(getCustomerPpFlag(), customer.getCustomerPpFlag()) &&
                Objects.equals(getTaxAddress(), customer.getTaxAddress()) &&
                Objects.equals(getTaxTel(), customer.getTaxTel()) &&
                Objects.equals(getTaxBank(), customer.getTaxBank()) &&
                Objects.equals(getTaxBankNo(), customer.getTaxBankNo()) &&
                Objects.equals(getCustomerAgent1(), customer.getCustomerAgent1()) &&
                Objects.equals(getImportantLevel(), customer.getImportantLevel());
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerCode='" + customerCode + '\'' +
                ", customerName='" + customerName + '\'' +
                ", statusId='" + statusId + '\'' +
                ", customerBelong='" + customerBelong + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", shortName='" + shortName + '\'' +
                ", customerType='" + customerType + '\'' +
                ", customerCode3='" + customerCode3 + '\'' +
                ", district='" + district + '\'' +
                ", city='" + city + '\'' +
                ", cityType='" + cityType + '\'' +
                ", agencyStatus='" + agencyStatus + '\'' +
                ", customerZt='" + customerZt + '\'' +
                ", o2oFlag='" + o2oFlag + '\'' +
                ", areaDistrict='" + areaDistrict + '\'' +
                ", balanceReceive=" + balanceReceive +
                ", creditCode='" + creditCode + '\'' +
                ", currencyId='" + currencyId + '\'' +
                ", dateCre=" + dateCre +
                ", dateUpdate=" + dateUpdate +
                ", deptNo='" + deptNo + '\'' +
                ", email='" + email + '\'' +
                ", fax='" + fax + '\'' +
                ", financeCode='" + financeCode + '\'' +
                ", intranet='" + intranet + '\'' +
                ", juridicPerson='" + juridicPerson + '\'' +
                ", notes='" + notes + '\'' +
                ", officeTel='" + officeTel + '\'' +
                ", postAddress='" + postAddress + '\'' +
                ", quotaSale=" + quotaSale +
                ", salesman='" + salesman + '\'' +
                ", scale='" + scale + '\'' +
                ", taxNo='" + taxNo + '\'' +
                ", tradeDesc='" + tradeDesc + '\'' +
                ", tAccount='" + tAccount + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", importantFlag='" + importantFlag + '\'' +
                ", deliveryAdress='" + deliveryAdress + '\'' +
                ", customerClassify='" + customerClassify + '\'' +
                ", followCode='" + followCode + '\'' +
                ", invoiceRecieveStart=" + invoiceRecieveStart +
                ", dateChangeStatus=" + dateChangeStatus +
                ", operaterChangeStatus='" + operaterChangeStatus + '\'' +
                ", reasonChangeStatus='" + reasonChangeStatus + '\'' +
                ", balanceReceiveZt=" + balanceReceiveZt +
                ", receiveMonthStart=" + receiveMonthStart +
                ", receiveMonthStartZt=" + receiveMonthStartZt +
                ", areaDistrict2='" + areaDistrict2 + '\'' +
                ", creator='" + creator + '\'' +
                ", updater='" + updater + '\'' +
                ", consignmentType='" + consignmentType + '\'' +
                ", pricePolicy='" + pricePolicy + '\'' +
                ", levelNo=" + levelNo +
                ", customerAccount='" + customerAccount + '\'' +
                ", customerGroup='" + customerGroup + '\'' +
                ", customerDmsl='" + customerDmsl + '\'' +
                ", customerFxssl='" + customerFxssl + '\'' +
                ", dateCrts=" + dateCrts +
                ", customerAgent='" + customerAgent + '\'' +
                ", areaDistrict3='" + areaDistrict3 + '\'' +
                ", officeTel1='" + officeTel1 + '\'' +
                ", handset='" + handset + '\'' +
                ", customerLevel='" + customerLevel + '\'' +
                ", transcompanyId='" + transcompanyId + '\'' +
                ", manager='" + manager + '\'' +
                ", stockNo='" + stockNo + '\'' +
                ", customerBelong2='" + customerBelong2 + '\'' +
                ", manager1='" + manager1 + '\'' +
                ", invoice='" + invoice + '\'' +
                ", receiveMonthStartSal=" + receiveMonthStartSal +
                ", receiveMonthStartZtSal=" + receiveMonthStartZtSal +
                ", handset2='" + handset2 + '\'' +
                ", transFlag='" + transFlag + '\'' +
                ", passDate=" + passDate +
                ", signFlag='" + signFlag + '\'' +
                ", moneyRebate=" + moneyRebate +
                ", juridicPersonHandsetD='" + juridicPersonHandsetD + '\'' +
                ", qqD='" + qqD + '\'' +
                ", faxWlD='" + faxWlD + '\'' +
                ", zipCodeWlD='" + zipCodeWlD + '\'' +
                ", shopAreaD=" + shopAreaD +
                ", businessWay='" + businessWay + '\'' +
                ", businessLicenseNo='" + businessLicenseNo + '\'' +
                ", organizationNo='" + organizationNo + '\'' +
                ", generaltaxpayerStatus='" + generaltaxpayerStatus + '\'' +
                ", customerNameOld='" + customerNameOld + '\'' +
                ", examineFlag='" + examineFlag + '\'' +
                ", dgzh='" + dgzh + '\'' +
                ", customerCode4='" + customerCode4 + '\'' +
                ", idcardNo='" + idcardNo + '\'' +
                ", flagSalItem='" + flagSalItem + '\'' +
                ", businesslicenseDateEnd='" + businesslicenseDateEnd + '\'' +
                ", customerPpFlag='" + customerPpFlag + '\'' +
                ", taxAddress='" + taxAddress + '\'' +
                ", taxTel='" + taxTel + '\'' +
                ", taxBank='" + taxBank + '\'' +
                ", taxBankNo='" + taxBankNo + '\'' +
                ", customerAgent1='" + customerAgent1 + '\'' +
                ", importantLevel='" + importantLevel + '\'' +
                '}';
    }
}