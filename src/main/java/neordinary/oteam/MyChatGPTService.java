package neordinary.oteam;

import io.github.flashvayne.chatgpt.service.ChatgptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyChatGPTService {
    private ChatgptService chatgptService;

    @Autowired
    public MyChatGPTService(ChatgptService chatgptService) {
        this.chatgptService = chatgptService;
    }

    public ChatGptRes getChatResponse(String prompt) {
        // ChatGPT 에게 질문을 던집니다.
        String responseMessage = chatgptService.sendMessage(prompt);
        return new ChatGptRes(responseMessage);
    }
}