package us.yhee.releasing.domain.body.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AcceptanceAsIsDTO {

    private String acceptScriptString;
    private int acceptScriptCount;
}
