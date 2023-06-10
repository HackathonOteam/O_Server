package neordinary.oteam;

// Necessary imports
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class AudioController {

    private final AudioService audioService;

    public AudioController(AudioService audioService) {
        this.audioService = audioService;
    }

    @PostMapping("/audio")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        return audioService.transcribeAudio(file);
    }
}

