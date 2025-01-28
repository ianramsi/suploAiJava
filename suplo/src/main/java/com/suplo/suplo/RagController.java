package com.suplo.suplo;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.prompt.Prompt;
import org.springframework.ai.prompt.PromptTemplate;
import org.springframework.ai.prompt.SystemPromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class RagController {
    private final ChatClient chatAiClient;
    private final VectorStore vectorStore;

    public RagController(ChatClient chatAiClient, VectorStore vectorStore) {
        this.chatAiClient = chatAiClient;
        this.vectorStore = vectorStore;
    }

    @GetMapping("/suploai/rag")
    public ResponseEntity<String> generateAnswer(@RequestParam String query) {
        List<Document> similarDocument = vectorStore.similaritySearch(query);
        String information = similarDocument.stream().map(Document::getContent)
                                .collect(Collectors.joining(System.lineSeparator()));
        var SystemPromptTemplate = new SystemPromptTemplate(
            """
                            You are a Suplo, helpful assistant.
                            Use only the following information to answer the question.
                            Do not use any other information. If you do not know, simply answer: Unknown.

                            {information}
                    
                    """);
        
        var systemMessage = systemPromptTemplate.createMessage(Map.of("information", information));
        var userPromptTemplate = new PromptTemplate("{query}");
        var userMessage = userPromptTemplate.createMessage(Map.of("query", query));
        var prompt = new Prompt(List.of(systemMessage, userMessage));

        return ResponseEntity.ok(chatAiClient.call(prompt).getResult().getOutput().getContent());

    }

}