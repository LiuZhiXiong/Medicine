package com.itcmdas.vo;

import java.util.*;
public class AprioriDemo {
        private final static String ITEM_SPLIT = "、"; // 项之间的分隔符
        private final static String CON = "->"; // 项之间的分隔符
        //private final static int SUPPORT=5; // 支持度阈值
        //private final static double CONFIDENCE=0.9; // 置信度阈值
        private int SUPPORT;
        private double CONFIDENCE;
        public int getSUPPORT()
        {
            return SUPPORT;
         }
        public void setSUPPORT(int SUPPORT)
        {
            this.SUPPORT = SUPPORT;
        }

        public double getCONFIDENCE()
        {
            return CONFIDENCE;
        }

        public void setCONFIDENCE(double CONFIDENCE)
        {
            this.CONFIDENCE = CONFIDENCE;
        }
        /**
         * 主方法
         * frequentSetMap 频繁项集
         * candidateSetMap 选出候选集
         * candidateKeySet 支持度
         *
         * */
        public Map<String, Integer> apriori(ArrayList<String> dataList){
            Map<String, Integer> stepFrequentSetMap = new HashMap<>();//所有频繁项集
            stepFrequentSetMap.putAll(findFrequentOneSets(dataList));
            Map<String, Integer> frequentSetMap = new HashMap<String, Integer>();//频繁项集
            frequentSetMap.putAll(stepFrequentSetMap);////复制所有映射关系,获取stepFrequentSetMap关系
            while(stepFrequentSetMap!=null && stepFrequentSetMap.size()>0)
            {
                Map<String, Integer> candidateSetMap = aprioriGen(stepFrequentSetMap);//选出候选集
                Set<String> candidateKeySet = candidateSetMap.keySet();//返回候选集中包含的键的 Set 视图。
                //扫描集合D，进行计数
                for(String data:dataList){
                    for(String candidate:candidateKeySet){
                        boolean flag = true;// 用来判断交易中是否出现该候选项，如果出现，计数加1
                        String[] strings = candidate.split(ITEM_SPLIT);
                        for(String string:strings){
                            if(data.indexOf(string+ITEM_SPLIT)==-1){
                                flag = false;
                                break;
                            }
                        }
                        if(flag)
                            candidateSetMap.put(candidate, candidateSetMap.get(candidate)+1);
                    }
                }
                //从候选集中找到符合支持度的频繁项集
                stepFrequentSetMap.clear();
                for(String candidate:candidateKeySet){
                    Integer count = candidateSetMap.get(candidate);//获取属性值-->支持度
                    if(count>=SUPPORT)//判断是否大于支持度阈值
                        stepFrequentSetMap.put(candidate, count);
                }
                // 合并所有频繁集
                frequentSetMap.putAll(stepFrequentSetMap);
            }
            return frequentSetMap;
        }



        /**
         * find frequent 1 itemsets
         */

        private Map<String, Integer> findFrequentOneSets(ArrayList<String> dataList){
            Map<String, Integer> resultSetMap = new HashMap<>();
            for(String data:dataList){
                String[] strings = data.split(ITEM_SPLIT);
                for(String string:strings){
                    string += ITEM_SPLIT;
                    if(resultSetMap.get(string)==null){
                        resultSetMap.put(string, 1);
                    }
                    else {
                        resultSetMap.put(string, resultSetMap.get(string)+1);
                    }
                }
            }
            return resultSetMap;
        }

        /**
         * 频繁项集
         * 根据频繁项集的集合选出候选集
         */
        private Map<String, Integer> aprioriGen(Map<String, Integer> setMap){
            Map<String, Integer> candidateSetMap = new HashMap<>();
            Set<String> candidateSet = setMap.keySet();
            for(String s1:candidateSet){
                String[] strings1 = s1.split(ITEM_SPLIT);
                String s1String = "";
                for(String temp:strings1)
                    s1String += temp+ITEM_SPLIT;
                for(String s2:candidateSet)		{
                    String[] strings2 = s2.split(ITEM_SPLIT);
                    boolean flag = true;
                    for(int i=0;i<strings1.length-1;i++){
                        if(strings1[i].compareTo(strings2[i])!=0){
                            flag = false;
                            break;
                        }
                    }
                    if(flag && strings1[strings1.length-1].compareTo(strings2[strings1.length-1])<0){
                        //连接步：产生候选
                        String c = s1String+strings2[strings2.length-1]+ITEM_SPLIT;
                        if(hasInfrequentSubset(c, setMap)){
                            //剪枝步：删除非频繁的候选
                        }
                        else {
                            candidateSetMap.put(c, 0);
                        }
                    }
                }
            }
            return candidateSetMap;
        }

        /**
         * 判断候选集是否是频繁项集
         */
        private boolean hasInfrequentSubset(String candidateSet, Map<String, Integer> setMap){
            String[] strings = candidateSet.split(ITEM_SPLIT);
            //找出候选集所有的子集，并判断每个子集是否属于频繁子集

            for(int i=0;i<strings.length;i++)	{
                String subString = "";
                for(int j=0;j<strings.length;j++){
                    if(j!=i)
                    {
                        subString += strings[j]+ITEM_SPLIT;
                    }
                }
                if(setMap.get(subString)==null)
                    return true;
            }
            return false;
        }
        /**
         * 由频繁项集产生关联规则
         */
        public Map<String, Double> getRelationRules(Map<String, Integer> frequentSetMap){
            Map<String, Double> relationsMap = new HashMap<>();
            Set<String> keySet = frequentSetMap.keySet();//频繁项集
            for(String key:keySet){
                List<String> keySubset = subset(key);//求key的所有非空子集
                //System.out.println(keySubset);
                for(String keySubsetItem:keySubset)
                {
                    //子集keySubsetItem也是频繁项
                    Integer count = frequentSetMap.get(keySubsetItem);
                    if(count!=null) {
                        Double confidence = (1.0 * frequentSetMap.get(key)) / (1.0 * frequentSetMap.get(keySubsetItem));
                        if (confidence > CONFIDENCE)//大于置信度阈值
                        {
                            relationsMap.put(keySubsetItem + CON + expect(key, keySubsetItem), confidence);
                        }
                    }
                }
            }
            return relationsMap;
        }
        /**
         * 求一个集合所有的非空真子集
         * 不是用递归的方法
         */

        private List<String> subset(String sourceSet)
        {
            List<String> result = new ArrayList<>();
            String[] strings = sourceSet.split(ITEM_SPLIT);
            //非空真子集
            for(int i=1;i<(int)(Math.pow(2, strings.length))-1;i++)
            {
                String item = "";
                String flag = "";
                int ii=i;
                do
                {
                    flag += ""+ii%2;
                    ii = ii/2;
                } while (ii>0);
                for(int j=flag.length()-1;j>=0;j--)
                {
                    if(flag.charAt(j)=='1')
                    {
                        item = strings[j]+ITEM_SPLIT+item;
                    }
                }
                result.add(item);
            }
            return result;
        }
        /**
         * 集合运算
         * A/B
         */
        private String expect(String stringA,String stringB){
            String result = "";
            String[] stringAs = stringA.split(ITEM_SPLIT);
            String[] stringBs = stringB.split(ITEM_SPLIT);
            for(int i=0;i<stringAs.length;i++){
                boolean flag = true;
                for(int j=0;j<stringBs.length;j++){
                    if(stringAs[i].compareTo(stringBs[j])==0){
                        flag = false;
                        break;
                    }
                }
                if(flag)
                    result += stringAs[i]+ITEM_SPLIT;
            }
            return result;
        }



        public ArrayList<String> getData1(Map<String, Integer> frequentSetMap){
            Map<String, Double> relationsMap = new HashMap<>();
            Set<String> keySet = frequentSetMap.keySet();//频繁项集
            ArrayList<String> mediacine1=new ArrayList<>();
            for(String key:keySet){
                List<String> keySubset = subset(key);//求key的所有非空子集
                //System.out.println(keySubset);
                for(String keySubsetItem:keySubset)
                {
                    //子集keySubsetItem也是频繁项
                    Integer count = frequentSetMap.get(keySubsetItem);
                    if(count!=null) {
                        Double confidence = (1.0 * frequentSetMap.get(key)) / (1.0 * frequentSetMap.get(keySubsetItem));
                        if (confidence > CONFIDENCE)//大于置信度阈值
                        {
                            mediacine1.add(keySubsetItem);
                        }
                    }
                }
            }
            return mediacine1;
        }

        public ArrayList<String> getData2(Map<String, Integer> frequentSetMap){
             Map<String, Double> relationsMap = new HashMap<>();
             Set<String> keySet = frequentSetMap.keySet();//频繁项集
             ArrayList<String> mediacine2=new ArrayList<>();
             for(String key:keySet){
                 List<String> keySubset = subset(key);//求key的所有非空子集
                //System.out.println(keySubset);
                 for(String keySubsetItem:keySubset)
                 {
                //子集keySubsetItem也是频繁项
                 Integer count = frequentSetMap.get(keySubsetItem);
                 if(count!=null) {
                    Double confidence = (1.0 * frequentSetMap.get(key)) / (1.0 * frequentSetMap.get(keySubsetItem));
                    if (confidence > CONFIDENCE)//大于置信度阈值
                    {
                        mediacine2.add(expect(key, keySubsetItem));
                    }
                }
            }
        }
        return mediacine2;
    }
    public ArrayList<Double> getConfidence(Map<String, Integer> frequentSetMap){
        Map<String, Double> relationsMap = new HashMap<>();
        Set<String> keySet = frequentSetMap.keySet();//频繁项集
        ArrayList<Double> confidence0=new ArrayList<>();
        for(String key:keySet){
            List<String> keySubset = subset(key);//求key的所有非空子集
            //System.out.println(keySubset);
            for(String keySubsetItem:keySubset)
            {
                //子集keySubsetItem也是频繁项
                Integer count = frequentSetMap.get(keySubsetItem);
                if(count!=null) {
                    Double confidence = (1.0 * frequentSetMap.get(key)) / (1.0 * frequentSetMap.get(keySubsetItem));
                    if (confidence > CONFIDENCE)//大于置信度阈值
                    {
                        confidence0.add(confidence);
                    }
                }
            }
        }
        return confidence0;
    }
}
