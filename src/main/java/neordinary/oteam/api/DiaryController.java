package neordinary.oteam.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import neordinary.oteam.domain.diary.Diary;
import neordinary.oteam.dto.diary.DiaryListResponseDto;
import neordinary.oteam.dto.diary.DiaryResponseDto;
import neordinary.oteam.dto.diary.EmotionCountResponseDto;
import neordinary.oteam.dto.diary.EmotionResponseDto;
import neordinary.oteam.global.util.DateTimeUtils;
import neordinary.oteam.service.DiaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;


@RestController
@Tag(name = "diary", description = "기억 API")
@RequiredArgsConstructor
@RequestMapping("/api/diary")
public class DiaryController {

    private final DiaryService diaryService;

    @Tag(name = "diary")
    @ApiOperation(value = "기억해야 할 것 리스트 조회 api", notes = "2023-06-11 형태로 주시면 됩니다")
    @GetMapping("")
    public ResponseEntity<DiaryListResponseDto> getHomeDiaryList(@RequestParam("memberName") String memberName,
                                                                @RequestParam("today") String date) {

        final LocalDate today = DateTimeUtils.stringToLocalDate(date);

        Diary yearAgoDiary = diaryService.getYearAgo(memberName, today);
        List<Diary> positiveDiaryList = diaryService.getPositiveDiary(memberName, today);
        List<Diary> anniversaryDiaryList = diaryService.getAnniversaryDiary(memberName, today);

        Random random = new Random();
        int poistiveIdx = random.nextInt(positiveDiaryList.size());
        int anniversaryIdx = random.nextInt(anniversaryDiaryList.size());

        return ResponseEntity.ok(DiaryListResponseDto.from(
                DiaryResponseDto.from(yearAgoDiary),
                DiaryResponseDto.from(positiveDiaryList.get(poistiveIdx)),
                DiaryResponseDto.from(anniversaryDiaryList.get(anniversaryIdx))));
    }


    @Tag(name = "diary")
    @ApiOperation(value = "이번달 캘린더 감정 조회 api", notes = "yearMonth는 yyyy-MM로 보내주시면 됩니다.")
    @GetMapping("/monthly")
    public List<EmotionResponseDto> getMonthlyEmotion(@RequestParam("yearMonth") String yearMonth,
                                                      @RequestParam("userName") String userName) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
        YearMonth date = YearMonth.parse(yearMonth, dateTimeFormatter);

        List<Diary> diaries = diaryService.getMonthlyDiary(userName, date);
        return diaries.stream().map(EmotionResponseDto::from).collect(Collectors.toList());
    }

    @Tag(name = "diary")
    @ApiOperation(value = "이번달 감정 분석 카드 조회 api", notes = "yearMonth는 yyyy-MM로 보내주시면 됩니다.")
    @GetMapping("/monthly/statistic")
    public List<EmotionResponseDto> getMonthlyStatistic(@RequestParam("yearMonth") String yearMonth,
                                                      @RequestParam("userName") String userName) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
        YearMonth date = YearMonth.parse(yearMonth, dateTimeFormatter);

        List<Diary> diaries = diaryService.getMonthlyDiary(userName, date);

        final Map<String, Integer> emotionCount = new HashMap<String, Integer>() {{
            put("지루해요", 0);
            put("놀랐어요", 0);
            put("화나요", 0);
            put("즐거워요", 0);
            put("슬퍼요", 0);
            put("괜찮아요", 0);
            put("아파요", 0);
        }};

        diaries.forEach(diary -> {
            emotionCount.put(diary.getEmotion(), emotionCount.get(diary.getEmotion()) + 1);
        });

//        return EmotionCountResponseDto.from(
//                feedbackVOS.size(),
//                emojiCount.get(1),
//                emojiCount.get(2),
//                emojiCount.get(3),
//                emojiCount.get(4),
//                emojiCount.get(5),
//                emojiCount.get(6),
//                emojiCount.get(7)
//        );


        return diaries.stream().map(EmotionResponseDto::from).collect(Collectors.toList());
    }

}
