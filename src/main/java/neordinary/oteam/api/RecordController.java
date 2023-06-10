package neordinary.oteam.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import neordinary.oteam.dto.chatGpt.ChatGptReq;
import neordinary.oteam.dto.chatGpt.ChatGptRes;
import neordinary.oteam.dto.record.*;
import neordinary.oteam.service.OpenAIService;
import neordinary.oteam.service.RecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

        ChatGptRes chatGptRes = openAIService.chat(new ChatGptReq(req.getContent()));

        recordService.addOneRecord(req.getName(), req.getContent(), chatGptRes.getAnswer());

        return ResponseEntity.ok(chatGptRes);
    }

    // 하루 레코드 요약 생성
    @PostMapping("/api/record/summary")
    public ResponseEntity<SummaryRes> getSummary(@RequestBody SummaryReq req) {
        return ResponseEntity.ok(recordService.getRecordSummary(req.getName()));
    }

    // 하루 레코드, 답변 목록 조회
    @GetMapping("api/record/list")
    public ResponseEntity<List<RecordListRes>> getRecordList(@RequestParam String name) {
        return ResponseEntity.ok(recordService.findRecordList(name));
    }


}