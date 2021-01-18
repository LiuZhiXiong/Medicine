package com.itcmdas.vo;

/**
 * 对药对和关联度系数单独拿出来，方便进行聚类操作
 */
public class TwoMedicineMapForClustering {
    private String medicine1;
    private String medicine2;
    private double correlationCoefficient;

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
    public TwoMedicineMapForClustering(String medicine1, String medicine2, double correlationCoefficient){
        this.medicine1=medicine1;
        this.medicine2=medicine2;
        this.correlationCoefficient=correlationCoefficient;
    }

    public TwoMedicineMapForClustering(){

    }

    @Override
    public String toString() {
        return "TwoMedicineMapForClustering{" +
                "medicine1='" + medicine1 + '\'' +
                ", medicine2='" + medicine2 + '\'' +
                ", correlationCoefficient=" + correlationCoefficient +
                '}';
    }
}
