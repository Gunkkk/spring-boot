package com.course;

import com.course.admin.repository.UserJPA;
import com.course.admin.service.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by 84074 on 2017/10/19.
 */
@RestController
public class demoC {
    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/index")
    public String hello(String aa){
        return "index"+aa;
    }


    @RequestMapping (value = "/toLogin")
    public ModelAndView login1(@RequestParam("username")String username,
                               @RequestParam("password")String password,
                               HttpServletRequest request){
        ModelAndView modelAndView=new ModelAndView();
        Map<String,Object> result=loginService.login(username,password);
        if (result.get("result").equals("success")){
            HttpSession session=request.getSession();
            session.setAttribute("user",result.get("user"));
            modelAndView.setViewName("/index");
        }else{
            modelAndView.setViewName("/login");
            modelAndView.addObject("msg",result.get("msg"));
        }
        return modelAndView;
    }

    @RequestMapping(value = "/login")
    public ModelAndView hello3(){
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("login");
        return modelAndView;
    }
}
