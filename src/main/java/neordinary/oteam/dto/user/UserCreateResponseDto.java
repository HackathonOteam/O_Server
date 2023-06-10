package neordinary.oteam.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@ApiModel(value = "유저 생성 응답 객체")
public class UserCreateResponseDto {

    @ApiModelProperty(value = "유저 이름")
    @NotNull
    private String name;

    public static UserCreateResponseDto create(String name) {
        return UserCreateResponseDto.builder()
                .name(name)
                .build();
    }
}
