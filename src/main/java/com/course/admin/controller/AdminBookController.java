package com.course.admin.controller;

import com.course.admin.service.AdminBookService;
import com.course.borrower.entity.Book;
import com.course.borrower.entity.Magazine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        modelAndView.setViewName("/bookAdmin");
        return modelAndView;
    }
    @RequestMapping(value = "/adminMagazine.action")
    public ModelAndView adminMagazineDefault(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/magazineAdmin");
        return modelAndView;
    }   /**
     * 查询
     * @param iid
     * @param name
     * @param author
     * @param isbn
     * @return
     */
    @RequestMapping(value = "/queryBook.action")
    public ModelAndView queryBook(@RequestParam ("id") String iid,@RequestParam("name") String name,@RequestParam("author") String author,
                                  @RequestParam("isbn") String isbn){
        ModelAndView modelAndView = new ModelAndView();
        List<Book> bookPage = new ArrayList<>();
        Integer id=null;
        if (iid.isEmpty()||iid==null){
            id=null;
        }
        else id = Integer.parseInt(iid);
        if (iid.isEmpty()&&name.isEmpty()&&author.isEmpty()&&isbn.isEmpty())
            bookPage = adminBookService.queryBook();
        else bookPage = adminBookService.queryBook(id,name,author,isbn);
        modelAndView.addObject("page",bookPage);

        modelAndView.setViewName("/bookAdmin");
        return modelAndView;

    }

    /**
     * 查询杂志
     * @param iid
     * @param name
     * @param author
     * @param isbn
     * @return
     */
    @RequestMapping(value = "/queryMagazine.action")
    public ModelAndView queryMagazine(@RequestParam ("id") String iid,@RequestParam("name") String name,@RequestParam("author") String author,
                                  @RequestParam("isbn") String isbn){
        ModelAndView modelAndView = new ModelAndView();
        List<Magazine> magazinePage = new ArrayList<>();
        Integer id=null;
        if (iid.isEmpty()||iid==null){
            id=null;
        } else id = Integer.parseInt(iid);
        if (iid.isEmpty()&&name.isEmpty()&&author.isEmpty()&&isbn.isEmpty())
            magazinePage = adminBookService.queryMagazine();
        else magazinePage = adminBookService.queryMagazine(id,name,author,isbn);
        modelAndView.addObject("page",magazinePage);
        modelAndView.setViewName("/magazineAdmin");
        return modelAndView;

    }

    /**
     * 新增书籍
     * @param name
     * @param author
     * @param isbn
     * @param price
     * @param press
     * @param publishDate
     * @param version
     * @return
     */
   @RequestMapping(value = "/addBook.action")
    public ModelAndView addBook(@RequestParam ("nameAdd") String name,
                                @RequestParam ("authorAdd") String author,
                                @RequestParam ("isbnAdd") String isbn,
                              //  @RequestParam ("totalNumberAdd") int totalNumber,
                                @RequestParam ("priceAdd") float price,
                                @RequestParam ("pressAdd") String press,
                                @RequestParam ("publishDateAdd") String publishDate,
                                @RequestParam ("versionAdd") String version){
        ModelAndView modelAndView = new ModelAndView("bookAdmin");
        Book book = new Book();
        book.setName(name);
        book.setAuthor(author);
        book.setIsbn(isbn);
        book.setTotalNumber(0);
        book.setPrice(price);
        book.setPress(press);
        book.setPublishDate(string2date(publishDate));
        book.setVersion(version);
        book.setType("书籍");
        adminBookService.addBook(book);
        return modelAndView;
    }

    /*添加杂志
     * @param name
     * @param author
     * @param isbn
     * @param totalNumber
     * @param price
     * @param press
     * @param volume
     * @return
     */
    @RequestMapping(value = "/addMagazine.action")
    public ModelAndView addMagazine(@RequestParam ("nameAdd") String name,
                                    @RequestParam ("authorAdd") String author,
                                    @RequestParam ("isbnAdd") String isbn,
                                  //  @RequestParam ("totalNumberAdd") int totalNumber,
                                    @RequestParam ("priceAdd") float price,
                                    @RequestParam ("pressAdd") String press,
                                    @RequestParam ("volumeAdd") String volume){
        ModelAndView modelAndView = new ModelAndView("magazineAdmin");
        Magazine magazine = new Magazine();
        magazine.setName(name);
        magazine.setAuthor(author);
        magazine.setIsbn(isbn);
        magazine.setPrice(price);
        magazine.setPress(press);
        magazine.setVolume(volume);
        magazine.setTotalNumber(0);
        magazine.setType("杂志");
        adminBookService.addMagazine(magazine);
        return modelAndView;
    }
    @RequestMapping(value = "/deleteBook.action")
    public ModelAndView deleteBook(@RequestParam("id") int id){
        ModelAndView modelAndView = new ModelAndView("bookAdmin");
        adminBookService.deleteTitle(id);
        return modelAndView;
    }
    @RequestMapping(value = "/deleteMagazine.action")
    public ModelAndView deleteMagazine(@RequestParam("id") int id){
        ModelAndView modelAndView = new ModelAndView("magazineAdmin");
        adminBookService.deleteTitle(id);
        return modelAndView;
    }

    /**
     * 修改书籍
     * @param id
     * @param name
     * @param author
     * @param isbn
     * @param price
     * @param press
     * @param publishDate
     * @param version
     * @return
     */
    @RequestMapping(value = "/changeBook.action")
    public ModelAndView changeBook (@RequestParam("idUpdate") int id,
                                    @RequestParam ("nameUpdate") String name,
                                    @RequestParam ("authorUpdate") String author,
                                    @RequestParam ("isbnUpdate") String isbn,
                                    @RequestParam ("priceUpdate") float price,
                                    @RequestParam ("pressUpdate") String press,
                                    @RequestParam ("publishDateUpdate") String publishDate,
                                    @RequestParam ("versionUpdate") String version){
        ModelAndView modelAndView = new ModelAndView("bookAdmin");
        Book book = new Book();
        book = adminBookService.queryOneBook(id);
        book.setName(name);
        book.setAuthor(author);
        book.setIsbn(isbn);
        book.setPrice(price);
        book.setPress(press);

        book.setPublishDate(string2date(publishDate));
        book.setVersion(version);
        adminBookService.changeBook(book);
        return modelAndView;
    }

    /**
     * 修改杂志
     * @param id
     * @param name
     * @param author
     * @param isbn
     * @param price
     * @param press
     * @param volume
     * @return
     */
    @RequestMapping(value = "/changeMagazine.action")
    public ModelAndView changeMagazine (
                                        @RequestParam("idUpdate") int id,
                                        @RequestParam ("nameUpdate") String name,
                                        @RequestParam ("authorUpdate") String author,
                                        @RequestParam ("isbnUpdate") String isbn,
                                        @RequestParam ("priceUpdate") float price,
                                        @RequestParam ("pressUpdate") String press,
                                        @RequestParam ("volumeUpdate") String volume){
        ModelAndView modelAndView = new ModelAndView("magazineAdmin");
        Magazine magazine = new Magazine();
        magazine = adminBookService.queryOneMagazine(id);
        magazine.setName(name);
        magazine.setAuthor(author);
        magazine.setIsbn(isbn);
        magazine.setPrice(price);
        magazine.setPress(press);
        magazine.setVolume(volume);
        adminBookService.changeMagazine(magazine);
        return  modelAndView;
    }

    private Date string2date (String date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date publisDate=new Date();
        try {
            publisDate = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  publisDate;
    }
}
