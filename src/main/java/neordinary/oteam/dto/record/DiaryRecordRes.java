package neordinary.oteam.dto.record;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "다이어리 레코드 생성 응답 객체")
public class DiaryRecordRes {
    @ApiModelProperty(value = "성공 여부")
    Integer isSuccess;
}
