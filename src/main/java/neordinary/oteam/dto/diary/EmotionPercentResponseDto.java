package neordinary.oteam.dto.diary;

import io.swagger.annotations.ApiModel;
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
@ApiModel(value = "감정 별 퍼센트 반환 객체")
public class EmotionPercentResponseDto {

    @ApiModelProperty(value = "감정", example = "신나요")
    private String emotion;

    @ApiModelProperty(value = "감정", example = "신나요")
    private Double percent;

    public static EmotionPercentResponseDto from(String emotion, Double percent) {
        return new EmotionPercentResponseDtoBuilder()
                .emotion(emotion)
                .percent(percent)
                .build();
    }

}
