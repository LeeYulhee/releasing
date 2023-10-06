package us.yhee.releasing.domain.affirmation.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AffirmationApiController {

    @GetMapping("/affirmation/start")
    public Map<String, Object> startAffirmation(HttpSession session, @RequestParam String affirmationSubject) {
        session.setAttribute("affirmationSubject", affirmationSubject);
        session.setAttribute("affirmationCount", 0);

        Map<String, Object> reponse = new HashMap<>();
        reponse.put("affirmationSubject", affirmationSubject);
        reponse.put("affirmationCount", 0);

        return reponse;
    }

    @GetMapping("/affirmation/next")
    public Map<String, Object> nextAffirmation(HttpSession session) {
        String affirmationSubject = (String) session.getAttribute("affirmationSubject");
        Integer affirmationCount = (Integer) session.getAttribute("affirmationCount");

        if(affirmationSubject == null || affirmationCount == null) {
            return startAffirmation(session, null);
        }

        affirmationCount++;

        session.setAttribute("affirmationSubject", affirmationSubject);
        session.setAttribute("affirmationCount", affirmationCount);

        Map<String, Object> reponse = new HashMap<>();
        reponse.put("affirmationSubject", affirmationSubject);
        reponse.put("affirmationCount", affirmationCount);

        return reponse;
    }

    @GetMapping("/affirmation/reload")
    public ResponseEntity<Map<String, Object>> reloadAffirmation(HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        response.put("affirmationSubject", session.getAttribute("affirmationSubject"));
        response.put("affirmationCount", session.getAttribute("affirmationCount"));

        return ResponseEntity.ok(response);
    }
}
