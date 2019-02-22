package com.yulan.pojo;

import java.sql.Date;

/**
 * 业务员区域清单
 */
public class Area_owner {
    private String owner;    //关联ERP
    private String position;
    private String area;     //关联AREA_DISTRICT、Area_user
    private String status_id;
    private String creator;
    private Date date_cre;
    private String updater;
    private Date date_update;
    private String area_code;
    private String base_pay;
    private String center_manager;//区域经理
    private String name;       //账号姓名
    private String area_codeName; //市场



    private String areaName; //区域
    private String center_managerName;

    public String getCenter_managerName() {
        return center_managerName;
    }

    public void setCenter_managerName(String center_managerName) {
        this.center_managerName = center_managerName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea_codeName() {
        return area_codeName;
    }

    public void setArea_codeName(String area_codeName) {
        this.area_codeName = area_codeName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Area_owner(String owner, String position, String area, String status_id, String creator, Date date_cre, String updater, Date date_update, String area_code, String base_pay, String center_manager, String name, String area_codeName, String areaName) {
        this.owner = owner;
        this.position = position;
        this.area = area;
        this.status_id = status_id;
        this.creator = creator;
        this.date_cre = date_cre;
        this.updater = updater;
        this.date_update = date_update;
        this.area_code = area_code;
        this.base_pay = base_pay;
        this.center_manager = center_manager;
        this.name = name;
        this.area_codeName = area_codeName;
        this.areaName = areaName;
    }

    public Area_owner(String owner, String position, String area, String status_id, String creator, Date date_cre, String updater, Date date_update, String area_code, String base_pay, String center_manager) {
        this.owner = owner;
        this.position = position;
        this.area = area;
        this.status_id = status_id;
        this.creator = creator;
        this.date_cre = date_cre;
        this.updater = updater;
        this.date_update = date_update;
        this.area_code = area_code;
        this.base_pay = base_pay;
        this.center_manager = center_manager;
    }

    public Area_owner() {
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStatus_id() {
        return status_id;
    }

    public void setStatus_id(String status_id) {
        this.status_id = status_id;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getDate_cre() {
        return date_cre;
    }

    public void setDate_cre(Date date_cre) {
        this.date_cre = date_cre;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Date getDate_update() {
        return date_update;
    }

    public void setDate_update(Date date_update) {
        this.date_update = date_update;
    }

    public String getArea_code() {
        return area_code;
    }

    public void setArea_code(String area_code) {
        this.area_code = area_code;
    }

    public String getBase_pay() {
        return base_pay;
    }

    public void setBase_pay(String base_pay) {
        this.base_pay = base_pay;
    }

    public String getCenter_manager() {
        return center_manager;
    }

    public void setCenter_manager(String center_manager) {
        this.center_manager = center_manager;
    }

    @Override
    public String toString() {
        return "AreaOwner{" +
                "owner='" + owner + '\'' +
                ", position='" + position + '\'' +
                ", area='" + area + '\'' +
                ", status_id='" + status_id + '\'' +
                ", creator='" + creator + '\'' +
                ", date_cre=" + date_cre +
                ", updater='" + updater + '\'' +
                ", date_update=" + date_update +
                ", area_code='" + area_code + '\'' +
                ", base_pay='" + base_pay + '\'' +
                ", center_manager='" + center_manager + '\'' +
                ", name='" + name + '\'' +
                ", area_codeName='" + area_codeName + '\'' +
                ", areaName='" + areaName + '\'' +
                ", center_managerName='" + center_managerName + '\'' +
                '}';
    }
}
