package com.zbPro.seed.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Sai on 16/3/28.
 */
@DatabaseTable(tableName = "City")
public class City {

    /**
     * {"cityName":"广州","firstLetter":"☆"
     * cityName : 鞍山
     * firstLetter : A
     */
    @DatabaseField(columnName = "cityName")
    private String cityName;
    @DatabaseField(columnName = "firstLetter")
    private String firstLetter;

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    public String getCityName() {
        return cityName;
    }

    public String getFirstLetter() {
        return firstLetter;
    }
}
