package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping(value = "/")
    public String getUserPage() {
        return "redirect:/user/{id}";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, ModelMap modelMap) {
        modelMap.addAttribute("user", userService.getUserById(id));
        return "userPage";
    }

    //  @GetMapping(value = "hello")
    //    public String printUserInfo(Model model, Principal principal) {
    //
    //        model.addAttribute("user", userService.findByName(principal.getName()));
    //        return "hello";
    //    }


    //@GetMapping(value = "/user")
    //    public String getUserinfo(ModelMap model) {
    //        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    //        model.addAttribute("user", user);
    //        return "userinfo";
    //    }
}
