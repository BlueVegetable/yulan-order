/*
package com.yulan.filter;


import com.alibaba.fastjson.JSON;
import com.yulan.pojo.Web_user;
import com.yulan.utils.Response;
import com.yulan.utils.Token;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebFilter(filterName = "AccessFilter")
public class AccessFilter implements Filter {
    public void destroy() {

    }
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String uri = request.getRequestURI();
        if(uri.endsWith("getArticle.do")||uri.endsWith("getAllArticles.do")||isValid(request)||uri.endsWith("login.html")) {
            chain.doFilter(req,resp);
        } else {
            //这句话的意思，是让浏览器用utf8来解析返回的数据
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            //这句话的意思，是告诉servlet用UTF-8转码，而不是用默认的ISO8859
            response.setCharacterEncoding("UTF-8");
            Map<String,Object> result = Response.getResponseMap(1,"请先登录",null);
            response.getWriter().println(JSON.toJSONString(result));
            response.getWriter().flush();
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

    private boolean isValid(HttpServletRequest request) throws IOException {
        String token = request.getParameter("token");
        if(token==null)
            token = request.getHeader("token");
        if(token==null)
            return false;
        Web_user web_user = (Web_user) Token.parseToken(token,Web_user.class);
        if(web_user==null)
            return false;
        request.setAttribute("admin",web_user);
        return true;
    }
}
*/
