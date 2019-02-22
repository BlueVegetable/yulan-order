package com.yulan.pojo;

/**
 * 客户资料卡-协议综合查询
 */
public class YLc_info {
    private String customerId;
    private String customerName;
    private String cardState;
    private int file_1_idcard;//0无，1有 身份证
    private int file_2_businesslicense;//营业执照
    private int file_3_orgcode;//组织机构代码证
    private int file_4_gtqc;//税务登记证
    private String tx_agent_name;//联系人
    private String x_juridic_person;//法人
    private String juridic_person_handset;//法人电话
    private String marketName;//大区
    private String marketManagerName;//大区管理人
    private int ylcId;//协议书状态
    private String ylcState;//协议书状态

    public String getYlcState() {
        return ylcState;
    }

    public void setYlcState(String ylcState) {
        this.ylcState = ylcState;
    }

    public YLc_info(String customerId, String customerName, String cardState, int file_1_idcard, int file_2_businesslicense, int file_3_orgcode, int file_4_gtqc, String tx_agent_name, String x_juridic_person, String juridic_person_handset, String marketName, String marketManagerName, int ylcId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.cardState = cardState;
        this.file_1_idcard = file_1_idcard;
        this.file_2_businesslicense = file_2_businesslicense;
        this.file_3_orgcode = file_3_orgcode;
        this.file_4_gtqc = file_4_gtqc;
        this.tx_agent_name = tx_agent_name;
        this.x_juridic_person = x_juridic_person;
        this.juridic_person_handset = juridic_person_handset;
        this.marketName = marketName;
        this.marketManagerName = marketManagerName;
        this.ylcId = ylcId;
    }

    public YLc_info() {
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCardState() {
        return cardState;
    }

    public void setCardState(String cardState) {
        this.cardState = cardState;
    }

    public int getFile_1_idcard() {
        return file_1_idcard;
    }

    public void setFile_1_idcard(int file_1_idcard) {
        this.file_1_idcard = file_1_idcard;
    }

    public int getFile_2_businesslicense() {
        return file_2_businesslicense;
    }

    public void setFile_2_businesslicense(int file_2_businesslicense) {
        this.file_2_businesslicense = file_2_businesslicense;
    }

    public int getFile_3_orgcode() {
        return file_3_orgcode;
    }

    public void setFile_3_orgcode(int file_3_orgcode) {
        this.file_3_orgcode = file_3_orgcode;
    }

    public int getFile_4_gtqc() {
        return file_4_gtqc;
    }

    public void setFile_4_gtqc(int file_4_gtqc) {
        this.file_4_gtqc = file_4_gtqc;
    }

    public String getTx_agent_name() {
        return tx_agent_name;
    }

    public void setTx_agent_name(String tx_agent_name) {
        this.tx_agent_name = tx_agent_name;
    }

    public String getX_juridic_person() {
        return x_juridic_person;
    }

    public void setX_juridic_person(String x_juridic_person) {
        this.x_juridic_person = x_juridic_person;
    }

    public String getJuridic_person_handset() {
        return juridic_person_handset;
    }

    public void setJuridic_person_handset(String juridic_person_handset) {
        this.juridic_person_handset = juridic_person_handset;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getMarketManagerName() {
        return marketManagerName;
    }

    public void setMarketManagerName(String marketManagerName) {
        this.marketManagerName = marketManagerName;
    }

    public int getYlcId() {
        return ylcId;
    }

    public void setYlcId(int ylcId) {
        this.ylcId = ylcId;
    }
}
