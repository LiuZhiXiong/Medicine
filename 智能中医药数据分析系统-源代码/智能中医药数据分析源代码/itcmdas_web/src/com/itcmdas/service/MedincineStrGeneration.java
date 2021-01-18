package com.itcmdas.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.itcmdas.dao.DataDao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Classname MedincineStrGeneration
 * @Description TODO
 * @Author liubo
 * @Date 2020/10/12 23:55
 * @Version 1.0
 */
public class MedincineStrGeneration {
    /**
     * 从excel中获得处方，存入一个ArrayList
     * @return
     */
    public ArrayList<String> getMedicalTableStr(String[] medicineStrs) throws Exception {

        ArrayList<String> medicineStrApriori=new ArrayList<>();
        for(String str:medicineStrs){
            medicineStrApriori.add(str);
        }

        return medicineStrApriori;
    }
}
