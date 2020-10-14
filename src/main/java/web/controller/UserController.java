package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping(value = "/1")
    public void addN() {
        User user = new User("Алексй", " Навальный" , "qqqq@mail.ru");
        userService.addUser(user);
        System.out.println(user);

    }

    @GetMapping(value = "/users/")
    public ModelAndView printUsers() {
        List<User> listUsers = userService.getAllUsers();
        ModelAndView modelAndView = new ModelAndView("users/all");
        modelAndView.addObject("allUsers", listUsers);
        return modelAndView;
    }

    @GetMapping(value = "/users/save")
    public ModelAndView addUser(){
        ModelAndView modelAndView = new ModelAndView("redirect:/users/");
        modelAndView.addObject("saveUser", userService.addUser(new User()));
        return  modelAndView;
    }

    @GetMapping(value = "/users/update/{id}")
    public ModelAndView updateUser(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/users/");
        User user = userService.getById(id);
        modelAndView.addObject("updateUser", userService.editUser(user));
        return modelAndView;
    }

    @GetMapping(value = "/users/delete/{id}")
    public ModelAndView deleteUser(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/users/");
        User user = userService.getById(id);
        modelAndView.addObject("deleteUser", userService.deleteUser(user));
        return modelAndView;
    }

    @GetMapping(value = "/users/find/{id}")
    public ModelAndView getUser(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("users/find");
        modelAndView.addObject("getUser", userService.getById(id));
        return modelAndView;
    }


}
