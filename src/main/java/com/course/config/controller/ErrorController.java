package com.course.config.controller;


import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 84074 on 2017/11/9.
 */
@Controller
class AdaptedErrorController implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/error";
    }
    @RequestMapping(value = "/error")
    public ModelAndView error(){
        ModelAndView modelAndView = new ModelAndView(getErrorPath());
        modelAndView.addObject("msg","404");
        return modelAndView;
    }
}
