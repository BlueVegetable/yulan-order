package com.yulan.pojo;

public class YLcontractentryShow {

    private Integer ID;
    private String name;
    private String test;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    @Override
    public String toString() {
        return "YLcontractentryShow{" +
                "ID=" + ID +
                ", test='" + test + '\'' +
                '}';
    }
}
