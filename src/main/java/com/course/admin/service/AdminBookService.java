package com.course.admin.service;

import com.course.borrower.repository.BookJPA;
import com.course.borrower.repository.MagazineJPA;
import com.course.borrower.repository.TitleJPA;
import com.course.borrower.entity.Book;
import com.course.borrower.entity.Magazine;
import com.course.borrower.entity.Title;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * 多条件模糊查询
     * @param name
     * @param author
     * @param isbn
     * @return
     */
    public List<Book> queryBook(int id,String name,String author,String isbn){
        List<Book> list =bookJPA.findByIdOrNameLikeOrAuthorOrIsbn(id,name,author,isbn);
        return list;
    }

    /**
     * 多条件模糊查询
     * @param name
     * @param author
     * @param isbn
     * @return
     */
    public List<Magazine> queryMagazine(int id,String name,String author,String isbn){
        List<Magazine> list =magazineJPA.findByIdOrNameLikeOrAuthorOrIsbn(id,name,author,isbn);

        return list;
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
