package com.itcmdas.vo;

import java.util.List;

/**
 * @author TangBo
 * @description
 * @data 2020/10/10
 */
public class AssociationData {
    //        关联药材
    private List<String> medicine1;
    //        被关联药材
    private List<String>medicine2;
    private double number;

    @Override
    public String toString() {
        return "AssociationData{" +
                "medicine1=" + medicine1 +
                ", medicine2=" + medicine2 +
                ", number=" + number +
                '}';
    }

    public List<String> getMedicine1() {
        return medicine1;
    }

    public void setMedicine1(List<String> medicine1) {
        this.medicine1 = medicine1;
    }

    public List<String> getMedicine2() {
        return medicine2;
    }

    public void setMedicine2(List<String> medicine2) {
        this.medicine2 = medicine2;
    }

    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }
}
