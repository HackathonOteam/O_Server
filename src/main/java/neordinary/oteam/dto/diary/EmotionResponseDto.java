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

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmotionResponseDto {

    @ApiModelProperty(value = "날짜", example = "2022-07-02")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate date;

    @ApiModelProperty(value = "감정", example = "신나요")
    private String emotion;

    public static EmotionResponseDto from(Diary diary) {
        return new EmotionResponseDtoBuilder()
                .date(diary.getDiaryDate())
                .emotion(diary.getEmotion())
                .build();
    }


}
