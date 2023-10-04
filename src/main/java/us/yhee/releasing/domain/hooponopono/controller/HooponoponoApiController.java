package us.yhee.releasing.domain.hooponopono.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HooponoponoApiController {

    private final String[] hooponoponoWords = {"사랑합니다", "감사합니다", "미안합니다", "용서하세요"};

    @GetMapping("/hooponopono/start")
    public Map<String, Object> startHooponopono(HttpSession session) {

        session.setAttribute("count", 0);
        session.setAttribute("mode", 0);

        Map<String, Object> response = new HashMap<>();
        response.put("word", hooponoponoWords[0]);
        response.put("count", 0);

        return response;
    }

    @GetMapping("/hooponopono/next")
    public Map<String, Object> nextHooponopono(HttpSession session) {

        int mode = (int) session.getAttribute("mode");
        int count = (int) session.getAttribute("count");

//        if(mode == 3) {
//            mode = 0;
//            session.setAttribute("count", ++count);
//            session.setAttribute("mode", mode);
//        } else {
//            session.setAttribute("mode", ++mode);
//        }
        if (mode < 3) {
            mode++;
        } else {
            mode = 0;
            count++;
        }

        session.setAttribute("mode", mode);
        session.setAttribute("count", count);

        Map<String, Object> response = new HashMap<>();
        response.put("word", hooponoponoWords[mode]);
        response.put("count", count);

        return response;
    }
}
