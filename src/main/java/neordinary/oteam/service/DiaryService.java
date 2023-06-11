package neordinary.oteam.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import neordinary.oteam.domain.diary.Diary;
import neordinary.oteam.domain.diary.DiaryRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
public class DiaryService {

    private final DiaryRepository diaryRepository;

    // 1년 전 오늘
    public Diary getYearAgo(String memberName, LocalDate date) {
        log.info("1 year ago :::::: " + date.minusYears(1));

        return diaryRepository.findYearAgo(memberName, date.minusYears(1));
    }

    // 좋았던 기억을 떠올려보세요
    public List<Diary> getPositiveDiary(String memberName, LocalDate date) {

        List<Diary> diaries = diaryRepository.findByUserName(memberName);

        return diaries.stream()
                .filter(diary -> diary.getEmotion().equals("ExcitedEmotion") || diary.getEmotion().equals("HappyEmotion"))
                .collect(Collectors.toList());
    }

    // 기념일에 대한 기록이 있네요
    public List<Diary> getAnniversaryDiary(String memberName, LocalDate date) {

        List<Diary> diaries = diaryRepository.findByUserName(memberName);

        return diaries.stream()
                .filter(this::isAnniversary)
                .collect(Collectors.toList());
    }

    public List<Diary> getMonthlyDiary(String userName, YearMonth date) {

        return diaryRepository.findMonthlyDiary(userName, date);
    }

    private boolean isAnniversary(Diary diary) {
        return diary.getRecords().stream()
                .anyMatch(record -> record.getContents().contains("기념일") || record.getContents().contains("생일"));
    }

}
