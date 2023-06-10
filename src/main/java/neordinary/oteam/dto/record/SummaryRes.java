package neordinary.oteam.dto.record;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "하루 레코드 요약 생성 응답 객체")
public class SummaryRes {
    @ApiModelProperty(value = "보내는 값")
    String date; // ex. 2023.06.11
    @ApiModelProperty(value = "보내는 값")
    String emotion; // 감정
    @ApiModelProperty(value = "보내는 값")
    String summary; // 요약

}
