package com.course;

import com.course.admin.service.LoginService;

import com.course.borrower.entity.Book;
import com.course.borrower.repository.BookJPA;
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
public class demoC {
    @Autowired
    LoginService loginService;
    @Autowired
    BookJPA bookJPA;
    @RequestMapping(value = "/index")
    public String hello(int id,String author,String press){
        Book book = new Book();
       // book.setId(id);
        book.setPress(press);
        book.setAuthor(author);
        bookJPA.save(book);
        return "index"+id;
    }


    @RequestMapping (value = "/toLogin")
    public ModelAndView login1(@RequestParam("username")String username,
                               @RequestParam("password")String password,
                               HttpServletRequest request){
        Map<String,Object> result=loginService.login(username,password);
        ModelAndView modelAndView=new ModelAndView();
        if (result.get("result").equals("success")){
            HttpSession session=request.getSession();
            session.setAttribute("user",result.get("user"));
            String currentPage = "0";
            modelAndView.addObject("currentPage",currentPage);
            modelAndView.setViewName("redirect:/adminBook.action");
            return modelAndView;
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
