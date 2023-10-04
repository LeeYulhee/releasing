package us.yhee.releasing.domain.hooponopono.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HooponoponoApiController {

    private final String[] hoopoWords = {"사랑합니다", "감사합니다", "미안합니다", "용서하세요"};

    @GetMapping("/hooponopono/start")
    public Map<String, Object> startHooponopono(HttpSession session) {

        session.setAttribute("hoopoCount", 0);
        session.setAttribute("hoopoMode", 0);

        Map<String, Object> response = new HashMap<>();
        response.put("hoopoWord", hoopoWords[0]);
        response.put("hoopoCount", 0);

        return response;
    }

    @GetMapping("/hooponopono/next")
    public Map<String, Object> nextHooponopono(HttpSession session) {

        int hoopoMode = (int) session.getAttribute("hoopoMode");
        int hoopoCount = (int) session.getAttribute("hoopoCount");
        
        if (hoopoMode < 3) {
            hoopoMode++;
        } else {
            hoopoMode = 0;
            hoopoCount++;
        }

        session.setAttribute("hoopoMode", hoopoMode);
        session.setAttribute("hoopoCount", hoopoCount);

        Map<String, Object> response = new HashMap<>();
        response.put("hoopoWord", hoopoWords[hoopoMode]);
        response.put("hoopoCount", hoopoCount);

        return response;
    }

    @GetMapping("/hooponopono/init")
    public Map<String, Object> initOrContinue(HttpSession session) {
        Integer hoopoMode = (Integer) session.getAttribute("hoopoMode");
        Integer hoopoCount = (Integer) session.getAttribute("hoopoCount");

        if(hoopoMode == null || hoopoCount == null) {
            return startHooponopono(session);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("hoopoWord", hoopoWords[hoopoMode]);
            response.put("hoopoCount", hoopoCount);

            return response;
        }
    }
}
