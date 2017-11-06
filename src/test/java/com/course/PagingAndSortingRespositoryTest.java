package com.course;

        import org.junit.Test;
        import org.junit.runner.RunWith;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.test.context.junit4.SpringRunner;


/**
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PagingAndSortingRespositoryTest {

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



}