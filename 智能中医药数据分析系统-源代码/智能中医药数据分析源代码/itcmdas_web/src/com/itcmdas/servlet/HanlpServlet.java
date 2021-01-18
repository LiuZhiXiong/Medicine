package com.itcmdas.servlet;

import com.alibaba.fastjson.JSONObject;
import com.hankcs.hanlp.classification.classifiers.IClassifier;
import com.hankcs.hanlp.classification.classifiers.NaiveBayesClassifier;
import com.hankcs.hanlp.classification.models.NaiveBayesModel;
import com.itcmdas.hanlp.HanlpService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author TangBo
 * @description 情感分析的servlet
 * @data 2020/11/18
 */
@WebServlet("/HanlpServlet")
public class HanlpServlet extends BasicServlet {
    public void hanlpResult(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        ServletContext context=getServletContext();
        String c=context.getRealPath("/WEB-INF/myModel");///WEB-INF/下的文件获取
        System.out.println("c文件："+c);
        String text=req.getParameter("text");
        NaiveBayesModel model=HanlpService.hanlpModel(c);
        IClassifier iClassifier=new NaiveBayesClassifier(model);
        String result =HanlpService.predict(iClassifier,text);
        System.out.println(result);
        PrintWriter out = resp.getWriter();
        JSONObject obj = new JSONObject();
        obj.put("result", result);
        String resultJson = obj.toJSONString();

        out.print(resultJson);
    }
}
