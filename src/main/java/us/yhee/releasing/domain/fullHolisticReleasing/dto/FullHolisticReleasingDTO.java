package us.yhee.releasing.domain.fullHolisticReleasing.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FullHolisticReleasingDTO {

    private String fullHolisticQuestion;
    private int fullHolisticCount;
    private String fullHolisticSubject;

    public FullHolisticReleasingDTO(String fullHolisticQuestion, int fullHolisticCount) {
        this.fullHolisticQuestion = fullHolisticQuestion;
        this.fullHolisticCount = fullHolisticCount;
    }
}
