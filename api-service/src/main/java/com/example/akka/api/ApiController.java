package com.example.akka.api;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ApiController {

    @GetMapping("/ask")
    public ResponseEntity<String> getAsk(@RequestParam(name = "question") String question) {
        String answer = ApiStorage.findAnswer(question);
        if (answer != null) {
            return ResponseEntity.ok(answer);
        }
        return ResponseEntity.ok("вы задали '" + question + "', я пока не знаю на него ответ," +
                " но наши программисты все еще работают над этим");
    }
}
