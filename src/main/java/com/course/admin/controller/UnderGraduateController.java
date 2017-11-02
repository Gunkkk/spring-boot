package com.course.admin.controller;

import com.course.admin.entity.Undergraduate;
import com.course.admin.service.UndergraduateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class UnderGraduateController {
    @Autowired
    UndergraduateService undergraduateService;

    @RequestMapping(value = "/addUndergraduate.action")
    public ModelAndView addUndergraduate(@RequestParam("userNameAdd")String username,
                                         @RequestParam("cardNoAdd")String cardNo,
                                         @RequestParam("passwordAdd")String password,
                                         @RequestParam("departmentAdd")String department,
                                         @RequestParam("majorAdd")String major
    ){
        Undergraduate undergraduate = new Undergraduate();

        undergraduate.setUsername(username);
        undergraduate.setCardNo(cardNo);
        undergraduate.setPassword(password);
        undergraduate.setType("undergraduate");
        undergraduate.setDepartment(department);
        undergraduate.setMajor(major);

        ModelAndView modelAndView = new ModelAndView();
        undergraduateService.save(undergraduate);
        modelAndView.setViewName("redirect:/findAllUndergraduates.action");
        return modelAndView;
    }

    @RequestMapping(value = "/deleteUndergraduate.action")
    public ModelAndView deleteUndergraduate(@RequestParam("id")int id){
        ModelAndView modelAndView = new ModelAndView();

        undergraduateService.delete(id);
        modelAndView.setViewName("redirect:/findAllUndergraduates.action");
        return modelAndView;
    }

    @RequestMapping(value = "/updateUndergraduate.action")
    public ModelAndView updateUndergraduate(@RequestParam("id")int id,
                                        @RequestParam("usernameUpdate")String username,
                                        @RequestParam("cardNoUpdate")String cardNo,
                                        @RequestParam("passwordUpdate")String password,
                                        @RequestParam("typeUpdate")String type,
                                        @RequestParam("departmentUpdate")String department,
                                        @RequestParam("majorUpdate")String major
    ){
        ModelAndView modelAndView = new ModelAndView();

        undergraduateService.updateGraduateById(username,cardNo,password,type,department,major,id);
        modelAndView.setViewName("redirect:/findAllUndergraduates.action");
        return modelAndView;
    }

    @RequestMapping(value = "/findAllUndergraduates.action")
    public ModelAndView findAllUndergraduates(){
        ModelAndView modelAndView = new ModelAndView();

        List<Undergraduate> UndergraduateList = undergraduateService.findAll();

        modelAndView.addObject("UndergraduateList",UndergraduateList);
        modelAndView.setViewName("UndergraduatesList");
        return modelAndView;
    }

    @RequestMapping(value = "/findUndergraduatesByConditions.action")
    public ModelAndView findUndergraduatesByConditions(@RequestParam("userNameSelect")String username,
                                                      @RequestParam("cardNoSelect")String cardNo,
                                                      @RequestParam("passwordSelect")String password,
                                                      @RequestParam("departmentSelect")String department,
                                                      @RequestParam("majorSelect")String major
    ){
        ModelAndView modelAndView = new ModelAndView();
        if(username=="")
        {
            username = null;
        }
        if(cardNo=="")
        {
            cardNo = null;
        }
        if(password=="")
        {
            password = null;
        }
        if(department=="")
        {
            department = null;
        }
        if(major=="")
        {
            major = null;
        }
        List<Undergraduate> UndergraduateList = undergraduateService.findUndergraduateByCondition(username,cardNo,password,department,major);


        modelAndView.addObject("UndergraduateList",UndergraduateList);
        modelAndView.addObject("username",username);
        modelAndView.addObject("cardNo",cardNo);
        modelAndView.addObject("password",password);
        modelAndView.addObject("department",department);
        modelAndView.addObject("major",major);
        modelAndView.setViewName("UndergraduatesList");
        return modelAndView;
    }

}
