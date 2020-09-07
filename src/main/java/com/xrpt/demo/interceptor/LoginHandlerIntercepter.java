package com.xrpt.demo.interceptor;


import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class LoginHandlerIntercepter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        Object user = request.getSession().getAttribute("currentUser");
        if (user == null) {
//            request.getRequestDispatcher("/index.html").forward(request, response);
            out.write("<script>alert('请先登录!');location.href='toIndex'</script>");
        } else {
            //已登录
            //放行
            return true;
        }
        return false;
    }
}
