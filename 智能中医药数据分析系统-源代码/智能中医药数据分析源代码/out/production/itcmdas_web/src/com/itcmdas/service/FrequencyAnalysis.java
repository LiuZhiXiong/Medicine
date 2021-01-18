package com.itcmdas.service;

import java.lang.reflect.Array;
import java.util.*;

public class FrequencyAnalysis {
    static  int maxn=100010;
    private static ArrayList<String>[] medicalTableStr = null;

    public static ArrayList<String>[] getMedicalTableStr() {
        return medicalTableStr;
    }

    public static void setMedicalTableStr(ArrayList<String>[] medicalTableStr) {
        FrequencyAnalysis.medicalTableStr = medicalTableStr;
    }


    /**
     * 通过给定需要统计的药物个数（即：单组药or药对or三元组）以及需要统计的频次前几名 ，返回对应的排名表
     *
     * @param medicineNum
     * @param num
     * @return
     */
    public static List<HashMap.Entry<ArrayList<String>, Integer>> getFrequencyAnalysisMap(int medicineNum, int num) {
        List<HashMap.Entry<ArrayList<String>, Integer>> resList = new ArrayList<>();

        if (medicineNum == 1) {
            resList = getOneMedicineAnalysisMap(num);
        } else if (medicineNum == 2) {
            resList = getTwoMedicineAnalysisMap(num);
        } else if (medicineNum == 3) {
            resList = getThreeMedicineAnalysisMap(num);
        }

        return resList;
    }

    /**
     * 单味药的频次分析
     *
     * @return
     */
    public static List<HashMap.Entry<ArrayList<String>, Integer>> getOneMedicineAnalysisMap(int num) {

        HashMap<ArrayList<String>, Integer> resMap = new HashMap<>();

        for (int i = 0; i < medicalTableStr.length; i++) {
            for (int j = 0; j < medicalTableStr[i].size(); j++) {
                ArrayList<String> list = new ArrayList<String>();
                list.add(medicalTableStr[i].get(j));
                if (resMap.containsKey(list)) {
                    Integer cnt = resMap.get(list);
                    cnt++;
                    resMap.put(list, cnt);
                } else {
                    resMap.put(list, 1);
                }
            }
        }

        //将HashMap转化为List，然后重写compare 进行排序
        List<HashMap.Entry<ArrayList<String>, Integer>> list =
                new ArrayList<>(resMap.entrySet());
        list.sort(new Comparator<HashMap.Entry<ArrayList<String>, Integer>>() {
            @Override
            public int compare(Map.Entry<ArrayList<String>, Integer> o1, Map.Entry<ArrayList<String>, Integer> o2) {
                if (o1.getValue() < o2.getValue()) return 1;
                else if (o1.getValue() > o2.getValue()) return -1;
                return 0;
            }
        });

        List<HashMap.Entry<ArrayList<String>, Integer>> resList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            resList.add(list.get(i));
        }

        return resList;
    }

    /**
     * 药对的频次分析
     *
     * @param num
     * @return
     */
    public static List<HashMap.Entry<ArrayList<String>, Integer>> getTwoMedicineAnalysisMap(int num) {

        //存放药对 与 药对出现次数
        HashMap<ArrayList<String>, Integer> resMap = new HashMap<>();
        /**
         * 第一层循环遍历所有处方，第二层&第三层循环 计算每个处方中的所有药对
         */
        for (int i = 0; i < medicalTableStr.length; i++) {
            for (int j = 0; j < medicalTableStr[i].size() - 1; j++) {
                for (int k = j + 1; k < medicalTableStr[i].size(); k++) {
                    if (medicalTableStr[i].get(j).equals(medicalTableStr[i].get(k))) continue;

                    ArrayList<String> list = new ArrayList<>();
                    list.add(medicalTableStr[i].get(j));
                    list.add(medicalTableStr[i].get(k));
                    list.sort(new Comparator<String>() {
                        @Override
                        public int compare(String o1, String o2) {
                            if (o1.compareTo(o2) > 0) return 1;
                            else return -1;
                        }
                    });

                    if (resMap.containsKey(list)) {
                        Integer cnt = resMap.get(list);
                        cnt++;
                        resMap.put(list, cnt);
                    } else {
                        //System.out.println(medicineList.get(i)+" "+medicineList.get(j));
                        resMap.put(list, 1);
                    }
                }
            }
        }


        //将HashMap转化为List，然后重写compare 进行排序
        List<HashMap.Entry<ArrayList<String>, Integer>> list =
                new ArrayList<>(resMap.entrySet());
        if (list.size() == 1) return list;
        list.sort(new Comparator<HashMap.Entry<ArrayList<String>, Integer>>() {
            @Override
            public int compare(Map.Entry<ArrayList<String>, Integer> o1, Map.Entry<ArrayList<String>, Integer> o2) {
                if (o1.getValue() < o2.getValue()) return 1;
                else if (o1.getValue() > o2.getValue()) return -1;
                return 0;
            }
        });

        List<HashMap.Entry<ArrayList<String>, Integer>> resList = new ArrayList<>();
        for (int i = 0; i < num && i < list.size(); i++) {
            resList.add(list.get(i));
        }

        return resList;
    }

    /**
     * 三元组的频次分析
     *
     * @param num
     * @return
     */
    public static List<HashMap.Entry<ArrayList<String>, Integer>> getThreeMedicineAnalysisMap(int num) {

        //存放三元组 与 三元组出现次数
        HashMap<ArrayList<String>, Integer> resMap = new HashMap<>();
        /**
         * 第一层循环遍历所有处方，第二层&第三层&第四层循环 计算每个处方中的所有药对
         */
        for (int i = 0; i < medicalTableStr.length; i++) {
            for (int j = 0; j < medicalTableStr[i].size() - 2; j++) {
                for (int k = j + 1; k < medicalTableStr[i].size() - 1; k++) {
                    for (int p = k + 1; p < medicalTableStr[i].size(); p++) {
                        if (medicalTableStr[i].get(j).equals(medicalTableStr[i].get(k))) continue;

                        ArrayList<String> list = new ArrayList<>();
                        list.add(medicalTableStr[i].get(j));
                        list.add(medicalTableStr[i].get(k));
                        list.add(medicalTableStr[i].get(p));
                        list.sort(new Comparator<String>() {
                            @Override
                            public int compare(String o1, String o2) {
                                if (o1.compareTo(o2) > 0) return 1;
                                else return -1;
                            }
                        });

                        if (resMap.containsKey(list)) {
                            Integer cnt = resMap.get(list);
                            cnt++;
                            resMap.put(list, cnt);
                        } else {
                            //System.out.println(medicineList.get(i)+" "+medicineList.get(j));
                            resMap.put(list, 1);
                        }
                    }

                }
            }
        }

        //将HashMap转化为List，然后重写compare 进行排序
        List<HashMap.Entry<ArrayList<String>, Integer>> list =
                new ArrayList<>(resMap.entrySet());
        if (list.size() == 1) return list;
        list.sort(new Comparator<HashMap.Entry<ArrayList<String>, Integer>>() {
            @Override
            public int compare(Map.Entry<ArrayList<String>, Integer> o1, Map.Entry<ArrayList<String>, Integer> o2) {
                if (o1.getValue() < o2.getValue()) return 1;
                else if (o1.getValue() > o2.getValue()) return -1;
                return 0;
            }
        });

        List<HashMap.Entry<ArrayList<String>, Integer>> resList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            resList.add(list.get(i));
        }

        return resList;
    }

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        String[][] str = new String[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                str[i][j] = scan.next();
            }
        }

        ArrayList<String>[] lists = new ArrayList[str.length];
        for(int i=0;i<str.length;i++){
            lists[i]=new ArrayList<>();
            for(int j=0;j<str[i].length;j++){
                lists[i].add(str[i][j]);
            }
        }

        FrequencyAnalysis.setMedicalTableStr(lists);
        List<HashMap.Entry<ArrayList<String>, Integer>> resList = new ArrayList<>();
        resList=FrequencyAnalysis.getFrequencyAnalysisMap(1,2);
        for(int i=0;i<resList.size();i++){
            Map.Entry entry=resList.get(i);
            System.out.println(entry.getKey()+" "+entry.getValue());
        }
    }
}

