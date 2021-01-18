package com.itcmdas.servlet;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RedirectServlet", urlPatterns = {"/RedirectServlet"})
public class RedirectServlet extends BasicServlet {

    public void getUrl(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = (String) req.getSession().getAttribute("url");

        System.out.println(url);

        //防止乱码
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");

        //servlet请求转发信息,将数据封装成json返回给前端
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("msg", "");
        jsonObject.put("data", url);

        PrintWriter out = resp.getWriter();
        String jsonStr = jsonObject.toJSONString();

        out.print(jsonStr);

        System.out.println(jsonStr);
    }
}
