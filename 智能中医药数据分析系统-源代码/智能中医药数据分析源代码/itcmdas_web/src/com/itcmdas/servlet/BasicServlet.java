package com.itcmdas.servlet;

/**
 *
 * @author TangBo
 * @description 继承httpServlet,后面的servlet继承它
 * @version 1.0
 * @data 2020年10月5日
 */
import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BasicServlet extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String methodName = req.getParameter("method");
        if(methodName==null) {
            methodName="listUI";
        }
        Class<? extends BasicServlet> clazz = this.getClass();
        try {
            Method method = clazz.getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
            method.invoke(this, req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
