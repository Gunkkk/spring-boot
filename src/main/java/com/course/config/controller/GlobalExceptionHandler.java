package com.course.config.controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 84074 on 2017/11/9.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request,Exception e) throws Exception{
            ModelAndView modelAndView = new ModelAndView("/error");
            modelAndView.addObject("exception",e);
            modelAndView.addObject("msg","异常");
            modelAndView.addObject("url",request.getRequestURL());
            return modelAndView;
    }

}
