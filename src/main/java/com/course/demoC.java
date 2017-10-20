package com.course;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 84074 on 2017/10/19.
 */
@RestController
public class demoC {

    @RequestMapping(value = "/index")
    public String hello(){
        return "index";
    }

    /**
     * 直接返回html模版 失败
     * @return
     */
    @GetMapping(value = "/login")
    public String hello2(){
        return "login";
    }

    @GetMapping(value = "/index2")
    public ModelAndView hello3(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
