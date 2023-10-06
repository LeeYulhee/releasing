package us.yhee.releasing.domain.affirmation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AffirmationViewController {

    @GetMapping("/affirmation")
    public String affirmation() {
        return "affirmation/affirmation";
    }
}
