package com.zbPro.seed.bean;

/**
 * 作用：重要信息上报 实体类
 * Created by Administrator on 2016/7/27.
 */
public class ImportantBean {
    private int id;
    private String title;
    private String date;
    private String province;
    private String city;
    private String county;
    private String town;
    private String village;
    private String contenttype;
    private String content;

    public ImportantBean() {
    }

    public ImportantBean(int id, String title, String date, String province, String city, String county, String town, String village, String contenttype, String content) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.province = province;
        this.city = city;
        this.county = county;
        this.town = town;
        this.village = village;
        this.contenttype = contenttype;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", town='" + town + '\'' +
                ", village='" + village + '\'' +
                ", contenttype='" + contenttype + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
