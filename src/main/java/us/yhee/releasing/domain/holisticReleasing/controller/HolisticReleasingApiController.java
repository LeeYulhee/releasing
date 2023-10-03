package us.yhee.releasing.domain.holisticReleasing.controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class HolisticReleasingApiController {

    @GetMapping("/api/start")
    public Map<String, Object> start(HttpSession session, @RequestParam String subject) {
        session.setAttribute("subject", subject);
        session.setAttribute("count", 0);
        session.setAttribute("mode", "resist");

        String question = String.format("나는 최대한 %s을 환영하도록 나를 허용할 수 있나요?", subject);
        Map<String, Object> response = new HashMap<>();
        response.put("question", question);
        response.put("count", 0);

        return response;
    }

    @GetMapping("/api/next")
    public Map<String, Object> next(HttpSession session) {

        String mode = (String) session.getAttribute("mode");
        String subject = (String) session.getAttribute("subject");
        int count = (int) session.getAttribute("count");

        String question;

        if(mode.equals("welcome")) {
            question = String.format("나는 최대한 %s을 환영하도록 나를 허용할 수 있나요?", subject);
        } else {
            question = String.format("나는 최대한 %s에 저항하도록 나를 허용할 수 있나요?", subject);
        }

        if ("welcome".equals(mode)) {
            session.setAttribute("count", ++count);
            session.setAttribute("mode", "resist");
        } else {
            session.setAttribute("mode", "welcome");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("question", question);
        response.put("count", count);

        return response;
    }
}
