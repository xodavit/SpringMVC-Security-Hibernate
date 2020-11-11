package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RootController {
    @GetMapping(value = "/")
    public String getHomePage(Model model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        messages.add("I'm Spring MVC-SECURITY application");
        messages.add("This is Root Page");
        model.addAttribute("messages", messages);
        return "helloPage";
    }

    @GetMapping(value = "login")
    public String getLoginPage() {
        return "loginPage";
    }
}
