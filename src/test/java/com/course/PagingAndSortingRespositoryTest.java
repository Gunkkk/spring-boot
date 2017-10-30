package com.course;

        import com.course.admin.entity.Borrower;
        import com.course.admin.repository.BorrowerPagingAndSortingRepository;
        import com.course.admin.service.OperateService;
        import org.junit.Test;
        import org.junit.runner.RunWith;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.context.ApplicationContext;
        import org.springframework.context.support.ClassPathXmlApplicationContext;
        import org.springframework.data.domain.Page;
        import org.springframework.data.domain.PageRequest;
        import org.springframework.data.domain.Sort;
        import org.springframework.data.domain.Sort.Direction;
        import org.springframework.data.domain.Sort.Order;
        import org.springframework.test.context.junit4.SpringRunner;

        import java.util.List;

/**
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PagingAndSortingRespositoryTest {


    @Autowired
    OperateService operateService;
//    @Test
//    public void testPage(){
//        //index 1 从0开始 不是从1开始的
//        Pageable page = new PageRequest(0,10);
//        Page<Borrower> employeeList = operateService.findAll(page);
//        System.out.println("查询总页数:"+employeeList.getTotalPages());
//        System.out.println("查询总记录数:"+employeeList.getTotalElements());
//        System.out.println("查询当前第几页:"+employeeList.getNumber()+1);
//        System.out.println("查询当前页面的集合:"+employeeList.getContent());
//        System.out.println("查询当前页面的记录数:"+employeeList.getNumberOfElements());
//    }

    @Test
    public void testFindAllPage(){
        int currentPage =0; //当前页从0 开始
        int pageSize = 5;

        //排序
        Order idOrder = new Order(Direction.DESC, "id");
        Order usernameOrder = new Order(Direction.ASC,"username");
        Sort sort = new Sort(idOrder,usernameOrder);
        PageRequest pageRequest  = new PageRequest(currentPage, pageSize, sort);

        Page<Borrower> page = operateService.findAll(pageRequest);
        System.out.println("总记录数:" + page.getTotalElements());
        System.out.println("总页数:" + page.getTotalPages());
        System.out.println("当前页（request):" + page.getNumber());
        System.out.println("当前页总记录数（request):" + page.getSize());
        System.out.println("当前页记录总数：" + page.getNumberOfElements());
        List<Borrower> borrowers = page.getContent();
        for (Borrower borrower : borrowers) {
            System.out.println(borrower);
        }
    }
}