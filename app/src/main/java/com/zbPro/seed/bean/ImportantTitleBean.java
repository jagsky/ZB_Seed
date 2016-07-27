package com.zbPro.seed.bean;

import android.widget.ImageView;

/**
 * 作用：listview标题栏
 * Created by Administrator on 2016/7/27.
 */
public class ImportantTitleBean {
    private ImageView imageView;
    private String title;
    private String content;

    public ImportantTitleBean(ImageView imageView, String title, String content) {
        this.imageView = imageView;
        this.title = title;
        this.content = content;
    }

    public ImportantTitleBean() {

    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ImportantTitleBean{" +
                "imageView=" + imageView +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
