package neordinary.oteam.dto.record;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "하루 레코드 요약 생성 요청 객체")
public class SummaryReq {
    @ApiModelProperty(value = "보내는 값")
    String name;
}
