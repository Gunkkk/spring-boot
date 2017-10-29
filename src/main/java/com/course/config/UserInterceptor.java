package com.course.config;

import com.course.admin.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;

/**
 * Created by 84074 on 2017/10/22.
*/


public class UserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //获取请求的URL
        String url = request.getRequestURI();
        //URL:login.jsp是公开的;这个demo是除了login.jsp是可以公开访问的，其它的URL都进行拦截控制
        if(url.contains("/login") || url.contains("/index") || url.contains("/toLogin")){
            return true;
        }
        //获取Session
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if(user!=null){
            return true;
        }
        String error="无权访问";
        error= URLEncoder.encode(error,"UTF8");
        response.sendRedirect("/toError.action?error="+error);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

}
