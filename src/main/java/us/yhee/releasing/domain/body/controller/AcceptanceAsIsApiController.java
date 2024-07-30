package us.yhee.releasing.domain.body.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import us.yhee.releasing.domain.body.dto.AcceptanceAsIsDTO;
import us.yhee.releasing.domain.body.service.AcceptanceAsIsService;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AcceptanceAsIsApiController {

    private final AcceptanceAsIsService acceptanceAsIsService;

    private final String[] acceptScript = {"당신의 몸이 지금 방식 그대로 존재하는 것을 환영할 수 있나요?",
                                            "당신의 몸이 지금 방식 그대로 존재하는 것을 환영하고 허용하는 느낌 속으로 더욱 이완해 들어갈 수 있나요?",
                                            "이제, 좀 더 그렇게 할 수 있나요?",
                                            "자신의 몸을 보는 방식 혹은 몸에 대한 느낌 중 당신이 저항하는 것들이 있나요?",
                                            "지금만이라도 저항하는 것을 흘려보내고, 있는 그대로의 모습 자체를 허용할 수 있나요?",
                                            "그렇다면 당신은 몸을 있는 그대로 수용할 수 있나요?",
                                            "편안하게 이완하며 그것을 환영하고 허용하는 느낌 속으로 들어갈 수 있나요?",
                                            "그리고 더욱더?",
                                            "그리고 여전히 더?"};

    @GetMapping("/accept/start")
    public AcceptanceAsIsDTO startAcceptAsIsScript(HttpSession session) {

        // TODO start 버튼이 눌리지 않아서 session에 값이 세팅되지 않음

        String acceptScriptString = acceptScript[0];

        session.setAttribute("acceptScriptCount", 0);
        session.setAttribute("acceptScriptMode", 0);
        session.setAttribute("acceptScriptString", acceptScriptString);

        return acceptanceAsIsService.startAcceptAsIsResponse(acceptScriptString);
    }

    @GetMapping("/accept/next")
    public Map<String, Object> nextAcceptAsIsScript(HttpSession session) {

        int acceptScriptMode = (int) session.getAttribute("acceptScriptMode");
        int acceptScriptCount = (int) session.getAttribute("acceptScriptCount");

        if(acceptScriptMode < acceptScript.length - 1) {
            acceptScriptMode++;
        } else {
            acceptScriptMode = 0;
            acceptScriptCount++;
        }

        String acceptScriptString = acceptScript[acceptScriptMode];

        session.setAttribute("acceptScriptCount", acceptScriptCount);
        session.setAttribute("acceptScriptMode", acceptScriptMode);
        session.setAttribute("acceptScriptString", acceptScriptString);

        Map<String, Object> response = new HashMap<>();
        response.put("acceptScriptString", acceptScriptString);
        response.put("acceptScriptCount", acceptScriptCount);

        return response;
    }

    @GetMapping("/accept/init")
    public AcceptanceAsIsDTO reloadAcceptAsIsScript(HttpSession session) {

        Integer acceptScriptMode = (Integer) session.getAttribute("acceptScriptMode");
        Integer acceptScriptCount = (Integer) session.getAttribute("acceptScriptCount");
        String acceptScriptString = (String) session.getAttribute("acceptScriptString");

        if (acceptScriptMode == null || acceptScriptCount == null) {
            return startAcceptAsIsScript(session);
        }

        return acceptanceAsIsService.startAcceptAsIsResponse(acceptScriptString, acceptScriptCount);
    }
}