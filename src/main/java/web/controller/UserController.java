package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public ModelAndView printUsers() {
        List<User> listUsers = userService.getAllUsers();
        ModelAndView modelAndView = new ModelAndView("users/all");
        modelAndView.addObject("allUsers", listUsers);
        return modelAndView;
    }

    @GetMapping(value = "/users/save")
    public ModelAndView addUser(){
        ModelAndView modelAndView = new ModelAndView("users/save");
        modelAndView.addObject("saveUser", userService.addUser(new User("Алексей","Навальный" ,"qqq@mail.ru")));
        return  modelAndView;
    }
}
