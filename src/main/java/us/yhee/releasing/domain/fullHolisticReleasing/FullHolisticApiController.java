package us.yhee.releasing.domain.fullHolisticReleasing;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class FullHolisticApiController {

    private final String[] fullHoliList = {"나는 최대한 %s에 저항하도록 나를 허용할 수 있는가?", "나는 최대한 %s을 환영하도록 나를 허용할 수 있는가?",
                                            "나는 최대한 %s을 거절하도록 나를 허용할 수 있는가?", "나는 최대한 %s를 수용하도록 나를 허용할 수 있는가?",
                                            "나는 최대한 %s을 싫어하도록 나를 허용할 수 있는가?", "나는 최대한 %s을 좋아하도록 나를 허용할 수 있는가?",
                                            "나는 최대한 %s을 미워하도록 나를 허용할 수 있는가?", "나는 최대한 %s을 사랑하도록 나를 허용할 수 있는가?",
                                            "나는 최대한 %s에 \"아니요\"라고 말하도록 나를 허용할 수 있는가?", "나는 최대한 %s에 \"예\"라고 말하도록 나를 허용할 수 있는가?",
                                            "나는 최대한 %s에 마음을 열도록 나를 허용할 수 있는가?", "나는 최대한 %s에 마음을 닫도록 나를 허용할 수 있는가?"};

    @GetMapping("/fullholistic/start")
    public Map<String, Object> startFullHolistic(HttpSession session, @RequestParam String fullHolisticSubject) {

        String fullHolisticQuestion = String.format(fullHoliList[0], fullHolisticSubject);

        session.setAttribute("fullHolisticSubject", fullHolisticSubject);
        session.setAttribute("fullHolisticCount", 0);
        session.setAttribute("fullHolisticMode", 0);
        session.setAttribute("fullHolisticQuestion", fullHolisticQuestion);

        Map<String, Object> response = new HashMap<>();
        response.put("fullHolisticQuestion", fullHolisticQuestion);
        response.put("fullHolisticCount", 0);

        return response;
    }

    @GetMapping("/fullholistic/next")
    public Map<String, Object> nextFullHolistic(HttpSession session) {

        String fullHolisticSubject = (String) session.getAttribute("fullHolisticSubject");
        int fullHolisticMode = (int) session.getAttribute("fullHolisticMode");
        int fullHolisticCount = (int) session.getAttribute("fullHolisticCount");

        if(fullHolisticMode < 11) {
            fullHolisticMode++;
        } else {
            fullHolisticMode = 0;
            fullHolisticCount++;
        }

        String fullHolisticQuestion = String.format(fullHoliList[fullHolisticMode], fullHolisticSubject);

        session.setAttribute("fullHolisticSubject", fullHolisticSubject);
        session.setAttribute("fullHolisticCount", fullHolisticCount);
        session.setAttribute("fullHolisticMode", fullHolisticMode);
        session.setAttribute("fullHolisticQuestion", fullHolisticQuestion);

        Map<String, Object> response = new HashMap<>();
        response.put("fullHolisticQuestion", fullHolisticQuestion);
        response.put("fullHolisticCount", fullHolisticCount);

        return response;
    }

    @GetMapping("/fullholistic/reload")
    public ResponseEntity<Map<String, Object>> reloadHolistic(HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        response.put("fullHolisticSubject", session.getAttribute("fullHolisticSubject"));
        response.put("fullHolisticQuestion", session.getAttribute("fullHolisticQuestion"));
        response.put("fullHolisticCount", session.getAttribute("fullHolisticCount"));

        return ResponseEntity.ok(response);
    }
}
