package com.itcmdas.vo;

/**
 * @Classname correlationCoefficientListData
 * @Description TODO
 * @Author liubo
 * @Date 2020/11/20 22:04
 * @Version 1.0
 */
public class CorrelationCoefficientListData {
    private int id;
    private String medicine1;
    private String medicine2;
    private double correlationCoefficient;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMedicine1() {
        return medicine1;
    }

    public void setMedicine1(String medicine1) {
        this.medicine1 = medicine1;
    }

    public String getMedicine2() {
        return medicine2;
    }

    public void setMedicine2(String medicine2) {
        this.medicine2 = medicine2;
    }

    public double getCorrelationCoefficient() {
        return correlationCoefficient;
    }

    public void setCorrelationCoefficient(double correlationCoefficient) {
        this.correlationCoefficient = correlationCoefficient;
    }

    @Override
    public String toString() {
        return "correlationCoefficientListData{" +
                "id=" + id +
                ", medicine1='" + medicine1 + '\'' +
                ", medicine2='" + medicine2 + '\'' +
                ", correlationCoefficient=" + correlationCoefficient +
                '}';
    }
}
