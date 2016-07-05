package com.zbPro.seed.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/* 时间：2016/5/27
* 名称：农户数据实例
* 作用：封装农夫信息
* 实现功能：从服务器数据库中收录农户的基本信息保存到数据库中
* */
@DatabaseTable(tableName = "FarmerBean")
public class FarmerBean {
    @DatabaseField(generatedId = true)
    private int id;
    //农户姓名
    @DatabaseField(columnName = "name")
    private String name;
    //地块号
    @DatabaseField(columnName = "dKNumber")
    private String dKNumber;
    //种类
    @DatabaseField(columnName = "type")
    private String type;
    //基地名称
    @DatabaseField(columnName = "basename")
    private String baseName;
    //农户联系地址
    @DatabaseField(columnName = "house")
    private String house;
    //农户身份证号码
    @DatabaseField(columnName = "idcard")
    private String idCard;
    //农户管理区域温度
    @DatabaseField(columnName = "temperature")
    private String temperature;
    //村委会配合程度
    @DatabaseField(columnName = "voyages")
    private String voyages;
    //农户土地管理模式
    @DatabaseField(columnName = "manure")
    private String manure;
    //农户所在生产队
    @DatabaseField(columnName = "troopsName")
    private String troopsName;
    //农户往年产量
    @DatabaseField(columnName = "yield")
    private String yield;
    //地块面积
    @DatabaseField(columnName = "aera")
    private String aera;

    @Override
    public String toString() {
        return "FarmerDao{" +
                "name='" + name + '\'' +
                ", dKNumber='" + dKNumber + '\'' +
                ", type='" + type + '\'' +
                ", baseName='" + baseName + '\'' +
                ", house='" + house + '\'' +
                ", idCard='" + idCard + '\'' +
                ", temperature='" + temperature + '\'' +
                ", voyages='" + voyages + '\'' +
                ", manure='" + manure + '\'' +
                ", troopsName='" + troopsName + '\'' +
                ", yield='" + yield + '\'' +
                ", aera='" + aera + '\'' +
                '}';
    }

    public FarmerBean(String name, String dKNumber, String type, String baseName,
                      String house, String idCard, String temperature,
                      String voyages, String manure,
                      String troopsName, String yield, String aera) {
        this.name = name;
        this.dKNumber = dKNumber;
        this.type = type;
        this.baseName = baseName;
        this.house = house;
        this.idCard = idCard;
        this.temperature = temperature;
        this.voyages = voyages;
        this.manure = manure;
        this.troopsName = troopsName;
        this.yield = yield;
        this.aera = aera;
    }

    public FarmerBean() {
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getdKNumber() {
        return dKNumber;
    }

    public void setdKNumber(String dKNumber) {
        this.dKNumber = dKNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBaseName() {
        return baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getVoyages() {
        return voyages;
    }

    public void setVoyages(String voyages) {
        this.voyages = voyages;
    }

    public String getManure() {
        return manure;
    }

    public void setManure(String manure) {
        this.manure = manure;
    }

    public String getTroopsName() {
        return troopsName;
    }

    public void setTroopsName(String troopsName) {
        this.troopsName = troopsName;
    }

    public String getYield() {
        return yield;
    }

    public void setYield(String yield) {
        this.yield = yield;
    }

    public String getAera() {
        return aera;
    }

    public void setAera(String aera) {
        this.aera = aera;
    }
}