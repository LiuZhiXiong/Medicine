package com.itcmdas.vo;

/**
 * @Classname Medicine
 * @Description TODO
 * @Author liubo
 * @Date 2020/10/8 16:02
 * @Version 1.0
 */
public class Medicine {
    private int id;
    private String medicineName;
    private String efficacy;
    private String properties;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getEfficacy() {
        return efficacy;
    }

    public void setEfficacy(String efficacy) {
        this.efficacy = efficacy;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }


    @Override
    public String toString() {
        return "Medicine{" +
                "id=" + id +
                ", medicineName='" + medicineName + '\'' +
                ", efficacy='" + efficacy + '\'' +
                ", properties='" + properties + '\'' +
                '}';
    }
}
