package com.course.admin.controller;

import com.course.admin.entity.Graduate;
import com.course.admin.service.GraduateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class GraduateController {

    @Autowired
    GraduateService graduateService;

    @RequestMapping(value = "/addGraduate.action")
    public ModelAndView addGraduates(@RequestParam("userNameAdd")String username,
                                     @RequestParam("cardNoAdd")String cardNo,
                                     @RequestParam("passwordAdd")String password,
                                     @RequestParam("departmentAdd")String department,
                                     @RequestParam("majorAdd")String major,
                                     @RequestParam("directorAdd")String director
                                     ){
        Graduate graduate = new Graduate();

        graduate.setUsername(username);
        graduate.setCardNo(cardNo);
        graduate.setPassword(password);
        graduate.setType("graduate");
        graduate.setDepartment(department);
        graduate.setMajor(major);
        graduate.setDirector(director);

        ModelAndView modelAndView = new ModelAndView("redirect:/findAllGraduates.action");
        graduateService.save(graduate);
        return modelAndView;
    }

    @RequestMapping(value = "/deleteGraduate.action")
    public ModelAndView deleteGraduates(@RequestParam("id")String idString){
        ModelAndView modelAndView = new ModelAndView("redirect:/findAllGraduates.action");

        int id = Integer.parseInt(idString);
        graduateService.delete(id);
        return modelAndView;
    }

    @RequestMapping(value = "/updateGraduate.action")
    public ModelAndView updateGraduates(@RequestParam("idUpdate")int id,
                                        @RequestParam("userNameUpdate")String username,
                                        @RequestParam("cardNoUpdate")String cardNo,
                                        @RequestParam("passwordUpdate")String password,
                                        @RequestParam("typeUpdate")String type,
                                        @RequestParam("departmentUpdate")String department,
                                        @RequestParam("majorUpdate")String major,
                                        @RequestParam("directorUpdate")String director
                                        ){
        ModelAndView modelAndView = new ModelAndView("redirect:/findAllGraduates.action");

        graduateService.updateGraduateById(id,username,cardNo,password,type,department,major,director);
        return modelAndView;
    }

    @RequestMapping(value = "/findAllGraduates.action")
    public ModelAndView findAllGraduates(){
        ModelAndView modelAndView = new ModelAndView("GraduatesList");

        List<Graduate> GraduateList = graduateService.findAll();

        modelAndView.addObject("GraduateList",GraduateList);
        return modelAndView;
    }

    @RequestMapping(value = "/findGraduatesByConditions.action")
    public ModelAndView findGraduatesByConditions(@RequestParam("userNameSelect")String username,
                                                  @RequestParam("cardNoSelect")String cardNo,
                                                  @RequestParam("passwordSelect")String password,
                                                  @RequestParam("departmentSelect")String department,
                                                  @RequestParam("majorSelect")String major,
                                                  @RequestParam("directorSelect")String director
    ){
        ModelAndView modelAndView = new ModelAndView("GraduatesList");

        //如果没有以下语句，什么都查不出来！！！
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
        if(director=="")
        {
            director = null;
        }

        List<Graduate> GraduateListFound = graduateService.findGraduateByCondition(username,cardNo,password,department,major,director);
        modelAndView.addObject("GraduateList",GraduateListFound);
        modelAndView.addObject("username",username);
        modelAndView.addObject("cardNo",cardNo);
        modelAndView.addObject("password",password);
        modelAndView.addObject("department",department);
        modelAndView.addObject("major",major);
        modelAndView.addObject("director",director);
        return modelAndView;
    }

}
