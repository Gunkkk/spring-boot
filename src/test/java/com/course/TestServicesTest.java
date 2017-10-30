package com.course;



import com.course.admin.entity.*;
import com.course.admin.service.OperateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestServicesTest {

    @Autowired
    OperateService operateService;

    @Test
    public void testSave() {
        Graduate graduate = new Graduate();
        graduate.setId(1);
        graduate.setCardNo("001");
        graduate.setDirector("Di.Wang");
        graduate.setMajor("CS");
        graduate.setDepartment("Dp.CS");
        graduate.setType("graduate");
        graduate.setUsername("Yanyufeng");
        graduate.setPassword("123456");

        operateService.save(graduate);

    }

    @Test
    public void testDelete() {
        operateService.delete(1);
    }

    @Test
    public void testFind() {
//        operateService.findById(1);
//        operateService.findByUsername("Yanyufeng");
//        operateService.findByCondition("YYF","011","graduate");
          operateService.findAll();
    }

    @Test
    public void testUpdate() {
        operateService.updateBorrowerById("YYF","011","123456","graduate","Dp.CS",2);
        operateService.findByUsername("YYF");
    }

}