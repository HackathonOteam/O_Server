package neordinary.oteam.dto.diary;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import neordinary.oteam.domain.diary.Diary;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiaryListResponseDto {

    @ApiModelProperty(value = "1년 전 오늘")
    private DiaryResponseDto yearAgoDiary;

    @ApiModelProperty(value = "좋았던 기억")
    private DiaryResponseDto positiveDiary;

    @ApiModelProperty(value = "기념일 기억")
    private DiaryResponseDto anniversaryDiary;

    public static DiaryListResponseDto from(DiaryResponseDto yearAgoDiary, DiaryResponseDto positiveDiary, DiaryResponseDto anniversaryDiary) {
        return new DiaryListResponseDtoBuilder()
                .yearAgoDiary(yearAgoDiary)
                .positiveDiary(positiveDiary)
                .anniversaryDiary(anniversaryDiary)
                .build();
    }

}
