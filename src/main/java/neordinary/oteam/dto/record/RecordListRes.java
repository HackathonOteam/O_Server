package neordinary.oteam.dto.record;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "하루 레코드, 답변 목록 조회 응답 객체")
public class RecordListRes {

    @ApiModelProperty(value = "보내는 값")
    private String contents;
    @ApiModelProperty(value = "gpt 응답 값")
    private String answer;
}
