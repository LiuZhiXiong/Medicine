package com.itcmdas.vo;

import com.itcmdas.service.MedincineStrGeneration;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class DataList {
    public ArrayList<String> getmediacine1(ArrayList<String> datalist, Integer SUPPORT, Double CONFIDENCE) throws Exception {
        AprioriDemo apriori2 = new AprioriDemo();
        apriori2.setSUPPORT(SUPPORT);// 支持度阈值,取整数
        apriori2.setCONFIDENCE(CONFIDENCE);// 置信度阈值，取小数
        ArrayList<String> mediacine1 = new ArrayList<>();
        try {
            Map<String, Integer> frequentSetMap = apriori2.apriori(datalist);
            Set<String> keySet = frequentSetMap.keySet();
            mediacine1 = apriori2.getData1(frequentSetMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mediacine1;
    }

    public ArrayList<String> getmediacine2(ArrayList<String> datalist, Integer SUPPORT, Double CONFIDENCE) throws Exception {
        MedincineStrGeneration common = new MedincineStrGeneration();
        AprioriDemo apriori2 = new AprioriDemo();
        apriori2.setSUPPORT(SUPPORT);// 支持度阈值,取整数
        apriori2.setCONFIDENCE(CONFIDENCE);// 置信度阈值，取小数
        ArrayList<String> mediacine2 = new ArrayList<>();
        try {
            Map<String, Integer> frequentSetMap = apriori2.apriori(datalist);
            Set<String> keySet = frequentSetMap.keySet();
            mediacine2 = apriori2.getData2(frequentSetMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mediacine2;
    }

    public ArrayList<Double> getConfidence(ArrayList<String> datalist ,Integer SUPPORT, Double CONFIDENCE) throws Exception {
        MedincineStrGeneration common = new MedincineStrGeneration();
        AprioriDemo apriori2 = new AprioriDemo();
        apriori2.setSUPPORT(SUPPORT);// 支持度阈值,取整数
        apriori2.setCONFIDENCE(CONFIDENCE);// 置信度阈值，取小数
        ArrayList<Double> confidence = new ArrayList<>();
        try{
            Map<String, Integer> frequentSetMap = apriori2.apriori(datalist);
            Set<String> keySet = frequentSetMap.keySet();
            confidence=apriori2.getConfidence(frequentSetMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return confidence;
    }
}
