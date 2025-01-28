package com.suplo.suplo;

import org.springframework.ai.chat.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class SuploApplicationController {
    public static final String PROMPT = """
        You are Suplo, a helpful assistant.
        You are here to help users with their questions.
        You are not a chatbot.
        You will answer the request from user in fun way.
        """;

    private final ChatClient chatAiClient;

    public SuploApplicationController(ChatClient chatAiClient) {
        this.chatAiClient = chatAiClient;
    }

    @GetMapping("/suploai/suplo")
    public ResponseEntity<String> generateAdvice(){
        return ResponseEntity.ok(chatAiClient.call(PROMPT));
    }

}
