package com.course;

import com.course.admin.entity.Borrower;
import com.course.admin.repository.UserJPA;
import com.course.admin.service.LoginService;

import com.course.admin.service.OperateService;
import com.course.borrower.entity.Book;
import com.course.borrower.repository.BookJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    @Autowired
    OperateService operateService;
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


    @RequestMapping (value = "/toLogin.action")
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
            modelAndView.setViewName("redirect:/findAllBorrowers.action");
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

   // @RequestMapping(value = "/findAllBorrowers.action")
    public ModelAndView findAllBorrower(@RequestParam("currentPage")String currentPage){
        ModelAndView modelAndView = new ModelAndView();

        int currentPageNum = Integer.parseInt(currentPage,10);

        int pageSize = 5;

        //排序
        Sort.Order idOrder = new Sort.Order(Sort.Direction.ASC, "id");
        Sort.Order usernameOrder = new Sort.Order(Sort.Direction.ASC,"username");
        Sort sort = new Sort(idOrder,usernameOrder);
        PageRequest pageRequest  = new PageRequest(currentPageNum, pageSize, sort);
        Page<Borrower> page = operateService.findAll(pageRequest);

//        System.out.println("总记录数:" + page.getTotalElements());
//        System.out.println("总页数:" + page.getTotalPages());
//        System.out.println("当前页（request):" + page.getNumber());
//        System.out.println("当前页总记录数（request):" + page.getSize());
//        System.out.println("当前页记录总数：" + page.getNumberOfElements());
//        List<Borrower> borrowers = page.getContent();

        modelAndView.addObject("page",page);

        modelAndView.setViewName("borrowers");
        return modelAndView;
    }
}
