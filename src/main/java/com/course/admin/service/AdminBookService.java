package com.course.admin.service;

import com.course.borrower.repository.BookJPA;
import com.course.borrower.repository.MagazineJPA;
import com.course.borrower.repository.TitleJPA;
import com.course.borrower.entity.Book;
import com.course.borrower.entity.Magazine;
import com.course.borrower.entity.Title;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 84074 on 2017/10/27.
 */
@Service
public class AdminBookService {
    @Autowired
    TitleJPA titleJPA;
    @Autowired
    BookJPA bookJPA;
    @Autowired
    MagazineJPA magazineJPA;

    int pageSize=10;
    /**
     * 条件查询书籍
     * @param id
     * @param name
     * @param author
     * @param isbn
     * @param currentPage
     * @return
     */
    public Page<Book> queryBook(Integer id, String name, String author, String isbn,int currentPage){

        //排序
        Sort.Order idOrder = new Sort.Order(Sort.Direction.ASC, "id");
        Sort.Order usernameOrder = new Sort.Order(Sort.Direction.ASC,"name");
        Sort sort = new Sort(idOrder,usernameOrder);
        Pageable pageRequest  = new PageRequest(currentPage, pageSize, sort);
       // Page<Book> page = bookJPA.findByIdOrNameLikeOrAuthorOrIsbn(pageRequest);
        Page<Book> page =bookJPA.findByIdOrNameLikeOrAuthorOrIsbn(id,name,author,isbn,pageRequest);
        return page;
    }
    public List<Book> queryBook(Integer id,String name,String author,String isbn){
        List<Book> list =bookJPA.findByIdOrNameLikeOrAuthorOrIsbn(id,"%"+name+"%",author,isbn);
        return list;
    }
    public List<Book> queryBook(){
        return bookJPA.findAll();
    }
    /**
     * 模糊查询杂志
     * @param id
     * @param name
     * @param author
     * @param isbn
     * @param currentPage
     * @return
     */
    public Page<Magazine> queryMagazine(Integer id,String name,String author,String isbn,int currentPage){
        //排序
        Sort.Order idOrder = new Sort.Order(Sort.Direction.ASC, "id");
        Sort.Order usernameOrder = new Sort.Order(Sort.Direction.ASC,"name");
        Sort sort = new Sort(idOrder,usernameOrder);
        Pageable pageRequest  = new PageRequest(currentPage, pageSize, sort);
        Page<Magazine> page =magazineJPA.findByIdOrNameLikeOrAuthorOrIsbn(id,name,author,isbn,pageRequest);

        return page;
    }
    public List<Magazine> queryMagazine(Integer id,String name,String author,String isbn){
        List<Magazine> list =magazineJPA.findByIdOrNameLikeOrAuthorOrIsbn(id,"%"+name+"%",author,isbn);
        return list;
    }
    public List<Magazine> queryMagazine(){
        return magazineJPA.findAll();
    }
    /**
     * 新增杂志
     * @param magazine
     */
    @Transactional
    public void addMagazeine(Magazine magazine){
        magazineJPA.save(magazine);

    }

    /**
     * 新增book
     * @param book
     */
    @Transactional
    public void addBook(Book book){
        bookJPA.save(book);
    }

    /**
     *统一删除
     *@param  id
     */
    @Transactional
    public void deleteTitle(int id){
        titleJPA.delete(id);
    }

    /**
     * 修改书
     * @param book
     */
    @Transactional
    public void changeBook(Book book){
        bookJPA.saveAndFlush(book);

    }

    /**
     * 修改杂志
     * @param magazine
     */
    @Transactional
    public void changeMagazine(Magazine magazine){

        magazineJPA.saveAndFlush(magazine);
    }

    /**
     * 返回单条信息， 供修改时和change方法联合使用。
     * @param id
     * @return
     */
    public Book queryOneBook(int id){
        return bookJPA.findOne(id);
    }
    public Magazine queryOneMagazine(int id){
        return magazineJPA.findOne(id);
    }
}
