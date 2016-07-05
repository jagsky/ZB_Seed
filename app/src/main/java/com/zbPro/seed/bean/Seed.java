package com.zbPro.seed.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Administrator on 2016/6/16.
 * ClassName ：com.zbPro.seed.bean
 * 作用：
 */
@DatabaseTable(tableName = "Seed")
public class Seed {
    @DatabaseField(generatedId = true)
    private int seed_id;
    //技术员ID
    @DatabaseField(columnName = "userId")
    private String userId;
    //农户姓名
    @DatabaseField(columnName = "framarName")
    private String framarName;
    //地块号
    @DatabaseField(columnName = "dKNumber")
    private String dKNumber;
    //种类
    @DatabaseField(columnName = "type")
    private String type;
    //播种开始时间
    @DatabaseField(columnName = "seedDate")
    private String seedDate;
    //播种父1
    @DatabaseField(columnName = "father1")
    private String father1;
    //播种父2
    @DatabaseField(columnName = "father2")
    private String father2;
    //播种母
    @DatabaseField(columnName = "mother")
    private String mother;
    //父本使用情况
    @DatabaseField(columnName = "fatherUse")
    private String fatherUse;
    //母本使用情况
    @DatabaseField(columnName = "motherUse")
    private String motherUse;
    //备注
    @DatabaseField(columnName = "beizhu")
    private String beizhu;

    public Seed() {
    }

    public Seed(String userId, String framarName, String dKNumber,
                String type, String seedDate, String father1,
                String father2, String mother, String fatherUse,
                String motherUse, String beizhu) {
        this.userId = userId;
        this.framarName = framarName;
        this.dKNumber = dKNumber;
        this.type = type;
        this.seedDate = seedDate;
        this.father1 = father1;
        this.father2 = father2;
        this.mother = mother;
        this.fatherUse = fatherUse;
        this.motherUse = motherUse;
        this.beizhu = beizhu;
    }

    public int getSeed_id() {
        return seed_id;
    }

    public void setSeed_id(int seed_id) {
        this.seed_id = seed_id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getSeedDate() {
        return seedDate;
    }

    public void setSeedDate(String seedDate) {
        this.seedDate = seedDate;
    }

    public String getFather1() {
        return father1;
    }

    public void setFather1(String father1) {
        this.father1 = father1;
    }

    public String getFather2() {
        return father2;
    }

    public void setFather2(String father2) {
        this.father2 = father2;
    }

    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public String getFatherUse() {
        return fatherUse;
    }

    public void setFatherUse(String fatherUse) {
        this.fatherUse = fatherUse;
    }

    public String getMotherUse() {
        return motherUse;
    }

    public void setMotherUse(String motherUse) {
        this.motherUse = motherUse;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }

    @Override
    public String toString() {
        return "Seed{" +
                "seed_id=" + seed_id +
                ", userId='" + userId + '\'' +
                ", framarName='" + framarName + '\'' +
                ", dKNumber='" + dKNumber + '\'' +
                ", type='" + type + '\'' +
                ", seedDate='" + seedDate + '\'' +
                ", father1='" + father1 + '\'' +
                ", father2='" + father2 + '\'' +
                ", mother='" + mother + '\'' +
                ", fatherUse='" + fatherUse + '\'' +
                ", motherUse='" + motherUse + '\'' +
                ", beizhu='" + beizhu + '\'' +
                '}';
    }
    public Seed(String framarName, String dKNumber, String type) {
        this.framarName = framarName;
        this.dKNumber = dKNumber;
        this.type = type;
    }
}
