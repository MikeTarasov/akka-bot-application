package com.example.akka.api;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ApiController {

    @GetMapping("/ask")
    public ResponseEntity<String> getAsk(@RequestParam(name = "question") String question) {
        return ResponseEntity.ok("вы задали '" + question + "', но наши программисты все еще работают над этим");
    }
}
