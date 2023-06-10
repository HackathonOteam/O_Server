package neordinary.oteam.dto.record;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "다이어리 레코드 생성 요청 객체")
public class DiaryRecordReq {
    @ApiModelProperty(value = "유저 이름")
    String name;
}
