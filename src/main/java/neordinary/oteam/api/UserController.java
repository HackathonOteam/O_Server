package neordinary.oteam.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import neordinary.oteam.domain.user.User;
import neordinary.oteam.dto.user.UserCreateRequestDto;
import neordinary.oteam.dto.user.UserCreateResponseDto;
import neordinary.oteam.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@Tag(name = "user", description = "유저 API")
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Tag(name = "user")
    @ApiOperation(value = "유저 생성 api")
    @PostMapping(value="")
    public ResponseEntity<UserCreateResponseDto> createUser(@RequestBody UserCreateRequestDto req){

        User user = userService.create(req.getName());
        UserCreateResponseDto res = UserCreateResponseDto.create(user.getName());
        return new ResponseEntity<>(res, HttpStatus.CREATED);
//        return new ResponseEntity<>(UserCreateResponseDto.create(req.getName()), HttpStatus.CREATED);
    }


}
