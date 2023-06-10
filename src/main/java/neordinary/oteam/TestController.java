package neordinary.oteam;

import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/chat-gpt")
public class TestController {
    private final ChatService chatService;
    private final ChatgptService chatgptService;

    private final MyChatGPTService chatGPTService;

    String qreQuestion = "안녕, ChatGPT! 나 질문이 있어.";

    //chat-gpt 와 간단한 채팅 서비스 소스
    @PostMapping("")
    public String test(@RequestBody String question) {
        return chatService.getChatResponse(question);
        //\n\nAs an AI language model, I don't have feelings, but I'm functioning well. Thank you for asking. How can I assist you today?
    }

    @ResponseBody
    @PostMapping("/askChatGPT")
    public ResponseEntity<ChatGptRes> askToChatGPT(@RequestBody ChatGptRes chatGptReq){

        String resultQuestion = qreQuestion + chatGptReq.getAnswer();
        ChatGptRes chatGptRes = chatGPTService.getChatResponse(resultQuestion);

        return ResponseEntity.ok(chatGptRes);
    }
}