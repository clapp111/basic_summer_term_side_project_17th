package server.board.domain.home.controller.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import server.board.domain.home.dto.LoginRequestDto;
import server.board.domain.user.dto.UserCreateRequestDto;
import server.board.global.exception.error.ErrorResponse;
import server.board.global.jwt.JwtToken;

@Tag(name = "HomeController", description = "Home management Controller")
public interface HomeControllerSpecification {

    @Operation(summary = "sign in", description = "로그인 시 사용되는 api")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "✅ 로그인 성공"),
            @ApiResponse(responseCode = "400", description = "❌ 로그인 실패",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = {
                                    @ExampleObject(
                                            name = "유효성 검사 실패",
                                            value = "{\"error\" : \"400\", \"message\" : \"유효성 검사에 실패하였습니다\"}"
                                    ),
                                    @ExampleObject(
                                            name = "일치하지 않는 패스워드",
                                            value = "{\"error\" : \"400\", \"message\" : \"일치하지 않는 패스워드입니다\"}"
                                    )
                            }, schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "❌ 로그인 실패",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = {
                                    @ExampleObject(
                                            name = "유저 조회 실패",
                                            value = "{\"error\" : \"404\", \"message\" : \"유저 조회에 실패하였습니다\"}"
                                    )
                            }, schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/sign-in")
    public ResponseEntity<JwtToken> signIn(@Valid @RequestBody LoginRequestDto loginRequestDto);

    @Operation(summary = "sign up", description = "회원가입 시 사용되는 api")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "✅ 회원가입 성공"),
            @ApiResponse(responseCode = "400", description = "❌ 회원가입 실패",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = {
                                    @ExampleObject(
                                            name = "유효성 검사 실패",
                                            value = "{\"error\" : \"400\", \"message\" : \"유효성 검사에 실패하였습니다\"}"
                                    )
                            }, schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "❌ 회원가입 실패",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = {
                                    @ExampleObject(
                                            name = "중복된 유저",
                                            value = "{\"error\" : \"409\", \"message\" : \"중복된 유저가 존재합니다\"}"
                                    )
                            }, schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/sign-up")
    public ResponseEntity<?>  signUp(@Valid @RequestBody UserCreateRequestDto userCreateRequestDto);

}
