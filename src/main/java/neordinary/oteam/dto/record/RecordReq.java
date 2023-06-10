package neordinary.oteam.dto.record;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import neordinary.oteam.dto.chatGpt.ChatGptReq;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "레코드 하나 생성 요청 객체")
public class RecordReq {
    @ApiModelProperty(value = "유저 이름")
    String name;
    @ApiModelProperty(value = "보내는 값")
    String content;
}
