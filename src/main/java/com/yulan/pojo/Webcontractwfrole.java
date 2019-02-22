package com.yulan.pojo;

public class Webcontractwfrole {
    private String id;
    private String descp;
    private String userNameTemate;
    private byte userCount;

    public Webcontractwfrole(String id, String descp, String userNameTemate, byte userCount) {
        this.id = id;
        this.descp = descp;
        this.userNameTemate = userNameTemate;
        this.userCount = userCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescp() {
        return descp;
    }

    public void setDescp(String descp) {
        this.descp = descp;
    }

    public String getUserNameTemate() {
        return userNameTemate;
    }

    public void setUserNameTemate(String userNameTemate) {
        this.userNameTemate = userNameTemate;
    }

    public byte getUserCount() {
        return userCount;
    }

    public void setUserCount(byte userCount) {
        this.userCount = userCount;
    }

    public Webcontractwfrole() {
    }
}
