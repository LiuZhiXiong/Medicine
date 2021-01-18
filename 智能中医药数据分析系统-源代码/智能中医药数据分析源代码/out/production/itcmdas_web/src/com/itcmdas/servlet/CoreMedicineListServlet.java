package com.itcmdas.servlet;

import com.alibaba.fastjson.JSONObject;
import com.itcmdas.service.ComplexEntropyClustering;
import com.itcmdas.service.MedicineStrTableGeneration;
import com.itcmdas.test.MedicineStrTest;
import com.itcmdas.vo.CoreMedicine;
import com.itcmdas.vo.MedicineFreTable;
import com.itcmdas.vo.TwoMedicineMapForClustering;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname CoreMedicineListServlet
 * @Description 核心药物组合表 Servlet
 * @Author liubo
 * @Date 2020/10/8 20:38
 * @Version 1.0
 */

@WebServlet(name = "CoreMedicineListServlet", urlPatterns = {"/CoreMedicineListServlet"})
public class CoreMedicineListServlet extends BasicServlet {
    //接收前端传入的excel文件 转化为的string数组
    static String[] medicineStrCoreMedicine = null;

    //接收前端传入的 最小药物数量 和 最大药物数量
    static int minMedicineNum;
    static int maxMedicineNum;

    /**
     * 获取前端传入的参数及Excel二维字符串数组表，保存到session中，以便第二次ajax后端能直接获取
     *
     * @param req
     * @param resp
     */
    public void saveDataToSession(HttpServletRequest req, HttpServletResponse resp) {
        //获取前端数据
        medicineStrCoreMedicine = req.getParameterValues("medicineStrCoreMedicine");
        minMedicineNum = Integer.parseInt(req.getParameter("minMedicineNum"));
        maxMedicineNum = Integer.parseInt(req.getParameter("maxMedicineNum"));

        //保存到session
        req.getSession().setAttribute("medicineStr", medicineStrCoreMedicine);
        req.getSession().setAttribute("minMedicineNum", minMedicineNum);
        req.getSession().setAttribute("maxMedicineNum", maxMedicineNum);

    }

    /**
     * 第二次ajax返回数据--->核心药物组合表
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void coreMedicineListsData(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();

        //获取session中的数据
        medicineStrCoreMedicine = (String[]) req.getSession().getAttribute("medicineStr");
        minMedicineNum = (int) req.getSession().getAttribute("minMedicineNum");
        maxMedicineNum = (int) req.getSession().getAttribute("maxMedicineNum");


        /**
         * 调用 MedicineStrTableGeneration 类的 getMedicineStrTable 方法，生成二维字符串处方数组
         */
        ComplexEntropyClustering.setMedicalTableStr(MedicineStrTableGeneration.getMedicineStrTable(medicineStrCoreMedicine));

        //类初始化，获取采用独热编码的处方表，以及处方总数，药方数
        ComplexEntropyClustering.init();

        //调用 getMedicineMapForMinCorrelationCoefficient 获取核心药物组合表
        ArrayList<ArrayList<String>> coreMedicineLists =
                ComplexEntropyClustering.getCoreMedicineLists(minMedicineNum, maxMedicineNum);

        /**
         *将 ComplexEntropyClustering 类中获得的 二维字符串数组构成的核心药物组 封装成
         * 一维数组（每个数组元素为 CoreMedicine 类的对象）
         */
        ArrayList<CoreMedicine> coreMedicines = new ArrayList<>();

        for (int i = 0; i < coreMedicineLists.size(); i++) {
            CoreMedicine coreMedicine = new CoreMedicine();
            ArrayList<String> list = new ArrayList<>();

            list = coreMedicineLists.get(i);
            String str = "";
            for (int j = 0; j < list.size(); j++) {
                if (j == 0) {
                    str += list.get(j);
                } else {
                    str = str + "," + list.get(j);
                }
            }

            coreMedicine.setId(i+1);
            coreMedicine.setCoreMedicineList(str);
            coreMedicines.add(coreMedicine);
        }

        int currentPage = Integer.parseInt(req.getParameter("page") == null ? "1" : req.getParameter("page"));
        int limit = Integer.parseInt(req.getParameter("limit") == null ? "10" : req.getParameter("limit"));

        List<CoreMedicine> limitDataCoreMedicine;
        int count = coreMedicines.size();

        if ((count - (currentPage - 1) * limit) / limit == 0) {
            limitDataCoreMedicine = new ArrayList<>(coreMedicines.subList((currentPage - 1) * limit, count));
        } else {
            limitDataCoreMedicine = new ArrayList<>(coreMedicines.subList((currentPage - 1) * limit, currentPage * limit));
        }


        JSONObject obj = new JSONObject();
        obj.put("code", 0);
        obj.put("msg", "");
        obj.put("count", coreMedicines.size());
        obj.put("data", limitDataCoreMedicine);

        String coreMedicineListsJson = obj.toJSONString();

        out.print(coreMedicineListsJson);

    }

    /**
     * 下载数据接口
     * @param req
     * @param resp
     * @throws Exception
     */
    public void coreMedicineListsDataDown(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();

        //获取session中的数据
        medicineStrCoreMedicine = (String[]) req.getSession().getAttribute("medicineStr");
        minMedicineNum = (int) req.getSession().getAttribute("minMedicineNum");
        maxMedicineNum = (int) req.getSession().getAttribute("maxMedicineNum");


        /**
         * 调用 MedicineStrTableGeneration 类的 getMedicineStrTable 方法，生成二维字符串处方数组
         */
        ComplexEntropyClustering.setMedicalTableStr(MedicineStrTableGeneration.getMedicineStrTable(medicineStrCoreMedicine));

        //类初始化，获取采用独热编码的处方表，以及处方总数，药方数
        ComplexEntropyClustering.init();

        //调用 getMedicineMapForMinCorrelationCoefficient 获取核心药物组合表
        ArrayList<ArrayList<String>> coreMedicineLists =
                ComplexEntropyClustering.getCoreMedicineLists(minMedicineNum, maxMedicineNum);

        /**
         *将 ComplexEntropyClustering 类中获得的 二维字符串数组构成的核心药物组 封装成
         * 一维数组（每个数组元素为 CoreMedicine 类的对象）
         */
        ArrayList<CoreMedicine> coreMedicines = new ArrayList<>();

        for (int i = 0; i < coreMedicineLists.size(); i++) {
            CoreMedicine coreMedicine = new CoreMedicine();
            ArrayList<String> list = new ArrayList<>();

            list = coreMedicineLists.get(i);
            String str = "";
            for (int j = 0; j < list.size(); j++) {
                if (j == 0) {
                    str += list.get(j);
                } else {
                    str = str + "," + list.get(j);
                }
            }

            coreMedicine.setId(i+1);
            coreMedicine.setCoreMedicineList(str);
            coreMedicines.add(coreMedicine);
        }



        JSONObject obj = new JSONObject();
        obj.put("code", 0);
        obj.put("msg", "");
        obj.put("count", coreMedicines.size());
        obj.put("data", coreMedicines);

        String coreMedicineListsJson = obj.toJSONString();

        out.print(coreMedicineListsJson);

    }


}
