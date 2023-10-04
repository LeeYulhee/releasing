package us.yhee.releasing.domain.holisticReleasing.controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class HolisticReleasingApiController {

    @GetMapping("/api/start")
    public Map<String, Object> start(HttpSession session, @RequestParam String holisticSubject) {

        String holisticQuestion = String.format("나는 최대한 %s을 환영하도록 나를 허용할 수 있나요?", holisticSubject);

        session.setAttribute("holisticSubject", holisticSubject);
        session.setAttribute("holisticCount", 0);
        session.setAttribute("holisticMode", "resist");
        session.setAttribute("holisticQuestion", holisticQuestion);

        Map<String, Object> response = new HashMap<>();
        response.put("holisticQuestion", holisticQuestion);
        response.put("holisticCount", 0);

        return response;
    }

    @GetMapping("/api/next")
    public Map<String, Object> next(HttpSession session) {

        String holisticMode = (String) session.getAttribute("holisticMode");
        String holisticSubject = (String) session.getAttribute("holisticSubject");
        int holisticCount = (int) session.getAttribute("holisticCount");

        String holisticQuestion;

        if(holisticMode.equals("welcome")) {
            holisticQuestion = String.format("나는 최대한 %s을 환영하도록 나를 허용할 수 있나요?", holisticSubject);
            session.setAttribute("holisticCount", ++holisticCount);
            session.setAttribute("holisticMode", "resist");
        } else {
            holisticQuestion = String.format("나는 최대한 %s에 저항하도록 나를 허용할 수 있나요?", holisticSubject);
            session.setAttribute("holisticMode", "welcome");
        }

        session.setAttribute("holisticQuestion", holisticQuestion);

        Map<String, Object> response = new HashMap<>();
        response.put("holisticQuestion", holisticQuestion);
        response.put("holisticCount", holisticCount);

        return response;
    }

    @GetMapping("/api/reload")
    public ResponseEntity<Map<String, Object>> reload(HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        response.put("holisticSubject", session.getAttribute("holisticSubject"));
        response.put("holisticQuestion", session.getAttribute("holisticQuestion"));
        response.put("holisticCount", session.getAttribute("holisticCount"));

        return ResponseEntity.ok(response);
    }
}
