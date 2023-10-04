package us.yhee.releasing.domain.hooponopono.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HooponoponoViewController {

    @GetMapping("/hooponopono")
    public String hooponopono() {
        return "hooponopono/hooponopono";
    }
}
