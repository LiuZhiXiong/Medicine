package com.itcmdas.servlet;

import com.alibaba.fastjson.JSONObject;
import com.itcmdas.vo.AssociationData;
import com.itcmdas.vo.AssociationFormData;
import com.itcmdas.vo.DataList;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author TangBo
 * @description 关联分析测试数据
 * @data 2020/10/9
 */
@WebServlet("/AssociationAnalysisServlet")
public class AssociationAnalysisServlet extends BasicServlet {

    //支持数
    int support;
    //置信度
    double confidence;
    //处方表
    String[] medicineStr;
    //动态处方表
    ArrayList<String> medicineStrApriori;

    //Common类作用：String[] ---> ArrayList<String>()

    public void saveData(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        support = Integer.parseInt(req.getParameter("Support"));
        confidence = Double.parseDouble(req.getParameter("Confidence"));
        medicineStr = req.getParameterValues("medicineStr");

        //将String[] --->ArrayList<String>
        medicineStrApriori=new ArrayList<>();
        for(String str:medicineStr){
            medicineStrApriori.add(str);
        }

        req.getSession().setAttribute("Support", support);
        req.getSession().setAttribute("Confidence", confidence);
        req.getSession().setAttribute("medicineStr", medicineStrApriori);
    }

    public void formData(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //      一个list存放多条数据包装类
        List<AssociationFormData> medicineTableData = new ArrayList<>();

        int currentPage = Integer.parseInt(req.getParameter("page") == null ? "1" : req.getParameter("page"));
        int limit = Integer.parseInt(req.getParameter("limit") == null ? "10" : req.getParameter("limit"));

//        将关联药材装入ArrayList


//----------------------------波姐的代码---------------------------------------------------------


        /**
         * 麻黄、杏仁
         * 甘草、杏仁
         * "麻黄、杏仁","甘草、杏仁"
         *
         */


        /*
         *调用Datalist中获取3个Arraylist
         * 调用Common获得datalist
         * 数据：data1\data2\con
         * 注入：support、confidence、url
         */
        DataList data = new DataList();

        ArrayList<String> datalist = new ArrayList<>();
        ArrayList<String> data1 = new ArrayList<>();
        ArrayList<String> data2 = new ArrayList<>();
        ArrayList<Double> con = new ArrayList<>();
        support = (int) req.getSession().getAttribute("Support");
        confidence = (double) req.getSession().getAttribute("Confidence");
        medicineStrApriori= (ArrayList<String>) req.getSession().getAttribute("medicineStr");

//        System.out.println("medicine:" + medicineStr);
//        String json = "麻黄、杏仁、生石膏、桑白皮、葶苈子、炙紫菀、百部、白前、陈皮、荆芥、川贝、桔梗、甘草, 炒瓜壳、桃仁、川贝、桑白皮、白前、法半夏、杏仁、桔梗、炙紫菀、百部、荆芥、薄荷、甘草、矮地茶, 桑叶、杏仁、沙参、麦冬、生石膏、阿胶、炙枇杷叶、甘草、桃仁、炒瓜壳、川贝、天花粉, 杏仁、桔梗、炙紫菀、百部、白前、陈皮、薄荷、矮地茶、甘草、桑白皮、川贝、青黛粉、海蛤粉, 麻黄、杏仁、炙冬花、法半夏、苏子、黄芩、白果、桑白皮、白芥子、炒菜菔子、甘草, 桑白皮、地骨皮、杏仁、炒瓜蒌壳、生大黄、生石膏、川贝、甘草, 麻黄、桂枝、白芍、细辛、五味子、干姜、法半夏、甘草, 芦根、桃仁、生薏苡仁、炒冬瓜子、黄连、法半夏、炒瓜壳、白及片、鱼腥草、野天麻、葛根、浙贝、防风, 百合、生地、熟地、玄参、浙贝、桔梗、麦冬、白芍、天冬、甘草、炙枇杷叶、白及片、丹皮、栀子炭、白茅根, 黄连、法半夏、炒瓜蒌壳、苇茎、薏苡仁、桃仁、椒目、葶苈、大枣、枳实, 香薷、厚朴、扁豆花、银花、连翘、黄芩、柴胡、法半夏、杏仁、甘草, 麻黄、杏仁、生石膏、桑白皮、葶苈子、炙紫菀、百部、白前、陈皮、川贝、桔梗、甘草、法半夏, 黄芪、炒白术、防风、炒瓜壳、川贝、杏仁、桔梗、炙紫菀、百部、白前、陈皮、法半夏、甘草、矮地茶, 桑叶、杏仁、沙参、麦冬、生石膏、阿胶珠、炙枇杷叶、甘草、桃仁、花粉, 麻黄、杏仁、炙冬花、法半夏、苏子、黄芩、白果、桑白皮、葶苈子、川贝、白芥子、炒菜菔子、厚朴、甘草, 麻黄、杏仁、甘草、苏子、桑白皮、川贝、生石膏、生大黄、炒瓜壳, 麻黄、射干、细辛、法半夏、炙紫菀、炙冬花、五味子、茯苓、大枣、生姜, 芦根、生薏苡仁、炒瓜壳、炒冬瓜子、杏仁、桔梗、炙紫菀、百部、陈皮、甘草、鱼腥草、浙贝、红藤, 百合、生地、熟地、玄参、浙贝、桔梗、麦冬、白芍、天冬、甘草、炙枇杷叶、白及片, 炒瓜壳、白芥子、川贝、椒目、茯苓、泽泻、猪苓、滑石、车前子、杏仁、枳实, 麻黄、杏仁、甘草、苏子、桑白皮、川贝、生石膏、炒瓜壳, 芦根、生薏苡仁、炒瓜壳、炒冬瓜子、杏仁、桔梗、炙紫菀、百部、陈皮、甘草、葶苈子、鱼腥草、浙贝、蒲公英、白及片、桑白皮、大枣, 西洋参、茯苓、炒白术、陈皮、法半夏、甘草、百合、百部、白及片, 鱼腥草、炒瓜壳、白芥子、川贝、椒目、茯苓、泽泻、猪苓、滑石、车前子、杏仁、枳实、甘草, 鱼腥草、炒瓜壳、白芥子、椒目、茯苓、泽泻、猪苓、滑石、车前子、杏仁、甘草、陈皮、法半夏, 芦根、生薏苡仁、炒瓜壳、炒冬瓜子、杏仁、桔梗、灸紫菀、百部、陈皮、甘草、鱼腥草、浙贝";
        try {
            datalist = medicineStrApriori;
            data1 = data.getmediacine1(datalist, support, confidence);
            data2 = data.getmediacine2(datalist, support, confidence);
            con = data.getConfidence(datalist, support, confidence);
        } catch (Exception e) {
            e.printStackTrace();
        }
//    System.out.println(datalist);
//    System.out.println(data1);
//    System.out.println(data2);
//    System.out.println(con);


//------------------------------------------------------------------
//        将每条string药物分离出来并进行匹配关联，进行类包装
        for (int i = 0; i < data1.size(); i++) {
//            String temp1=data1.get(i)
            AssociationFormData myData = new AssociationFormData();
            String[] medicineStr1 = data1.get(i).split("、");
            String[] medicineStr2 = data2.get(i).split("、");
            List<String> temp1 = new ArrayList<>();
            List<String> temp2 = new ArrayList<>();

            for (int j = 0; j < medicineStr1.length; j++) {
                temp1.add(medicineStr1[j]);
            }
            for (int j = 0; j < medicineStr2.length; j++) {
                temp2.add(medicineStr2[j]);
            }
            String s = temp1.toString().replace("[","{").replace("]","}") + "->" + temp2.toString().replace("[","{").replace("]","}");
//            System.out.println(s);
            myData.setMedicines(s);
            myData.setId(i+1);

            medicineTableData.add(myData);


        }

        List<AssociationFormData> limitData;
        int count=medicineTableData.size();
        System.out.println("page"+currentPage+"limit:"+limit);
        if((count-(currentPage-1)*limit)/limit==0){
            limitData=new ArrayList<>(medicineTableData.subList((currentPage-1)*limit,count));
        }else {
            limitData=new ArrayList<>(medicineTableData.subList((currentPage-1)*limit,currentPage*limit));
        }

        for (AssociationFormData c:limitData){

            System.out.println(c.getMedicines());
        }
        JSONObject obj = new JSONObject();
        obj.put("code", 0);
        obj.put("msg", "");
        obj.put("count", count);
        obj.put("data", limitData);
        PrintWriter out = resp.getWriter();
        String correlationCoefficientListJson = obj.toJSONString();

        out.print(correlationCoefficientListJson);

    }

    public void echartsData(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //        测试数据关联药材
        List<String> list1 = new ArrayList<>();
        //      一个list存放多条数据包装类
        List<AssociationData> medicineData = new ArrayList<>();
//        将关联药材装入ArrayList


//----------------------------波姐的代码---------------------------------------------------------

        /*
         *调用Datalist中获取3个Arraylist
         * 调用Common获得datalist
         * 数据：data1\data2\con
         * 注入：support、confidence、url
         */
        DataList data = new DataList();

        ArrayList<String> datalist = new ArrayList<>();
        ArrayList<String> data1 = new ArrayList<>();
        ArrayList<String> data2 = new ArrayList<>();
        ArrayList<Double> con = new ArrayList<>();
        support = (int) req.getSession().getAttribute("Support");
        confidence = (double) req.getSession().getAttribute("Confidence");
        medicineStrApriori= (ArrayList<String>) req.getSession().getAttribute("medicineStr");

        try {
            datalist =medicineStrApriori;
            data1 = data.getmediacine1(datalist, support, confidence);
            data2 = data.getmediacine2(datalist, support, confidence);
            con = data.getConfidence(datalist, support, confidence);
        } catch (Exception e) {
            e.printStackTrace();
        }
//------------------------------------------------------------------
//        将每条string药物分离出来并进行匹配关联，进行类包装
        for (int i = 0; i < data1.size(); i++) {
//            String temp1=data1.get(i)
            AssociationData myData = new AssociationData();
            String[] medicineStr1 = data1.get(i).split("、");
            String[] medicineStr2 = data2.get(i).split("、");
            List<String> temp1 = new ArrayList<>();
            List<String> temp2 = new ArrayList<>();

            for (int j = 0; j < medicineStr1.length; j++) {
                temp1.add(medicineStr1[j]);
            }
            for (int j = 0; j < medicineStr2.length; j++) {
                temp2.add(medicineStr2[j]);
            }
            myData.setMedicine1(temp1);
            myData.setMedicine2(temp2);
            myData.setNumber(con.get(i));

            medicineData.add(myData);


        }


        JSONObject obj = new JSONObject();
        obj.put("code", 0);
        obj.put("msg", "");
        obj.put("count", 100);
        obj.put("data", medicineData);
        PrintWriter out = resp.getWriter();
        String JsonXXXX = obj.toJSONString();

        out.print(JsonXXXX);

    }



}
