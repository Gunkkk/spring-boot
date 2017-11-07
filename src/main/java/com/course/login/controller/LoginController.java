package com.course.login.controller;

import com.course.admin.service.LoginService;

import com.course.borrower.repository.BookJPA;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by 84074 on 2017/10/19.
 */
@RestController
public class LoginController {
    @Autowired
    LoginService loginService;
    @Autowired
    BookJPA bookJPA;
    @RequestMapping(value = "/index")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("/index");
        return modelAndView;
    }


    @RequestMapping (value = "/toLogin")
    public ModelAndView toLogin(@RequestParam("username")String username,
                               @RequestParam("password")String password,
                               HttpServletRequest request){
        Map<String,Object> result=loginService.login(username,password);
        ModelAndView modelAndView=new ModelAndView();
        if (result.get("result").equals("success")){
            HttpSession session=request.getSession();
            session.setAttribute("user",result.get("user"));
            modelAndView.setViewName("redirect:/adminBook.action");
            return modelAndView;
        }else{

            modelAndView.setViewName("/login");
            modelAndView.addObject("msg",result.get("msg"));
        }
        return modelAndView;
    }

    @RequestMapping(value = "/login")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("login");
        return modelAndView;
    }

    /**
     *
     * @param request
     * @return
     */
/*    @RequestMapping(value = "/toBorrowerLogin")
    public ModelAndView toBorrowerLogin(@RequestParam("username")String username,@RequestParam("password") String password
            ,HttpServletRequest request){
        ModelAndView modelAndView =new ModelAndView();
        Map<String,Object> result=loginService.borrowerLogin(username,password);
        if (result.get("result").equals("success")){
            HttpSession session=request.getSession();
            session.setAttribute("borrower",result.get("borrower"));
            modelAndView.setViewName("index");
            return modelAndView;
        }else{
            modelAndView.setViewName("/index");
            modelAndView.addObject("msg",result.get("msg"));
        }
        return modelAndView;
    }*/
    @RequestMapping(value = "/toBorrowerLogin")
    public String toBorrowerLogin2(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Map<String,Object> result=loginService.borrowerLogin(username,password);
        if (result.get("result").equals("success")){
            HttpSession session=request.getSession();
            session.setAttribute("borrower",result.get("borrower"));
            return "success";
        }else{
            JSONObject json = new JSONObject(result);
            return json.toString();
        }
    }
    @RequestMapping("/borrowerLogout.action")
    public ModelAndView borrowerLogin (HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("redirect:/index");
        HttpSession session = request.getSession();
        session.removeAttribute("borrower");
        return modelAndView;
    }

}
