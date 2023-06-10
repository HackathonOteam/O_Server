package neordinary.oteam.service;

import lombok.extern.slf4j.Slf4j;
import neordinary.oteam.dto.chatGpt.ChatGptReq;
import neordinary.oteam.dto.chatGpt.ChatGptRes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Service
@Slf4j
public class OpenAIService {
    private final String OPENAI_URL = "https://api.openai.com/v1/chat/completions";

    @Value("${openaiApiKey}")
    private String OPENAI_API_KEY;

    public ChatGptRes chat(ChatGptReq chatGptReq) {
        RestTemplate restTemplate = new RestTemplate();

        // Setup headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + OPENAI_API_KEY);

        log.info("token ::::::::::::::::: " + OPENAI_API_KEY);
        log.info("header :::::::::::: " + String.valueOf(headers));

        // Prepare the system and user messages
        List<Map<String, String>> messages = new ArrayList<>();
        Map<String, String> systemMessage = new HashMap<>();
        systemMessage.put("role", "system");
        systemMessage.put("content", "You are a helpful assistant.");
        messages.add(systemMessage);
        Map<String, String> userMessageMap = new HashMap<>();
        userMessageMap.put("role", "user");
        userMessageMap.put("content", chatGptReq.getContent());
        messages.add(userMessageMap);

        // Create the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-3.5-turbo");
        requestBody.put("messages", messages);

        // Create the HTTP request
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        // Send the request and get the response
        ResponseEntity<Map> response = restTemplate.postForEntity(OPENAI_URL, entity, Map.class);

        // Extract the message from the response
        Map<String, Object> choice = (Map<String, Object>) ((List<Object>)response.getBody().get("choices")).get(0);
        Map<String, String> message = (Map<String, String>) choice.get("message");
        String completion = message.get("content");

        return new ChatGptRes(completion);
    }

}

