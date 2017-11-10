package com.course.admin.controller;

import com.course.login.entity.User;
import com.course.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/findAllUser.action")
    public ModelAndView findAllUndergraduates(){
        ModelAndView modelAndView = new ModelAndView();

        List<User> UserList = userService.findAll();

        modelAndView.addObject("UserList",UserList);
        modelAndView.setViewName("UsersList");
        return modelAndView;
    }

    @RequestMapping(value = "/deleteUser.action")
    public ModelAndView deleteGraduates(@RequestParam("id")String idString){
        ModelAndView modelAndView = new ModelAndView("redirect:/findAllUser.action");

        int id = Integer.parseInt(idString);
        userService.delete(id);
        return modelAndView;
    }

    @RequestMapping(value = "/addUser.action")
    public ModelAndView addGraduates(@RequestParam("userNameAdd")String username,
                                     @RequestParam("passwordAdd")String password
    ){
        User user = new User();

        user.setUsername(username);
        user.setPassword(password);

        ModelAndView modelAndView = new ModelAndView("redirect:/findAllUser.action");
        userService.save(user);
        return modelAndView;
    }
}
