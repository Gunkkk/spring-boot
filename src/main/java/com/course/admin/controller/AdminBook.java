package com.course.admin.controller;

import com.course.admin.service.AdminBookService;
import com.course.borrower.entity.Book;
import com.course.borrower.entity.Magazine;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AdminBook {
    @Autowired
    AdminBookService adminBookService;

    /**
     * 查询
     * @param id
     * @param name
     * @param author
     * @param isbn
     * @param type
     * @return
     */
    @RequestMapping(name = "/queryBook")
    public ModelAndView addBook(@RequestParam("id") int id ,@RequestParam("name") String name,
                                @RequestParam("author") String author,@RequestParam("isbn") String isbn,
                                @RequestParam("type") String type){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("");
        if (type.equals("书籍")){
            List<Book> bookList = adminBookService.queryBook(id,name,author,isbn);
            modelAndView.addObject("list",bookList);
        }else{
            List<Magazine> magazineList = adminBookService.queryMagazine(id,name,author,isbn);
            modelAndView.addObject("list",magazineList);
        }
        return modelAndView;

    }

    @RequestMapping(name = "/addBook")
    public ModelAndView addBook(){
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }
    @RequestMapping(name = "/addMagazine")
    public ModelAndView addMagazine(){
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }
    @RequestMapping(name = "/delete")
    public ModelAndView delete(){
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }
    @RequestMapping(name = "/changeBook")
    public ModelAndView changeBook (){
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }
    @RequestMapping(name = "/changeMagazine")
    public ModelAndView changeMagazine (){
        ModelAndView modelAndView = new ModelAndView();
        return  modelAndView;
    }
}
