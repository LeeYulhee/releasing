package us.yhee.releasing.domain.body.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AcceptanceAsIsViewController {

    @GetMapping("/accept")
    public String acceptanceAsIsScript() {
        return "acceptanceAsIsScript/acceptanceAsIsScript";
    }
}
