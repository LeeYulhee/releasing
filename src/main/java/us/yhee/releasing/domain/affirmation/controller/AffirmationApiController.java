package us.yhee.releasing.domain.affirmation.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import us.yhee.releasing.domain.affirmation.dto.AffirmationDTO;
import us.yhee.releasing.domain.affirmation.service.AffirmationService;

@RestController
@RequiredArgsConstructor
public class AffirmationApiController {

    private final AffirmationService affirmationService;

    @GetMapping("/affirmation/start")
    public AffirmationDTO startAffirmation(HttpSession session, @RequestParam String affirmationSubject) {
        session.setAttribute("affirmationSubject", affirmationSubject);
        session.setAttribute("affirmationCount", 0);

        return affirmationService.startAffirmationResponse(affirmationSubject);
    }

    @GetMapping("/affirmation/next")
    public AffirmationDTO nextAffirmation(HttpSession session) {
        String affirmationSubject = (String) session.getAttribute("affirmationSubject");
        Integer affirmationCount = (Integer) session.getAttribute("affirmationCount");

        if(affirmationSubject == null || affirmationCount == null || affirmationSubject.equals("")) {
            return startAffirmation(session, null);
        }

        AffirmationDTO reponse = affirmationService.updatedCount(affirmationSubject, affirmationCount);

        session.setAttribute("affirmationSubject", reponse.getAffirmationSubject());
        session.setAttribute("affirmationCount", reponse.getAffirmationCount());

        return reponse;
    }

    @GetMapping("/affirmation/reload")
    public ResponseEntity<AffirmationDTO> reloadAffirmation(HttpSession session) {
        AffirmationDTO response = new AffirmationDTO((String)session.getAttribute("affirmationSubject"), (int)session.getAttribute("affirmationCount"));

        return ResponseEntity.ok(response);
    }
}
