package com.course;

import com.course.admin.service.AdminBookService;
import com.course.borrower.entity.Book;
import com.course.borrower.repository.BookJPA;
import com.course.libraryAdmin.service.ItemAdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SystemApplicationTests {
	@Autowired
	AdminBookService adminBookService;
	@Test
	public void contextLoads() {

	}

/*	@Test
	public void addbook(){
		Book book =new Book();
		book.setAuthor("aa");
		book.setPress("few");
		book.setPublishDate(new Date(2017/9/9));
		book.setVersion("ef");
		book.setIsbn("fwefwfewf");
		book.setName("sef");
		book.setType("书籍");
		adminBookService.addBook(book);

	}*/
	@Test
	public void testUpdate () {
		Book book = new Book();
		book.setId(1);
		book.setName("fwuhfe");
		adminBookService.changeBook(book);

	}
	@Autowired
	ItemAdminService itemAdminService;
	@Test
	public void addItem(){
		itemAdminService.addItem("xxxx11",4);

	}
	@Test
	public void deleteItem(){
		itemAdminService.deleteItem(4,4);
	}

	@Test
	public void addLoan(){
		itemAdminService.loanBook("1","xxxx11");
	}
}
