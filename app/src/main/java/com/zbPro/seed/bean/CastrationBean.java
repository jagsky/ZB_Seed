package com.zbPro.seed.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/* 时间：2016/6/7
* 名称：去雄表
* 作用：封装去雄信息
* 实现功能：获取技术员填写的信息发送到服务器中
* */
@DatabaseTable(tableName = "CastrationBean")
public class CastrationBean {
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
    //去雄开始时间
    @DatabaseField(columnName = "startTime")
    private String startTime;
    //母本5%抽丝调查时间
    @DatabaseField(columnName = "motherExtractTime")
    private String motherExtractTime;
    //去雄实际调查时间
    @DatabaseField(columnName = "inspectTime")
    private String inspectTime;
    //母本未去雄
    @DatabaseField(columnName = "motherNoCastration")
    private String motherNoCastration;
    //母本抽丝率
    @DatabaseField(columnName = "motherExtract")
    private String motherExtract;
    //母本散粉数
    @DatabaseField(columnName = "motherLoose")
    private String motherLoose;
    //父本散粉数
    @DatabaseField(columnName = "fatherLoose")
    private String fatherLoose;
    //备注
    @DatabaseField(columnName = "content")
    private String content;

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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getMotherExtractTime() {
        return motherExtractTime;
    }

    public void setMotherExtractTime(String motherExtractTime) {
        this.motherExtractTime = motherExtractTime;
    }

    public String getInspectTime() {
        return inspectTime;
    }

    public void setInspectTime(String inspectTime) {
        this.inspectTime = inspectTime;
    }

    public String getMotherNoCastration() {
        return motherNoCastration;
    }

    public void setMotherNoCastration(String motherNoCastration) {
        this.motherNoCastration = motherNoCastration;
    }

    public String getMotherExtract() {
        return motherExtract;
    }

    public void setMotherExtract(String motherExtract) {
        this.motherExtract = motherExtract;
    }

    public String getMotherLoose() {
        return motherLoose;
    }

    public void setMotherLoose(String motherLoose) {
        this.motherLoose = motherLoose;
    }

    public String getFatherLoose() {
        return fatherLoose;
    }

    public void setFatherLoose(String fatherLoose) {
        this.fatherLoose = fatherLoose;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "CastrationBean{" +
                "UserId='" + UserId + '\'' +
                ", framarName='" + framarName + '\'' +
                ", dKNumber='" + dKNumber + '\'' +
                ", type='" + type + '\'' +
                ", startTime='" + startTime + '\'' +
                ", motherExtractTime='" + motherExtractTime + '\'' +
                ", inspectTime='" + inspectTime + '\'' +
                ", motherNoCastration='" + motherNoCastration + '\'' +
                ", motherExtract='" + motherExtract + '\'' +
                ", motherLoose='" + motherLoose + '\'' +
                ", fatherLoose='" + fatherLoose + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public CastrationBean(String userId, String framarName, String dKNumber,
                          String type, String startTime, String motherExtractTime,
                          String inspectTime, String motherNoCastration,
                          String motherExtract, String motherLoose,
                          String fatherLoose, String content) {
        UserId = userId;
        this.framarName = framarName;
        this.dKNumber = dKNumber;
        this.type = type;
        this.startTime = startTime;
        this.motherExtractTime = motherExtractTime;
        this.inspectTime = inspectTime;
        this.motherNoCastration = motherNoCastration;
        this.motherExtract = motherExtract;
        this.motherLoose = motherLoose;
        this.fatherLoose = fatherLoose;
        this.content = content;
    }

    public CastrationBean() {
    }

    public CastrationBean(String framarName, String dKNumber, String type) {
        this.framarName = framarName;
        this.dKNumber = dKNumber;
        this.type = type;
    }
}
