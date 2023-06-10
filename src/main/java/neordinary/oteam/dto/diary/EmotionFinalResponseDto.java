package neordinary.oteam.dto.diary;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmotionFinalResponseDto {

    @ApiModelProperty(value = "가장 퍼센트가 높은 감정 문구")
    private String emotionComment;

    @ApiModelProperty(value = "가장 퍼센트가 높은 감정")
    private EmotionPercentResponseDto firstEmotionDto;

    @ApiModelProperty(value = "두번째로 높은 감정")
    private EmotionPercentResponseDto secondEmotionDto;

    @ApiModelProperty(value = "세번째로 높은 감정")
    private EmotionPercentResponseDto thirdEmotionDto;

    @ApiModelProperty(value = "기타 감정")
    private EmotionPercentResponseDto etcEmotionDto;

    public static EmotionFinalResponseDto from(EmotionPercentResponseDto firstEmotionDto,
                                               EmotionPercentResponseDto secondEmotionDto,
                                               EmotionPercentResponseDto thirdEmotionDto,
                                               String emotionComment) {
        return new EmotionFinalResponseDtoBuilder()
                .firstEmotionDto(firstEmotionDto)
                .secondEmotionDto(secondEmotionDto)
                .thirdEmotionDto(thirdEmotionDto)
                .etcEmotionDto(EmotionPercentResponseDto.from("etc",
                                (100 - (firstEmotionDto.getPercent() + secondEmotionDto.getPercent() + thirdEmotionDto.getPercent()))))
                .emotionComment(emotionComment)
                .build();
    }

}
