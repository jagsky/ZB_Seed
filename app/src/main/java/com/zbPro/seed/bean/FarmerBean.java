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
    private String farmerName;
    //地块号
    @DatabaseField(columnName = "dKNumber")
    private String dKnumber;
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
    private String idcard;
    //农户管理区域温度
    @DatabaseField(columnName = "temperature")
    private String temperature;
    //村委会配合程度
    @DatabaseField(columnName = "voyages")
    private String farmer_voyages;
    //农户土地管理模式
    @DatabaseField(columnName = "manure")
    private String farmer_manure;
    //农户所在生产队
    @DatabaseField(columnName = "troopsName")
    private String farmer_troopsname;
    //农户往年产量
    @DatabaseField(columnName = "yield")
    private String farmer_yield;
    //地块面积
    @DatabaseField(columnName = "aera")
    private String farmer_area;

    private String farmer_letter;

    public String getFarmer_letter() {
        return farmer_letter;
    }

    public void setFarmer_letter(String farmer_letter) {
        this.farmer_letter = farmer_letter;
    }

    public FarmerBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFarmerName() {
        return farmerName;
    }

    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }

    public String getdKnumber() {
        return dKnumber;
    }

    public void setdKnumber(String dKnumber) {
        this.dKnumber = dKnumber;
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

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getFarmer_voyages() {
        return farmer_voyages;
    }

    public void setFarmer_voyages(String farmer_voyages) {
        this.farmer_voyages = farmer_voyages;
    }

    public String getFarmer_manure() {
        return farmer_manure;
    }

    public void setFarmer_manure(String farmer_manure) {
        this.farmer_manure = farmer_manure;
    }

    public String getFarmer_troopsname() {
        return farmer_troopsname;
    }

    public void setFarmer_troopsname(String farmer_troopsname) {
        this.farmer_troopsname = farmer_troopsname;
    }

    public String getFarmer_yield() {
        return farmer_yield;
    }

    public void setFarmer_yield(String farmer_yield) {
        this.farmer_yield = farmer_yield;
    }

    public String getFarmer_area() {
        return farmer_area;
    }

    public void setFarmer_area(String farmer_area) {
        this.farmer_area = farmer_area;
    }

    @Override
    public String toString() {
        return "FarmerBean{" +
                "id=" + id +
                ", farmerName='" + farmerName + '\'' +
                ", dKnumber='" + dKnumber + '\'' +
                ", type='" + type + '\'' +
                ", baseName='" + baseName + '\'' +
                ", house='" + house + '\'' +
                ", idcard='" + idcard + '\'' +
                ", temperature='" + temperature + '\'' +
                ", farmer_voyages='" + farmer_voyages + '\'' +
                ", farmer_manure='" + farmer_manure + '\'' +
                ", farmer_troopsname='" + farmer_troopsname + '\'' +
                ", farmer_yield='" + farmer_yield + '\'' +
                ", farmer_area='" + farmer_area + '\'' +
                '}';
    }
}