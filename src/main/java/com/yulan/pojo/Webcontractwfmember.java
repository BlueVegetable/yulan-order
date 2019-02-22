package com.yulan.pojo;

public class Webcontractwfmember {
    private String roleId;
    private int cYear;
    private String wfUserId;
    private String bindUserName;
    private String bindUserId;
    private int disabled;
    private Webcontractwfrole webcontractwfrole;

    public Webcontractwfmember(String roleId, int cYear, String wfUserId, String bindUserName, String bindUserId, int disabled, Webcontractwfrole webcontractwfrole) {
        this.roleId = roleId;
        this.cYear = cYear;
        this.wfUserId = wfUserId;
        this.bindUserName = bindUserName;
        this.bindUserId = bindUserId;
        this.disabled = disabled;
        this.webcontractwfrole = webcontractwfrole;
    }

    public Webcontractwfrole getWebcontractwfrole() {
        return webcontractwfrole;
    }

    public void setWebcontractwfrole(Webcontractwfrole webcontractwfrole) {
        this.webcontractwfrole = webcontractwfrole;
    }

    public String getBindUserId() {
        return bindUserId;
    }

    public void setBindUserId(String bindUserId) {
        this.bindUserId = bindUserId;
    }

    public Webcontractwfmember(String roleId, int cYear, String wfUserId, String bindUserName, String bindUserId, int disabled) {
        this.roleId = roleId;
        this.cYear = cYear;
        this.wfUserId = wfUserId;
        this.bindUserName = bindUserName;
        this.bindUserId = bindUserId;
        this.disabled = disabled;
    }

    public Webcontractwfmember(String roleId, int cYear, String wfUserId, String bindUserName, int disabled) {
        this.roleId = roleId;
        this.cYear = cYear;
        this.wfUserId = wfUserId;
        this.bindUserName = bindUserName;
        this.disabled = disabled;
    }

    public String getRoleId() {
        return roleId;
    }

    public Webcontractwfmember() {
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public int getcYear() {
        return cYear;
    }

    public void setcYear(int cYear) {
        this.cYear = cYear;
    }

    public String getWfUserId() {
        return wfUserId;
    }

    public void setWfUserId(String wfUserId) {
        this.wfUserId = wfUserId;
    }

    public String getBindUserName() {
        return bindUserName;
    }

    public void setBindUserName(String bindUserName) {
        this.bindUserName = bindUserName;
    }

    public int getDisabled() {
        return disabled;
    }

    public void setDisabled(int disabled) {
        this.disabled = disabled;
    }
}