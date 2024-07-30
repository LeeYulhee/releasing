package us.yhee.releasing.domain.body.service;

import org.springframework.stereotype.Service;
import us.yhee.releasing.domain.body.dto.AcceptanceAsIsDTO;

@Service
public class AcceptanceAsIsService {

    public AcceptanceAsIsDTO startAcceptAsIsResponse(String AcceptScriptString) {

        return new AcceptanceAsIsDTO(AcceptScriptString, 0);
    }

    public AcceptanceAsIsDTO startAcceptAsIsResponse(String AcceptScriptString, int acceptScriptCount) {

        return new AcceptanceAsIsDTO(AcceptScriptString, acceptScriptCount);
    }
}
