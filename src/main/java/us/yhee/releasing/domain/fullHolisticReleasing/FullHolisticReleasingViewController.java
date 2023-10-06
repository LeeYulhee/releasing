package us.yhee.releasing.domain.fullHolisticReleasing;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FullHolisticReleasingViewController {

    @GetMapping("/releasing/fullholistic")
    public String fullHolisticReleasing() {
        return "fullHolisticReleasing/fullHolisticReleasing";
    }
}
