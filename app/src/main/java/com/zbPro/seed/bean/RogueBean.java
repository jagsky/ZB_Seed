package com.zbPro.seed.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/* 时间：2016/6/7
* 名称：去杂表
* 作用：封装去杂信息
* 实现功能：获取技术员填写的信息发送到服务器中
* */
@DatabaseTable(tableName = "RogueBean")
public class RogueBean {
    @DatabaseField(generatedId = true)
    private int id;
    //用户登入的ID
    private String UserId;
    @DatabaseField(columnName = "framarName")
    private String framarName;
    //地块号
    @DatabaseField(columnName = "dKNumber")
    private String dKNumber;
    //种类
    @DatabaseField(columnName = "type")
    private String type;
    //去杂调查时间
    @DatabaseField(columnName = "time")
    private String time;
    //株距父
    @DatabaseField(columnName = "rowFather")
    private String rowFather;
    //株距母
    @DatabaseField(columnName = "rowMothers")
    private String rowMothers;
    //行距
    @DatabaseField(columnName = "lineWidth")
    private String lineWidth;
    //行比
    @DatabaseField(columnName = "lineRatio")
    private String lineRatio;
    //出苗父
    @DatabaseField(columnName = "comeOutFather")
    private String comeOutFather;
    //出苗母
    @DatabaseField(columnName = "conmeOutMother")
    private String conmeOutMother;
    //杂株率
    @DatabaseField(columnName = "impurties")
    private String impurties;
    //可育株率调查
    @DatabaseField(columnName = "fertility")
    private String fertility;
    //备注
    @DatabaseField(columnName = "beiZhu")
    private String beiZhu;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRowFather() {
        return rowFather;
    }

    public void setRowFather(String rowFather) {
        this.rowFather = rowFather;
    }

    public String getRowMothers() {
        return rowMothers;
    }

    public void setRowMothers(String rowMothers) {
        this.rowMothers = rowMothers;
    }

    public String getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(String lineWidth) {
        this.lineWidth = lineWidth;
    }

    public String getLineRatio() {
        return lineRatio;
    }

    public void setLineRatio(String lineRatio) {
        this.lineRatio = lineRatio;
    }

    public String getComeOutFather() {
        return comeOutFather;
    }

    public void setComeOutFather(String comeOutFather) {
        this.comeOutFather = comeOutFather;
    }

    public String getConmeOutMother() {
        return conmeOutMother;
    }

    public void setConmeOutMother(String conmeOutMother) {
        this.conmeOutMother = conmeOutMother;
    }

    public String getImpurties() {
        return impurties;
    }

    public void setImpurties(String impurties) {
        this.impurties = impurties;
    }

    public String getFertility() {
        return fertility;
    }

    public void setFertility(String fertility) {
        this.fertility = fertility;
    }

    public String getBeiZhu() {
        return beiZhu;
    }

    public void setBeiZhu(String beiZhu) {
        this.beiZhu = beiZhu;
    }

    public RogueBean(int id, String userId, String framarName,
                     String dKNumber, String type, String time,
                     String rowFather, String rowMothers,
                     String lineWidth, String lineRatio, String comeOutFather,
                     String conmeOutMother, String impurties,
                     String fertility, String beiZhu) {
        this.id = id;
        UserId = userId;
        this.framarName = framarName;
        this.dKNumber = dKNumber;
        this.type = type;
        this.time = time;
        this.rowFather = rowFather;
        this.rowMothers = rowMothers;
        this.lineWidth = lineWidth;
        this.lineRatio = lineRatio;
        this.comeOutFather = comeOutFather;
        this.conmeOutMother = conmeOutMother;
        this.impurties = impurties;
        this.fertility = fertility;
        this.beiZhu = beiZhu;
    }

    public RogueBean() {

    }

    @Override
    public String toString() {
        return "RogueDao{" +
                "UserId='" + UserId + '\'' +
                ", framarName='" + framarName + '\'' +
                ", dKNumber='" + dKNumber + '\'' +
                ", type='" + type + '\'' +
                ", time='" + time + '\'' +
                ", rowFather='" + rowFather + '\'' +
                ", rowMothers='" + rowMothers + '\'' +
                ", lineWidth='" + lineWidth + '\'' +
                ", lineRatio='" + lineRatio + '\'' +
                ", comeOutFather='" + comeOutFather + '\'' +
                ", conmeOutMother='" + conmeOutMother + '\'' +
                ", impurties='" + impurties + '\'' +
                ", fertility='" + fertility + '\'' +
                ", beiZhu='" + beiZhu + '\'' +
                '}';
    }

    public RogueBean(String framarName, String dKNumber, String type) {
        this.framarName = framarName;
        this.dKNumber = dKNumber;
        this.type = type;
    }
}
