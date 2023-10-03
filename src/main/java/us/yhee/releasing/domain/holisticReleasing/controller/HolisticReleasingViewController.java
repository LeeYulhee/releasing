package us.yhee.releasing.domain.holisticReleasing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HolisticReleasingViewController {

    @GetMapping("/releasing/holistic")
    public String holisticReleasing() {
        return "holisticReleasing/holisticReleasing";
    }
}
