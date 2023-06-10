package neordinary.oteam.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import neordinary.oteam.domain.record.RecordRepository;
import neordinary.oteam.dto.record.DiaryRecordRes;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;

    // 다이어리 레코드 생성
    @Transactional
    public DiaryRecordRes addTodayDiary(String name) {
        // name -> id
        Long userId = recordRepository.findUsername(name);

        LocalDate today = LocalDate.now();

        if (recordRepository.findUserTodayDiary(userId, today) == 0) { // 아직 오늘 다이어리 레코드 생성 안 됐을 때만 레코드 생성
            LocalDateTime createdAt = LocalDateTime.now();
            recordRepository.addUserTodayDiary(createdAt, userId);
        }

        return new DiaryRecordRes(recordRepository.findUserTodayDiary(userId, today));
    }

    // 레코드 하나 생성
    @Transactional
    public void addOneRecord(String name, String contents) {
        Long userId = recordRepository.findUsername(name);
        Long diaryId = recordRepository.findDiaryId(userId);

        LocalDateTime createdAt = LocalDateTime.now();

        recordRepository.addUserRecord(createdAt, contents, diaryId);
    }
}
