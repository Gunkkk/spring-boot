package com.course.admin.controller;

import com.course.admin.service.AdminBookService;
import com.course.borrower.entity.Book;
import com.course.borrower.entity.Magazine;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import java.util.List;

/**
 * Created by 84074 on 2017/10/30.
 */
@Controller
public class AdminBookController {
    @Autowired
    AdminBookService adminBookService;


    @RequestMapping(value = "/adminBook.action")
    public ModelAndView adminBookDefault(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/titlesAdmin");
        return modelAndView;
    }    /**
     * 查询
     * @param id
     * @param name
     * @param author
     * @param isbn
     * @param type
     * @return
     */
    @RequestMapping(value = "/queryBook.action")
    public ModelAndView queryBook( HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        int id = Integer.parseInt(String.valueOf(request.getAttribute("id")));
        String name = String.valueOf(request.getAttribute("name"));
        String author = String.valueOf(request.getAttribute("author"));
        String isbn = String.valueOf(request.getAttribute("isbn"));
        String type = String.valueOf(request.getAttribute("type"));
        String currentPage =String.valueOf(request.getAttribute("currentPage"));
        if (currentPage.isEmpty())
            currentPage="1";

        if (type.equals("书籍")){
            Page<Book> bookPage = adminBookService.queryBook(id,name,author,isbn,0);
            modelAndView.addObject("page",bookPage);
        }else{
            Page<Magazine> magazinePage = adminBookService.queryMagazine(id,name,author,isbn,Integer.parseInt(currentPage));
            modelAndView.addObject("page",magazinePage);
        }
        modelAndView.setViewName("/titlesAdmin");
        return modelAndView;

    }

   @RequestMapping(value = "/addBook.action")
    public ModelAndView addBook(){
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }
    @RequestMapping(value = "/addMagazine.action")
    public ModelAndView addMagazine(){
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }
    @RequestMapping(value = "/deleteTitle.action")
    public ModelAndView deleteTitle(@RequestParam("id ") int id){
        ModelAndView modelAndView = new ModelAndView();
        adminBookService.deleteTitle(id);
        return modelAndView;
    }
    @RequestMapping(value = "/changeBook.action")
    public ModelAndView changeBook (){
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }
    @RequestMapping(value = "/changeMagazine.action")
    public ModelAndView changeMagazine (){
        ModelAndView modelAndView = new ModelAndView();
        return  modelAndView;
    }
}
