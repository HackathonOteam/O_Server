package neordinary.oteam.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import neordinary.oteam.dto.chatGpt.ChatGptReq;
import neordinary.oteam.dto.chatGpt.ChatGptRes;
import neordinary.oteam.dto.record.*;
import neordinary.oteam.service.OpenAIService;
import neordinary.oteam.service.RecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;

    private final OpenAIService openAIService;

    // 다이어리 레코드 생성
    @PostMapping("/api/record/diary")
    public ResponseEntity<DiaryRecordRes> addRecordDiary(@RequestBody DiaryRecordReq req) {
        return ResponseEntity.ok(recordService.addTodayDiary(req.getName()));
    }

    // 레코드 하나 생성
    @PostMapping("/api/record")
    public ResponseEntity<ChatGptRes> addRecord(@RequestBody RecordReq req) {

        recordService.addOneRecord(req.getName(), req.getContent());

        ChatGptRes chatGptRes = openAIService.chat(new ChatGptReq(req.getContent()));
        return ResponseEntity.ok(chatGptRes);
    }

    // 요약 생성
    @PostMapping("/api/record/summary")
    public ResponseEntity<SummaryRes> getSummary(@RequestBody SummaryReq req) {
        return ResponseEntity.ok(recordService.getRecordSummary(req.getName()));
    }

}
