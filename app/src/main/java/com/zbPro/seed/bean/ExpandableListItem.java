package com.zbPro.seed.bean;

/**
 * Created by Administrator on 2016/8/18.
 */
public class ExpandableListItem {
    String name;
    Seed seed;

    public ExpandableListItem() {
    }

    public ExpandableListItem(String name, Seed seed) {
        this.name = name;
        this.seed = seed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Seed getSeed() {
        return seed;
    }

    public void setSeed(Seed seed) {
        this.seed = seed;
    }
}
