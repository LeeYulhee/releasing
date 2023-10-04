package us.yhee.releasing.domain.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String start() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home() {
        return "home/home";
    }
}
