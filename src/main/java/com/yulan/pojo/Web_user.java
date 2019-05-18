package com.yulan.pojo;

import java.sql.Date;

public class Web_user {
    private String loginName;
    private String password;
    private String realName;
    private String company;
    private String tel;
    private String email;
    private String memo;
    private Date ts;
    private String userId;
    private Date modifyDate;
    private String macAddress;
    private String type;
    private String passwordX;
    private Integer stopped;
    private String userState;//账号状态，""第一次，“10”未修改初始名密码但已经阅读了网络签定提示书，“01”修改了初始密码，但未阅读网络签定书，‘11’修改
    private Integer isManager;

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public Web_user(String loginName, String password, String realName, String company, String tel, String email, String memo, Date ts, String userId, Date modifyDate, String macAddress, String type, String passwordX, Integer stopped, String userState, String creatAdmin) {
        this.loginName = loginName;
        this.password = password;
        this.realName = realName;
        this.company = company;
        this.tel = tel;
        this.email = email;
        this.memo = memo;
        this.ts = ts;
        this.userId = userId;
        this.modifyDate = modifyDate;
        this.macAddress = macAddress;
        this.type = type;
        this.passwordX = passwordX;
        this.stopped = stopped;
        this.userState = userState;
        this.creatAdmin = creatAdmin;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    public Web_user() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getIsManager() {
        return isManager;
    }

    public void setIsManager(Integer isManager) {
        this.isManager = isManager;
    }

    public Web_user(String loginName, String password, String realName, String company, String tel, String email, String memo, Date ts, String userId, Date modifyDate, String macAddress, String type, String passwordX, Integer stopped, String creatAdmin) {
        this.loginName = loginName;
        this.password = password;
        this.realName = realName;
        this.company = company;
        this.tel = tel;
        this.email = email;
        this.memo = memo;
        this.ts = ts;
        this.userId = userId;
        this.modifyDate = modifyDate;
        this.macAddress = macAddress;
        this.type = type;
        this.passwordX = passwordX;
        this.stopped = stopped;
        this.creatAdmin = creatAdmin;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPasswordX() {
        return passwordX;
    }

    public void setPasswordX(String passwordX) {
        this.passwordX = passwordX;
    }

    public Integer getStopped() {
        return stopped;
    }

    public void setStopped(Integer stopped) {
        this.stopped = stopped;
    }

    public String getCreatAdmin() {
        return creatAdmin;
    }

    public void setCreatAdmin(String creatAdmin) {
        this.creatAdmin = creatAdmin;
    }

    private String creatAdmin;


    public String getLoginName() {
        return loginName;
    }

    public String getPassword() {
        return password;
    }

    public String getRealName() {
        return realName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
