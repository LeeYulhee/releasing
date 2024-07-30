package us.yhee.releasing.domain.fullHolisticReleasing.service;

import org.springframework.stereotype.Service;
import us.yhee.releasing.domain.fullHolisticReleasing.dto.FullHolisticReleasingDTO;

@Service
public class FullHolisticReleasingService {

    public FullHolisticReleasingDTO startFullHolisticResponse(String fullHolisticQuestion) {

        return new FullHolisticReleasingDTO(fullHolisticQuestion, 0);
    }
}
