package com.itcmdas.servlet;

import com.alibaba.fastjson.JSONObject;
import com.itcmdas.dao.MedicineDao;
import com.itcmdas.vo.Medicine;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author TangBo
 * @description
 * @data 2020/10/10
 */
@WebServlet("/medicineServlet")
public class MedicineServlet extends BasicServlet{
    public void medicineDescription(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        MedicineDao medicineDao=new MedicineDao();
        String medicineName =req.getParameter("medicine");
        Medicine medicine =medicineDao.getMedicineByMedicineName(medicineName);

        JSONObject obj = new JSONObject();
        obj.put("code", 0);
        obj.put("msg", "");
        obj.put("count", 100);
        obj.put("data", medicine);
        PrintWriter out = resp.getWriter();
        String correlationCoefficientListJson = obj.toJSONString();

        out.print(correlationCoefficientListJson);
    }
}
