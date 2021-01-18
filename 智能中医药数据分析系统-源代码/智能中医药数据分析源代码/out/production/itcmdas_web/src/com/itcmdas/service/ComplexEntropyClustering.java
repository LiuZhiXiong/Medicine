package com.itcmdas.service;

import com.itcmdas.vo.TwoMedicineCombination;
import com.itcmdas.vo.TwoMedicineMapForClustering;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

/**
 * 复杂系统熵聚类算法
 */
public class ComplexEntropyClustering {
    public static int maxn = 10010;
    //处方总和
    public static int prescriptionSum = 0;
    //药物和处方构成的二维字符串数组
    private static ArrayList<String>[] medicalTableStr = null;

    public static ArrayList<String>[] getMedicalTableStr() {
        return medicalTableStr;
    }

    public static void setMedicalTableStr(ArrayList<String>[] medicalTableStr) {
        ComplexEntropyClustering.medicalTableStr = medicalTableStr;
    }

    //使用独热编码，将二维字符串数组转化为整型数组
    public static int[][] medicalTableOneHot = null;
    //所有药物的集合
    public static ArrayList<String> medicineList = new ArrayList<String>();

    /**
     * 初始化方法：获取处方总数、处方中一共有多少种不同药方、生成独热编码的二维整形数组
     */
    public static void init(){
        //获得处方总和
        getPrescriptionSum();
        //System.out.println(prescriptionSum);

        //获得所有处方中一共有多少种不同的药方
        getMedicineList();

        //得到使用独热编码的二维整型数组，存储处方和药物的关系
        getMedicalTableOneHot();
    }


    /**
     * 计算所有处方的和
     */
    public static void getPrescriptionSum() {
        prescriptionSum = medicalTableStr.length;
    }

    /**
     * 统计总共有多少味药，并存入一个集合中
     */
    public static void getMedicineList() {
        for (int i = 0; i < medicalTableStr.length; i++) {
            for (int j = 0; j < medicalTableStr[i].size(); j++) {
                if (!medicineList.contains(medicalTableStr[i].get(j))) {
                    medicineList.add(medicalTableStr[i].get(j));
                }
            }
        }
        /*for(int i=0;i<medicineList.size();i++){
            System.out.println(medicineList.get(i));
        }
        System.out.println(medicineList.size());*/
    }

    /**
     * 使用独热编码将二维字符串数组转化为整型数组
     * 即：当某个药在处方中出现，则设置为1，否则为0
     */
    public static void getMedicalTableOneHot() {
        int row = prescriptionSum;
        int col = medicineList.size();

        medicalTableOneHot = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (medicalTableStr[i].contains(medicineList.get(j))) {
                    medicalTableOneHot[i][j] = 1;
                } else {
                    medicalTableOneHot[i][j] = 0;
                }
            }
        }
        /*for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                System.out.print(medicalTableOneHot[i][j]+" ");
            }
            System.out.println();
        }*/
    }

    /**
     * 计算某个药品在处方出现的概率
     *
     * @param medicineName
     * @return 出现的概率
     */
    public static double getMedicineExistedRate(String medicineName) {
        int count = 0;
        for (int i = 0; i < medicineList.size(); i++) {
            if (medicineList.get(i).equals(medicineName)) {
                for (int j = 0; j < prescriptionSum; j++) {
                    if (medicalTableOneHot[j][i] == 1) {
                        count++;
                    }
                }
                break;
            }
        }
        //System.out.println(count*1.0/prescriptionSum);
        /*if(medicineName.equals("桑白皮")){
            System.out.println(medicineName+": "+count);
        }*/
        double rate = 0;
        if (count != 0)
            rate = count * 1.0 / prescriptionSum;
        return rate;
    }

    /**
     * 计算某个药品在处方不出现的概率
     *
     * @param medicineName
     * @return 不出现的概率
     */
    public static double getMedicineNotExistedRate(String medicineName) {
        int count = 0;
        for (int i = 0; i < medicineList.size(); i++) {
            if (medicineList.get(i).equals(medicineName)) {
                for (int j = 0; j < prescriptionSum; j++) {
                    if (medicalTableOneHot[j][i] == 0) {
                        count++;
                    }
                }
                break;
            }
        }
        /*if(medicineName.equals("桑白皮")){
            System.out.println(medicineName+": "+count);
        }*/
        double rate = 0;
        if (count != 0)
            rate = (count * 1.0 / prescriptionSum);
        return rate;
    }

    /**
     * 计算单个药品的熵
     *
     * @param medicineName
     * @return
     */
    public static double getOneMedicineEntropy(String medicineName) {
        double rate1 = getMedicineExistedRate(medicineName);
        double rate2 = getMedicineNotExistedRate(medicineName);
        double oneMedicineEntropy = (rate1 * Math.log10(rate1)) + (rate2 * Math.log10(rate2));
        return -1.0 * oneMedicineEntropy;
    }

    /**
     * 计算两个药品同时属于某个处方的概率
     *
     * @param medicine1Name
     * @param medicine2Name
     * @return
     */
    public static double getTwoMedicineOneOneRate(String medicine1Name, String medicine2Name) {
        int count = 0;
        int a = 0, b = 0;
        for (int i = 0; i < medicineList.size(); i++) {
            if (medicineList.get(i).equals(medicine1Name)) {
                a = i;
            }
            if (medicineList.get(i).equals(medicine2Name)) {
                b = i;
            }
        }
        for (int i = 0; i < prescriptionSum; i++) {
            if (medicalTableOneHot[i][a] == 1 && medicalTableOneHot[i][b] == 1) {
                count++;
            }
        }
        /*if(medicine1Name.equals("百部")&&medicine2Name.equals("桑白皮")){
            System.out.println(count);
        }*/
        double rate = 0;
        if (count != 0)
            rate = (count * 1.0 / prescriptionSum);
        return rate;
    }

    /**
     * 计算两个药品都不属于某个处方的概率
     *
     * @param medicine1Name
     * @param medicine2Name
     * @return
     */
    public static double getTwoMedicineZeroZeroRate(String medicine1Name, String medicine2Name) {
        int count = 0;
        int a = 0, b = 0;
        for (int i = 0; i < medicineList.size(); i++) {
            if (medicineList.get(i).equals(medicine1Name)) {
                a = i;
            }
            if (medicineList.get(i).equals(medicine2Name)) {
                b = i;
            }
        }
        for (int i = 0; i < prescriptionSum; i++) {
            if (medicalTableOneHot[i][a] == 0 && medicalTableOneHot[i][b] == 0) {
                count++;
            }
        }
        /*if(medicine1Name.equals("百部")&&medicine2Name.equals("桑白皮")){
            System.out.println(count);
        }*/
        double rate = 0;
        if (count != 0)
            rate = (count * 1.0 / prescriptionSum);
        return rate;
    }

    /**
     * 计算第一个属于第二个不属于某个处方的概率
     *
     * @param medicine1Name
     * @param medicine2Name
     * @return
     */
    public static double getTwoMedicineOneZeroRate(String medicine1Name, String medicine2Name) {
        int count = 0;
        int a = 0, b = 0;
        for (int i = 0; i < medicineList.size(); i++) {
            if (medicineList.get(i).equals(medicine1Name)) {
                a = i;
            }
            if (medicineList.get(i).equals(medicine2Name)) {
                b = i;
            }
        }
        for (int i = 0; i < prescriptionSum; i++) {
            if ((medicalTableOneHot[i][a] == 1 && medicalTableOneHot[i][b] == 0)) {
                count++;
            }
        }
        /*if(medicine1Name.equals("百部")&&medicine2Name.equals("桑白皮")){
            System.out.println(count);
        }*/
        double rate = 0;
        if (count != 0)
            rate = (1.0 * count / prescriptionSum);
        return rate;
    }

    /**
     * 计算第一个药品不属于第二个药品属于某个处方的情况
     *
     * @param medicine1Name
     * @param medicine2Name
     * @return
     */
    public static double getTwoMedicineZeroOneRate(String medicine1Name, String medicine2Name) {
        int count = 0;
        int a = 0, b = 0;
        for (int i = 0; i < medicineList.size(); i++) {
            if (medicineList.get(i).equals(medicine1Name)) {
                a = i;
            }
            if (medicineList.get(i).equals(medicine2Name)) {
                b = i;
            }
        }
        for (int i = 0; i < prescriptionSum; i++) {
            if ((medicalTableOneHot[i][a] == 0 && medicalTableOneHot[i][b] == 1)) {
                count++;
            }
        }
        /*if(medicine1Name.equals("百部")&&medicine2Name.equals("桑白皮")){
            System.out.println(count);
        }*/
        double rate = 0;
        if (count != 0)
            rate = (count * 1.0 / prescriptionSum);
        return rate;
    }

    /**
     * 计算两个药品的联合熵
     *
     * @param medicine1Name
     * @param medicine2Name
     * @return
     */
    public static double getJointEntropy(String medicine1Name, String medicine2Name) {
        double rate1 = getTwoMedicineOneOneRate(medicine1Name, medicine2Name);
        double rate2 = getTwoMedicineZeroZeroRate(medicine1Name, medicine2Name);
        double rate3 = getTwoMedicineOneZeroRate(medicine1Name, medicine2Name);
        double rate4 = getTwoMedicineZeroOneRate(medicine1Name, medicine2Name);
        double jointEntropy = (rate1 * Math.log10(rate1)) + (rate2 * Math.log10(rate2)) + (rate3 * Math.log10(rate3)) + (rate4 * Math.log10(rate4));
        return -1.0 * jointEntropy;
    }

    /**
     * 计算两种药品的关联度
     *
     * @param medicine1Entropy
     * @param medicine2Entropy
     * @param jointEntropy
     * @return
     */
    public static double getCorrelationDegree(double medicine1Entropy, double medicine2Entropy, double jointEntropy) {
        double correlationDegree = medicine1Entropy + medicine2Entropy - jointEntropy;
        return correlationDegree;
    }

    /**
     * 计算两种药品形成的药对的关联度系数
     *
     * @param correlationDegree
     * @param medicine1Entropy
     * @param medicine2Entropy
     * @return
     */
    public static double getCorrelationCoefficient(double correlationDegree, double medicine1Entropy, double medicine2Entropy) {
        double correlationCoefficient = correlationDegree / (Math.sqrt(medicine1Entropy) * Math.sqrt(medicine2Entropy));
        return correlationCoefficient;
    }

    /**
     * 给定最小关联度阈值，求药对关联度系数排名表
     *
     * @param minCorrelationCoefficient
     * @return 对两个药品形成一个药品对，里面仅存储药对的名称和关联度系数 TwoMedicineMapForClustering
     */
    public static ArrayList<TwoMedicineMapForClustering> getMedicineMapForMinCorrelationCoefficient(double minCorrelationCoefficient) {
        int size = medicineList.size() * (medicineList.size() - 1);
        int t = 0;

        //用一Map存储药对关系，防止：（药物一，药物二）和（药物二、药物一）同时出现
        HashMap<String,String> hashMap=new HashMap<>();

        //求所有药对的相关信息
        TwoMedicineCombination[] twoMedicineCombinations = new TwoMedicineCombination[size];

        //最终的返回值：在最小关联度阈值条件下，满足的药对信息
        ArrayList<TwoMedicineMapForClustering> resMedicineMap = new ArrayList<>();

        for (int i = 0; i < medicineList.size(); i++) {
            for (int j = 0; j < medicineList.size(); j++) {
                if (j == i) continue;

                String medicine1Name = medicineList.get(i);
                String medicine2Name = medicineList.get(j);
                double medicine1Entropy = getOneMedicineEntropy(medicine1Name);
                double medicine2Entropy = getOneMedicineEntropy(medicine2Name);
                double jointEntropy = getJointEntropy(medicine1Name, medicine2Name);
                double correlationDegree = getCorrelationDegree(medicine1Entropy, medicine2Entropy, jointEntropy);
                double correlationCoefficient = getCorrelationCoefficient(correlationDegree, medicine1Entropy, medicine2Entropy);

                twoMedicineCombinations[t] = new TwoMedicineCombination(medicine1Name, medicine2Name, medicine1Entropy, medicine2Entropy,
                        jointEntropy, correlationDegree, correlationCoefficient);
                //System.out.println(twoMedicineCombinations[t].getMedicine1Name()+" "+twoMedicineCombinations[t].getMedicine2Name()+" "+twoMedicineCombinations[t].getCorrelationCoefficient());
                t++;
            }
        }

        for (int i = 0; i < twoMedicineCombinations.length; i++) {
            if (twoMedicineCombinations[i].getCorrelationCoefficient() > minCorrelationCoefficient) {

                String medicine1=twoMedicineCombinations[i].getMedicine1Name();
                String medicine2=twoMedicineCombinations[i].getMedicine2Name();

                int flag=0;
                //通过key值遍历Map中的元素，实现药对去重
                for(String key:hashMap.keySet()){
                    String val=hashMap.get(key);

                    if((medicine1.equals(key)&&medicine2.equals(val))||(medicine1.equals(val)&&medicine2.equals(key))){
                        flag=1;break;
                    }
                }

                if(flag==1) continue;

                TwoMedicineMapForClustering twoMedicineMapForClustering = new TwoMedicineMapForClustering(twoMedicineCombinations[i].getMedicine1Name(),
                        twoMedicineCombinations[i].getMedicine2Name(), twoMedicineCombinations[i].getCorrelationCoefficient());
                resMedicineMap.add(twoMedicineMapForClustering);

                hashMap.put(medicine1,medicine2);
            }
        }

        Collections.sort(resMedicineMap,new CorrelationCoefficientCmp());


        return resMedicineMap;

    }

    /**
     * 求核心药物组合表
     * 基本思路：
     * 首先开一个map，key存当前药品，value存前十的药品集合（集合用ArrayList就可以）
     * 然后对map遍历，对于每一个键值对的话再加一层循环（这层循环就是对value里面的list遍历，
     * 一一判断，例如key为陈皮，value中有百草这个药物，就只要判断key为百草的value中是否有陈皮，直接用list. contains() 就可以了）
     * 里面那层循环结束 就产生了一个核心药物组合，对组合用set存就可以，这样可以自动去重
     * <p>
     * set存的是所有药方数量情况的核心药物组合
     * 从中选择药方数量为minnum,的核心药物组合，存到coreMedicineLists中，简单对药方数量按从大到小排序即可
     *
     * @param minnum
     * @param maxnum
     * @return 核心药物组合表
     */
    public static ArrayList<ArrayList<String>> getCoreMedicineLists(int minnum, int maxnum) {

        //存每味药与其相关联的关联度前十的药物
        HashMap<String, ArrayList<String>> coreMedicineMap = new HashMap<String, ArrayList<String>>();
        //求相关联前十的药物
        for (int i = 0; i < medicineList.size(); i++) {
            //存每个药对和药对的关联度系数
            TwoMedicineMapForClustering[] twoMedicineMapForClusterings = new TwoMedicineMapForClustering[medicineList.size()];
            int t = 0;
            for (int j = 0; j < medicineList.size(); j++) {
                if (j == i) continue;

                String medicine1Name = medicineList.get(i);
                String medicine2Name = medicineList.get(j);
                double medicine1Entropy = getOneMedicineEntropy(medicine1Name);
                double medicine2Entropy = getOneMedicineEntropy(medicine2Name);
                double jointEntropy = getJointEntropy(medicine1Name, medicine2Name);
                double correlationDegree = getCorrelationDegree(medicine1Entropy, medicine2Entropy, jointEntropy);
                double correlationCoefficient = getCorrelationCoefficient(correlationDegree, medicine1Entropy, medicine2Entropy);
                //如果为空通通设为-1
                if (Double.isNaN(correlationCoefficient)) {
                    correlationCoefficient = -1;
                }
                twoMedicineMapForClusterings[t++] = new TwoMedicineMapForClustering(medicine1Name, medicine2Name, correlationCoefficient);
            }

            //按照关联度系数从大到小排序
            Arrays.sort(twoMedicineMapForClusterings, 0, t, new CorrelationCoefficientCmp());

            ArrayList<String> list = new ArrayList<>();
            //取前十个药对
            for (int j = 0; j < 10 && j < twoMedicineMapForClusterings.length; j++) {
                list.add(twoMedicineMapForClusterings[j].getMedicine2());
            }

            coreMedicineMap.put(medicineList.get(i), list);
        }

        /*for(Map.Entry<String,ArrayList<String> > entry:coreMedicineMap.entrySet()){
            System.out.println(entry.getKey()+" "+entry.getValue());
        }*/

        //聚类，求出所有的药物组合
        HashSet<ArrayList<String>> coreMedicineSets = new HashSet<ArrayList<String>>();
        for (Map.Entry<String, ArrayList<String>> entry : coreMedicineMap.entrySet()) {

            String medicineName = entry.getKey();
            ArrayList<String> list = new ArrayList<String>();
            list = entry.getValue();

            ArrayList<String> stringArrayList = new ArrayList<>();   //存每一个核心药物集合
            stringArrayList.add(medicineName);

            //遍历候选的药物集合，看是不是都满足
            for (int i = 0; i < list.size(); i++) {
                if (coreMedicineMap.get(list.get(i)).contains(medicineName)) {
                    stringArrayList.add(list.get(i));
                }
            }
            if (stringArrayList.size() > 1) {
                coreMedicineSets.add(stringArrayList);
            }
        }

        //对求出的所有药物组合进行筛选，选择药物数在minnum~maxnum的情况
        ArrayList<ArrayList<String>> coreMedicineLists = new ArrayList<>();
        for (ArrayList<String> arrayList : coreMedicineSets) {
            if (arrayList.size() >= minnum && arrayList.size() <= maxnum) {
                coreMedicineLists.add(arrayList);
            }
        }

        //从小到大排序
        Collections.sort(coreMedicineLists, new CoreMedicineCmp());

        //遍历药物组合表，查询下是否成功
        /*for(int i=0;i<coreMedicineLists.size();i++){
            System.out.println(coreMedicineLists.get(i));
        }*/

        return coreMedicineLists;
    }

    //对核心药物组合表按照药方的次数从大到小排序
    private static class CoreMedicineCmp implements Comparator<ArrayList<String>> {
        @Override
        public int compare(ArrayList<String> o1, ArrayList<String> o2) {
            if (o1.size() > o2.size()) {
                return -1;
            } else if (o1.size() == o2.size()) {
                return 0;
            } else {
                return 1;
            }
        }
    }

    //自定义比较函数（药对关联度系数从大到小排序）
    private static class CorrelationCoefficientCmp implements Comparator<TwoMedicineMapForClustering> {

        @Override
        public int compare(TwoMedicineMapForClustering o1, TwoMedicineMapForClustering o2) {
            double correlationCoefficient1 = o1.getCorrelationCoefficient();
            double correlationCoefficient2 = o2.getCorrelationCoefficient();
            BigDecimal a = BigDecimal.valueOf(correlationCoefficient1);
            BigDecimal b = BigDecimal.valueOf(correlationCoefficient2);
            if (a.compareTo(b) > 0) {
                return -1;
            } else if (a.compareTo(b) == 0) {
                return 0;
            } else {
                return 1;
            }
        }
    }
}
