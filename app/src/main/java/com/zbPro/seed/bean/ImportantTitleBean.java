package com.zbPro.seed.bean;

import android.widget.ImageView;

/**
 * 作用：listview标题栏
 * Created by Administrator on 2016/7/27.
 */
public class ImportantTitleBean {
    private String userId;
    private String userName;
    private String date;
    private String content;

    @Override
    public String toString() {
        return "ImportantTitleBean{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", date='" + date + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ImportantTitleBean() {

    }

    public ImportantTitleBean(String userId, String userName, String date, String content) {
        this.userId = userId;
        this.userName = userName;
        this.date = date;
        this.content = content;
    }
}
