package neordinary.oteam.dto.diary;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "감정 별 개수 반환 객체")
public class EmotionCountResponseDto {

    @ApiModelProperty(value = "개수")
    private Integer emoji_1;

    @ApiModelProperty(value = "이모지 2 개수")
    private Integer emoji_2;

    @ApiModelProperty(value = "이모지 3 개수")
    private Integer emoji_3;

    @ApiModelProperty(value = "이모지 4 개수")
    private Integer emoji_4;

    @ApiModelProperty(value = "이모지 5 개수")
    private Integer emoji_5;

    @ApiModelProperty(value = "이모지 6 개수")
    private Integer emoji_6;

//    public static FeedbackCountResponseDto from(Integer feedback, Integer emoji_1, Integer emoji_2, Integer emoji_3,
//                                                Integer emoji_4, Integer emoji_5, Integer emoji_6){
//
//        return new FeedbackCountResponseDtoBuilder()
//                .comment(feedback - (emoji_1 + emoji_2 + emoji_3 + emoji_4 + emoji_5 + emoji_6))
//                .emoji_1(emoji_1)
//                .emoji_2(emoji_2)
//                .emoji_3(emoji_3)
//                .emoji_4(emoji_4)
//                .emoji_5(emoji_5)
//                .emoji_6(emoji_6)
//                .build();
//    }


}
