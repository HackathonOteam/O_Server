package neordinary.oteam.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import neordinary.oteam.dto.record.DiaryRecordReq;
import neordinary.oteam.dto.record.DiaryRecordRes;
import neordinary.oteam.dto.record.RecordRes;
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

    // 다이어리 레코드 생성
    @PostMapping("/api/record/diary")
    public ResponseEntity<DiaryRecordRes> addRecordDiary(@RequestBody DiaryRecordReq req) {
        return ResponseEntity.ok(recordService.addTodayDiary(req.getName()));
    }

    // 레코드 하나 생성
    @PostMapping("/api/record")
    public ResponseEntity<RecordRes>

}
