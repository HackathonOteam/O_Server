package neordinary.oteam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ChatController {

    private final OpenAIService openAIService;

    @Autowired
    public ChatController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @PostMapping("/chat")
    public ResponseEntity<ChatGptRes> chat(@RequestBody ChatGptReq chatGptReq) {
        return ResponseEntity.ok(openAIService.chat(chatGptReq));
    }
}

