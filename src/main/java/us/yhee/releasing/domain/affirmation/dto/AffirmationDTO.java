package us.yhee.releasing.domain.affirmation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AffirmationDTO {
    private String affirmationSubject;
    private int affirmationCount;
}
