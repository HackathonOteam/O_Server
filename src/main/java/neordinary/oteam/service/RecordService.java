package neordinary.oteam.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import neordinary.oteam.domain.record.RecordRepository;
import neordinary.oteam.dto.chatGpt.ChatGptReq;
import neordinary.oteam.dto.chatGpt.ChatGptRes;
import neordinary.oteam.dto.record.DiaryRecordRes;
import neordinary.oteam.dto.record.RecordListRes;
import neordinary.oteam.dto.record.SummaryRes;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;

    private final OpenAIService openAIService;

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
    public void addOneRecord(String name, String contents, String answer) {
        Long userId = recordRepository.findUsername(name);
        Long diaryId = recordRepository.findDiaryId(userId);

        LocalDateTime createdAt = LocalDateTime.now();

        recordRepository.addUserRecord(createdAt, contents, answer, diaryId);
    }

    // 요약 생성
    @Transactional
    public SummaryRes getRecordSummary(String name) {
        Long userId = recordRepository.findUsername(name);
        Long diaryId = recordRepository.findDiaryId(userId);

        // name 으로 오늘에 해당하는 record 값 다 가져와서 contents 합치기
        LocalDate today = LocalDate.now();
        List<String> list = recordRepository.findTodayContents(diaryId, today);
        String todayContents = String.join(" ", list);
        log.info("todayContents ::::::::::::::::: " + todayContents);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        String formattedDate = today.format(formatter);

        // 글자수 세고, 제한값 설정
        Integer contentsLength = todayContents.length();
        Integer summaryMax;
        if (contentsLength < 50) {
            summaryMax = 30;
        } else if (contentsLength < 100) {
            summaryMax = 50;
        } else if (contentsLength < 300) {
            summaryMax = 100;
        } else {
            summaryMax = 200;
        }

        // gpt 호출해 요약
        String emotionCondition = "나는 오늘 이런 하루를 보냈어. " + todayContents + "이 문장을 지루해요, 놀랐어요, 화나요, 즐거워요, 슬퍼요, 괜찮아요, 아파요, 좋지 않아요, 신나요 중 하나로만 표현해줘. 꼭 이 중 한 단어로 보내줘. 더 말 하지마.";
        String summaryCondition = "나는 오늘 이런 하루를 보냈어. " + todayContents + "이 내용을 " + summaryMax + "자 내외로 요약해줘. 나한테 친절하고 다정한 말투로 알려줘. " + name + "님은 으로 시작해서 ~인 하루를 보냈어요. 로 끝나도록 답변해줘.";
        ChatGptRes emotionRes = openAIService.chat(new ChatGptReq(emotionCondition));
        ChatGptRes summaryRes = openAIService.chat(new ChatGptReq(summaryCondition));

        // diaryDB 저장
        recordRepository.updateTodayDiary(diaryId, emotionRes.getAnswer(), summaryRes.getAnswer());

        // 감정, 요약, 날짜 반환
        return new SummaryRes(formattedDate, emotionRes.getAnswer(), summaryRes.getAnswer());
    }

    // 하루 레코드, 답변 목록 조회
    public List<RecordListRes> findRecordList(String name) {
        Long userId = recordRepository.findUsername(name);
        Long diaryId = recordRepository.findDiaryId(userId);

        return recordRepository.findUserRecordList(diaryId);

    }
}
