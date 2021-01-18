package com.itcmdas.vo;

import java.util.ArrayList;

/**
 * 将核心药物组封装到 具体类 中，方便前端js处理
 */
public class CoreMedicine {
    private  int id;
    private String coreMedicineList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCoreMedicineList() {
        return coreMedicineList;
    }

    public void setCoreMedicineList(String coreMedicineList) {
        this.coreMedicineList = coreMedicineList;
    }

    @Override
    public String toString() {
        return "CoreMedicine{" +
                "id=" + id +
                ", coreMedicineList='" + coreMedicineList + '\'' +
                '}';
    }
}
