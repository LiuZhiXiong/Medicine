package com.itcmdas.vo;

import java.util.ArrayList;

/**
 * @author TangBo
 * @description
 * @data 2020/11/11
 */
public class MedicineFreTable {
//    ArrayList<String>, Integer>
    private int id;
    private String medicineListFre;
    private int medicineNumFre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMedicineListFre() {
        return medicineListFre;
    }

    public void setMedicineListFre(String medicineListFre) {
        this.medicineListFre = medicineListFre;
    }

    public int getMedicineNumFre() {
        return medicineNumFre;
    }

    public void setMedicineNumFre(int medicineNumFre) {
        this.medicineNumFre = medicineNumFre;
    }

}
