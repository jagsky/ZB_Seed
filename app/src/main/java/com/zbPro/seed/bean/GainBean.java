package com.zbPro.seed.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Administrator on 2016/6/7.
 * ClassName ：com.zbPro.seed.dao
 * 作用：
 */

@DatabaseTable(tableName = "GainBean")
public class GainBean {
    @DatabaseField(generatedId = true)
    private int id;
    //用户ID
    private String UserId;
    //农户姓名
    @DatabaseField(columnName = "framarName")
    private String framarName;
    //地块号
    @DatabaseField(columnName = "dKNumber")
    private String dKNumber;
    //种类
    @DatabaseField(columnName = "type")
    private String type;
    //母本苗包数
    @DatabaseField(columnName = "motherNO")
    private String motherNO;
    //单株粒数
    @DatabaseField(columnName = "singlePlant")
    private String singlePlant;
    //千粒重（KG）
    @DatabaseField(columnName = "thousand")
    private String thousand;
    //父本割除开始时间
    @DatabaseField(columnName = "fatherExciseStart")
    private String fatherExciseStart;
    //父本割除结束时间
    @DatabaseField(columnName = "fatherExciseStop")
    private String fatherExciseStop;
    //地块收获时间
    @DatabaseField(columnName = "gainTime")
    private String gainTime;
    //收获具体产量
    @DatabaseField(columnName = "gainOutput")
    private String gainOutput;

    @DatabaseField(columnName = "BeiZhu")
    private String BeiZhu;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public GainBean() {
    }

    public GainBean(int id, String userId, String framarName, String dKNumber, String type, String motherNO, String singlePlant, String thousand, String fatherExciseStart, String fatherExciseStop, String gainTime, String gainOutput, String beiZhu) {
        this.id = id;
        UserId = userId;
        this.framarName = framarName;
        this.dKNumber = dKNumber;
        this.type = type;
        this.motherNO = motherNO;
        this.singlePlant = singlePlant;
        this.thousand = thousand;
        this.fatherExciseStart = fatherExciseStart;
        this.fatherExciseStop = fatherExciseStop;
        this.gainTime = gainTime;
        this.gainOutput = gainOutput;
        BeiZhu = beiZhu;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getFramarName() {
        return framarName;
    }

    public void setFramarName(String framarName) {
        this.framarName = framarName;
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

    public String getMotherNO() {
        return motherNO;
    }

    public void setMotherNO(String motherNO) {
        this.motherNO = motherNO;
    }

    public String getSinglePlant() {
        return singlePlant;
    }

    public void setSinglePlant(String singlePlant) {
        this.singlePlant = singlePlant;
    }

    public String getThousand() {
        return thousand;
    }

    public void setThousand(String thousand) {
        this.thousand = thousand;
    }

    public String getFatherExciseStart() {
        return fatherExciseStart;
    }

    public void setFatherExciseStart(String fatherExciseStart) {
        this.fatherExciseStart = fatherExciseStart;
    }

    public String getFatherExciseStop() {
        return fatherExciseStop;
    }

    public void setFatherExciseStop(String fatherExciseStop) {
        this.fatherExciseStop = fatherExciseStop;
    }

    public String getGainTime() {
        return gainTime;
    }

    public void setGainTime(String gainTime) {
        this.gainTime = gainTime;
    }

    public String getGainOutput() {
        return gainOutput;
    }

    public void setGainOutput(String gainOutput) {
        this.gainOutput = gainOutput;
    }

    @Override
    public String toString() {
        return "Gain{" +
                "UserId='" + UserId + '\'' +
                ", framarName='" + framarName + '\'' +
                ", dKNumber='" + dKNumber + '\'' +
                ", type='" + type + '\'' +
                ", motherNO='" + motherNO + '\'' +
                ", singlePlant='" + singlePlant + '\'' +
                ", thousand='" + thousand + '\'' +
                ", fatherExciseStart='" + fatherExciseStart + '\'' +
                ", fatherExciseStop='" + fatherExciseStop + '\'' +
                ", gainTime='" + gainTime + '\'' +
                ", gainOutput='" + gainOutput + '\'' +
                '}';
    }
    public GainBean(String framarName, String dKNumber, String type) {
        this.framarName = framarName;
        this.dKNumber = dKNumber;
        this.type = type;
    }

    public String getBeiZhu() {
        return BeiZhu;
    }

    public void setBeiZhu(String beiZhu) {
        BeiZhu = beiZhu;
    }
}
