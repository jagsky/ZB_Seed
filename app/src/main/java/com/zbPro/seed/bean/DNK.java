package com.zbPro.seed.bean;

/**
 * Created by Administrator on 2016/7/7.
 */
public class DNK {
    private String farmerName;
    private String dkNumber;
    private String type;

    public DNK(String farmerName, String dkNumber, String type) {
        this.farmerName = farmerName;
        this.dkNumber = dkNumber;
        this.type = type;
    }

    public String getFarmerName() {
        return farmerName;
    }

    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }

    public String getDkNumber() {
        return dkNumber;
    }

    public void setDkNumber(String dkNumber) {
        this.dkNumber = dkNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
