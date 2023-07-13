package neordinary.oteam.dto.diary;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import neordinary.oteam.domain.diary.Diary;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiaryResponseDto {

    @ApiModelProperty(value = "기록 일자", example = "2022.07.02")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd", timezone = "Asia/Seoul")
    private LocalDate date;

    @ApiModelProperty(value = "감정", example = "신나요")
    private String emotion;

    @ApiModelProperty(value = "감정", example = "신나요")
    private String contents;

    public static DiaryResponseDto from(Diary diary) {

        if(diary == null){
            return new DiaryResponseDtoBuilder()
                    .date(null)
                    .emotion("")
                    .contents("해당되는 기록이 없습니다.")
                    .build();
        } else{
            return new DiaryResponseDtoBuilder()
                    .date(LocalDate.from(diary.getDiaryDate()))
                    .emotion(diary.getEmotion())
                    .contents(diary.getSummary())
                    .build();
        }
    }

    public static DiaryResponseDto from(List<Diary> diaries) {

        if(diaries.size() == 0){
            return new DiaryResponseDtoBuilder()
                    .date(null)
                    .emotion("")
                    .contents("해당되는 기록이 없습니다.")
                    .build();
        }

        else {
            Random random = new Random();
            int idx = random.nextInt(diaries.size());

            return new DiaryResponseDtoBuilder()
                    .date(LocalDate.from(diaries.get(idx).getDiaryDate()))
                    .emotion(diaries.get(idx).getEmotion())
                    .contents(diaries.get(idx).getSummary())
                    .build();
        }

    }

}
