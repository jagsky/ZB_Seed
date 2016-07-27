package com.zbPro.seed.bean;

/**
 * 作用：重要信息上报 实体类
 * Created by Administrator on 2016/7/27.
 */
public class ImportantBean {
    private int id;
    private String userName;
    private String data;
    private String province;
    private String city;
    private String county;
    private String town;
    private String village;
    private String contenttype;
    private String theme;
    private String content;

    public ImportantBean() {
    }

    public ImportantBean(int id, String userName, String data, String province, String city, String county, String town, String village, String contenttype, String theme, String content) {
        this.id = id;
        this.userName = userName;
        this.data = data;
        this.province = province;
        this.city = city;
        this.county = county;
        this.town = town;
        this.village = village;
        this.contenttype = contenttype;
        this.theme = theme;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getContenttype() {
        return contenttype;
    }

    public void setContenttype(String contenttype) {
        this.contenttype = contenttype;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ImportantBean{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", data='" + data + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", town='" + town + '\'' +
                ", village='" + village + '\'' +
                ", contenttype='" + contenttype + '\'' +
                ", theme='" + theme + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
