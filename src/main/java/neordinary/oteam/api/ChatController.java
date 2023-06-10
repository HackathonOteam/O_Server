package neordinary.oteam.api;

import neordinary.oteam.dto.chatGpt.ChatGptReq;
import neordinary.oteam.dto.chatGpt.ChatGptRes;
import neordinary.oteam.service.OpenAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChatController {

    private final OpenAIService openAIService;

    @Autowired
    public ChatController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @PostMapping("/api/chat")
    public ResponseEntity<ChatGptRes> chat(@RequestBody ChatGptReq chatGptReq) {
        return ResponseEntity.ok(openAIService.chat(chatGptReq));
    }
}

