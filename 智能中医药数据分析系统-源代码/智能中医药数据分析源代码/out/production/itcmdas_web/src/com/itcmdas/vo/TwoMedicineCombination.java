package com.itcmdas.vo;

/**
 * 两个药品形成一个药品对，里面存储各自的药品名称、熵、联合熵、关联度、关联度系数
 */
public class TwoMedicineCombination {
    private String medicine1Name;
    private String medicine2Name;
    private double medicine1Entropy;
    private double medicine2Entropy;
    private double jointEntropy;
    private double correlationDegree;
    private double correlationCoefficient;

    public String getMedicine1Name() {
        return medicine1Name;
    }

    public void setMedicine1Name(String medicine1Name) {
        this.medicine1Name = medicine1Name;
    }

    public String getMedicine2Name() {
        return medicine2Name;
    }

    public void setMedicine2Name(String medicine2Name) {
        this.medicine2Name = medicine2Name;
    }

    public double getMedicine1Entropy() {
        return medicine1Entropy;
    }

    public void setMedicine1Entropy(double medicine1Entropy) {
        this.medicine1Entropy = medicine1Entropy;
    }

    public double getMedicine2Entropy() {
        return medicine2Entropy;
    }

    public void setMedicine2Entropy(double medicine2Entropy) {
        this.medicine2Entropy = medicine2Entropy;
    }

    public double getJointEntropy() {
        return jointEntropy;
    }

    public void setJointEntropy(double jointEntropy) {
        this.jointEntropy = jointEntropy;
    }

    public double getCorrelationDegree() {
        return correlationDegree;
    }

    public void setCorrelationDegree(double correlationDegree) {
        this.correlationDegree = correlationDegree;
    }

    public double getCorrelationCoefficient() {
        return correlationCoefficient;
    }

    public void setCorrelationCoefficient(double correlationCoefficient) {
        this.correlationCoefficient = correlationCoefficient;
    }

    public TwoMedicineCombination(String medicine1Name,String medicine2Name,double medicine1Entropy,double medicine2Entropy,
                                  double jointEntropy,double correlationDegree,double correlationCoefficient){
        this.medicine1Name=medicine1Name;
        this.medicine2Name=medicine2Name;
        this.medicine1Entropy=medicine1Entropy;
        this.medicine2Entropy=medicine2Entropy;
        this.jointEntropy=jointEntropy;
        this.correlationDegree=correlationDegree;
        this.correlationCoefficient=correlationCoefficient;
    }

}
