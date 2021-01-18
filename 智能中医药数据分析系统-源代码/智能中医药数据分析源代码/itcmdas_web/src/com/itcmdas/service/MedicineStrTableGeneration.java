package com.itcmdas.service;

import java.util.ArrayList;

/**
 * 二维字符串处方表产生类
 */
public class MedicineStrTableGeneration {
    /**
     * 将一维字符串数组 转成 二维字符串数组
     * @param medicineStr
     * @return
     */
    public static ArrayList<String>[] getMedicineStrTable(String[] medicineStr){
        ArrayList<String>[] medicineTable = new ArrayList[medicineStr.length];

        for (int i = 0; i < medicineStr.length; i++) {
            //利用split函数，实现按照中文逗号、英文逗号、中文顿号分割药物
            String[] str = medicineStr[i].split("[,，、]");
            medicineTable[i] = new ArrayList<>();
            for (String s : str) {
                medicineTable[i].add(s);
            }
        }

        return medicineTable;
    }
}
