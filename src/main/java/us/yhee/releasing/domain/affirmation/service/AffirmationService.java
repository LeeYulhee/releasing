package us.yhee.releasing.domain.affirmation.service;

import org.springframework.stereotype.Service;
import us.yhee.releasing.domain.affirmation.dto.AffirmationDTO;

@Service
public class AffirmationService {

    public AffirmationDTO startAffirmationResponse(String affirmationSubject) {

        return new AffirmationDTO(affirmationSubject, 0);
    }

    public AffirmationDTO updatedCount(String affirmationSubject, Integer affirmationCount) {

        int updatedCount = affirmationCount + 1;
        return new AffirmationDTO(affirmationSubject, updatedCount);
    }
}
