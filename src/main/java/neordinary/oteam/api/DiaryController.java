package neordinary.oteam.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import neordinary.oteam.domain.diary.Diary;
import neordinary.oteam.dto.diary.DiaryListResponseDto;
import neordinary.oteam.dto.diary.DiaryResponseDto;
import neordinary.oteam.global.util.DateTimeUtils;
import neordinary.oteam.service.DiaryService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;


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

}
